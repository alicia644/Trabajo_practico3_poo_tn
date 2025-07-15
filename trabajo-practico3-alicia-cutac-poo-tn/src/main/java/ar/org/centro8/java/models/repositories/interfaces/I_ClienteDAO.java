package ar.org.centro8.java.models.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import ar.org.centro8.java.models.entidades.Cliente;

public interface I_ClienteDAO {
    void create(Cliente cliente) throws SQLException;
    Cliente findById(int id) throws SQLException;
    List<Cliente> findAll() throws SQLException;
    int update(Cliente cliente) throws SQLException;
    int delete(int id) throws Exception;
}
