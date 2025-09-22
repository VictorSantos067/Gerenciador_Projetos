import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Classe responsável por validar entradas do usuário.
 * Permite ler Strings, inteiros, datas, emails e CPF com validação.
 */

public class Validador {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Lê uma string não vazia.

    public static String pedirString(String mensagem) {
        String entrada;
        do {
            System.out.print(mensagem);
            entrada = scanner.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("Campo obrigatório! Tente novamente.");
            }
        } while (entrada.isEmpty());
        return entrada;
    }

    // Lê um número inteiro dentro de um intervalo [min, max] com validação.

    public static int pedirInteiro(String mensagem, int min, int max) {
        int valor = -1;
        do {
            try {
                System.out.print(mensagem);
                String line = scanner.nextLine().trim();
                valor = Integer.parseInt(line);
                if (valor < min || valor > max) {
                    System.out.println("Valor inválido! Digite um número entre " + min + " e " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
            }
        } while (valor < min || valor > max);
        return valor;
    }

    // Lê uma data no formato dd/MM/yyyy.

    public static LocalDate pedirData(String mensagem) {
        LocalDate data = null;
        do {
            try {
                System.out.print(mensagem);
                String entrada = scanner.nextLine().trim();
                data = LocalDate.parse(entrada, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Use o formato dd/MM/yyyy.");
            }
        } while (data == null);
        return data;
    }

    // Lê um e-mail válido (contendo @ e .)

    public static String pedirEmail(String mensagem) {
        String email;
        do {
            email = pedirString(mensagem);
            if (!email.contains("@") || !email.contains(".") || email.contains(" ")) {
                System.out.println("E-mail inválido! Digite novamente (ex: usuario@dominio.com).");
                email = null;
            }
        } while (email == null);
        return email;
    }

    // Lê um CPF válido (11 números, pontos e traços opcionais).
     
    public static String pedirCpf(String mensagem) {
        String cpf;
        do {
            cpf = pedirString(mensagem);
            String somenteDigitos = cpf.replaceAll("\\D", "");
            if (!somenteDigitos.matches("\\d{11}")) {
                System.out.println("CPF inválido! Digite exatamente 11 números (pode usar pontos/traço).");
                cpf = null;
            } else {
                cpf = somenteDigitos;
            }
        } while (cpf == null);
        return cpf;
    }
}
