package ar.org.centro8.java.models.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import ar.org.centro8.java.models.entidades.DetalleVenta;

public interface I_DetalleVentaDAO {
    void create(DetalleVenta detalleVenta) throws SQLException;
    DetalleVenta findById(int id) throws SQLException;
    List<DetalleVenta> findAll() throws SQLException;
    int update(DetalleVenta detalleVenta) throws SQLException;
    int delete(int id) throws SQLException;
}
