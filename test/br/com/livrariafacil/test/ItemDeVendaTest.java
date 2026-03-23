package br.com.livrariafacil.test;
import br.com.livrariafacil.model.ItemDeVenda;
import br.com.livrariafacil.model.Livro;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemDeVendaTest {
    private Livro criarLivro(double precoVenda) {
        return new Livro(1, "978-0-13", "Clean Code", "Martin", "TI", 20.00, precoVenda, 10);
    }
    @Test
    public void testSubtotal_Quantidade1() {
        assertEquals(59.90, new ItemDeVenda(criarLivro(59.90), 1).getSubtotal(), 0.01);
    }
    @Test
    public void testSubtotal_Quantidade3() {
        assertEquals(149.70, new ItemDeVenda(criarLivro(49.90), 3).getSubtotal(), 0.01);
    }
    @Test
    public void testSubtotal_Quantidade10() {
        assertEquals(299.00, new ItemDeVenda(criarLivro(29.90), 10).getSubtotal(), 0.01);
    }
    @Test
    public void testGetLivro_RetornaLivroCorreto() {
        assertEquals("Clean Code", new ItemDeVenda(criarLivro(39.90), 2).getLivro().getTitulo());
    }
    @Test
    public void testGetQuantidade_Correto() {
        assertEquals(4, new ItemDeVenda(criarLivro(39.90), 4).getQuantidade());
    }
}
