import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final Scanner scanner;
    private Token currentToken;
    private final List<String> commands;

    public Parser(String input) {
        this.scanner = new Scanner(input);
        this.commands = new ArrayList<>();
        this.currentToken = scanner.nextToken();
    }

    private void match(TokenType type) {
        if (currentToken.type == type) {
            currentToken = scanner.nextToken();
        } else {
            throw new RuntimeException("Erro sintático: esperado " + type + ", encontrado " + currentToken.type + " ('" + currentToken.lexeme + "')");
        }
    }

    private void emit(String command) {
        commands.add(command);
    }

    public List<String> parse() {
        statements();
        return commands;
    }

    // statements -> statement*
    private void statements() {
        while (currentToken.type != TokenType.EOF) {
            statement();
        }
    }

    // statement -> letStatement | printStatement
    private void statement() {
        if (currentToken.type == TokenType.LET) {
            letStatement();
        } else if (currentToken.type == TokenType.PRINT) {
            printStatement();
        } else {
            throw new RuntimeException("Erro sintático: comando inválido iniciando com '" + currentToken.lexeme + "'");
        }
    }

    // letStatement -> 'let' IDENT '=' expr ';'
    private void letStatement() {
        match(TokenType.LET);
        String id = currentToken.lexeme;
        match(TokenType.IDENT);
        match(TokenType.EQ);
        expr();
        emit("pop " + id);
        match(TokenType.SEMICOLON);
    }

    // printStatement -> 'print' expr ';'
    private void printStatement() {
        match(TokenType.PRINT);
        expr();
        emit("print");
        match(TokenType.SEMICOLON);
    }

    // expr -> term (('+' | '-') term)*
    private void expr() {
        term();
        while (currentToken.type == TokenType.PLUS || currentToken.type == TokenType.MINUS) {
            TokenType op = currentToken.type;
            match(op);
            term();
            if (op == TokenType.PLUS) {
                emit("add");
            } else {
                emit("sub");
            }
        }
    }

    // term -> factor (('*' | '/') factor)*
    private void term() {
        factor();
        while (currentToken.type == TokenType.STAR || currentToken.type == TokenType.SLASH) {
            TokenType op = currentToken.type;
            match(op);
            factor();
            if (op == TokenType.STAR) {
                emit("mul");
            } else {
                emit("div");
            }
        }
    }

    // factor -> NUMBER | IDENT
    private void factor() {
        if (currentToken.type == TokenType.NUMBER) {
            emit("push " + currentToken.lexeme);
            match(TokenType.NUMBER);
        } else if (currentToken.type == TokenType.IDENT) {
            emit("push " + currentToken.lexeme);
            match(TokenType.IDENT);
        } else {
            throw new RuntimeException("Erro sintático: esperado número ou identificador, encontrado '" + currentToken.lexeme + "'");
        }
    }
}
