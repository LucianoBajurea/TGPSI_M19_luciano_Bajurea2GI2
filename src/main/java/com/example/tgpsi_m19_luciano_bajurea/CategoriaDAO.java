package com.example.tgpsi_m19_luciano_bajurea;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaDAO {
    public static ObservableList<Categoria> listCategoria() {
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<Categoria> categoria = Settings.getlistCategory();
        String sql = "SELECT * FROM categoria;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idCategoria = rs.getInt("idCategoria");
                String nome = rs.getString("nomeCategoria");
                String descCategoria = rs.getString("descricaoCategoria");
                Categoria c = new Categoria(idCategoria, nome, descCategoria);
                categoria.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar a Categoria: " + ex);
        } finally {
            ConexaoBD.closeDB(stmt, rs);
        }
        return categoria;
    }
}
