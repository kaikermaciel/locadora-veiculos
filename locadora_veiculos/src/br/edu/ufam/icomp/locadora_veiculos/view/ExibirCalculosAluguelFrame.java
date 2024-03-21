package br.edu.ufam.icomp.locadora_veiculos.view;

import javax.swing.*;

import br.edu.ufam.icomp.locadora_veiculos.model.entidades.Veiculo;

import java.text.DecimalFormat;

public class ExibirCalculosAluguelFrame extends JFrame {

    // Outros membros da classe

    void exibirCalculosAluguel(Veiculo veiculo, int diferencaDias, boolean possuiGPS, boolean possuiAssentoCrianca, boolean possuiSeguroCompleto, boolean possuiAirBag, boolean possuiFreioABS, boolean possuiDvd) {
        double valorBase = veiculo.getvalorPorDia() * diferencaDias;
        double extras = 0.0;

        // Adicionar valor dos extras se aplicáveis
        if (possuiGPS) extras += 70.0;
        if (possuiAssentoCrianca) extras += 50.0;
        if (possuiSeguroCompleto) extras += 250.0;
        if (possuiAirBag) extras += 150.0;
        if (possuiFreioABS) extras += 100.0;
        if (possuiDvd) extras += 30.0;


        double total = valorBase + extras;

        DecimalFormat df = new DecimalFormat("#.##");

        StringBuilder mensagem = new StringBuilder("Detalhes do Aluguel:\n\n");
        mensagem.append("Valor Base (").append(diferencaDias).append(" dias): $").append(df.format(valorBase)).append("\n")
                .append("Extras:\n")
                .append("  GPS: ").append(possuiGPS ? "$70\n" : "Não possui\n")
                .append("  Assento para Criança: ").append(possuiAssentoCrianca ? "$50\n" : "Não possui\n")
                .append("  Seguro Completo: ").append(possuiSeguroCompleto ? "$250\n" : "Não possui\n")
                .append("  AirBag: ").append(possuiAirBag ? "$150\n" : "Não possui\n")
                .append("  Freio ABS: ").append(possuiFreioABS ? "$100\n": "Não possui\n")
                .append("  DVD: ").append(possuiDvd ? "$30\n" : "Não possui\n")
                .append("Total: $").append(df.format(total));

        JOptionPane.showMessageDialog(this, mensagem.toString(), "Cálculo do Aluguel", JOptionPane.INFORMATION_MESSAGE);
    }

    // Outros métodos da classe

}


