package ar.org.centro8.java.models.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import ar.org.centro8.java.models.entidades.Producto;

public interface I_ProductoDAO {
    void create(Producto producto) throws SQLException;
    Producto findById(int id) throws SQLException;
    List<Producto> findAll() throws SQLException;
    int update(Producto producto) throws SQLException;
    int delete(int id)throws SQLException;
    List<Producto> findByTipoDePrenda(String tipoDePrenda) throws SQLException;
}
