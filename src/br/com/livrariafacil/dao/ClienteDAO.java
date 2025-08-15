  package br.com.livrariafacil.dao;

import br.com.livrariafacil.model.Cliente;
import br.com.livrariafacil.dao.ConexaoDB; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public List<Cliente> getAll() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao buscar clientes: " + e.getMessage());
        }
        return clientes;
    }

    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, cpf, telefone, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao salvar cliente: " + e.getMessage());
        }
    }
}           