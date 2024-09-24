package controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.Produto;
import modelo.Estoque;

public class ControleProduto {
    private Scanner read = new Scanner(System.in);
    private int add = 1; 
    private ArrayList<Produto> listaProdutos = new ArrayList<>();
    private ArrayList<Estoque> listaEstoque = new ArrayList<>(); 

    public void cadastrarProduto() {
        Produto produto = new Produto(); 
        produto.setId(add);

        System.out.println("========================================");
        System.out.println("            Cadastro de Produtos");
        System.out.println("========================================");

        System.out.println("Nome do Produto: ");
        String nome = read.nextLine();

        System.out.println("Descrição do Produto: ");
        String descricao = read.nextLine();

        System.out.println("Preço do Produto: ");
        double valor = read.nextDouble();
        read.nextLine(); 

        System.out.println("Quantidade do Produto: ");
        int quantidade = read.nextInt();
        read.nextLine(); 

        
        if (validarCampos(nome, descricao, valor)) {
            produto.setNome(nome);
            produto.setDescricao(descricao);
            produto.setValor(valor);
            listaProdutos.add(produto); 
            Estoque estoque = new Estoque(add, produto, quantidade);
            listaEstoque.add(estoque);
            add++;

            System.out.println("Produto cadastrado e adicionado ao estoque com sucesso!");
        } else {
            System.out.println("Erro: Todos os campos devem ser preenchidos corretamente.");
        }
    }

    public ArrayList<Estoque> getListaEstoque(){
        return listaEstoque;
    }

    public Produto getProtudoId(int id) {
        for (Produto produto : listaProdutos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null; 
    }
    


    private boolean validarCampos(String nome, String descricao, double valor) {
        if (nome.isEmpty() || descricao.isEmpty() || valor <= 0) {
            return false;
        }   
        return true;
    }
}
