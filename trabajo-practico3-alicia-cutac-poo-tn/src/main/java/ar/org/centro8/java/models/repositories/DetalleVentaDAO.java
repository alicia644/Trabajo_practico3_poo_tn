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

import ar.org.centro8.java.models.entidades.DetalleVenta;
import ar.org.centro8.java.models.repositories.interfaces.I_DetalleVentaDAO;

@Repository
public class DetalleVentaDAO implements I_DetalleVentaDAO {
     private final DataSource dataSource;

    private static final String SQL_CREATE=
        "INSERT INTO detalle_ventas(id_producto,cantidad,subtotal) values (?,?,?)";
    private static final String SQL_FIND_BY_ID=
    "SELECT * FROM detalle_ventas WHERE id_venta=?";
    private static final String SQL_FIND_ALL = 
        "SELECT * FROM detalle_ventas";
    private static final String SQL_UPDATE =
        "UPDATE DetalleVenta SET id_producto=?, cantidad=?, subtotal=? WHERE id_venta=?";
    private static final String SQL_DELETE = 
        "DELETE FROM detalle_ventas WHERE id_venta=?";
    public DetalleVentaDAO(DataSource dataSource){
        this.dataSource=dataSource;

    }
    @Override
    public void create(DetalleVenta detalleVenta) throws SQLException {
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_CREATE,Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1,detalleVenta.getIdProducto());
            ps.setInt(2, detalleVenta.getCantidad());
            ps.setDouble(3, detalleVenta.getSubtotal());
            ps.executeUpdate();
            try(ResultSet Keys= ps.getGeneratedKeys()){
                if(Keys.next()){
                    detalleVenta.setIdVenta(Keys.getInt(1));
                }
            }

        }
    }

    @Override
    public DetalleVenta findById(int id) throws SQLException {
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
    public List<DetalleVenta> findAll() throws SQLException {
      List<DetalleVenta> lista = new ArrayList<>();
    try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
        ResultSet rs = ps.executeQuery()){
        while ( (rs.next())) {
            lista.add(mapRow(rs));
        }
    }
    return lista;
}

    @Override
    public int update(DetalleVenta detalleVenta) throws SQLException {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)){
        ps.setInt(1, detalleVenta.getIdProducto());
        ps.setInt(2, detalleVenta.getCantidad());
        ps.setDouble(3, detalleVenta.getSubtotal());
       
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
private DetalleVenta mapRow(ResultSet rs) throws SQLException{
    DetalleVenta detalleVenta = new DetalleVenta();
    detalleVenta.setIdVenta(rs.getInt("id_venta"));
    detalleVenta.setIdProducto(rs.getInt("id_producto"));
    detalleVenta.setCantidad(rs.getInt("cantidad"));
    detalleVenta.setSubtotal(rs.getDouble("subtotal"));
    return detalleVenta;
    }
}
