package br.com.livrariafacil.test;
import br.com.livrariafacil.model.ItemDeVenda;
import br.com.livrariafacil.model.Livro;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CarrinhoCalculoTest {
    private Livro livroA, livroB;

    @Before
    public void setUp() {
        livroA = new Livro(1, "978-1", "Java OOP", "Bloch", "TI", 20.00, 50.00, 10);
        livroB = new Livro(2, "978-2", "Clean Code", "Martin", "TI", 15.00, 39.90, 5);
    }

    private double calcularTotal(List<ItemDeVenda> itens) {
        if (itens == null || itens.isEmpty()) return 0.0;
        return itens.stream().mapToDouble(ItemDeVenda::getSubtotal).sum();
    }

    @Test
    public void testTotal_CarrinhoVazio() {
        assertEquals(0.0, calcularTotal(Collections.emptyList()), 0.01);
    }
    @Test
    public void testTotal_UmItem() {
        List<ItemDeVenda> c = new ArrayList<>();
        c.add(new ItemDeVenda(livroA, 2));
        assertEquals(100.00, calcularTotal(c), 0.01);
    }
    @Test
    public void testTotal_MultiplosItens() {
        List<ItemDeVenda> c = new ArrayList<>();
        c.add(new ItemDeVenda(livroA, 2));
        c.add(new ItemDeVenda(livroB, 1));
        assertEquals(139.90, calcularTotal(c), 0.01);
    }
    @Test
    public void testTotal_QuantidadeAlta() {
        List<ItemDeVenda> c = new ArrayList<>();
        c.add(new ItemDeVenda(livroB, 5));
        assertEquals(199.50, calcularTotal(c), 0.01);
    }
}
