package br.com.livrariafacil.dao;

import br.com.livrariafacil.dao.ConexaoDB;
import br.com.livrariafacil.model.Livro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    public List<Livro> getAll() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livro livro = new Livro(
                    rs.getInt("id"),
                    rs.getString("isbn"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("categoria"),
                    rs.getDouble("preco_custo"),
                    rs.getDouble("preco_venda"),
                    rs.getInt("quantidade_estoque")
                );
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public void salvar(Livro livro) {
        String sql = "INSERT INTO livros (isbn, titulo, autor, categoria, preco_custo, preco_venda, quantidade_estoque) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getIsbn());
            stmt.setString(2, livro.getTitulo());
            stmt.setString(3, livro.getAutor());
            stmt.setString(4, livro.getCategoria());
            stmt.setDouble(5, livro.getPrecoCusto());
            stmt.setDouble(6, livro.getPrecoVenda());
            stmt.setInt(7, livro.getQuantidadeEstoque());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
       
    public boolean atualizarEstoque(int livroId, int novaQuantidade) {
    String sql = "UPDATE livros SET quantidade_estoque = ? WHERE id = ? AND quantidade_estoque >= 0";
    
    try (Connection conn = ConexaoDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, novaQuantidade);
        stmt.setInt(2, livroId);
        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
        }
    }
    public boolean temEstoqueSuficiente(int livroId, int quantidadeRequerida) {
    String sql = "SELECT quantidade_estoque FROM livros WHERE id = ?";
    
    try (Connection conn = ConexaoDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, livroId);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            return rs.getInt("quantidade_estoque") >= quantidadeRequerida;
        }
        return false;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
    }
    
}
