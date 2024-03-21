package br.edu.ufam.icomp.locadora_veiculos.controller;

import java.sql.*;

import br.edu.ufam.icomp.locadora_veiculos.controller.dao.ClienteDAOJDBC;
import br.edu.ufam.icomp.locadora_veiculos.controller.dao.VeiculoDAOJDBC;

public class DAOFactory {
    
    public static Connection conexao = null;

    public DAOFactory() throws SQLException{
        if (conexao == null) {
            createConnection();
        }
    }
    public static Connection createConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/locadoraBD?useLegacyAuth=true"; // Adicionando o parâmetro useLegacyAuth=true
        String user = "kaike";
        String password = "kaike";
    
        try {
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (SQLException e) {
            // Log do erro ou relatório de erro para identificar o problema
            e.printStackTrace();
            // Lançar a exceção para indicar que a conexão não pôde ser estabelecida
            throw new SQLException("Erro ao conectar ao banco de dados", e);
        }
    }
    
    

    public static boolean closeConnection(){
        try{
            conexao.close();
            return true;
        }
        catch (SQLException e){
            return false;
        }
    }


    public VeiculoDAOJDBC createVeiculoDao() throws SQLException{
        return new VeiculoDAOJDBC();
    }

    public ClienteDAOJDBC createClienteDAOJDBC() throws SQLException{
        return new ClienteDAOJDBC();
    }
}
