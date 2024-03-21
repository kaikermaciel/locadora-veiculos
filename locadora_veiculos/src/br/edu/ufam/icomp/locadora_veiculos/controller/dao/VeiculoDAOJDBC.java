package br.edu.ufam.icomp.locadora_veiculos.controller.dao;

import java.sql.*;
import java.util.ArrayList;

import br.edu.ufam.icomp.locadora_veiculos.controller.*;
import br.edu.ufam.icomp.locadora_veiculos.model.entidades.*;



public class VeiculoDAOJDBC implements VeiculoDAO{

    public VeiculoDAOJDBC() throws SQLException {
        DAOFactory.conexao = DAOFactory.createConnection();
    }

    
    @Override
    public void insert(Veiculo veiculo) throws SQLException {
        try {
            PreparedStatement ps = DAOFactory.conexao.prepareStatement("INSERT INTO veiculos VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, veiculo.getCategoria());
            ps.setInt(2, veiculo.getmaxPassageiros());
            ps.setInt(3, veiculo.gettamBagageiro());
            ps.setString(4, veiculo.gettipoCambio());
            ps.setBoolean(5, veiculo.getarCondicionado());
            ps.setFloat(6, veiculo.getmediaConsumo());
            ps.setBoolean(7, veiculo.getairbag());
            ps.setBoolean(8, veiculo.getfreioABS());
            ps.setBoolean(9, veiculo.getdvd());
            ps.setFloat(10, veiculo.getvalorPorDia());

            ps.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update( Veiculo veiculo, int id) {
        try {
            PreparedStatement ps = DAOFactory.conexao.prepareStatement("UPDATE veiculos SET categoria=?, max_passageiros=?, tam_bagageiro=?, tipo_cambio=?, ar_condicionado=?, media_consumo=?, airbag=?, freio_abs=?, dvd=?, valor_por_dia=? WHERE id=?");
            ps.setString(1, veiculo.getCategoria());
            ps.setInt(2, veiculo.getmaxPassageiros());
            ps.setInt(3, veiculo.gettamBagageiro());
            ps.setString(4, veiculo.gettipoCambio());
            ps.setBoolean(5, veiculo.getarCondicionado());
            ps.setFloat(6, veiculo.getmediaConsumo());
            ps.setBoolean(7, veiculo.getairbag());
            ps.setBoolean(8, veiculo.getfreioABS());
            ps.setBoolean(9, veiculo.getdvd());
            ps.setFloat(10, veiculo.getvalorPorDia());
            ps.setInt(11, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            PreparedStatement ps = DAOFactory.conexao.prepareStatement("DELETE FROM veiculos WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public Veiculo findById(int id) {
        Veiculo veiculo = null;

        try {
            PreparedStatement ps = DAOFactory.conexao.prepareStatement("SELECT * FROM veiculos WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                veiculo = new Veiculo();
                veiculo.setCategoria(rs.getString("categoria"));
                veiculo.setmaxPassageiros(rs.getInt("max_passageiros"));
                veiculo.settamBagageiro(rs.getInt("tam_bagageiro"));
                veiculo.settipoCambio(rs.getString("tipo_cambio"));
                veiculo.setarCondicionado(rs.getBoolean("ar_condicionado"));
                veiculo.setmediaConsumo(rs.getFloat("media_consumo"));
                veiculo.setairbag(rs.getBoolean("airbag"));
                veiculo.setfreioABS(rs.getBoolean("freio_abs"));
                veiculo.setdvd(rs.getBoolean("dvd"));
                veiculo.setvalorPorDia(rs.getFloat("valor_por_dia"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return veiculo;
    }

    @Override
    public ArrayList<Veiculo> findAll() {
        ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();

        try {
            Statement st = DAOFactory.conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM veiculos");

            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setCategoria(rs.getString("categoria"));
                veiculo.setmaxPassageiros(rs.getInt("max_passageiros"));
                veiculo.settamBagageiro(rs.getInt("tam_bagageiro"));
                veiculo.settipoCambio(rs.getString("tipo_cambio"));
                veiculo.setarCondicionado(rs.getBoolean("ar_condicionado"));
                veiculo.setmediaConsumo(rs.getFloat("media_consumo"));
                veiculo.setairbag(rs.getBoolean("airbag"));
                veiculo.setfreioABS(rs.getBoolean("freio_abs"));
                veiculo.setdvd(rs.getBoolean("dvd"));
                veiculo.setvalorPorDia(rs.getFloat("valor_por_dia"));

                veiculos.add(veiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return veiculos;
    }

    public ArrayList<Integer> obterIdsDisponiveis() throws SQLException {
        ArrayList<Integer> ids = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = DAOFactory.conexao.prepareStatement("SELECT id FROM veiculos");
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                ids.add(id);
            }
        } finally {
            // Certifique-se de fechar os recursos (PreparedStatement e ResultSet) para evitar vazamentos de recursos
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return ids;
    }
    
    
}

