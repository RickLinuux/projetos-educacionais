package utils;

import modelo.Cliente;
import modelo.Produto;
import controle.ControleCliente;
import controle.ControleProduto;
import controle.ControleVenda;

import java.util.Scanner;

public class RegistrarVenda {

    public RegistrarVenda(){}

    public void buscarInformacoesVenda(){
        Scanner read = new Scanner(System.in);
        ControleProduto controleProduto = new ControleProduto();
        ControleVenda controleVenda = new ControleVenda();
        ControleCliente controleCliente = new ControleCliente();

        System.out.println("Informe o ID do produto: ");
        int id = read.nextInt();
        Produto produto = controleProduto.getProtudoId(id);
        read.nextLine(); 

        System.out.println("Informe seu nome: ");
        String nome = read.nextLine();
        Cliente cliente = controleCliente.getClienteNome(nome);

        System.out.println("Informe a quantidade da compra: ");
        int quantidade = read.nextInt();

        if (produto != null && cliente != null && quantidade > 0) {
            controleVenda.registrarVenda(cliente, produto, quantidade);
        } else {
            System.out.println("Preencha os campos corretamente");
        }
    }
}
