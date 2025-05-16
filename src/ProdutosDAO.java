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
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;

    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public boolean salvarProduto(String nome, String valor, String status) {
        String url = "jdbc:mysql://localhost:3306/uc11?useSSL=false";
        String usuario = "root";
        String senha = "12345";

        try {
            Connection conn = DriverManager.getConnection(url, usuario, senha);
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setInt(2, Integer.parseInt(valor));
            stmt.setString(3, status);
            stmt.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        String url = "jdbc:mysql://localhost:3306/uc11?useSSL=false";
        String usuario = "root";
        String senha = "12345";

        ArrayList<ProdutosDTO> listagem = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(url, usuario, senha);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM produtos");

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nome = rs.getString("nome");
                Integer valor = rs.getInt("valor");
                String status = rs.getString("status");

                ProdutosDTO p = new ProdutosDTO(id, nome, valor, status);
                listagem.add(p);
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
            return null;
        }
        return listagem;
    }

    public boolean venderProduto(int id) {
        String url = "jdbc:mysql://localhost:3306/uc11?useSSL=false";
        String usuario = "root";
        String senha = "12345";

        try {
            Connection conn = DriverManager.getConnection(url, usuario, senha);
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            conn.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao vender produto: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        String url = "jdbc:mysql://localhost:3306/uc11?useSSL=false";
        String usuario = "root";
        String senha = "12345";

        ArrayList<ProdutosDTO> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(url, usuario, senha);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM produtos WHERE status = 'Vendido'");

            while (rs.next()) {
                Integer id = rs.getObject("id", Integer.class);
                String nome = rs.getString("nome");
                Integer valor = rs.getObject("valor", Integer.class);
                String status = rs.getString("status");

                ProdutosDTO p = new ProdutosDTO(id, nome, valor, status);
                lista.add(p);
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos vendidos: " + e.getMessage());
        }

        return lista;
    }
}
