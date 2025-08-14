public class Livro {
    private int id;
    private String isbn;
    private String titulo;
    private String autor;
    private String categoria;
    private double precoCusto;
    private double precoVenda;
    private int quantidadeEstoque;

    public Livro(int id, String isbn,String titulo, String autor, String categoria,
                 double precoCusto, double precoVenda, int quantidadeEstoque) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    

    // Getters e Setters
    
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getIsbn() {  return isbn; }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public double getPrecoCusto() { return precoCusto; }
    public void setPrecoCusto(double precoCusto) { this.precoCusto = precoCusto; }

    public double getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(double precoVenda) { this.precoVenda = precoVenda; }

    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }

    @Override
public String toString() {
    return titulo + " (" + autor + ")"; // Melhor para exibir em listas
}

// Método para reduzir estoque com segurança
public boolean reduzirEstoque(int quantidade) {
    if (quantidade <= quantidadeEstoque) {
        quantidadeEstoque -= quantidade;
        return true;
    }
    return false;
}
}

