package br.edu.ufam.icomp.locadora_veiculos.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;




public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("TWIN PINES Rent-a-car");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centralizar na tela

        // Layout da tela principal
        getContentPane().setLayout(new BorderLayout());

        // Painel para conter o texto
        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setBackground(new Color(Integer.parseInt("010711", 16))); // Cor de fundo azul escuro
        getContentPane().add(painelEsquerdo, BorderLayout.CENTER);

        String caminhoImagem = "/home/kaike/eclipse-workspace/locadora_veiculos/src/br/edu/ufam/icomp/locadora_veiculos/view/fundo.png"; // Ajuste o caminho conforme necessário
        ImageIcon icon = new ImageIcon(caminhoImagem); // Carregar a imagem
        JLabel logoLabel = new JLabel(icon); // Criar um JLabel para exibir a imagem
        painelEsquerdo.add(logoLabel, BorderLayout.CENTER);// Adicionar a JLabel ao painel esquerdo

        // Painel para os botões no lado direito
        JPanel botoesPanel = new JPanel(new GridLayout(3, 1));
        getContentPane().add(botoesPanel, BorderLayout.EAST);

        // Botões
        JButton botao1 = new JButton("Gerenciar Veiculos");
        JButton botao2 = new JButton("Alugar Veiculos");
        JButton botao3 = new JButton("Gerenciar Clientes");

		estilizarBotao(botao1);
        estilizarBotao(botao2);
        estilizarBotao(botao3);

        // Adicionando os botões ao painel
        botoesPanel.add(botao1);
        botoesPanel.add(botao2);
        botoesPanel.add(botao3);

        // Ação do botão 1
        botao1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    abrirTelaGerenciamentoVeiculos();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Ação do botão 2
        botao2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    abrirAluguelClientes();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Ação do botão 3
        botao3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    abrirGerenciarClientes();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    // Método para abrir a tela 1
    private void abrirTelaGerenciamentoVeiculos() throws SQLException {
        GerenciamentoVeiculosFrame frame = new GerenciamentoVeiculosFrame();
        frame.setVisible(true);
    }

    // Método para abrir a tela 2
    private void abrirAluguelClientes() throws SQLException {
        InserirClienteDialog frame = new InserirClienteDialog(null);
        frame.setVisible(true);
    }

    // Método para abrir a tela 3
    private void abrirGerenciarClientes() throws SQLException {
        GerenciarClienteFrame frame = new GerenciarClienteFrame();
        frame.setVisible(true);
    }

	private void estilizarBotao(JButton botao) {
        botao.setFont(new Font("Cascadia Code", Font.BOLD, 14));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setBackground(new Color(Integer.parseInt("010711", 16)));
        botao.setForeground(Color.white);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(Integer.parseInt("58a6b3", 16)), 2),
                        BorderFactory.createEmptyBorder(8, 22, 8, 22)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botao.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
            }
        });
    }
	

     public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);   
            }
        });
    }
}


