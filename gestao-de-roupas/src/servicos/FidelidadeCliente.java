package servicos;

import java.util.ArrayList;
import controle.ControleVenda;
import modelo.Venda;

public class FidelidadeCliente {
    private ControleVenda controleVenda;

    public FidelidadeCliente(ControleVenda controleVenda) {
        this.controleVenda = controleVenda;
    }

    public void fidelidade() {
        ArrayList<Venda> listaVendas = controleVenda.getListaVendas();

        for (Venda venda : listaVendas) {
            if (venda.getQuantidade() > 20) {
                venda.setTotal(venda.getTotal() - 30);
                System.out.println(venda.getCliente().getNome() + " ganhou um desconto de 30 reais!");
            } else {
                int faltando = 21 - venda.getQuantidade();
                System.out.println(venda.getCliente().getNome() + " Não tem programa de fidelidade. Faltam " + faltando + " compras para ganhar o bônus.");
            }
        }
    }
}
