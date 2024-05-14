package com.example.tgpsi_m19_luciano_bajurea;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {
    public static ObservableList<Cliente> listCliente() {
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
                String morada = rs.getString("morada");
                String email = rs.getString("email");
                String numTel = rs.getString("numTelemovel");
                Cliente c = new Cliente(idCliente, nome, nif, email, morada, numTel);
                cliente.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar o Cliente: " + ex);
        } finally {
            ConexaoBD.closeDB(stmt, rs);
        }
        return cliente;
    }
}
