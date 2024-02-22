package controller.dao;

import java.sql.*;
import java.util.ArrayList;

import controller.*;
import controller.exceptions.*;
import model.entidades.*;


public class ClienteDAOJDBC implements ClienteDAO {

    public ClienteDAOJDBC() {
        try {
            DAOFactory.conexao = DAOFactory.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }

    public boolean veiculoAlugado(int veiculo_id) throws CarroAlugadoException, SQLException{
        PreparedStatement statement = DAOFactory.conexao.prepareStatement("SELECT * FROM clientes WHERE veiculo_id=?");
        statement.setInt(1, veiculo_id);
        
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            throw new CarroAlugadoException();
        }
        return false;
    }

    public boolean verificaAluguel(Date retirada, Date devolucao) throws CarroAluguelInvalido, SQLException{
        PreparedStatement statement = DAOFactory.conexao.prepareStatement("SELECT DATEDIFF(day, ?, ?) AS diferenca_dias");
        statement.setDate(1, retirada);
        statement.setDate(2, devolucao);
        
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        int diferencaDias = resultSet.getInt("diferenca_dias");

        if (diferencaDias <= 0){
            throw new CarroAluguelInvalido();
        }
        return true;
    }

    @Override
    public void insert(Cliente cliente) throws SQLException, CarroAlugadoException, CarroAluguelInvalido {
        try{
            veiculoAlugado(cliente.getVeiculoId());
            verificaAluguel(cliente.getDataRetirada(), cliente.getDataDevolucao());
            PreparedStatement ps = DAOFactory.conexao.prepareStatement("INSERT INTO clientes VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setDate(1, cliente.getDataRetirada());
            ps.setString(2, cliente.getLocalRetirada());
            ps.setDate(3, cliente.getDataDevolucao());
            ps.setString(4, cliente.getLocalDevolucao());
            ps.setInt(5, cliente.getIdadeCondutor());
            ps.setInt(6, cliente.getVeiculoId());
            ps.setBoolean(7, cliente.getGPS());
            ps.setBoolean(8, cliente.getAssentoCrianca());
            ps.setBoolean(9, cliente.getSeguroCompleto());
            
            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (CarroAlugadoException e){
            e.printStackTrace();
        } catch (CarroAluguelInvalido e){
            e.printStackTrace();
        }
        
    }

    @Override
    public void update(Cliente cliente, int cliente_id) throws SQLException {
        try{
            PreparedStatement ps = DAOFactory.conexao.prepareStatement("UPDATE clientes SET data_retirada=?, local_retirada=?, data_devolucao=?, local_devolucao=?, idade_condutor=?, veiculo_id=?, gps=?, assento_crianca=?, seguro_completo=? WHERE id=?");
            ps.setDate(1, cliente.getDataRetirada());
            ps.setString(2, cliente.getLocalRetirada());
            ps.setDate(3, cliente.getDataDevolucao());
            ps.setString(4, cliente.getLocalDevolucao());
            ps.setInt(5, cliente.getIdadeCondutor());
            ps.setInt(6, cliente.getVeiculoId());
            ps.setBoolean(7, cliente.getGPS());
            ps.setBoolean(8, cliente.getAssentoCrianca());
            ps.setBoolean(9, cliente.getSeguroCompleto());
            ps.setInt(8, cliente_id);

            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        try {
            PreparedStatement ps = DAOFactory.conexao.prepareStatement("DELETE FROM veiculos WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente findById(int id) throws SQLException {
        Cliente cliente = null;
        try {
            PreparedStatement ps = DAOFactory.conexao.prepareStatement("SELECT * FROM clientes WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setDataRetirada(rs.getDate("data_retirada"));
                cliente.setLocalRetirada(rs.getString("local_retirada"));
                cliente.setDataDevolucao(rs.getDate("data_local_devolucao"));
                cliente.setLocalDevolucao(rs.getString("local_devolucao"));
                cliente.setIdadeCondutor(rs.getInt("idade_condutor"));
                cliente.setVeiculoId(rs.getInt("veiculo_id"));
                cliente.setGPS(rs.getBoolean("gps"));
                cliente.setAssentoCrianca(rs.getBoolean("assento_crianca"));
                cliente.setSeguroCompleto(rs.getBoolean("seguro_completo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public ArrayList<Cliente> findAll() throws SQLException {
          ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        try {
            Statement st = DAOFactory.conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM clientes");

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setDataRetirada(rs.getDate("data_retirada"));
                cliente.setLocalRetirada(rs.getString("local_retirada"));
                cliente.setDataDevolucao(rs.getDate("data_devolucao"));
                cliente.setLocalRetirada(rs.getString("local_devolucao"));
                cliente.setIdadeCondutor(rs.getInt("idade_condutor"));
                cliente.setVeiculoId(rs.getInt("veiculo_id"));
                cliente.setGPS(rs.getBoolean("gps"));
                cliente.setAssentoCrianca(rs.getBoolean("assento_crianca"));
                cliente.setSeguroCompleto(rs.getBoolean("seguro_completo"));

                clientes.add(cliente);
                System.out.println("adicionado com sucesso");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return clientes;
    }
    
}
