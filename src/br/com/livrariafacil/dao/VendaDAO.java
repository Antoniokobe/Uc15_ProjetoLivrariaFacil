package br.com.livrariafacil.dao;

import br.com.livrariafacil.model.ItemDeVenda;
import br.com.livrariafacil.model.Venda;

import java.sql.*;
import java.util.List;

public class VendaDAO {

    public void salvarVenda(Venda venda, List<ItemDeVenda> itens) throws SQLException {
        String sqlVenda = "INSERT INTO vendas (cliente_id, valor_total) VALUES (?, ?)";
        String sqlItem = "INSERT INTO itens_venda (venda_id, livro_id, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";

        Connection conn = ConexaoDB.getConnection();
        conn.setAutoCommit(false); // Inicia transação

        try (PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS)) {

            stmtVenda.setInt(1, venda.getCliente().getId());
            stmtVenda.setDouble(2, venda.calcularTotal());
            stmtVenda.executeUpdate();

            // Obter o ID da venda recém-criada
            ResultSet rs = stmtVenda.getGeneratedKeys();
            int vendaId;
            if (rs.next()) {
                vendaId = rs.getInt(1);
            } else {
                throw new SQLException("Falha ao obter ID da venda.");
            }

            // Inserir cada item
            try (PreparedStatement stmtItem = conn.prepareStatement(sqlItem)) {
                for (ItemDeVenda item : itens) {
                    stmtItem.setInt(1, vendaId);
                    stmtItem.setInt(2, item.getLivro().getId());
                    stmtItem.setInt(3, item.getQuantidade());
                    stmtItem.setDouble(4, item.getLivro().getPrecoVenda());
                    stmtItem.setDouble(5, item.getSubtotal());
                    stmtItem.addBatch(); // Usar batch para melhor performance
                }
                stmtItem.executeBatch();
            }

            conn.commit(); // Confirma transação

        } catch (SQLException e) {
            conn.rollback(); // Desfaz tudo se falhar
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}