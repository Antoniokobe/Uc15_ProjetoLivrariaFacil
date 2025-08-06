import java.util.ArrayList;
import java.util.List;

public class LivrariaController {
    private List<Livro> livros;
    private List<Cliente> clientes;
    private List<Venda> vendas;

    public LivrariaController() {
        this.livros = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.vendas = new ArrayList<>();
    }

    // Simulação de cadastro de livro
    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
        System.out.println("Livro cadastrado: " + livro.getTitulo());
    }

    // Simulação de cadastro de cliente
    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente cadastrado: " + cliente.getNome());
    }

    // Simulação de venda
    public boolean realizarVenda(int idLivro, int quantidade, Cliente cliente) {
        for (Livro livro : livros) {
            if (livro.getId() == idLivro) {
                if (livro.getQuantidadeEstoque() >= quantidade) {
                    double total = livro.getPrecoVenda() * quantidade;
                    Venda venda = new Venda(vendas.size() + 1, cliente, total);
                    vendas.add(venda);
                    livro.setQuantidadeEstoque(livro.getQuantidadeEstoque() - quantidade);
                    System.out.println("Venda realizada com sucesso!");
                    System.out.println(venda);
                    return true;
                } else {
                    System.out.println("Estoque insuficiente para o livro: " + livro.getTitulo());
                    return false;
                }
            }
        }
        System.out.println("Livro não encontrado.");
        return false;
    }

    // Consultar estoque
    public void consultarEstoque() {
        System.out.println("\n=== ESTOQUE ATUAL ===");
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }
}
