package controle;

import java.util.ArrayList;

import modelo.Estoque;

public class ControleEstoque {
    private ControleProduto controleProduto;

    public ControleEstoque(ControleProduto controleProduto){
        this.controleProduto = controleProduto;
    }
    public void listarEstoque(){
        ArrayList<Estoque> listaEstoques = controleProduto.getListaEstoque();

        if(listaEstoques.isEmpty()){
            System.out.println("O estoque está vazio!");
        }else{
            System.out.println("========================================");
            System.out.println("               Estoque Atual");
            System.out.println("========================================");
            for (Estoque item : listaEstoques) {
                System.out.println("ID: " + item.getId());
                System.out.println("Nome: " + item.getProduto().getNome());
                System.out.println("Descrição: " + item.getProduto().getDescricao());
                System.out.println("Preço: R$" + item.getProduto().getValor());
                System.out.println("Quantidade: " + item.getQuantidade());
                System.out.println("----------------------------------------");
            }
        }

    }


    
}
