package modelo;

public class Estoque {
    private int id;
    private Produto produto;
    private int quantidade;

    public Estoque(int id, Produto produto, int quantidade) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
}
