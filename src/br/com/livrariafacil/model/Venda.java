package br.com.livrariafacil.model;

import br.com.livrariafacil.model.ItemDeVenda;
import br.com.livrariafacil.model.Cliente;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Venda {
    private int id;
    private Cliente cliente;
    private Date data;
    private List<ItemDeVenda> itens;

    public Venda(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.data = new Date();
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemDeVenda item) {
        itens.add(item);
    }

    public double calcularTotal() {
        return itens.stream()
                    .mapToDouble(ItemDeVenda::getSubtotal)
                    .sum();
    }

    // Getters
    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Date getData() { return data; }
    public List<ItemDeVenda> getItens() { return itens; }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", cliente=" + cliente.getNome() +
                ", total=R$ " + String.format("%.2f", calcularTotal()) +
                '}';
    }
}