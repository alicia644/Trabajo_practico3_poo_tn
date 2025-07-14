package ar.org.centro8.java.models.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import ar.org.centro8.java.models.entidades.Venta;
import ar.org.centro8.java.models.enums.EstadoVenta;

public interface I_VentaDAO {
    void create(Venta venta) throws SQLException;
    Venta findById(int id) throws SQLException;
    List<Venta> findAll() throws SQLException;
    int update(Venta venta) throws SQLException;
    int delete(int id) throws SQLException;
    List<Venta> findByEstado(EstadoVenta estadoVenta) throws SQLException;
}
