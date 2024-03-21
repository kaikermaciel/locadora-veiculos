package br.edu.ufam.icomp.locadora_veiculos.view;

import javax.swing.*;

import br.edu.ufam.icomp.locadora_veiculos.controller.dao.VeiculoDAO;
import br.edu.ufam.icomp.locadora_veiculos.controller.dao.VeiculoDAOJDBC;
import br.edu.ufam.icomp.locadora_veiculos.model.entidades.Veiculo;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class GerenciamentoVeiculosFrame extends JFrame {
    private VeiculoDAO veiculoDAO;

    public GerenciamentoVeiculosFrame() throws SQLException {
        veiculoDAO = new VeiculoDAOJDBC();

        setTitle("Gerenciamento de Veículos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centralizar na tela

        // Layout da tela
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(Integer.parseInt("010711", 16)));

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(new Color(Integer.parseInt("010711", 16))); // Define cor de fundo do botão
        btnVoltar.setForeground(Color.white); // Define cor do texto do botão
        btnVoltar.setFocusPainted(false); // Remove destaque ao focar
        btnVoltar.setFont(new Font("Cascadia Code", Font.BOLD, 14)); // Define fonte e tamanho
        btnVoltar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Adiciona espaçamento interno


        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela de gerenciamento de veículos
            }
        });

        // Adiciona o botão "Voltar" à parte superior da tela
        JPanel panelBotaoVoltar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotaoVoltar.setBackground(new Color(Integer.parseInt("58a6b3", 16))); // Define cor de fundo do painel
        panelBotaoVoltar.add(btnVoltar);
        getContentPane().add(panelBotaoVoltar, BorderLayout.NORTH);

        // Restante do código da tela de gerenciamento de veículos...

        // Painel para os botões
        JPanel botoesPanel = new JPanel(new GridLayout(5, 1));
        getContentPane().add(botoesPanel, BorderLayout.CENTER);

        // Botões
        JButton inserirButton = new JButton("Inserir Veículo");
        JButton atualizarButton = new JButton("Atualizar Veículo");
        JButton excluirButton = new JButton("Excluir Veículo por ID");
        JButton buscarPorIdButton = new JButton("Buscar Veículo por ID");
        JButton listarTodosButton = new JButton("Relatório dos Veículos");

        // Estilizar botões
        estilizarBotao(inserirButton);
        estilizarBotao(atualizarButton);
        estilizarBotao(excluirButton);
        estilizarBotao(buscarPorIdButton);
        estilizarBotao(listarTodosButton);

        // Adicionar botões ao painel
        botoesPanel.add(inserirButton);
        botoesPanel.add(atualizarButton);
        botoesPanel.add(excluirButton);
        botoesPanel.add(buscarPorIdButton);
        botoesPanel.add(listarTodosButton);

        // Ação do botão "Inserir Veículo"
        inserirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirInserir();
            }
        });

        // Ação do botão "Atualizar Veículo"
        atualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirAtualizarPorId();
            }
        });

        // Ação do botão "Excluir Veículo por ID"
        excluirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    abrirTelaExcluirPorId();
                } catch (SQLIntegrityConstraintViolationException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Ação do botão "Buscar Veículo por ID"
        buscarPorIdButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    abrirTelaBuscarPorId();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Ação do botão "Listar Todos os Veículos"
        listarTodosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarTodosVeiculos();
            }
        });

        UIManager.put("OptionPane.background", new Color(Integer.parseInt("58a6b3", 16)));
        UIManager.put("Panel.background", new Color(Integer.parseInt("58a6b3", 16)));
        UIManager.put("OptionPane.messageForeground", new Color(Integer.parseInt("010711", 16)));
        UIManager.put("MessageDialog.backgroun", new Color(Integer.parseInt("58a6b3", 16)));
        UIManager.put("OptionPane.font", new Font("Cascadia Code", Font.BOLD, 14));
    }

    // Método para abrir a tela de inserção de veículo
    private void abrirInserir() {
        try {
            String[] categorias = {"Compacto", "Standard", "Grande", "Econômico", "Premium", "Minivan"};
            String[] cambios = {"Automatico", "Manual"};

            // Criar campos de texto e checkbox para os atributos do veículo
            JComboBox<String> categoriaComboBox = new JComboBox<>(categorias);
            JTextField maxPassageirosField = new JTextField();
            JTextField tamBagageiroField = new JTextField();
            JComboBox<String> tipoCambioField = new JComboBox<>(cambios);
            JCheckBox arCondicionadoCheckBox = new JCheckBox();
            JTextField mediaConsumoField = new JTextField();
            JCheckBox airbagCheckBox = new JCheckBox();
            JCheckBox freioABSCheckBox = new JCheckBox();
            JCheckBox dvdCheckBox = new JCheckBox();
            JTextField valorPorDiaField = new JTextField();
    
            // Criar um painel para organizar os componentes
            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Categoria:"));
            panel.add(categoriaComboBox);
            panel.add(new JLabel("Máximo de Passageiros:"));
            panel.add(maxPassageirosField);
            panel.add(new JLabel("Tamanho do Bagageiro:"));
            panel.add(tamBagageiroField);
            panel.add(new JLabel("Tipo de Câmbio:"));
            panel.add(tipoCambioField);
            panel.add(new JLabel("Ar Condicionado:"));
            panel.add(arCondicionadoCheckBox);
            panel.add(new JLabel("Média de Consumo:"));
            panel.add(mediaConsumoField);
            panel.add(new JLabel("Airbag:"));
            panel.add(airbagCheckBox);
            panel.add(new JLabel("Freio ABS:"));
            panel.add(freioABSCheckBox);
            panel.add(new JLabel("DVD:"));
            panel.add(dvdCheckBox);
            panel.add(new JLabel("Valor por Dia:"));
            panel.add(valorPorDiaField);
    
            // Mostrar os campos em uma caixa de diálogo para inserção
            int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Inserir Novo Veículo",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );
    
            if (result == JOptionPane.OK_OPTION) { // Se o usuário confirmou a inserção
                // Criar um novo objeto Veiculo com os dados inseridos
                Veiculo novoVeiculo = new Veiculo(
                    (String) categoriaComboBox.getSelectedItem(),
                    Integer.parseInt(maxPassageirosField.getText()),
                    Integer.parseInt(tamBagageiroField.getText()),
                    (String) tipoCambioField.getSelectedItem(),
                    arCondicionadoCheckBox.isSelected(),
                    Float.parseFloat(mediaConsumoField.getText()),
                    airbagCheckBox.isSelected(),
                    freioABSCheckBox.isSelected(),
                    dvdCheckBox.isSelected(),
                    Float.parseFloat(valorPorDiaField.getText())
                );
    
                // Chamar o método insert para inserir o veículo no banco de dados
                veiculoDAO.insert(novoVeiculo);
                // Mostrar uma mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Veículo inserido com sucesso.");
            }
        } catch (NumberFormatException e) {
            // Lidar com exceção se algum campo numérico estiver vazio ou com valor inválido
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            // Lidar com exceções de banco de dados
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao inserir veículo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    

    // Método para abrir a tela de atualização de veículo
    private void abrirAtualizarPorId() {
        try {
            String[] categorias = {"Compacto", "Standard", "Grande", "Econômico", "Premium", "Minivan"};
            String[] cambios = {"Automatico", "Manual"};
    
            // Obter os IDs disponíveis
            ArrayList<Integer> ids = veiculoDAO.obterIdsDisponiveis();
            
            // Verificar se há IDs disponíveis
            if (!ids.isEmpty()) {
                // Converter os IDs em uma matriz de strings para mostrar na caixa de diálogo
                String[] veiculoInfo = new String[ids.size()];
                for (int i = 0; i < ids.size(); i++) {
                    veiculoInfo[i] = "ID: " + ids.get(i); // Mostrar apenas o ID
                }
    
                // Mostra a caixa de diálogo personalizada
                String selectedVeiculoInfo = (String) JOptionPane.showInputDialog(
                    null,
                    "Selecione o Veículo a Ser Atualizado:",
                    "Selecionar Veículo",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    veiculoInfo,
                    null
                );
    
        
                if (selectedVeiculoInfo != null) { // Se um veículo foi selecionado
                    // Extrair o ID do veículo selecionado
                    int idSelecionado = Integer.parseInt(selectedVeiculoInfo.split(": ")[1]); // Extrair o ID
        
                    // Buscar o veículo pelo ID
                    Veiculo veiculoSelecionado = veiculoDAO.findById(idSelecionado);
        
                    // Solicitar ao usuário que insira os novos dados do veículo
                    JComboBox<String> categoriaField = new JComboBox<>(categorias);
                    JTextField maxPassageirosField = new JTextField(Integer.toString(veiculoSelecionado.getmaxPassageiros()));
                    JTextField tamBagageiroField = new JTextField(Integer.toString(veiculoSelecionado.gettamBagageiro()));
                    JComboBox<String> tipoCambioField = new JComboBox<>(cambios);
                    tipoCambioField.setSelectedItem(veiculoSelecionado.gettipoCambio()); // Definir o tipo de câmbio selecionado
                    JCheckBox arCondicionadoCheckBox = new JCheckBox();
                    JTextField mediaConsumoField = new JTextField(Float.toString(veiculoSelecionado.getmediaConsumo()));
                    JCheckBox airbagCheckBox = new JCheckBox();
                    JCheckBox freioABSCheckBox = new JCheckBox();
                    JCheckBox dvdCheckBox = new JCheckBox();
                    JTextField valorPorDiaField = new JTextField(Float.toString(veiculoSelecionado.getvalorPorDia()));
        
                    // Criar um painel para os campos de entrada
                    JPanel panel = new JPanel(new GridLayout(0, 1));
                    panel.add(new JLabel("Categoria:"));
                    panel.add(categoriaField);
                    panel.add(new JLabel("Máximo de Passageiros:"));
                    panel.add(maxPassageirosField);
                    panel.add(new JLabel("Tamanho do Bagageiro:"));
                    panel.add(tamBagageiroField);
                    panel.add(new JLabel("Tipo de Câmbio:"));
                    panel.add(tipoCambioField);
                    panel.add(new JLabel("Ar Condicionado:"));
                    panel.add(arCondicionadoCheckBox);
                    panel.add(new JLabel("Média de Consumo:"));
                    panel.add(mediaConsumoField);
                    panel.add(new JLabel("Airbag:"));
                    panel.add(airbagCheckBox);
                    panel.add(new JLabel("Freio ABS:"));
                    panel.add(freioABSCheckBox);
                    panel.add(new JLabel("DVD:"));
                    panel.add(dvdCheckBox);
                    panel.add(new JLabel("Valor por Dia:"));
                    panel.add(valorPorDiaField);
                    
                    
    
                    // Exibir uma caixa de diálogo para os campos de entrada
                    int result = JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Atualizar Veículo",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                    );
        
                    if (result == JOptionPane.OK_OPTION) { // Se o usuário confirmou a atualização
                        // Criar um novo objeto Veiculo com os novos atributos
                        Veiculo novoVeiculo = new Veiculo(
                            (String) categoriaField.getSelectedItem(),
                            Integer.parseInt(maxPassageirosField.getText()),
                            Integer.parseInt(tamBagageiroField.getText()),
                            (String) tipoCambioField.getSelectedItem(),
                            arCondicionadoCheckBox.isSelected(),
                            Float.parseFloat(mediaConsumoField.getText()),
                            airbagCheckBox.isSelected(),
                            freioABSCheckBox.isSelected(),
                            dvdCheckBox.isSelected(),
                            Float.parseFloat(valorPorDiaField.getText())
                        );
        
                        // Atualizar o veículo no banco de dados
                        veiculoDAO.update(novoVeiculo, idSelecionado); // Usar o ID selecionado
                        JOptionPane.showMessageDialog(this, "Veículo atualizado com sucesso.");
                    }
                }
            } else {
                // Se não houver IDs disponíveis, exibir uma mensagem indicando isso
                JOptionPane.showMessageDialog(this, "Nenhum veículo disponível para atualização.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            // Lidar com exceção se algum campo numérico estiver vazio ou com valor inválido
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para os campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            // Lidar com exceções de banco de dados
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar veículos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    

    // Método para abrir a tela de exclusão de veículo por ID
    private void abrirTelaExcluirPorId() throws SQLIntegrityConstraintViolationException {
        try {
            // Listar os IDs disponíveis
            listarIdsDisponiveis();
            
            // Pedir ao usuário que insira o ID do veículo a ser excluído
            String idStr = JOptionPane.showInputDialog(this, "Insira o ID do veículo a ser excluído:", "Excluir Veículo por ID", JOptionPane.QUESTION_MESSAGE);
            
            if (idStr != null && !idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    // Buscar o veículo pelo ID usando o método findById da VeiculoDAOJDBC
                    Veiculo veiculo = veiculoDAO.findById(id);
                    
                    if (veiculo != null) {
                        // Se o veículo foi encontrado, exibir suas informações e pedir confirmação
                        String mensagem = "Deseja realmente excluir o veículo com o seguinte ID?\n\n" +
                                "ID: " + id + "\n" +
                                "Categoria: " + veiculo.getCategoria() + "\n" +
                                "Max Passageiros: " + veiculo.getmaxPassageiros() + "\n" +
                                "Tamanho do Bagageiro: " + veiculo.gettamBagageiro() + "\n" +
                                "Tipo de Câmbio: " + veiculo.gettipoCambio() + "\n" +
                                "Ar Condicionado: " + (veiculo.getarCondicionado() ? "Sim" : "Não") + "\n" +
                                "Média de Consumo: " + veiculo.getmediaConsumo() + "\n" +
                                "Airbag: " + (veiculo.getairbag() ? "Sim" : "Não") + "\n" +
                                "Freio ABS: " + (veiculo.getfreioABS() ? "Sim" : "Não") + "\n" +
                                "DVD: " + (veiculo.getdvd() ? "Sim" : "Não") + "\n" +
                                "Valor por Dia: " + veiculo.getvalorPorDia() + "\n\n" +
                                "Esta ação não pode ser desfeita!";
                        
                        int confirmacao = JOptionPane.showConfirmDialog(this, mensagem, "Confirmar Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        
                        if (confirmacao == JOptionPane.YES_OPTION) {
                            // Excluir o veículo usando o método deleteById da VeiculoDAOJDBC
                            veiculoDAO.deleteById(id);
                            JOptionPane.showMessageDialog(this, "Veículo excluído com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        // Se o veículo não foi encontrado, exibir uma mensagem indicando isso
                        JOptionPane.showMessageDialog(this, "Nenhum veículo encontrado com o ID fornecido.", "Veículo Não Encontrado", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    // Se o usuário fornecer um ID inválido, exibir uma mensagem de erro
                    JOptionPane.showMessageDialog(this, "ID inválido. Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (SQLIntegrityConstraintViolationException e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Veiculo está associado a um cliente, portanto não pode ser excluído.", "Erro", JOptionPane.ERROR_MESSAGE);
                } 
            }
        
        } catch (SQLException e) {
            // Lidar com exceções de banco de dados, se necessário
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar IDs de veículos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } 

    }
    


    // Método para abrir a tela de busca de veículo por ID
    private void abrirTelaBuscarPorId() throws SQLException {
        // Listar os IDs disponíveis
        listarIdsDisponiveis();
        
        // Pedir ao usuário que insira o ID do veículo a ser buscado
        JTextField idField = new JTextField(10);
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Insira o ID do veículo:"));
        inputPanel.add(idField);
        
        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Buscar Veículo por ID", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            String idStr = idField.getText();
            if (!idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    // Buscar o veículo pelo ID usando o método findById da VeiculoDAOJDBC
                    Veiculo veiculo = veiculoDAO.findById(id);
                    
                    if (veiculo != null) {
                        // Se o veículo foi encontrado, exibir suas informações em uma caixa de diálogo personalizada
                        VeiculoDialog dialog = new VeiculoDialog(this, veiculo);
                        dialog.setVisible(true);
                    } else {
                        // Se o veículo não foi encontrado, exibir uma mensagem indicando isso
                        JOptionPane.showMessageDialog(this, "Nenhum veículo encontrado com o ID fornecido.", "Veículo Não Encontrado", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    // Se o usuário fornecer um ID inválido, exibir uma mensagem de erro
                    JOptionPane.showMessageDialog(this, "ID inválido. Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void listarIdsDisponiveis() {
        try {
            // Obter os IDs disponíveis usando VeiculoDAOJDBC
            ArrayList<Integer> ids = veiculoDAO.obterIdsDisponiveis();
            
            // Verificar se há IDs disponíveis
            if (!ids.isEmpty()) {
                // Construir uma mensagem com os IDs disponíveis
                StringBuilder mensagem = new StringBuilder("IDs disponíveis:\n");
                for (Integer id : ids) {
                    mensagem.append(id).append("\n");
                }
                
                // Exibir os IDs disponíveis em uma janela de diálogo
                JOptionPane.showMessageDialog(this, mensagem.toString(), "Prévia dos IDs Disponíveis", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Se não houver IDs disponíveis, exibir uma mensagem indicando isso
                JOptionPane.showMessageDialog(this, "Nenhum veículo disponível.", "Prévia dos IDs Disponíveis", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            // Lidar com exceções de banco de dados, se necessário
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar IDs disponíveis: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Método para listar todos os veículos
    private void listarTodosVeiculos() {
        try {
            // Buscar todos os veículos do banco de dados
            ArrayList<Veiculo> veiculos = veiculoDAO.findAll();
            
            // Mostrar os veículos em um diálogo personalizado
            ListarVeiculosDialog.mostrarDialog(this, veiculos);
        } catch (SQLException e) {
            // Lidar com exceções de banco de dados, se necessário
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar veículos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    


    // Método para estilizar botões
    private void estilizarBotao(JButton botao) {
        botao.setBackground(new Color(Integer.parseInt("010711", 16)));
        botao.setForeground(Color.white);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Cascadia Code", Font.BOLD, 14));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.white, 1), // Borda branca de 1 pixel
            BorderFactory.createEmptyBorder(10, 25, 10, 25) // Espaçamento interno
        ));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GerenciamentoVeiculosFrame().setVisible(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
