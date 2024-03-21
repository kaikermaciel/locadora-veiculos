package br.edu.ufam.icomp.locadora_veiculos.view;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.util.ArrayList;
import br.edu.ufam.icomp.locadora_veiculos.controller.dao.*;
import br.edu.ufam.icomp.locadora_veiculos.controller.exceptions.*;
import br.edu.ufam.icomp.locadora_veiculos.model.entidades.Cliente;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InserirClienteDialog extends JDialog {
    
    private ClienteDAOJDBC clienteDAO;
    private VeiculoDAOJDBC veiculoDAO;
    private JTextField localRetiradaField;
    private JTextField localDevolucaoField;
    private JFormattedTextField dataRetiradaField;
    private JFormattedTextField dataDevolucaoField;
    private JTextField idadeCondutorField;
    private JComboBox<Integer> veiculoIdComboBox;
    private JCheckBox gpsCheckBox;
    private JCheckBox assentoCriancaCheckBox;
    private JCheckBox seguroCompletoCheckBox;
    
    public InserirClienteDialog(Frame owner) throws SQLException {
        super(owner, "Inserir Cliente", true);
        clienteDAO = new ClienteDAOJDBC();
        veiculoDAO = new VeiculoDAOJDBC();
        
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Campos de entrada para os dados do cliente
        localRetiradaField = new JTextField();
        localDevolucaoField = new JTextField();
        dataRetiradaField = new JFormattedTextField(createFormatter("##/##/####"));
        dataDevolucaoField = new JFormattedTextField(createFormatter("##/##/####"));
        idadeCondutorField = new JTextField();
            


        // Recuperar os IDs dos veículos disponíveis
        ArrayList<Integer> veiculoIds = null;
        try {
            veiculoIds = veiculoDAO.obterIdsDisponiveis();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar veículos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            dispose();
            return;
        }
        
        // ComboBox para selecionar o veículo
        veiculoIdComboBox = new JComboBox<>(veiculoIds.toArray(new Integer[0]));
        
        // Checkbox para os serviços adicionais
        gpsCheckBox = new JCheckBox("GPS");
        assentoCriancaCheckBox = new JCheckBox("Assento para Criança");
        seguroCompletoCheckBox = new JCheckBox("Seguro Completo");
        
        dataRetiradaField.setText("__/__/____");
        dataDevolucaoField.setText("__/__/____");

        // Adicionando os componentes ao painel
        panel.add(new JLabel("Local de Retirada:"));
        panel.add(localRetiradaField);
        panel.add(new JLabel("Local de Devolução:"));
        panel.add(localDevolucaoField);
        panel.add(new JLabel("Data de Retirada:"));
        panel.add(dataRetiradaField);
        panel.add(new JLabel("Data de Devolução:"));
        panel.add(dataDevolucaoField);
        panel.add(new JLabel("Idade do Condutor:"));
        panel.add(idadeCondutorField);
        panel.add(new JLabel("Selecione o Veículo:"));
        panel.add(veiculoIdComboBox);
        panel.add(gpsCheckBox);
        panel.add(assentoCriancaCheckBox);
        panel.add(seguroCompletoCheckBox);
        
        // Botões de Ação
        JButton btnInserir = new JButton("Inserir");
        JButton btnCancelar = new JButton("Cancelar");
        
        // Adicionando listeners aos botões
        btnInserir.addActionListener(e -> {
            try {
                inserirCliente();
            } catch (ClienteIdadeInvalidaException | CarroAlugadoException | CarroAluguelInvalido e1) {
                JOptionPane.showMessageDialog(this, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnCancelar.addActionListener(e -> dispose());
        
        // Adicionando botões ao painel
        panel.add(btnInserir);
        panel.add(btnCancelar);
        
        add(panel);
        
        pack();
        setLocationRelativeTo(owner);
    }

    final MaskFormatter createFormatter(String format) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(format);
            formatter.setPlaceholderCharacter('_'); // Define o caractere de espaço reservado como '_'
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return formatter;   
    }   
    
    private void inserirCliente() throws ClienteIdadeInvalidaException, CarroAlugadoException, CarroAluguelInvalido {
        // Recuperar os valores dos campos de entrada
        String localRetirada = localRetiradaField.getText();
        String localDevolucao = localDevolucaoField.getText();
        String dataRetiradaStr = (String) dataRetiradaField.getText();
        String dataDevolucaoStr = (String) dataDevolucaoField.getText();
        int idadeCondutor = Integer.parseInt(idadeCondutorField.getText());
        
        // Recuperar o ID do veículo selecionado
        int veiculoId = (int) veiculoIdComboBox.getSelectedItem();
        
        // Converter strings para java.sql.Date usando SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataRetiradaSQL = null;
        Date dataDevolucaoSQL = null;
        try {
            dataRetiradaSQL = new Date(dateFormat.parse(dataRetiradaStr).getTime());
            dataDevolucaoSQL = new Date(dateFormat.parse(dataDevolucaoStr).getTime());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Erro ao converter data: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }

        // Criar um novo objeto Cliente com os valores recuperados
        Cliente cliente = new Cliente(dataRetiradaSQL, localRetirada, dataDevolucaoSQL, localDevolucao, idadeCondutor, veiculoId, gpsCheckBox.isSelected(), assentoCriancaCheckBox.isSelected(), seguroCompletoCheckBox.isSelected());

        // Chamar o método insert do DAO para inserir o cliente no banco de dados
        try {
            clienteDAO.insert(cliente);
            JOptionPane.showMessageDialog(this, "Cliente inserido com sucesso.");
            dispose(); // Fechar a caixa de diálogo após a inserção
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao inserir cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira uma idade válida.", "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    
    public static void mostrarDialog(Frame owner) throws SQLException {
        InserirClienteDialog dialog = new InserirClienteDialog(owner);
        dialog.setVisible(true);
    }
}


