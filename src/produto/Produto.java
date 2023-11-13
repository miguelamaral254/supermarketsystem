package produto;


public class Produto {

    private String cod;
    private String nome;
    private double preco;
    private int quantidadeDisponivel;

    public Produto(String cod, String nome, double preco, int quantidadeDisponivel) {
        this.cod = cod;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void decrementarQuantidade() {
        quantidadeDisponivel--;
    }
}
