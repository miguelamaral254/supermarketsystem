
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Item> carrinhoDeCompras = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int proximoNumero = 1; // Variável para controlar o próximo número a ser exibido

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
                        scanner.nextLine(); // Limpar a linha anterior
                        String nome = scanner.nextLine();

                        if (nome.isEmpty()) {
                            System.out.println("Por favor, atribua um nome válido.");
                        } else if (itemExiste(carrinhoDeCompras, nome)) {
                            System.out.println("Item já adicionado na lista.");
                        } else {
                            System.out.print("Preço do item: ");
                            float preco = scanner.nextFloat();
                            Item item = new Item(nome, preco);
                            carrinhoDeCompras.add(item);
                            System.out.println(nome + " foi adicionado ao carrinho de compras!");
                        }

                        break;
                    case 2:
                        if (carrinhoDeCompras.isEmpty()) {
                            System.out.println("Não há itens no carrinho.");
                        } else {
                            // Mostrar a lista de itens
                            System.out.println("Itens no carrinho:");
                            for (int i = 0; i < carrinhoDeCompras.size(); i++) {
                                Item currentItem = carrinhoDeCompras.get(i);
                                System.out.println(proximoNumero + ": " + currentItem.getNome() + " - Preço: "
                                        + currentItem.getPreco());
                                proximoNumero++;
                            }

                            System.out.print("Qual item você deseja remover (pela ordem na lista)? ");
                            int itemToRemove = scanner.nextInt();
                            if (itemToRemove >= 1 && itemToRemove <= carrinhoDeCompras.size()) {
                                carrinhoDeCompras.remove(itemToRemove - 1);
                                System.out.println("Item removido com sucesso!");
                            } else {
                                System.out.println("Número de item inválido.");
                            }
                        }
                        break;
                    case 3:
                        if (carrinhoDeCompras.isEmpty()) {
                            System.out.println("Não há itens no carrinho.");
                        } else {
                            // Mostrar a lista de itens
                            System.out.println("Itens no carrinho:");
                            for (int i = 0; i < carrinhoDeCompras.size(); i++) {
                                Item currentItem = carrinhoDeCompras.get(i);
                                System.out.println(proximoNumero + ": " + currentItem.getNome() + " - Preço: "
                                        + currentItem.getPreco());
                                proximoNumero++;
                            }

                            System.out.print("Qual item você deseja alterar (pela ordem na lista)? ");
                            int itemToAlter = scanner.nextInt();
                            if (itemToAlter >= 1 && itemToAlter <= carrinhoDeCompras.size()) {
                                Item itemExistente = carrinhoDeCompras.get(itemToAlter - 1);
                                System.out.println("Item " + itemExistente.getNome() + " selecionado!");
                                System.out.print("Você deseja mudar o nome ou o preço? ");
                                scanner.nextLine();
                                String escolhaAlteracao = scanner.nextLine();

                                if (escolhaAlteracao.equals("nome")) {
                                    System.out.print("Novo nome: ");
                                    String novoNome = scanner.nextLine();

                                    if (novoNome.isEmpty()) {
                                        System.out.println("Por favor, atribua um nome válido.");
                                    } else {
                                        itemExistente.setNome(novoNome);
                                        System.out.println(
                                                "O nome do item foi alterado para " + novoNome + " com sucesso!");
                                    }
                                } else if (escolhaAlteracao.equals("preço")) {
                                    try {
                                        System.out.print("Novo preço: ");
                                        float novoPreco = scanner.nextFloat();
                                        itemExistente.setPreco(novoPreco);
                                        System.out.println(
                                                "O preço do item foi alterado para " + novoPreco + " com sucesso!");
                                    } catch (Exception e) {
                                        System.out.println("Não foi possível alterar o preço do item");
                                    }
                                } else {
                                    System.out.println("Opção inválida. Nenhum dado foi alterado.");
                                }
                            } else {
                                System.out.println("Número de item inválido.");
                            }
                        }
                        break;
                    case 4:
                        if (carrinhoDeCompras.isEmpty()) {
                            System.out.println("Ainda não há itens no carrinho.");
                        } else {
                            // Mostrar a lista de itens
                            System.out.println("Carrinho de Compras:");
                            for (int i = 0; i < carrinhoDeCompras.size(); i++) {
                                Item currentItem = carrinhoDeCompras.get(i);
                                System.out.println(proximoNumero + ": " + currentItem.getNome() + " - Preço: "
                                        + currentItem.getPreco());
                                proximoNumero++;
                            }
                        }
                        break;
                    case 5:
                        if (carrinhoDeCompras.isEmpty()) {
                            System.out.println("Ainda não há itens no carrinho.");
                        } else {
                            float totalAPagar = calcularTotalAPagar(carrinhoDeCompras);
                            System.out.println("Total a Pagar: " + totalAPagar);
                        }
                        break;
                    case 6:
                        if (carrinhoDeCompras.isEmpty()) {
                            System.out.println("Ainda não há itens no carrinho.");
                        } else {
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

    private static boolean itemExiste(List<Item> carrinhoDeCompras, String nome) {
        for (Item item : carrinhoDeCompras) {
            if (item.getNome().equals(nome)) {
                return true;

            }
        }
        return false;
    }

    private static float calcularTotalAPagar(List<Item> carrinhoDeCompras) {
        float total = 0;
        for (Item item : carrinhoDeCompras) {
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
}
