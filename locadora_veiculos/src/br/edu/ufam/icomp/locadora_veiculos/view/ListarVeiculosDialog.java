package br.edu.ufam.icomp.locadora_veiculos.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import br.edu.ufam.icomp.locadora_veiculos.model.entidades.Veiculo;

public class ListarVeiculosDialog extends JDialog {
    
    public ListarVeiculosDialog(Frame owner, ArrayList<Veiculo> veiculos) {
        super(owner, "Todos os Veículos", true);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        
        JPanel veiculosPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        
        for (Veiculo veiculo : veiculos) {
            JPanel veiculoPanel = new JPanel(new GridLayout(0, 2, 10, 5));
            veiculoPanel.setBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt("010711", 16))));
            veiculoPanel.setBackground(new Color(Integer.parseInt("010711", 16)));
            
            adicionarInformacaoVeiculo(veiculoPanel, "Categoria", veiculo.getCategoria());
            adicionarInformacaoVeiculo(veiculoPanel, "Máximo passageiros permitido", String.valueOf(veiculo.getmaxPassageiros()));
            adicionarInformacaoVeiculo(veiculoPanel, "Tamanho do Bagageiro (em Litros)", String.valueOf(veiculo.gettamBagageiro()));
            adicionarInformacaoVeiculo(veiculoPanel, "Tipo de Câmbio", veiculo.gettipoCambio());
            adicionarInformacaoVeiculo(veiculoPanel, "Possui Ar Condicionado", veiculo.getarCondicionado() ? "Sim" : "Não");
            adicionarInformacaoVeiculo(veiculoPanel, "Média de consumo veiculo (em km/L)", String.valueOf(veiculo.getmediaConsumo()));
            adicionarInformacaoVeiculo(veiculoPanel, "Possui airbags", veiculo.getairbag() ? "Sim" : "Não");
            adicionarInformacaoVeiculo(veiculoPanel, "Possui Freio ABS", veiculo.getfreioABS() ? "Sim" : "Não");
            adicionarInformacaoVeiculo(veiculoPanel, "Possui dvd", veiculo.getdvd() ? "Sim" : "Não");
            
            veiculosPanel.add(veiculoPanel);
        }
        
        JScrollPane scrollPane = new JScrollPane(veiculosPanel);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        add(panel);
        
        pack();
        setLocationRelativeTo(owner);
    }
    
    private void adicionarInformacaoVeiculo(JPanel panel, String rotulo, String valor) {
        JLabel labelRotulo = new JLabel(rotulo + ":");
        labelRotulo.setFont(new Font("Cascadia Code", Font.BOLD, 14));
        labelRotulo.setForeground(new Color(Integer.parseInt("58a6b3", 16)));
        panel.add(labelRotulo);
        
        JLabel labelValor = new JLabel(valor);
        labelValor.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
        labelValor.setForeground(new Color(Integer.parseInt("58a6b3", 16)));
        panel.add(labelValor);
    }
    
    public static void mostrarDialog(Frame owner, ArrayList<Veiculo> veiculos) {
        ListarVeiculosDialog dialog = new ListarVeiculosDialog(owner, veiculos);
        dialog.setVisible(true);
    }
}
