package com.example.tgpsi_m19_luciano_bajurea;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {
    public static ObservableList<Cliente> listClient() {
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<Cliente> cliente = Settings.getListClient();
        String sql = "SELECT * FROM cliente;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String nome = rs.getString("nome");
                String nif = rs.getString("nif");
                String endereco = rs.getString("endereco");
                String email = rs.getString("email");
                String numTel = rs.getString("telemovel");
                Cliente c = new Cliente(idCliente, nome, nif, email, endereco, numTel);
                cliente.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar os Fornecedores: " + ex);
        } finally {
            ConexaoBD.closeDB(stmt, rs);
        }
        return cliente;
    }
}
