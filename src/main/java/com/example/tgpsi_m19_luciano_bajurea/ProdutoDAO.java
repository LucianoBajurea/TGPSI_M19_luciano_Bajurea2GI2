package com.example.tgpsi_m19_luciano_bajurea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoDAO {

    public static void removeProduct(int idProduto) {
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt = null;

        String sql = "DELETE FROM contactos WHERE id = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            stmt.execute();
            System.out.println("Contacto eliminado com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao eliminar contacto: " + ex);
        } finally {
            ConexaoBD.closeDB(stmt);
        }
    }
}