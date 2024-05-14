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
                String numTel = rs.getString("numTelemovel");
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

    public static void removerForn(int id){
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt = null;

        String sql = "DELETE FROM fornecedor WHERE idFornecedor = ?;";

        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("Fornecedor eliminado com sucesso");
        }
        catch (SQLException ex) {
            System.out.println("Erro ao eliminar o Fornecedor: "+ex);
        }
        finally {
            ConexaoBD.closeDB(stmt);
        }
    }
}
