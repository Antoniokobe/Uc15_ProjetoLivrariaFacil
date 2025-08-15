package br.com.livrariafacil.controller;

import br.com.livrariafacil.dao.ClienteDAO;
import br.com.livrariafacil.dao.LivroDAO;
import br.com.livrariafacil.dao.VendaDAO;
import br.com.livrariafacil.model.Cliente;
import br.com.livrariafacil.model.ItemDeVenda;
import br.com.livrariafacil.model.Venda;

import java.util.ArrayList;
import java.util.List;

public class LivrariaController {

    private LivroDAO livroDAO = new LivroDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private VendaDAO vendaDAO = new VendaDAO();

    // Carrinho temporário (não é uma classe, só uma lista)
    private List<ItemDeVenda> carrinho = new ArrayList<>();

 
    public void cadastrarLivro(br.com.livrariafacil.model.Livro livro) {
        livroDAO.salvar(livro);
    }

    public void cadastrarCliente(Cliente cliente) {
        clienteDAO.salvar(cliente);
    }

    public List<br.com.livrariafacil.model.Livro> getLivros() {
        return livroDAO.getAll();
    }

    public List<Cliente> getClientes() {
        return clienteDAO.getAll();
    }

    
    public void adicionarAoCarrinho(br.com.livrariafacil.model.Livro livro, int quantidade) {
        if (livro.getQuantidadeEstoque() >= quantidade) {
            carrinho.add(new ItemDeVenda(livro, quantidade));
        } else {
            throw new IllegalArgumentException("Estoque insuficiente para: " + livro.getTitulo());
        }
    }

    public List<ItemDeVenda> getCarrinho() {
        return new ArrayList<>(carrinho); 
    }

    public void limparCarrinho() {
        carrinho.clear();
    }

        public void finalizarVenda(Cliente cliente, List<ItemDeVenda> itens) {
        if (itens.isEmpty()) {
            throw new IllegalArgumentException("Carrinho vazio.");
        }

        //Validar estoque para todos os itens
        for (ItemDeVenda item : itens) {
            if (!livroDAO.temEstoqueSuficiente(item.getLivro().getId(), item.getQuantidade())) {
                throw new IllegalArgumentException(
                    "Estoque insuficiente para o livro: " + item.getLivro().getTitulo()
                );
            }
        }

        //Salvar venda e itens no banco
        try {
            vendaDAO.salvarVenda(new Venda(0, cliente), itens);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar venda no banco.", e);
        }

        //Atualizar estoque no banco
        for (ItemDeVenda item : itens) {
            int novoEstoque = item.getLivro().getQuantidadeEstoque() - item.getQuantidade();
            boolean sucesso = livroDAO.atualizarEstoque(item.getLivro().getId(), novoEstoque);
            if (!sucesso) {
                throw new RuntimeException("Falha ao atualizar estoque do livro: " + item.getLivro().getTitulo());
            }
        }

        //Limpar carrinho local
        carrinho.clear();
    }
}