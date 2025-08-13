public class ItemDeVenda {
    private Livro livro;
    private int quantidade;
    private double subtotal;

    public ItemDeVenda(Livro livro, int quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
        this.subtotal = livro.getPrecoVenda() * quantidade;
    }

    public Livro getLivro() { return livro; }
    public int getQuantidade() { return quantidade; }
    public double getSubtotal() { return subtotal; }

    @Override
    public String toString() {
        return livro.getTitulo() + " x " + quantidade + " = R$ " + String.format("%.2f", subtotal);
    }
}
