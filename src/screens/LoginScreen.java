package screens;
import java.util.Scanner;

public class LoginScreen {
    private boolean userAdmin = false;
    private String usernameAdmin = "admin";
    private String passwordAdmin = "admin";
    private String username = "oper";
    private String password = "1234";

    public boolean isUsuarioAdmin() {
        return userAdmin;
    }

    public boolean realizarLogin(Scanner scanner) {
        int tentativas = 3;

        while (tentativas > 0) {
            System.out.println("----------------------------");
            System.out.println("Por favor, faça o login.");
            System.out.println("----------------------------");
            System.out.print("Nome de usuário: ");
            String inputUsuario = scanner.nextLine();

            System.out.print("Senha: ");
            String inputSenha = scanner.nextLine();
            System.out.println("----------------------------------------------");

            if (inputUsuario.equals(usernameAdmin) && inputSenha.equals(passwordAdmin)) {
                System.out.println("Login de administrador bem-sucedido. Seja bem-vindo!");
                userAdmin = true; 
                return true;
            } else if (inputUsuario.equals(username) && inputSenha.equals(password)) {
                System.out.println("Login bem-sucedido. Bem-vindo!");
                return true;
            } else {
                System.out.println("Login falhou. Tente novamente.");
                tentativas--;
                if (tentativas > 0) {
                    System.out.println("Tentativas restantes: " + tentativas);
                } else {
                    System.out.println("Número máximo de tentativas excedido. Saindo do programa.");
                    return false;
                }
            }
        }
        return false;
    }
}
