package controller.dao;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entidades.Veiculo;

public interface VeiculoDAO {
    public void insert(Veiculo veiculo) throws SQLException;
    public void update(Veiculo veiculo,int id) throws SQLException;
    public void deleteById(int id) throws SQLException;
    public Veiculo findById(int id) throws SQLException;
    public ArrayList<Veiculo> findAll() throws SQLException; 
}
