package br.com.livrariafacil.test;



import br.com.livrariafacil.model.Livro;
import org.junit.Test;
import static org.junit.Assert.*;

public class LivroTest {

    private Livro criarLivro(double precoVenda, int estoque) {
        return new Livro(1, "978-0-13", "Effective Java", "Bloch",
                         "TI", 30.00, precoVenda, estoque);
    }

    @Test
    public void testPrecoVenda_Valido() {
        assertTrue(criarLivro(49.90, 10).getPrecoVenda() > 0);
    }

    @Test
    public void testPrecoVenda_Zero_EhInvalido() {
        assertFalse(criarLivro(0.0, 10).getPrecoVenda() > 0);
    }

    @Test
    public void testPrecoVenda_Negativo_EhInvalido() {
        assertFalse(criarLivro(-15.00, 10).getPrecoVenda() > 0);
    }

    @Test
    public void testEstoque_Suficiente() {
        assertTrue(criarLivro(49.90, 5).getQuantidadeEstoque() >= 3);
    }

    @Test
    public void testEstoque_Insuficiente() {
        assertFalse(criarLivro(49.90, 2).getQuantidadeEstoque() >= 5);
    }

    @Test
    public void testEstoque_Zerado_BloqueiaVenda() {
        assertTrue(criarLivro(49.90, 0).getQuantidadeEstoque() <= 0);
    }

    @Test
    public void testReduzirEstoque_Valido() {
        Livro livro = criarLivro(49.90, 10);
        assertTrue(livro.reduzirEstoque(3));
        assertEquals(7, livro.getQuantidadeEstoque());
    }

    @Test
    public void testReduzirEstoque_AlemDoDisponivel() {
        Livro livro = criarLivro(49.90, 2);
        assertFalse(livro.reduzirEstoque(5));
        assertEquals(2, livro.getQuantidadeEstoque());
    }
}

