package br.com.livrariafacil.controller;

import br.com.livrariafacil.model.Venda;
import br.com.livrariafacil.model.Livro;
import br.com.livrariafacil.model.ItemDeVenda;
import br.com.livrariafacil.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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

    // Método modificado para aceitar Cliente e itens do carrinho
    public void finalizarVenda(Cliente cliente, List<ItemDeVenda> itensCarrinho) {
        if (itensCarrinho.isEmpty()) return;

        // Cria uma nova venda com ID incrementado
        int novoId = vendas.size() + 1;
        Venda venda = new Venda(novoId, cliente);

        // Adiciona cada item do carrinho à venda
        for (ItemDeVenda item : itensCarrinho) {
            venda.adicionarItem(item);
        }

        // Salva a venda no sistema
        vendas.add(venda);

        // Limpa o carrinho temporário
        carrinho.clear();

        // Exibe mensagem de sucesso
        JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
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