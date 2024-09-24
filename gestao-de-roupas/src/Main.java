import java.util.Scanner;

import controle.ControleCliente;
import controle.ControleEstoque;
import controle.ControleProduto;
import controle.ControleVenda;
import modelo.Cliente;
import modelo.Produto;
import utils.RegistrarVenda;

public class Main {
    public static void main(String[] args) {
        RegistrarVenda registrarVenda = new RegistrarVenda();
        Scanner read = new Scanner(System.in);
        ControleProduto controleProduto = new ControleProduto();
        ControleEstoque controleEstoque = new controle.ControleEstoque(controleProduto);
        ControleVenda controleVenda = new ControleVenda();
        ControleCliente controleCliente = new ControleCliente();

        int opcao = 0;
        do{
            System.out.println("========================================");
            System.out.println("       Gerencimento do Sistema | Roupas ");
            System.out.println("========================================");
            System.out.println("1- Cadastrar Cliente");
            System.out.println("2- Cadastrar Produto");
            System.out.println("3- Visualizar Estoque");
            System.out.println("4- Registrar Venda");
            System.out.println("5- Listar Vendas");
            System.out.println("6- Emitir Recibo");
            opcao = read.nextInt();
            read.nextLine();
            switch(opcao){
                case 1 -> controleCliente.cadastrarCliente();
                case 2 -> controleProduto.cadastrarProduto();
                case 3 -> controleEstoque.listarEstoque();
                case 4 -> registrarVenda.buscarInformacoesVenda();
                case 5 -> controleVenda.listarVendas();
                case 6 -> {
                    System.out.println("Informe o id da compra");
                    int id = read.nextInt();
                    controleVenda.emitirRecibo(id);
                }
                default -> System.out.println("Saindo...");
            }
            


        




        
        }while(true);
    }
}