import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Exemplo de teste conforme especificação do trabalho
        String sourceCode = "let a = 42 + 5 * 2; print a / 2 - 6;";

        System.out.println("=== Código Fonte de Entrada ===");
        System.out.println(sourceCode);
        System.out.println();

        // 1. O Parser (usando o Scanner) realiza a análise e traduz para pós-fixa
        System.out.println("=== Tradução para Notação Pós-fixa ===");
        Parser parser = new Parser(sourceCode);
        List<String> commands = parser.parse();

        for (String cmd : commands) {
            System.out.println(cmd);
        }
        System.out.println();

        // 2. O Interpretador executa as instruções pós-fixas
        System.out.println("=== Execução pelo Interpretador ===");
        Interpreter interpreter = new Interpreter();
        interpreter.execute(commands);
    }
}
