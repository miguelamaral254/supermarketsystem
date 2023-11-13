package screens;

import item.Item;
import produto.Produto;
import produto.ProdutoList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OperScreen {

    private List<Produto> listaProdutos;
    private float totalAPagar;

    public OperScreen() {
        this.listaProdutos = ProdutoList.inicializarListaProdutos();

    }

    private void exibirListaProdutos() {
        System.out.println("Lista de Produtos Disponíveis:");
        for (Produto produto : listaProdutos) {
            System.out.println("Código: " + produto.getCod() +
                    ", Nome: " + produto.getNome() +
                    ", Preço: " + produto.getPreco() +
                    ", Quantidade Disponível: " + produto.getQuantidadeDisponivel());
        }
    }

    public void exibirMenuOperador(Scanner scanner) {
        List<Item> carrinhoDeCompras = new ArrayList<>();
        int proximoNumero = 0;
        boolean pagamentoConfirmado = false;

        this.totalAPagar = 0;

        while (!pagamentoConfirmado) {
            System.out.println("----------------------------------------------");
            System.out.println("1 - Adicionar um item ao carrinho");
            System.out.println("2 - Remover um item do carrinho");
            System.out.println("3 - Visualizar seu carrinho de compras");
            System.out.println("4 - Visualizar total a pagar");
            System.out.println("5 - Aplicar desconto");
            System.out.println("6 - Pagar");
            System.out.println("7 - Sair");
            System.out.println("----------------------------------------------");

            try {
                int escolha = scanner.nextInt();
                switch (escolha) {
                    case 1:
                        System.out.print("Digite o código do item desejado: ");
                        String codigoItem = scanner.next();
                        adicionarItemAoCarrinho(carrinhoDeCompras, codigoItem);
                        break;

                        case 2:
                        if (carrinhoDeCompras.isEmpty()) {
                            System.out.println("Não há itens no carrinho.");
                        } else {
                            System.out.println("Itens no carrinho:");
                            for (int i = 0; i < carrinhoDeCompras.size(); i++) {
                                Item currentItem = carrinhoDeCompras.get(i);
                                int itemID = i + 1;
                                System.out.println(itemID + ": " + currentItem.getNome() + " - Preço: "
                                        + currentItem.getPreco());
                            }
                    
                            System.out.print("Qual item você deseja remover (pelo número na lista)? ");
                            int itemToRemove = scanner.nextInt();
                    
                            if (itemToRemove >= 1 && itemToRemove <= carrinhoDeCompras.size()) {
                                Item itemRemovido = carrinhoDeCompras.remove(itemToRemove - 1);
                                System.out.println("Item removido com sucesso!");
                                totalAPagar -= itemRemovido.getPreco();
                            } else {
                                System.out.println("Número de item inválido ou fora de ordem.");
                            }
                        }
                        break;
                    

                    case 3:
                        if (carrinhoDeCompras.isEmpty()) {
                            System.out.println("Ainda não há itens no carrinho.");
                        } else {
                            System.out.println("Carrinho de Compras:");
                            for (int i = 0; i < carrinhoDeCompras.size(); i++) {
                                Item currentItem = carrinhoDeCompras.get(i);
                                int itemID = i + 1;
                                System.out.println(
                                        itemID + ": " + currentItem.getNome());
                            }
                        }
                        break;

                    case 4:
                        if (carrinhoDeCompras.isEmpty()) {
                            System.out.println("Ainda não há itens no carrinho.");
                        } else {
                            System.out.printf("Total a Pagar: R$ %.2f%n", totalAPagar);
                        }
                        break;

                    case 5:
                        if (carrinhoDeCompras.isEmpty()) {
                            System.out.println("Ainda não há itens no carrinho.");
                        } else {
                            System.out.print("Digite o valor do desconto: ");
                            float desconto = scanner.nextFloat();

                            if (desconto >= 0 && desconto <= 100) {
                                float valorDesconto = (desconto / 100) * totalAPagar;
                                totalAPagar -= valorDesconto;
                                System.out.printf("Desconto aplicado: %.2f%%", desconto);
                                System.out.printf(" - Total a Pagar com Desconto: R$ %.2f%n", totalAPagar);
                            } else {
                                System.out.println("O desconto deve ser um valor entre 0 e 100.");
                            }
                        }
                        break;

                    case 6:
                        if (carrinhoDeCompras.isEmpty()) {
                            System.out.println("Ainda não há itens no carrinho.");
                        } else {
                            float totalComDesconto = totalAPagar;
                            float valorDesconto = 0;

                            if (totalAPagar < calcularTotalAPagar(carrinhoDeCompras)) {
                                valorDesconto = calcularTotalAPagar(carrinhoDeCompras) - totalAPagar;
                                totalComDesconto = totalAPagar;
                            }

                            System.out.printf("Total a Pagar: R$ %.2f%n", totalAPagar);

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

                            // Sair do loop
                            pagamentoConfirmado = true;
                        }
                        break;

                    case 7:
                        pagamentoConfirmado = true;
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

    private float calcularTotalAPagar(List<Item> carrinhoDeCompras) {
        float total = 0;
        for (Item item : carrinhoDeCompras) {
            total += item.getPreco();
        }
        return total;
    }

    private void adicionarItemAoCarrinho(List<Item> carrinhoDeCompras, String codigoItem) {
        Produto produtoSelecionado = null;

        for (Produto produto : listaProdutos) {
            if (produto.getCod().equals(codigoItem)) {
                produtoSelecionado = produto;
                break;
            }
        }

        if (produtoSelecionado != null && produtoSelecionado.getQuantidadeDisponivel() > 0) {
            Item item = new Item(produtoSelecionado.getNome(), produtoSelecionado.getPreco());
            carrinhoDeCompras.add(item);
            produtoSelecionado.decrementarQuantidade();

            float novoTotalAPagar = 0;
            for (Item i : carrinhoDeCompras) {
                novoTotalAPagar += i.getPreco();
            }
            totalAPagar = novoTotalAPagar;

            System.out.println(item.getNome() + " foi adicionado ao carrinho de compras!");
        } else {
            System.out.println("Código de item inválido ou sem estoque disponível.");
        }
    }
}
