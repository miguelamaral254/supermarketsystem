import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginScreen loginScreen = new LoginScreen();
        AdminScreen adminScreen = new AdminScreen();

        if (loginScreen.realizarLogin(scanner)) {
            if (loginScreen.isUsuarioAdmin()) {
                adminScreen.exibirMenuAdministrador(scanner);
            } else {
                List<Item> carrinhoDeCompras = new ArrayList<>();
            int proximoNumero = 1;
            boolean pagamentoConfirmado = false;
            float totalAPagar = 0;

            while (!pagamentoConfirmado) {
                System.out.println("----------------------------------------------");
                System.out.println("1 - Adicionar um item");
                System.out.println("2 - Remover um item");
                System.out.println("3 - Alterar um item");
                System.out.println("4 - Visualizar seu carrinho de compras");
                System.out.println("5 - Visualizar total a pagar");
                System.out.println("6 - Aplicar desconto");
                System.out.println("7 - Pagar");
                System.out.println("8 - Sair");
                System.out.println("----------------------------------------------");

                try {
                    int escolha = scanner.nextInt();
                    switch (escolha) {
                        case 1:
                            System.out.print("Nome do item: ");
                            scanner.nextLine();
                            String nome = scanner.nextLine();

                            if (nome.isEmpty()) {
                                System.out.println("Por favor, atribua um nome válido.");
                            } else if (itemExiste(carrinhoDeCompras, nome)) {
                                System.out.println("Item já adicionado na lista.");
                            } else {
                                System.out.print("Preço do item: ");
                                float preco;

                                if (scanner.hasNextFloat()) {
                                    preco = scanner.nextFloat();
                                    Item item = new Item(nome, preco);
                                    carrinhoDeCompras.add(item);
                                    System.out.println(nome + " foi adicionado ao carrinho de compras!");
                                    totalAPagar += preco;
                                } else {
                                    System.out.println(
                                            "Valor inválido. Certifique-se de inserir um número válido como preço.");
                                    scanner.nextLine();
                                }
                            }
                            break;

                        case 2:
                            if (carrinhoDeCompras.isEmpty()) {
                                System.out.println("Não há itens no carrinho.");
                            } else {
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
                                    Item itemRemovido = carrinhoDeCompras.remove(itemToRemove - 1);
                                    System.out.println("Item removido com sucesso!");
                                    totalAPagar -= itemRemovido.getPreco();
                                } else {
                                    System.out.println("Número de item inválido.");
                                }
                            }
                            break;

                        case 3:
                            if (carrinhoDeCompras.isEmpty()) {
                                System.out.println("Não há itens no carrinho.");
                            } else {
                                System.out.println("Itens no carrinho:");
                                for (int i = 0; i < carrinhoDeCompras.size(); i++) {
                                    Item currentItem = carrinhoDeCompras.get(i);
                                    int itemID = i + 1;
                                    System.out.println(
                                            itemID + ": " + currentItem.getNome() + " - Preço: "
                                                    + currentItem.getPreco());
                                }

                                System.out.print("Qual item você deseja alterar (pela ordem na lista)? ");
                                int itemToAlter = scanner.nextInt();
                                if (itemToAlter >= 1 && itemToAlter <= carrinhoDeCompras.size()) {
                                    Item itemExistente = carrinhoDeCompras.get(itemToAlter - 1);
                                    System.out.println("Item " + itemExistente.getNome() + " selecionado!");
                                    System.out.println("Escolha uma opção:");
                                    System.out.println("1 - Alterar o nome");
                                    System.out.println("2 - Alterar o preço");
                                    int escolhaAlteracao = scanner.nextInt();

                                    if (escolhaAlteracao == 1) {
                                        System.out.print("Novo nome: ");
                                        String novoNome = scanner.next();

                                        if (novoNome.isEmpty()) {
                                            System.out.println("Por favor, atribua um nome válido.");
                                        } else {
                                            itemExistente.setNome(novoNome);
                                            System.out.println(
                                                    "O nome do item foi alterado para " + novoNome + " com sucesso!");
                                        }
                                    } else if (escolhaAlteracao == 2) {
                                        System.out.print("Novo preço: ");
                                        String novoPrecoStr = scanner.next();

                                        if (novoPrecoStr.matches("^\\d*\\.?\\d+$")) {
                                            try {
                                                float novoPreco = Float.parseFloat(novoPrecoStr);
                                                totalAPagar -= itemExistente.getPreco();
                                                itemExistente.setPreco(novoPreco);
                                                totalAPagar += novoPreco;
                                                System.out.println(
                                                        "O preço do item foi alterado para " + novoPreco
                                                                + " com sucesso!");
                                            } catch (NumberFormatException e) {
                                                System.out.println(
                                                        "Valor inválido. Certifique-se de inserir um número válido como preço.");
                                            }
                                        } else {
                                            System.out.println(
                                                    "Valor inválido. Certifique-se de inserir um número válido como preço.");
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
                                System.out.println("Carrinho de Compras:");
                                for (int i = 0; i < carrinhoDeCompras.size(); i++) {
                                    Item currentItem = carrinhoDeCompras.get(i);
                                    int itemID = i + 1;
                                    System.out.println(
                                            itemID + ": " + currentItem.getNome() + " - Preço: "
                                                    + currentItem.getPreco());
                                }
                            }
                            break;

                        case 5:
                            if (carrinhoDeCompras.isEmpty()) {
                                System.out.println("Ainda não há itens no carrinho.");
                            } else {
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
                                    float valorDesconto = (desconto / 100) * totalAPagar;
                                    totalAPagar -= valorDesconto;
                                    System.out.println("Desconto aplicado: " + desconto + "%");
                                    System.out.println("Total a Pagar com Desconto: " + totalAPagar);
                                } else {
                                    System.out.println("O desconto deve ser um valor entre 0 e 100.");
                                }
                            }
                            break;

                        case 7:
                            if (carrinhoDeCompras.isEmpty()) {
                                System.out.println("Ainda não há itens no carrinho.");
                            } else {
                                float totalComDesconto = totalAPagar;
                                float valorDesconto = 0;

                                if (totalAPagar < calcularTotalAPagar(carrinhoDeCompras)) {
                                    valorDesconto = (calcularTotalAPagar(carrinhoDeCompras) - totalAPagar);
                                    totalComDesconto = totalAPagar;
                                }

                                System.out.println("Total a Pagar: " + totalAPagar);

                                System.out.println("Escolha uma opção de pagamento:");
                                System.out.println("1 - Dinheiro");
                                System.out.println("2 - Cartão");
                                System.out.println("3 - Pix");

                                int escolhaPagamento = scanner.nextInt();

                                switch (escolhaPagamento) {
                                    case 1:
                                        System.out.print("Digite o valor recebido: ");
                                        float valorRecebido = scanner.nextFloat();

                                        if (valorRecebido >= totalComDesconto) {
                                            float troco = valorRecebido - totalComDesconto;
                                            System.out.println("Troco: " + troco);
                                            System.out.println("Pagamento realizado com sucesso!");
                                        } else {
                                            System.out.println("Valor insuficiente. O pagamento não foi concluído.");
                                        }
                                        break;

                                    case 2:
                                        System.out.println("Escolha uma opção de cartão:");
                                        System.out.println("1 - Débito");
                                        System.out.println("2 - Crédito");

                                        int escolhaCartao = scanner.nextInt();

                                        if (escolhaCartao == 1) {
                                            System.out.println("Pagamento no valor de " + totalComDesconto
                                                    + " realizado com sucesso!.");
                                        } else if (escolhaCartao == 2) {
                                            System.out.print("Digite o número de parcelas desejadas: ");
                                            int parcelas = scanner.nextInt();

                                            if (parcelas >= 1) {
                                                float valorParcela = totalComDesconto / parcelas;
                                                System.out.println(
                                                        "Pagamento em " + parcelas + "x de R$ " + valorParcela);
                                                System.out.println("1 - Confirmar valor");
                                                System.out.println("2 - Retornar para opções de pagamento");
                                                int escolhaConfirmacao = scanner.nextInt();

                                                if (escolhaConfirmacao == 1) {
                                                    System.out.println("Pagamento realizado com sucesso!.");
                                                } else if (escolhaConfirmacao == 2) {
                                                    System.out.println("Retornando para opções de pagamento.");
                                                } else {
                                                    System.out.println("Opção inválida. Pagamento não confirmado.");
                                                }
                                            } else {
                                                System.out.println("Número de parcelas inválido.");
                                            }
                                        } else {
                                            System.out.println("Opção de cartão inválida.");
                                        }
                                        break;

                                    case 3:
                                        System.out.println("Escolha uma opção de Pix:");
                                        System.out.println("1 - Pagamento por QR CODE");
                                        System.out.println("2 - Pagamento por chave Pix");

                                        int escolhaPix = scanner.nextInt();

                                        if (escolhaPix == 1) {
                                            System.out.println(
                                                    "Aproxime seu dispositivo do QR CODE para efetuar o pagamento.");
                                        } else if (escolhaPix == 2) {
                                            System.out.println("Escolha uma opção de chave Pix:");
                                            System.out.println("1 - CNPJ 03.485.324/0001-55");
                                            System.out.println("2 - Número de telefone 81 3413-6728");

                                            int escolhaChavePix = scanner.nextInt();

                                            if (escolhaChavePix == 1) {
                                                System.out.println(
                                                        "Chave Pix CNPJ selecionada. Realize o pagamento pelo aplicativo do seu banco.");
                                            } else if (escolhaChavePix == 2) {
                                                System.out.println(
                                                        "Chave Pix número de telefone selecionada. Realize o pagamento pelo aplicativo do seu banco.");
                                            } else {
                                                System.out.println("Opção de chave Pix inválida.");
                                            }
                                        } else {
                                            System.out.println("Opção de Pix inválida.");
                                        }
                                        break;

                                    default:
                                        System.out.println("Opção de pagamento inválida.");
                                        break;
                                }

                                if (valorDesconto > 0) {
                                    System.out.println("Desconto aplicado: " + valorDesconto);
                                }

                                // Definir pagamentoConfirmado como true para sair do loop
                                pagamentoConfirmado = true;
                            }
                            break;

                        case 8:
                            pagamentoConfirmado = true; // Também sai do loop se a opção 8 for selecionada
                            break;

                        default:
                            System.out.println("Escolha uma opção válida");
                    }
                } catch (Exception e) {
                    System.out.println("Digite um valor válido");
                    scanner.nextLine();
                }
            }

            scanner.close();

            }
            

        } else {
            System.out.println("Programa encerrado devido ao login malsucedido.");
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

