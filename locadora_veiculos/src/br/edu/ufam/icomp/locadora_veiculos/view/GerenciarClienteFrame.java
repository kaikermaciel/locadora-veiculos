package br.edu.ufam.icomp.locadora_veiculos.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.ufam.icomp.locadora_veiculos.controller.dao.*;
import br.edu.ufam.icomp.locadora_veiculos.controller.exceptions.ClienteIdadeInvalidaException;
import br.edu.ufam.icomp.locadora_veiculos.model.entidades.*;

public class GerenciarClienteFrame extends JFrame {
    
    private ClienteDAOJDBC clienteDAO;
    private JComboBox<Integer> clienteIdComboBox;
    private VeiculoDAOJDBC veiculoDAO;
    
    public GerenciarClienteFrame() throws SQLException {
        super("Gerenciar Cliente");
        clienteDAO = new ClienteDAOJDBC();
        veiculoDAO = new VeiculoDAOJDBC();

        setTitle("Gerenciamento Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centralizar na tela

        // Layout da tela
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(Integer.parseInt("010711", 16)));
        
        clienteIdComboBox = new JComboBox<>();
        updateClienteIdComboBox();

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

        // Botão para voltar ao MainFrame
        JPanel panelBotaoVoltar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotaoVoltar.setBackground(new Color(Integer.parseInt("58a6b3", 16))); // Define cor de fundo do painel
        panelBotaoVoltar.add(btnVoltar);
        getContentPane().add(panelBotaoVoltar, BorderLayout.NORTH);
        
        JPanel botoesPanel = new JPanel(new GridLayout(5,1));
        getContentPane().add(botoesPanel, BorderLayout.CENTER);
        
        // Botões
        JButton atualizarButton = new JButton("Atualizar Informações do Cliente");
        JButton btnRemover = new JButton("Remover Cliente");
        JButton btnBuscar = new JButton("Resgatar Informações do Cliente");
        JButton btnRelatorio = new JButton("Relatório dos Clientes");
        JButton btnFecharConta = new JButton("Finalizar Aluguel");

        // Estilizar botões
        estilizarBotao(atualizarButton);
        estilizarBotao(btnRemover);
        estilizarBotao(btnBuscar);
        estilizarBotao(btnRelatorio);
        estilizarBotao(btnFecharConta);

        // Adicionar botões ao painel
        botoesPanel.add(atualizarButton);
        botoesPanel.add(btnRemover);
        botoesPanel.add(btnBuscar);
        botoesPanel.add(btnRelatorio);
        botoesPanel.add(btnFecharConta);
    
    
        
    
    
        // Atualiza os IDs dos clientes disponíveis na ComboBox
        atualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    atualizarCliente(e);
                } catch (ClienteIdadeInvalidaException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                removerCliente(e);
            }
        });

        btnBuscar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    buscarCliente(e);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnRelatorio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gerarRelatorioClientes(e);
            }
        });

        btnFecharConta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                fecharConta(e);
            }
        });
    }
    
    private void updateClienteIdComboBox() {
        clienteIdComboBox.removeAllItems();
        try {
            ArrayList<Integer> ids = clienteDAO.obterIdsDisponiveis();
            for (Integer id : ids) {
                clienteIdComboBox.addItem(id);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar IDs de clientes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Listener para o botão "Atualizar Informações Cliente"
    private void buscarCliente(ActionEvent e) throws SQLException {
        // Listar os IDs disponíveis
        listarIdsClientesDisponiveis();
        
        // Pedir ao usuário que insira o ID do cliente a ser buscado
        JTextField idField = new JTextField(10);
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Insira o ID do cliente:"));
        inputPanel.add(idField);
        
        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Buscar Cliente por ID", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            String idStr = idField.getText();
            if (!idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    // Buscar o cliente pelo ID usando o método findById da ClienteDAO
                    Cliente cliente = clienteDAO.findById(id);
                    
                    if (cliente != null) {
                        // Se o cliente foi encontrado, exibir suas informações em uma caixa de diálogo personalizada
                        ClienteDialogFrame dialog = new ClienteDialogFrame(this, cliente);
                        dialog.setVisible(true);
                    } else {
                        // Se o cliente não foi encontrado, exibir uma mensagem indicando isso
                        JOptionPane.showMessageDialog(this, "Nenhum cliente encontrado com o ID fornecido.", "Cliente Não Encontrado", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException e1) {
                    // Se o usuário fornecer um ID inválido, exibir uma mensagem de erro
                    JOptionPane.showMessageDialog(this, "ID inválido. Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    private void listarIdsClientesDisponiveis() {
        try {
            // Obter os IDs disponíveis usando ClienteDAO
            ArrayList<Integer> ids = clienteDAO.obterIdsDisponiveis();
            
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
                JOptionPane.showMessageDialog(this, "Nenhum cliente disponível.", "Prévia dos IDs Disponíveis", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            // Lidar com exceções de banco de dados, se necessário
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar IDs disponíveis: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    // Listener para o botão "Remover Cliente"
    private void removerCliente(ActionEvent e) {
        try {
            // Listar os IDs disponíveis
            listarIdsClientesDisponiveis();
            
            // Pedir ao usuário que insira o ID do cliente a ser excluído
            String idStr = JOptionPane.showInputDialog(this, "Insira o ID do cliente a ser excluído:", "Excluir Cliente por ID", JOptionPane.QUESTION_MESSAGE);
            
            if (idStr != null && !idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    // Buscar o cliente pelo ID usando o método findById da ClienteDAO
                    Cliente cliente = clienteDAO.findById(id);
                    
                    if (cliente != null) {
                        // Se o cliente foi encontrado, exibir suas informações e pedir confirmação
                        String mensagem = "Deseja realmente excluir o cliente com o seguinte ID?\n\n" +
                                "ID: " + id + "\n" +
                                "Data de Retirada: " + cliente.getDataRetirada() + "\n" +
                                "Local de Retirada: " + cliente.getLocalRetirada() + "\n" +
                                "Data de Devolução: " + cliente.getDataDevolucao() + "\n" +
                                "Local de Devolução: " + cliente.getLocalDevolucao() + "\n" +
                                "Idade do Condutor: " + cliente.getIdadeCondutor() + "\n" +
                                "Veículo ID: " + cliente.getVeiculoId() + "\n" +
                                "GPS: " + (cliente.getGPS() ? "Sim" : "Não") + "\n" +
                                "Assento para Criança: " + (cliente.getAssentoCrianca() ? "Sim" : "Não") + "\n" +
                                "Seguro Completo: " + (cliente.getSeguroCompleto() ? "Sim" : "Não") + "\n\n" +
                                "Esta ação não pode ser desfeita!";
                        
                        int confirmacao = JOptionPane.showConfirmDialog(this, mensagem, "Confirmar Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        
                        if (confirmacao == JOptionPane.YES_OPTION) {
                            // Excluir o cliente usando o método deleteById da ClienteDAO
                            clienteDAO.deleteById(id);
                            JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        // Se o cliente não foi encontrado, exibir uma mensagem indicando isso
                        JOptionPane.showMessageDialog(this, "Nenhum cliente encontrado com o ID fornecido.", "Cliente Não Encontrado", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException e1) {
                    // Se o usuário fornecer um ID inválido, exibir uma mensagem de erro
                    JOptionPane.showMessageDialog(this, "ID inválido. Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e1) {
            // Lidar com exceções de banco de dados, se necessário
            e1.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar IDs de clientes: " + e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    // Listener para o botão "Resgatar Informações do Cliente"
    private void atualizarCliente(ActionEvent e) throws ClienteIdadeInvalidaException, SQLException {
        try {
            // Obter todos os clientes disponíveis
            ArrayList<Integer> clientes = clienteDAO.obterIdsDisponiveis();
    
            // Verificar se há clientes disponíveis
            if (!clientes.isEmpty()) {
                // Converter os clientes em uma matriz de strings para mostrar na caixa de diálogo
                String[] clienteInfo = new String[clientes.size()];
                for (int i = 0; i < clientes.size(); i++) {
                    clienteInfo[i] = "ID: " + clientes.get(i);;
                }
    
                // Mostra a caixa de diálogo personalizada para selecionar o cliente
                String selectedClienteInfo = (String) JOptionPane.showInputDialog(
                        this,
                        "Selecione o Cliente a Ser Atualizado:",
                        "Selecionar Cliente",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        clienteInfo,
                        null
                );
    
                if (selectedClienteInfo != null) { // Se um cliente foi selecionado
                    // Extrair o ID do cliente selecionado
                    int clienteId = Integer.parseInt(selectedClienteInfo.split(": ")[1]);
    
                    // Buscar o cliente pelo ID
                    Cliente clienteSelecionado = clienteDAO.findById(clienteId);
    
                    // Solicitar ao usuário que insira os novos dados do cliente
                    JTextField dataRetiradaField = new JTextField(clienteSelecionado.getDataRetirada().toString());
                    JTextField localRetiradaField = new JTextField(clienteSelecionado.getLocalRetirada());
                    JTextField dataDevolucaoField = new JTextField(clienteSelecionado.getDataDevolucao().toString());
                    JTextField localDevolucaoField = new JTextField(clienteSelecionado.getLocalDevolucao());
                    JTextField idadeCondutorField = new JTextField(Integer.toString(clienteSelecionado.getIdadeCondutor()));
                    JTextField veiculoIdField = new JTextField(Integer.toString(clienteSelecionado.getVeiculoId()));
                    JCheckBox gpsCheckBox = new JCheckBox();
                    JCheckBox assentoCriancaCheckBox = new JCheckBox();
                    JCheckBox seguroCompletoCheckBox = new JCheckBox();
    
                    // Criar um painel para os campos de entrada
                    JPanel panel = new JPanel(new GridLayout(0, 1));
                    panel.add(new JLabel("Data de Retirada:"));
                    panel.add(dataRetiradaField);
                    panel.add(new JLabel("Local de Retirada:"));
                    panel.add(localRetiradaField);
                    panel.add(new JLabel("Data de Devolução:"));
                    panel.add(dataDevolucaoField);
                    panel.add(new JLabel("Local de Devolução:"));
                    panel.add(localDevolucaoField);
                    panel.add(new JLabel("Idade do Condutor:"));
                    panel.add(idadeCondutorField);
                    panel.add(new JLabel("Veículo ID:"));
                    panel.add(veiculoIdField);
                    panel.add(new JLabel("GPS:"));
                    panel.add(gpsCheckBox);
                    panel.add(new JLabel("Assento para Criança:"));
                    panel.add(assentoCriancaCheckBox);
                    panel.add(new JLabel("Seguro Completo:"));
                    panel.add(seguroCompletoCheckBox);
    
                    // Exibir uma caixa de diálogo para os campos de entrada
                    int result = JOptionPane.showConfirmDialog(
                            this,
                            panel,
                            "Atualizar Cliente",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE
                    );
    
                    if (result == JOptionPane.OK_OPTION) { // Se o usuário confirmou a atualização
                        try {
                            // Criar um novo objeto Cliente com os novos atributos
                            Cliente novoCliente = new Cliente(
                                    clienteSelecionado.getDataRetirada(), // Não precisa ser alterado
                                    localRetiradaField.getText(),
                                    clienteSelecionado.getDataDevolucao(), // Não precisa ser alterado
                                    localDevolucaoField.getText(),
                                    Integer.parseInt(idadeCondutorField.getText()),
                                    Integer.parseInt(veiculoIdField.getText()),
                                    gpsCheckBox.isSelected(),
                                    assentoCriancaCheckBox.isSelected(),
                                    seguroCompletoCheckBox.isSelected()
                            );
    
                            // Atualizar o cliente no banco de dados
                            clienteDAO.update(novoCliente, clienteId);
                            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso.");
                        } catch (NumberFormatException ex) {
                            // Lidar com exceção se algum campo numérico estiver vazio ou com valor inválido
                            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para os campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else {
                // Se não houver clientes disponíveis, exibir uma mensagem indicando isso
                JOptionPane.showMessageDialog(this, "Nenhum cliente disponível para atualização.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            // Lidar com exceções de banco de dados
            JOptionPane.showMessageDialog(this, "Erro ao buscar clientes: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
        
    // Listener para o botão "Relatório dos Clientes"
    private void gerarRelatorioClientes(ActionEvent e) {
        try {
            ArrayList<Cliente> clientes = clienteDAO.findAll();
            
            ListarClienteDialog.mostrarDialog(this, clientes);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar informações dos clientes: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    // Listener para o botão "Finalizar Aluguel"
    private void fecharConta(ActionEvent e) {
        try {
            // Obter a lista de clientes disponíveis
            ArrayList<Cliente> clientes = clienteDAO.findAll();
            
            // Verificar se há clientes disponíveis
            if (!clientes.isEmpty()) {
                // Mostrar uma prévia dos clientes em uma janela de diálogo
                ListarClienteDialog.mostrarDialog(this, clientes);
                
                // Pedir ao usuário que selecione um cliente
                String clienteIdStr = JOptionPane.showInputDialog(this, "Insira o ID do cliente:", "Selecionar Cliente", JOptionPane.QUESTION_MESSAGE);
                
                // Verificar se o usuário cancelou a seleção
                if (clienteIdStr != null && !clienteIdStr.isEmpty()) {
                    int clienteId = Integer.parseInt(clienteIdStr);
                    
                    // Obter o cliente selecionado
                    Cliente cliente = clienteDAO.findById(clienteId);
                    
                    // Verificar se o cliente foi encontrado
                    if (cliente != null) {
                        // Calcular a diferença de dias
                        int diferencaDias = clienteDAO.geraDiferencaDias(clienteId);
                        
                        // Obter o veículo associado ao cliente
                        Veiculo veiculo = veiculoDAO.findById(cliente.getVeiculoId());
                        
                        // Verificar se o veículo foi encontrado
                        if (veiculo != null) {
                            // Exibir os cálculos em uma janela de diálogo
                            ExibirCalculosAluguelFrame frame = new ExibirCalculosAluguelFrame();
                            frame.exibirCalculosAluguel(veiculo, diferencaDias, cliente.getGPS(), cliente.getAssentoCrianca(), cliente.getSeguroCompleto(), veiculo.getairbag(), veiculo.getfreioABS(), veiculo.getdvd());
                        } else {
                            JOptionPane.showMessageDialog(this, "Veículo não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum cliente disponível.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao fechar a conta: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

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
        SwingUtilities.invokeLater(() -> {
            try {
                new GerenciarClienteFrame();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
