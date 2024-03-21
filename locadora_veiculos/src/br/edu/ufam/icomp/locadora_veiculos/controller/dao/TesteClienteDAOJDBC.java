package br.edu.ufam.icomp.locadora_veiculos.controller.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import br.edu.ufam.icomp.locadora_veiculos.controller.exceptions.CarroAlugadoException;
import br.edu.ufam.icomp.locadora_veiculos.controller.exceptions.CarroAluguelInvalido;
import br.edu.ufam.icomp.locadora_veiculos.controller.exceptions.ClienteIdadeInvalidaException;
import br.edu.ufam.icomp.locadora_veiculos.model.entidades.Cliente;

public class TesteClienteDAOJDBC {
    public static void main(String[] args) throws SQLException, ClienteIdadeInvalidaException, CarroAlugadoException, CarroAluguelInvalido, ParseException {

        // Configure database connection (if needed)

        ClienteDAO clienteDAO = new ClienteDAOJDBC();

        // Test insert()
        @SuppressWarnings("deprecation")
        Date data1 = new Date(2004, 05, 05);
        @SuppressWarnings("deprecation")
        Date data2 = new Date(2004, 05, 10);
        
        Cliente newCliente1= new Cliente(data1,"Manaus", data2, "Manaus", 19, 1, true, false, true);
        Cliente newCliente2= new Cliente(data2, "Manaus", data1,"Manaus", 25, 2, true, false, true);
        
        clienteDAO.insert(newCliente1);
        clienteDAO.insert(newCliente2);

        // Test findAll()
        ArrayList<Cliente> clientes = clienteDAO.findAll();
        for(Cliente c : clientes){
            System.out.println(c.toString());
        }
        // Assert that the inserted cliente is present in the list

        // Test findById()
        
        // Assert that retrievedCliente's data matches newCliente's

        // Test update()
        

        // Test deleteById()
        // (If applicable)

        // Close database connection (if needed)

        System.out.println("Tests completed successfully!");
    }
}
