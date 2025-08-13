import java.util.ArrayList;
import java.util.List;

public class LivrariaController {
    private List<Livro> livros;
    private List<Cliente> clientes;
    private List<Venda> vendas;
    private List<ItemDeVenda> carrinho; // Carrinho temporário

    public LivrariaController() {
        this.livros = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.vendas = new ArrayList<>();
        this.carrinho = new ArrayList<>();
    }

    // Métodos de cadastro
    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    // Métodos para GUI
    public List<Livro> getLivros() { return new ArrayList<>(livros); }
    public List<Cliente> getClientes() { return new ArrayList<>(clientes); }
    public List<Venda> getVendas() { return new ArrayList<>(vendas); }

    // Carrinho de compras
    public void adicionarAoCarrinho(Livro livro, int quantidade) {
        if (livro.reduzirEstoque(quantidade)) {
            carrinho.add(new ItemDeVenda(livro, quantidade));
        } else {
            throw new IllegalArgumentException("Estoque insuficiente para: " + livro.getTitulo());
        }
    }

    public List<ItemDeVenda> getCarrinho() {
        return new ArrayList<>(carrinho);
    }

    public double getTotalCarrinho() {
        return carrinho.stream().mapToDouble(ItemDeVenda::getSubtotal).sum();
    }

    public void finalizarVenda(Cliente cliente) {
        if (carrinho.isEmpty()) return;

        Venda venda = new Venda(vendas.size() + 1, cliente);
        for (ItemDeVenda item : carrinho) {
            venda.adicionarItem(item);
        }
        vendas.add(venda);
        carrinho.clear();
    }

    public void limparCarrinho() {
        carrinho.clear();
    }

    // Consultar estoque
    public void consultarEstoque() {
        System.out.println("\n=== ESTOQUE ATUAL ===");
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }
}