package controller;

import java.sql.*;

import controller.dao.ClienteDAOJDBC;
import controller.dao.VeiculoDAOJDBC;

public class DAOFactory {
    
    public static Connection conexao = null;

    public DAOFactory() throws SQLException{
        if (conexao == null) {
            createConnection();
        }
    }
    public static Connection createConnection() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/locadoraBD";
        String user = "kaike";
        String password = "kaike";

        
        try{
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        }
        catch (SQLException e) {return (Connection) e;}
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
