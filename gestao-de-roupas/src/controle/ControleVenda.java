package controle;

import java.util.ArrayList;

import modelo.Cliente;
import modelo.Produto;
import modelo.Venda;


public class ControleVenda {
    private ArrayList<Venda> listaVendas = new ArrayList<>();
    private int add = 1;

    public void registrarVenda(Cliente cliente, Produto produto, int quantidade) {
        Venda venda = new Venda(add++, cliente, produto, quantidade);
        listaVendas.add(venda);
        System.out.println("Venda registrada com sucesso: ");
        System.out.println(venda);
    }


    public void listarVendas(){
        if (listaVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            System.out.println("========================================");
            System.out.println("               Vendas Registradas");
            System.out.println("========================================");
            for (Venda venda : listaVendas) {
                System.out.println(venda);
            }
        }
    }
    
    public void emitirRecibo(int id){
        for (Venda venda : listaVendas) {
            if (venda.getId() == id) {
                System.out.println("========================================");
                System.out.println("                 Recibo");
                System.out.println("========================================");
                System.out.println(venda);
                return;
            }
        }
        System.out.println("Venda não encontrada com ID: " + id);
    }

    public ArrayList<Venda> getListaVendas(){
        return listaVendas;
    }
}
