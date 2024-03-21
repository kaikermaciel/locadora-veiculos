package br.edu.ufam.icomp.locadora_veiculos.view;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import br.edu.ufam.icomp.locadora_veiculos.model.entidades.Cliente;

public class ListarClienteDialog extends JDialog {
    
    public ListarClienteDialog(Frame owner, ArrayList<Cliente> clientes) {
        super(owner, "Todos os Veículos", true);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        
        JPanel clientesPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        
        for (Cliente cliente : clientes) {
            JPanel clientePanel = new JPanel(new GridLayout(0, 2, 10, 5));
            clientePanel.setBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt("010711", 16))));
            clientePanel.setBackground(new Color(Integer.parseInt("010711", 16)));
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dataRetiradaFormatada = dateFormat.format(cliente.getDataRetirada());
            String dataDevolucaoFormatada = dateFormat.format(cliente.getDataDevolucao());
            
            adicionarInformacaoCliente(clientePanel, "Data de Retirada", dataRetiradaFormatada);
            adicionarInformacaoCliente(clientePanel, "Local de Retirada", cliente.getLocalRetirada());
            adicionarInformacaoCliente(clientePanel, "Data de Devolução", dataDevolucaoFormatada);
            adicionarInformacaoCliente(clientePanel, "Local de Devolução", cliente.getLocalDevolucao());
            adicionarInformacaoCliente(clientePanel, "Idade do Condutor", String.valueOf(cliente.getIdadeCondutor()));
            adicionarInformacaoCliente(clientePanel, "ID do Veículo", String.valueOf(cliente.getVeiculoId()));
            adicionarInformacaoCliente(clientePanel, "GPS", cliente.getGPS() ? "Sim" : "Não");
            adicionarInformacaoCliente(clientePanel, "Assento para Criança", cliente.getAssentoCrianca() ? "Sim" : "Não");
            adicionarInformacaoCliente(clientePanel, "Seguro Completo", cliente.getSeguroCompleto() ? "Sim" : "Não");
            
            clientesPanel.add(clientePanel);
        }
        
        JScrollPane scrollPane = new JScrollPane(clientesPanel);
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
    
    public static void mostrarDialog(Frame owner, ArrayList<Cliente> clientes) {
        ListarClienteDialog dialog = new ListarClienteDialog(owner, clientes);
        dialog.setVisible(true);
    }
}