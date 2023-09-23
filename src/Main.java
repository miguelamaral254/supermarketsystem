import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Map<String, Item> carrinhoDeCompras = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----------------------------------------------");
            System.out.println("1 - adicionar um item");
            System.out.println("2 - remover um item");
            System.out.println("3 - alterar um item");
            System.out.println("4 - visualizar seu carrinho de compras");
            System.out.println("5 - visualizar total a pagar");
            System.out.println("6 - aplicar desconto");
            System.out.println("7 - sair");
            System.out.println("----------------------------------------------");
            try {
                int escolha = scanner.nextInt();
                switch (escolha) {
                    case 1:
                        try {
                            System.out.print("nome do item: ");
                            scanner.nextLine(); // Consume newline
                            String nome = scanner.nextLine();
                            System.out.print("preço do item: ");
                            float preco = scanner.nextFloat();
                            Item item = new Item(nome, preco);
                            carrinhoDeCompras.put(nome, item);
                            System.out.println(nome + " foi adicionado ao carrinho de compras!");
                        } catch (Exception e) {
                            System.out.println("Houve um erro e o item não foi adicionado.");
                        }
                        break;
                    case 2:
                        try {
                            System.out.print("nome do item que deseja remover: ");
                            scanner.nextLine(); // Consume newline
                            String nomeToRemove = scanner.nextLine();
                            carrinhoDeCompras.remove(nomeToRemove);
                            System.out.println("Item removido com sucesso!");
                        } catch (Exception e) {
                            System.out.println("Não foi possível remover o item pois ele não existe.");
                        }
                        break;
                    case 3:
                        try {
                            System.out.print("qual item você deseja alterar (pelo nome)? ");
                            scanner.nextLine(); // Consume newline
                            String nomeToAlter = scanner.nextLine();
                            if (carrinhoDeCompras.containsKey(nomeToAlter)) {
                                System.out.println("item " + nomeToAlter + " selecionado!");
                                System.out.print("você deseja mudar o nome ou o preço? ");
                                String escolhaAlteracao = scanner.nextLine();
                                if (escolhaAlteracao.equals("nome")) {
                                    System.out.print("novo nome: ");
                                    String novoNome = scanner.nextLine();
                                    carrinhoDeCompras.get(nomeToAlter).setNome(novoNome);
                                    System.out.println("o nome do item foi alterado para " + novoNome + " com sucesso!");
                                } else if (escolhaAlteracao.equals("preco")) {
                                    try {
                                        System.out.print("novo preço: ");
                                        float novoPreco = scanner.nextFloat();
                                        carrinhoDeCompras.get(nomeToAlter).setPreco(novoPreco);
                                        System.out.println("o preço do item foi alterado para " + novoPreco + " com sucesso!");
                                    } catch (Exception e) {
                                        System.out.println("Não foi possível alterar o preço do item");
                                    }
                                } else {
                                    System.out.println("Opção inválida. Nenhum dado foi alterado.");
                                }
                            } else {
                                System.out.println("Este item não existe");
                            }
                        } catch (Exception e) {
                            System.out.println("Digite um valor válido");
                        }
                        break;
                    case 4:
                        System.out.println("Carrinho de Compras:");
                        for (Map.Entry<String, Item> entry : carrinhoDeCompras.entrySet()) {
                            String nomeItem = entry.getKey();
                            Item item = entry.getValue();
                            System.out.println("Nome: " + nomeItem + " | Preço: " + item.getPreco());
                        }
                        break;
                    case 5:
                        float totalAPagar = calcularTotalAPagar(carrinhoDeCompras);
                        System.out.println("Total a Pagar: " + totalAPagar);
                        break;
                    case 6:
                        try {
                            System.out.print("Digite o valor do desconto: ");
                            float desconto = scanner.nextFloat();
                            float totalAntesDoDesconto = calcularTotalAPagar(carrinhoDeCompras);
                            float totalComDesconto = totalAntesDoDesconto - desconto;
                            if (totalComDesconto < 0) {
                                System.out.println("O desconto resultaria em um total negativo. O desconto não foi aplicado.");
                            } else {
                                System.out.println("Total com Desconto: " + totalComDesconto);
                            }
                        } catch (Exception e) {
                            System.out.println("Valor de desconto inválido.");
                        }
                        break;
                    case 7:
                        scanner.close();
                        return;
                    default:
                        System.out.println("Escolha uma opção válida");
                }
            } catch (Exception e) {
                System.out.println("Digite um valor válido");
                scanner.nextLine(); // Consume newline
            }
        }
    }

    private static float calcularTotalAPagar(Map<String, Item> carrinhoDeCompras) {
        float total = 0;
        for (Item item : carrinhoDeCompras.values()) {
            total += item.getPreco();
        }
        return total;
    }
}

class Item {
    private String nome;
    private float preco;

    public Item(String nome, float preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Item{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                '}';
    }
}