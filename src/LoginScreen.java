import java.util.Scanner;

public class LoginScreen {
    private String username = "oper";
    private String password = "1234";

    public boolean realizarLogin(Scanner scanner) {
        int tentativas = 3;

        while (tentativas > 0) {
            System.out.println("Por favor, faça o login.");
            System.out.print("Nome de usuário: ");
            String inputUsuario = scanner.nextLine();

            System.out.print("Senha: ");
            String inputSenha = scanner.nextLine();

            if (inputUsuario.equals(username) && inputSenha.equals(password)) {
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

    public boolean isUsuarioAdmin() {
        return false;
    }
}
