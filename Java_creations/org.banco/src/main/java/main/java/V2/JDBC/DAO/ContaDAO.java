package main.java.V2.JDBC.DAO;

import main.java.V2.JDBC.Config.interfaces.DbConnection;
import main.java.V2.JDBC.Model.Cliente;
import main.java.V2.JDBC.Model.Conta;
import main.java.V2.JDBC.Model.ContaCorrente;
import main.java.V2.JDBC.Model.ContaPoupanca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ContaDAO {
    private final DbConnection dbConnection;
    private final ClienteDAO clienteDAO;

    public ContaDAO(DbConnection dbConnection, ClienteDAO clienteDAO) {
        this.dbConnection = dbConnection;
        this.clienteDAO= clienteDAO;
    }

    public void criarConta(Conta c) {
        String sql = "INSERT INTO conta (saldo, tipo, clientes_id, ativo) VALUES (?, ?, ?, ?)";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, c.getSaldo());
            ps.setString(2, c instanceof ContaCorrente ? "CC" : "CP");
            ps.setInt(3, c.getCliente().getId());
            ps.setBoolean(4, c.isAtivo());
            ps.executeUpdate();

        } catch (SQLException ex) {// utilizar como default
            System.out.println("Erro SQL [" + ex.getErrorCode() + " | " + ex.getSQLState() + "]: " + ex.getMessage());
        }
    }



public int depositar(Connection con, int numero, double valor) throws SQLException {
    String sql = "UPDATE conta SET saldo = saldo + ? WHERE numero = ? AND ativo = true AND saldo >= ?";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setDouble(1, valor);
        ps.setInt(2, numero);
        ps.setDouble(3, valor);//retorno 0, deposito menor do que saldo
        return ps.executeUpdate();
    }
}

    public int sacar(Connection con, int numero, double valor) throws SQLException {
        String sql = "UPDATE conta SET saldo = saldo - ? WHERE numero = ? AND ativo = true AND saldo >= ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, valor);
            ps.setInt(2, numero);
            ps.setDouble(3, valor);//retorno 0, saldo menor do que saque
            return ps.executeUpdate();
        }
    }

    public List<Conta> readContasPorClienteId(int clienteId) throws SQLException {
        String sql = "SELECT conta.*, clientes.name, clientes.cpf, clientes.login, clientes.email, clientes.ativo " +
                "FROM conta INNER JOIN clientes ON conta.clientes_id = clientes.id " +
                "WHERE clientes.id = ?";

        List<Conta> contas = new ArrayList<>();

        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, clienteId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    Cliente cliente = new Cliente();
                    cliente.setId(clienteId);
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setLogin(rs.getString("login"));
                    cliente.setNome(rs.getString("name"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setAtivo(rs.getBoolean("ativo"));

                    // O construtor aceita o cliente pois ele não é nulo e tem CPF/Login
                    Conta conta = "CC".equals(rs.getString("tipo"))
                            ? new ContaCorrente(cliente)
                            : new ContaPoupanca(cliente);

                    conta.setId(rs.getInt("id"));
                    conta.setNumero(rs.getInt("numero"));
                    conta.setSaldo(rs.getDouble("saldo"));

                    contas.add(conta);
                }
            }
        } catch (SQLException ex) {
            // Log de erro para facilitar o debug
            System.err.println("Erro ao ler contas: " + ex.getMessage());
        }
        return contas;
    }

    public Conta readContaPorNumero(Connection con, int numero) throws SQLException {
        String sql = "SELECT id, numero, saldo, tipo, clientes_id, ativo FROM conta WHERE numero = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, numero);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                int clienteId = rs.getInt("clientes_id");

                    Cliente cliente = clienteDAO.readClientePorId(con, clienteId);// Busca cliente vinculado

                    Conta conta = "CC".equalsIgnoreCase(rs.getString("tipo"))
                            ? new ContaCorrente(cliente)
                            : new ContaPoupanca(cliente);

                    conta.setId(rs.getInt("id"));
                    conta.setNumero(rs.getInt("numero"));
                    conta.setSaldo(rs.getDouble("saldo"));
                    conta.setAtivo(rs.getBoolean("ativo"));

                    return conta;
                }
            }
}


//todo cliente vem null
    public List<Conta> listarContas() {
        //alternativa: inner join para trazer com os clientes reais
        String sql = "SELECT id, numero, saldo, tipo, clientes_id, ativo FROM conta";
        List<Conta> contas = new ArrayList<>();

        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            //evitar cliente null (arquitetura de conta exige cliente)
            Cliente c = new Cliente("00000000000","teste", "teste.teste", "teste@gmail.com", true);

            while (rs.next()) {
                Conta conta = "CC".equalsIgnoreCase(rs.getString("tipo"))
                        ? new ContaCorrente(c)
                        : new ContaPoupanca(c);

                conta.setId(rs.getInt("id"));
                conta.setNumero(rs.getInt("numero"));
                conta.setSaldo(rs.getDouble("saldo"));
                conta.setAtivo(rs.getBoolean("ativo"));
                contas.add(conta);


            }

        } catch (SQLException ex) {
            System.out.println("Erro SQL: " + ex.getMessage() + " Código: " + ex.getErrorCode());
        }

        return contas;
    }
}



