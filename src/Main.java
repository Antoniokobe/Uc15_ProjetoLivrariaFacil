public class Main {
    public static void main(String[] args) {
        // Criando o controlador
        LivrariaController sistema = new LivrariaController();

        // Cadastrando livros (protótipo)
        Livro l1 = new Livro(1, "O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia",
                30.0, 80.0, 10);
        Livro l2 = new Livro(2, "1984", "George Orwell", "Distopia",
                25.0, 60.0, 5);

        sistema.cadastrarLivro(l1);
        sistema.cadastrarLivro(l2);

        // Cadastrando cliente
        Cliente cliente1 = new Cliente(1, "Ana Silva", "123.456.789-00", "99999-8888", "ana@email.com");
        sistema.cadastrarCliente(cliente1);

        // Consultar estoque antes da venda
        sistema.consultarEstoque();

        // Realizar venda (protótipo da funcionalidade)
        System.out.println("\n--- Realizando venda ---");
        sistema.realizarVenda(1, 2, cliente1);

        // Consultar estoque após venda
        sistema.consultarEstoque();
    }
}