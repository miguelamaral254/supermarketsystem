package screens;
import java.util.Scanner;

public class AdminScreen {
    
    public void exibirMenuAdministrador(Scanner scanner) {
        while (true) {
            System.out.println("----------------------------------------------");
            System.out.println("Opções para o Administrador:");
            System.out.println("1 - Adicionar funcionário");
            System.out.println("2 - Editar funcionário");
            System.out.println("3 - Voltar ao menu principal");
            System.out.println("----------------------------------------------");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar a quebra de linha

            switch (escolha) {
                case 1:
                    // Lógica para adicionar funcionário
                    System.out.println("Opção para adicionar funcionário selecionada.");
                    break;
                case 2:
                    // Lógica para editar funcionário
                    System.out.println("Opção para editar funcionário selecionada.");
                    break;
                case 3:
                    return; // Retorna ao menu principal
                default:
                    System.out.println("Escolha uma opção válida.");
            }
        }
    }
}
