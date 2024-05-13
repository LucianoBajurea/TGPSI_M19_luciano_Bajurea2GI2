package com.example.tgpsi_m19_luciano_bajurea;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class FornecedorDAO {
    public static ObservableList<Fornecedor> listFornecedor() {
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<Fornecedor> fornecedor = Settings.getListForn();
        String sql = "SELECT * FROM fornecedor;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idFornecedor = rs.getInt("idFornecedor");
                String nome = rs.getString("nome");
                String nif = rs.getString("nif");
                String endereco = rs.getString("morada");
                String email = rs.getString("email");
                String numTel = rs.getString("telemovel");
                Fornecedor f = new Fornecedor(idFornecedor, nome, nif, email, endereco, numTel);
                fornecedor.add(f);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar os Fornecedores: " + ex);
        } finally {
            ConexaoBD.closeDB(stmt, rs);
        }
        return fornecedor;
    }
}
