import java.util.Scanner;
import screens.AdminScreen;
import screens.LoginScreen;
import screens.OperScreen;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginScreen loginScreen = new LoginScreen();
        AdminScreen adminScreen = new AdminScreen();
        OperScreen operScreen = new OperScreen();

        if (loginScreen.realizarLogin(scanner)) {
            if (loginScreen.isUsuarioAdmin()) {
                adminScreen.exibirMenuAdministrador(scanner);
            } else {
                operScreen.exibirMenuOperador(scanner);
            }
        } else {
            System.out.println("Programa encerrado devido ao login malsucedido.");
        }
    }
}
