package controller.dao;

import java.sql.*;
import java.util.ArrayList;

import controller.exceptions.CarroAlugadoException;
import controller.exceptions.CarroAluguelInvalido;
import model.entidades.Cliente;


public interface ClienteDAO {
    public void insert(Cliente cliente) throws SQLException, CarroAlugadoException, CarroAluguelInvalido;
    public void update(Cliente cliente,int cliente_id) throws SQLException;
    public void deleteById(int id) throws SQLException;
    public Cliente findById(int id) throws SQLException;
    public ArrayList<Cliente> findAll() throws SQLException; 
}
