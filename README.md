# Tradutor de Expressões Aritméticas (Trabalho 1 - Compiladores)

Este projeto consiste na implementação de um tradutor simples de expressões aritméticas para notação pós-fixa, desenvolvido como atividade prática da disciplina de Compiladores.

O programa recebe comandos e expressões aritméticas em notação infixa, traduz a estrutura para comandos em notação pós-fixa e executa o código resultante utilizando um interpretador baseado em pilha.

---

## 🧩 Estrutura do Projeto

O código está organizado em três componentes principais:

* **Scanner (`Scanner.java`, `Token.java`, `TokenType.java`):** Realiza a análise léxica. Converte o texto de entrada em tokens (números de múltiplos dígitos, identificadores, palavras reservadas `let` e `print`, e símbolos), ignorando espaços em branco.
* **Parser (`Parser.java`):** Realiza a análise sintática descendente recursiva. Consome os tokens gerados pelo Scanner e emite as instruções em notação pós-fixa (`push`, `pop`, `add`, `sub`, `mul`, `div`, `print`).
* **Interpretador (`Interpreter.java`):** Executa a sequência de instruções pós-fixadas gerada pelo Parser utilizando uma pilha para os operandos e um mapa para armazenamento de variáveis.
* **Main (`Main.java`):** Classe principal que integra os componentes e executa um programa de teste.

---

## ✨ Funcionalidades

- Suporte aos comandos de atribuição (`let`) e de saída (`print`).
- Reconhecimento de variáveis e números inteiros com múltiplos dígitos.
- Suporte aos operadores aritméticos de adição (`+`), subtração (`-`), multiplicação (`*`) e divisão (`/`), respeitando a precedência de operadores.

---

## 🛠️ Como Compilar e Executar

```
1. **Abra o terminal** na pasta onde estão salvos os arquivos `.java` do projeto.

2. **Compile todos os arquivos Java:**
   ```bash
   javac *.java

```

1. **Execute o programa principal:**

```
java Main

```

```


---

```
