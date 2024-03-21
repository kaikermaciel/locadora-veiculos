package br.edu.ufam.icomp.locadora_veiculos.view;

import javax.swing.*;
import java.awt.*;
import br.edu.ufam.icomp.locadora_veiculos.model.entidades.Veiculo;

public class VeiculoDialog extends JDialog {
    public VeiculoDialog(Frame owner, Veiculo veiculo) {
        super(owner, "Detalhes do Veículo", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(owner);
        
        // Criar um painel para organizar os componentes
        JPanel panel = new JPanel(new GridLayout(10, 1, 10, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(Integer.parseInt("010711", 16)));
        panel.setForeground(new Color(Integer.parseInt("010711", 16)));
        
        // Adicionar os componentes ao painel
        adicionarCampo(panel, "Categoria", veiculo.getCategoria());
        adicionarCampo(panel, "Max Passageiros", String.valueOf(veiculo.getmaxPassageiros()));
        adicionarCampo(panel, "Tamanho do Bagageiro", String.valueOf(veiculo.gettamBagageiro()));
        adicionarCampo(panel, "Tipo de Câmbio", veiculo.gettipoCambio());
        adicionarCampo(panel, "Ar Condicionado", veiculo.getarCondicionado() ? "Sim" : "Não");
        adicionarCampo(panel, "Média de Consumo", String.valueOf(veiculo.getmediaConsumo()));
        adicionarCampo(panel, "Airbag", veiculo.getairbag() ? "Sim" : "Não");
        adicionarCampo(panel, "Freio ABS", veiculo.getfreioABS() ? "Sim" : "Não");
        adicionarCampo(panel, "DVD", veiculo.getdvd() ? "Sim" : "Não");
        adicionarCampo(panel, "Valor por Dia", String.valueOf(veiculo.getvalorPorDia()));
        
        // Adicionar o painel ao diálogo
        add(panel);
    }
    
    // Método auxiliar para adicionar um campo de texto ao painel
    private void adicionarCampo(JPanel panel, String nomeCampo, String valorCampo) {
        JLabel label = new JLabel(nomeCampo + ":");
        label.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
        label.setForeground(new Color(Integer.parseInt("58a6b3", 16)));
        panel.add(label);
        
        JTextField textField = new JTextField(valorCampo);
        textField.setEditable(false);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setForeground(new Color(Integer.parseInt("58a6b3", 16)));
        textField.setBackground(new Color(Integer.parseInt("010711", 16)));
        textField.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
        panel.add(textField);
    }
}

