package br.edu.ufam.icomp.locadora_veiculos.view;

import br.edu.ufam.icomp.locadora_veiculos.model.entidades.Cliente;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class ClienteDialogFrame extends JDialog {
    
    public ClienteDialogFrame(Frame owner, Cliente cliente) {
        super(owner, "Detalhes do Cliente", true);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel clientePanel = new JPanel(new GridLayout(0, 2, 10, 5));
        
        adicionarInformacaoCliente(clientePanel, "Data de Retirada", new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataRetirada()));
        adicionarInformacaoCliente(clientePanel, "Local de Retirada", cliente.getLocalRetirada());
        adicionarInformacaoCliente(clientePanel, "Data de Devolução", new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataDevolucao()));
        adicionarInformacaoCliente(clientePanel, "Local de Devolução", cliente.getLocalDevolucao());
        adicionarInformacaoCliente(clientePanel, "Idade do Condutor", String.valueOf(cliente.getIdadeCondutor()));
        adicionarInformacaoCliente(clientePanel, "Veículo ID", String.valueOf(cliente.getVeiculoId()));
        adicionarInformacaoCliente(clientePanel, "GPS", cliente.getGPS() ? "Sim" : "Não");
        adicionarInformacaoCliente(clientePanel, "Assento para Criança", cliente.getAssentoCrianca() ? "Sim" : "Não");
        adicionarInformacaoCliente(clientePanel, "Seguro Completo", cliente.getSeguroCompleto() ? "Sim" : "Não");
        
        JScrollPane scrollPane = new JScrollPane(clientePanel);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        add(panel);
        
        pack();
        setLocationRelativeTo(owner);
    }
    
    private void adicionarInformacaoCliente(JPanel panel, String rotulo, String valor) {
        JLabel labelRotulo = new JLabel(rotulo + ":");
        labelRotulo.setFont(new Font("Cascadia Code", Font.BOLD, 14));
        labelRotulo.setForeground(new Color(Integer.parseInt("58a6b3", 16)));
        panel.add(labelRotulo);
        
        JLabel labelValor = new JLabel(valor);
        labelValor.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
        labelValor.setForeground(new Color(Integer.parseInt("58a6b3", 16)));
        panel.add(labelValor);
    }
    
    public static void mostrarDialog(Frame owner, Cliente cliente) {
        ClienteDialogFrame dialog = new ClienteDialogFrame(owner, cliente);
        dialog.setVisible(true);
    }
}
