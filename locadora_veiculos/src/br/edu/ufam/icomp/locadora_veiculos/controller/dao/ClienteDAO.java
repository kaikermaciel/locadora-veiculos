package br.edu.ufam.icomp.locadora_veiculos.controller.dao;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

import br.edu.ufam.icomp.locadora_veiculos.controller.exceptions.CarroAlugadoException;
import br.edu.ufam.icomp.locadora_veiculos.controller.exceptions.CarroAluguelInvalido;
import br.edu.ufam.icomp.locadora_veiculos.model.entidades.Cliente;


public interface ClienteDAO {
    public void insert(Cliente cliente) throws SQLException, CarroAlugadoException, CarroAluguelInvalido, ParseException;
    public void update(Cliente cliente,int cliente_id) throws SQLException, ParseException;
    public void deleteById(int id) throws SQLException;
    public Cliente findById(int id) throws SQLException, ParseException;
    public ArrayList<Cliente> findAll() throws SQLException; 
    public ArrayList<Integer> obterIdsDisponiveis() throws SQLException;

}
