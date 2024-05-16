package com.example.tgpsi_m19_luciano_bajurea;

import javafx.collections.ObservableList;

import java.sql.*;

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

    public static void removerClient(int idClient){
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt = null;

        String sql = "DELETE FROM cliente WHERE idCliente = ?;";

        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idClient);
            stmt.executeUpdate();
            System.out.println("Cliente eliminado com sucesso");
        }
        catch (SQLException ex) {
            System.out.println("Erro ao eliminar o Cliente: "+ex);
        }
        finally {
            ConexaoBD.closeDB(stmt);
        }
    }
    public static void editarContacto(Cliente c){
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt = null;

        String sql = "UPDATE cliente SET nome = ?, nif = ?, morada = ?, numTelemovel = ?, email = ? WHERE idCliente = ?;";

        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, c.getIdCliente());
            stmt.setString(2, c.getNome());;
            stmt.setString(3, c.getNif());
            stmt.setString(4, c.getMorada());
            stmt.setString(5, c.getNumTelemovel());
            stmt.setString(6, c.getEmail());
            stmt.execute();
            System.out.println("Contacto atualizado com sucesso");
        }
        catch(SQLException ex){
            System.out.println("Erro ao atualizar contacto: "+ex);
        }
        finally {
            ConexaoBD.closeDB(stmt);
        }
    }
}
