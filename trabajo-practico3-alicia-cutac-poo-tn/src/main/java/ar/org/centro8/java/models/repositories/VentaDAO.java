package ar.org.centro8.java.models.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import ar.org.centro8.java.models.entidades.Venta;
import ar.org.centro8.java.models.enums.EstadoVenta;
import ar.org.centro8.java.models.repositories.interfaces.I_VentaDAO;

@Repository
public class VentaDAO implements I_VentaDAO {
    private final DataSource dataSource;

    private static final String SQL_CREATE=
        "INSERT INTO ventas(id_cliente,fecha_venta,total,estado_venta) values (?,?,?,?)";
    private static final String SQL_FIND_BY_ID=
    "SELECT * FROM ventas WHERE id_venta=?";
    private static final String SQL_FIND_ALL = 
        "SELECT * FROM ventas";
    private static final String SQL_UPDATE =
        "UPDATE ventas SET id_cliente=?,fecha_venta=?,total=?,estado_venta =? WHERE id_venta=?";
    private static final String SQL_DELETE = 
        "DELETE FROM ventas WHERE id_venta=?";
        private static final String SQL_FIND_BY_ESTADO = 
        "SELECT * FROM ventas WHERE estado_venta=?";
        
        public VentaDAO(DataSource dataSource){
            this.dataSource=dataSource;
        }
    @Override
    public void create(Venta venta) throws SQLException {
         try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_CREATE,Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1,venta.getIdCliente());
            ps.setObject(2, venta.getFechaVenta());
            ps.setDouble(3,venta.getTotal());
            ps.setString(4, venta.getEstadoVenta().name());
            ps.executeUpdate();
            try(ResultSet Keys= ps.getGeneratedKeys()){
                if(Keys.next()){
                   venta.setIdVenta(Keys.getInt(1));
                }
            }

        }
    }

    @Override
    public Venta findById(int id) throws SQLException {
        try (Connection conn=dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)){
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if ((rs.next())){
                return mapRow(rs);
            }
        }
     }
    return null;
}

    @Override
    public List<Venta> findAll() throws SQLException {
        List<Venta> lista = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
        ResultSet rs = ps.executeQuery()){
        while (rs.next()) {
            lista.add(mapRow(rs));
        }
    }
    return lista;
}


    @Override
    public int update(Venta venta) throws SQLException {
       try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)){
        ps.setInt(1,venta.getIdCliente());
        ps.setObject(2,venta.getFechaVenta());
        ps.setDouble(3, venta.getTotal());
        ps.setString(4, venta.getEstadoVenta().name());
        ps.setInt(5, venta.getIdVenta());
        int filasAfectadas = ps.executeUpdate();
        return filasAfectadas;
    }
}


    @Override
    public int delete(int id) throws SQLException {
       try (Connection conn = dataSource.getConnection();
       PreparedStatement ps = conn.prepareStatement(SQL_DELETE)){
        ps.setInt(1, id);
        int filasAfectadas = ps.executeUpdate();
        return filasAfectadas;
       }
    }

    @Override
    public List<Venta> findByEstado(EstadoVenta estadoVenta) throws SQLException {
        List<Venta> lista = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ESTADO)) {
            ps.setString(1, estadoVenta.name());
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                    lista.add(mapRow(rs));
                }
            } 
        } 
        return lista;
    }
    private Venta mapRow(ResultSet rs) throws SQLException{
        Venta venta = new Venta();
        venta.setIdVenta(rs.getInt("id_venta"));
        venta.setIdCliente(rs.getInt("id_cliente"));
        venta.setFechaVenta(rs.getDate("fecha_venta").toLocalDate());
        venta.setTotal(rs.getDouble("total"));
        venta.setEstadoVenta(EstadoVenta.valueOf(rs.getString("estado_venta")));
        return venta;
    }
}
