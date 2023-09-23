import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Map<String, Item> carrinhoDeCompras = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----------------------------------------------");
            System.out.println("1 - Adicionar um item");
            System.out.println("2 - Remover um item");
            System.out.println("3 - Alterar um item");
            System.out.println("4 - Visualizar seu carrinho de compras");
            System.out.println("5 - Visualizar total a pagar");
            System.out.println("6 - Aplicar desconto");
            System.out.println("7 - Sair");
            System.out.println("----------------------------------------------");
            try {
                int escolha = scanner.nextInt();
                switch (escolha) {
                    case 1:
                        System.out.print("Nome do item: ");
                        scanner.nextLine();
                        String nome = scanner.nextLine();
                        System.out.print("Preço do item: ");
                        float preco = scanner.nextFloat();
                        Item item = new Item(nome, preco);
                        carrinhoDeCompras.put(nome, item);
                        System.out.println(nome + " foi adicionado ao carrinho de compras!");
                        break;
                    case 2:
                        System.out.print("Nome do item que deseja remover: ");
                        scanner.nextLine();
                        String nomeToRemove = scanner.nextLine();
                        carrinhoDeCompras.remove(nomeToRemove);
                        System.out.println("Item removido com sucesso!");
                        break;
                    case 3:
                        if (carrinhoDeCompras.isEmpty()) {
                            System.out.println("Não há itens no carrinho.");
                        } else {
                            System.out.print("Qual item você deseja alterar (pelo nome)? ");
                            scanner.nextLine();
                            String nomeToAlter = scanner.nextLine();
                            if (carrinhoDeCompras.containsKey(nomeToAlter)) {
                                System.out.println("Item " + nomeToAlter + " selecionado!");
                                System.out.print("Você deseja mudar o nome ou o preço? ");
                                String escolhaAlteracao = scanner.nextLine();
                                if (escolhaAlteracao.equals("nome")) {
                                    System.out.print("Novo nome: ");
                                    String novoNome = scanner.nextLine();
                                    Item itemExistente = carrinhoDeCompras.get(nomeToAlter);
                                    itemExistente.setNome(novoNome);
                                    carrinhoDeCompras.remove(nomeToAlter); // Remova o item antigo
                                    carrinhoDeCompras.put(novoNome, itemExistente); // Adicione o item com o novo nome
                                    System.out.println("O nome do item foi alterado para " + novoNome + " com sucesso!");
                                } else if (escolhaAlteracao.equals("preco")) {
                                    try {
                                        System.out.print("Novo preço: ");
                                        float novoPreco = scanner.nextFloat();
                                        Item itemExistente = carrinhoDeCompras.get(nomeToAlter);
                                        itemExistente.setPreco(novoPreco);
                                        System.out.println("O preço do item foi alterado para " + novoPreco + " com sucesso!");
                                    } catch (Exception e) {
                                        System.out.println("Não foi possível alterar o preço do item");
                                    }
                                } else {
                                    System.out.println("Opção inválida. Nenhum dado foi alterado.");
                                }
                            } else {
                                System.out.println("Este item não existe");
                            }
                        }
                        break;
                    case 4:
                        if (carrinhoDeCompras.isEmpty()) {
                            System.out.println("Não há itens no carrinho.");
                        } else {
                            System.out.println("Carrinho de Compras:");
                            for (Map.Entry<String, Item> entry : carrinhoDeCompras.entrySet()) {
                                String nomeItem = entry.getKey();
                                Item itemExistente = entry.getValue();
                                System.out.println("Nome: " + nomeItem + " | Preço: " + itemExistente.getPreco());
                            }
                        }
                        break;
                    case 5:
                        if (carrinhoDeCompras.isEmpty()) {
                            System.out.println("Não há itens no carrinho.");
                        } else {
                            float totalAPagar = calcularTotalAPagar(carrinhoDeCompras);
                            System.out.println("Total a Pagar: " + totalAPagar);
                        }
                        break;
                    case 6:
                        System.out.print("Digite o valor do desconto: ");
                        float desconto = scanner.nextFloat();
                        if (desconto >= 0 && desconto <= 100) {
                            float totalAPagar = calcularTotalAPagar(carrinhoDeCompras);
                            float valorDesconto = (desconto / 100) * totalAPagar;
                            float totalComDesconto = totalAPagar - valorDesconto;
                            System.out.println("Desconto aplicado: " + desconto + "%");
                            System.out.println("Total a Pagar com Desconto: " + totalComDesconto);
                        } else {
                            System.out.println("O desconto deve ser um valor entre 0 e 100.");
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
                scanner.nextLine();
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
