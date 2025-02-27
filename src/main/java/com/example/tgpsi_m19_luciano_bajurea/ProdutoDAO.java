package com.example.tgpsi_m19_luciano_bajurea;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO {
    public static ObservableList<Produto> listProduct() {
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<Produto> produto = Settings.getListProduct();
        String sql = "SELECT * FROM produto;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idProduto = rs.getInt("idProduto");
                String nomeProduto = rs.getString("nomeProduto");
                double preco = rs.getDouble("precoProduto");
                String descricao = rs.getString("categoriaProduto");
                Produto p = new Produto(idProduto, nomeProduto, preco, descricao);
                produto.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar o Produto: " + ex);
        } finally {
            ConexaoBD.closeDB(stmt, rs);
        }
        return produto;
    }

    public static void removerProduto(int idProduto){
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt = null;

        String sql = "DELETE FROM produto WHERE idProduto = ?;";

        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            stmt.executeUpdate();
            System.out.println("Produto eliminado com sucesso");
        }
        catch (SQLException ex) {
            System.out.println("Erro ao eliminar o Produto: "+ex);
        }
        finally {
            ConexaoBD.closeDB(stmt);
        }
    }
}