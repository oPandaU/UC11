/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public boolean salvarProduto(String nome, String valor) {
        String url = "jdbc:mysql://localhost:3306/uc11?useSSL=false";
        String usuario = "root";
        String senha = "12345";

        try {
            Connection conn = DriverManager.getConnection(url, usuario, senha);
            String sql = "INSERT INTO produtos (nome, valor) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setInt(2, Integer.parseInt(valor));
            stmt.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

}
