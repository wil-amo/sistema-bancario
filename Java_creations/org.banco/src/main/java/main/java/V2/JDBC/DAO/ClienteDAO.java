package main.java.V2.JDBC.DAO;

import main.java.V2.JDBC.Config.interfaces.DbConnection;
import main.java.V2.JDBC.Model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO {
    //Previne alteração de DB
    private final DbConnection dbConnection;

    public ClienteDAO(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void createCliente(Cliente c) {
        String insertSql = "INSERT INTO clientes (cpf, name, login, email, ativo) VALUES (?, ?, ?, ?,?)";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getCpf());
            ps.setString(2, c.getNome());
            ps.setString(3, c.getLogin());
            ps.setString(4, c.getEmail());
            ps.setBoolean(5, c.isAtivo());
            ps.executeUpdate();

            // Recupera o ID gerado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    c.setId(idGerado); // agora o objeto tem o id real
                    System.out.println("Cliente inserido com ID: " + idGerado);
                }
            }
                //Código vem do mySql e da pra tratar dessa forma
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                System.out.println("Erro: CPF já cadastrado no sistema.");
                // aqui você pode decidir: ignorar, atualizar, ou avisar o usuário
            } else {
                System.out.println("Erro SQL: " + ex.getMessage());
            }

        }
    }

    public Cliente readClienteporCPF(String cpf) {
        String sql = "SELECT id, name, login, email, cpf, ativo FROM clientes WHERE cpf = ?";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cpf);

            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()){ // move para a primeira linha
                Cliente c = new Cliente(
                    rs.getString("cpf"),
                    rs.getString("name"),
                    rs.getString("login"),
                    rs.getString("email"),
                    rs.getBoolean("ativo")
            );
                // id separado, já que não está no construtor
                c.setId(rs.getInt("id"));
                return c;
            }}
        } catch (SQLException ex) {
            System.out.println("Erro SQL: " + ex.getMessage() + " Código: " + ex.getErrorCode());
        }
        return null;
    }

    public void updateCliente(Cliente c){
        String updateSql = "UPDATE clientes SET name = ?, login = ?, email = ? , ativo = ? WHERE cpf = ?";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(updateSql)) {

            ps.setString(1, c.getNome());
            ps.setString(2, c.getLogin());
            ps.setString(3, c.getEmail());
            ps.setBoolean(4, c.isAtivo());
            ps.setString(5, c.getCpf());
            ps.executeUpdate();


            //Código vem do mySql e da para gravar dessa forma
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode()+ ex.getMessage());
            }
        }

        //soft delete implementado com trigger no DB
    public void deleteCliente(Cliente c, boolean ativo){
        String updateSql = "UPDATE clientes SET ativo = ? WHERE cpf = ?";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(updateSql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setBoolean(1, ativo);
            ps.setString(2, c.getCpf());
            ps.executeUpdate();


        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode()+ ex.getMessage());
        }
        System.out.println("O Cliente -  "+ c.getNome() + " , está desativado do banco.");
        //TODO retornar com a confirmação se cliente esta true or false
    }


    public Cliente readClientePorId(Connection con, int id) throws SQLException {
        String sql = "SELECT id, cpf, name, login, email, ativo FROM clientes WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                Cliente cliente = new Cliente(
                        rs.getString("cpf"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("email"),
                        rs.getBoolean("ativo")
                );
                cliente.setId(rs.getInt("id"));
                return cliente;
            }
        }
    }

    public List<Cliente> readAllClientes() {
        String sql = "SELECT id, name, login, email, cpf, ativo FROM clientes";
        List<Cliente> clientes = new ArrayList<>();

        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente c = new main.java.V2.JDBC.Model.Cliente(
                        rs.getString("cpf"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("email"),
                        rs.getBoolean("ativo")
                );
                c.setId(rs.getInt("id"));
                clientes.add(c);
            }

        } catch (SQLException ex) {
            System.out.println("Erro SQL: " + ex.getMessage() + " Código: " + ex.getErrorCode());
        }

        return clientes;
    }

}
