package br.edu.ufam.icomp.locadora_veiculos.view;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AluguelClientesFrame extends JFrame {

    public AluguelClientesFrame() {
        // Configurações básicas do frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setTitle("Aluguel de Clientes");

        // Criação dos componentes
        JButton btnInserirCliente = new JButton("Inserir Cliente");

        // Adiciona um ActionListener ao botão para lidar com o evento de clique
        btnInserirCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chama a caixa de diálogo para inserir um novo cliente
                try {
                    InserirClienteDialog.mostrarDialog(AluguelClientesFrame.this);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Adiciona o botão ao frame
        add(btnInserirCliente);

        // Define o layout como FlowLayout
        setLayout(new FlowLayout());

        // Torna o frame visível
        setVisible(true);
    }

    public static void main(String[] args) {
        // Cria uma instância do AluguelClientesFrame
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AluguelClientesFrame();
            }
        });
    }
}
