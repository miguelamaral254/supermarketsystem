package screens;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginScreen {
    private boolean userAdmin = false;
    private Map<String, UserInfo> userCredentials;

    public LoginScreen() {
        userCredentials = new HashMap<>();
        // Adicione os pares de nome de usuário, senha e nome real desejados
        userCredentials.put("admin", new UserInfo("admin", "admin", "Administrador"));
        userCredentials.put("oper", new UserInfo("oper", "1234", "Operador"));
        userCredentials.put("miguelamaral", new UserInfo("miguelamaral", "senhaMiguel", "Miguel Amaral"));
        userCredentials.put("joaog", new UserInfo("joaopedro", "senhaJoao", "João Pedro"));
        userCredentials.put("weslleysantana", new UserInfo("weslleysantana", "senhaWeslley", "Weslley Santana"));
    }

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

            if (userCredentials.containsKey(inputUsuario) && userCredentials.get(inputUsuario).getSenha().equals(inputSenha)) {
                UserInfo userInfo = userCredentials.get(inputUsuario);
                if (inputUsuario.equals("admin")) {
                    System.out.println("Login de administrador bem-sucedido. Seja bem-vindo!");
                    userAdmin = true;
                } else {
                    System.out.println("Login bem-sucedido. Seja bem-vindo " + userInfo.getNomeReal() + "!" );
                }
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

    private static class UserInfo {
        private String nomeUsuario;
        private String senha;
        private String nomeReal;

        public UserInfo(String nomeUsuario, String senha, String nomeReal) {
            this.nomeUsuario = nomeUsuario;
            this.senha = senha;
            this.nomeReal = nomeReal;
        }

        public String getNomeReal() {
            return nomeReal;
        }

        public String getSenha() {
            return senha;
        }
    }
}
