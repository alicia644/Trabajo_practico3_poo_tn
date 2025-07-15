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
import ar.org.centro8.java.models.entidades.Producto;
import ar.org.centro8.java.models.enums.Talle;
import ar.org.centro8.java.models.repositories.interfaces.I_ProductoDAO;

@Repository
public class ProductoDAO implements I_ProductoDAO{
    private final DataSource dataSource;

    private static final String SQL_CREATE=
        "INSERT INTO productos(tipo_prenda,talle,color,precio,stock,precio_costo) values (?,?,?,?,?,?)";
    private static final String SQL_FIND_BY_ID=
    "SELECT * FROM productos WHERE id_producto=?";
    private static final String SQL_FIND_ALL = 
        "SELECT * FROM productos";
    private static final String SQL_UPDATE =
        "UPDATE productos SET tipo_prenda=?, talle=?, color=?, precio=?, stock=?, precio_costo=? WHERE id_producto=?";
    private static final String SQL_DELETE = 
        "DELETE FROM productos WHERE tipo_prenda=?";
        private static final String SQL_FIND_BY_PRENDA = 
            "SELECT * FROM productos WHERE tipo_prenda= ?";
    public ProductoDAO(DataSource dataSource){
        this.dataSource=dataSource;
    }

    @Override
    public void create(Producto producto) throws SQLException {
       try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_CREATE,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,producto.getTipoPrenda());
            ps.setString(2,producto.getTalle().name());
            ps.setString(3,producto.getColor());
            ps.setDouble(4, producto.getPrecio());
            ps.setInt(5, producto.getStock());
            ps.setDouble(6, producto.getPrecioCosto());
            ps.executeUpdate();
            try(ResultSet keys= ps.getGeneratedKeys()){
                if(keys.next()){
                    producto.setIdProducto(keys.getInt(1));
                }
            }
        }
    }

    @Override
    public Producto findById(int id) throws SQLException {
       try (Connection conn=dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)){
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()){
                return mapRow(rs);
            }
        }
     }
    return null;
}

    @Override
    public List<Producto> findAll() throws SQLException {
       List<Producto> lista = new ArrayList<>();
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
    public int update(Producto producto) throws SQLException {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)){
        ps.setString(1,producto.getTipoPrenda());
        ps.setString(2, producto.getTalle().name());
        ps.setString(3, producto.getColor());
        ps.setDouble(4, producto.getPrecio());
        ps.setInt(5, producto.getStock());
        ps.setDouble(6, producto.getPrecioCosto());
        ps.setInt(7, producto.getIdProducto());
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
    public List<Producto> findByTipoDePrenda(String tipoDePrenda) throws SQLException {
        List<Producto> lista = new ArrayList<>();
         try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_PRENDA)) {
            ps.setString(1, tipoDePrenda);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                    lista.add(mapRow(rs));
                }
            } 
        } 
        return lista;
    }
private Producto mapRow(ResultSet rs) throws SQLException{
    Producto producto = new Producto();
    producto.setIdProducto(rs.getInt("id_producto"));
    producto.setTipoPrenda(rs.getString("tipo_prenda"));
    producto.setTalle(Talle.valueOf((rs.getString("talle"))));
    producto.setColor(rs.getString("color"));
    producto.setPrecio(rs.getDouble("precio"));
    producto.setStock(rs.getInt("stock"));
    producto.setPrecioCosto(rs.getDouble("precio_costo"));
    return producto ;
    }
}

