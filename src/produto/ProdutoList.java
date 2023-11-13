package produto;
import produto.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoList {

    public static List<Produto> inicializarListaProdutos() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("001", "Arroz", 5.99, 50));
        produtos.add(new Produto("002", "Feijão", 3.49, 30));
        produtos.add(new Produto("003", "Azeite", 10.99, 20));
        produtos.add(new Produto("004", "Macarrão", 2.99, 40));
        produtos.add(new Produto("005", "Café", 8.49, 25));
        return produtos;
    }
}
