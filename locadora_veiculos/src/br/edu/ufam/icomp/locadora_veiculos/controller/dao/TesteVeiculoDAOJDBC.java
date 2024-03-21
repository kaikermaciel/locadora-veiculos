package br.edu.ufam.icomp.locadora_veiculos.controller.dao;

import java.sql.SQLException;

import br.edu.ufam.icomp.locadora_veiculos.model.entidades.Veiculo;

public class TesteVeiculoDAOJDBC {
    public static void main(String[] args) throws SQLException {
        // Criar instância do DAO
        VeiculoDAOJDBC veiculoDAO = new VeiculoDAOJDBC();

        // Criar alguns veículos
        Veiculo veiculo1 = new Veiculo("Categoria1", 5, 500, "Automatico", true, 12.5f, true, true, true, 150.0f);
        Veiculo veiculo2 = new Veiculo("Categoria2", 7, 700, "Manual", false, 10.5f, false, true, false, 120.0f);

        try {
            // Adicionar os veículos ao banco de dados
            veiculoDAO.insert(veiculo1);
            veiculoDAO.insert(veiculo2);
            //veiculoDAO.insert(veiculo1);

            // Listar todos os veículos
            System.out.println("Veículos no banco de dados:");
            for (Veiculo veiculo : veiculoDAO.findAll()) {
                System.out.println(veiculo);
            }

            // Atualizar o primeiro veículo
            //veiculo1.setCategoria("NovaCategoria");
            //veiculoDAO.update(veiculo1);

            // Deletar o segundo veículo
            //veiculoDAO.deleteById(1);
            // System.out.println("\nVeiculo com id=1");
            // Veiculo veiculoR = veiculoDAO.findById(1);
            // System.err.println(veiculoR);

            // Listar os veículos novamente
            System.out.println("\nVeículos no banco de dados após atualização e exclusão:");
            for (Veiculo veiculo : veiculoDAO.findAll()) {
                System.out.println(veiculo);
            }

        } catch (SQLException e) {
            System.err.println("deu erro negao");
            e.printStackTrace();
        }
    }
}