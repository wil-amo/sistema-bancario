package main.java.V2.JDBC.Service;

import main.java.V1.POO.Exception.MovimentacaoInvalidaException;
import main.java.V2.JDBC.Config.interfaces.DbConnection;
import main.java.V2.JDBC.DAO.ClienteDAO;
import main.java.V2.JDBC.DAO.ContaDAO;
import main.java.V2.JDBC.Infra.exception.NegocioException;
import main.java.V2.JDBC.Model.Cliente;
import main.java.V2.JDBC.Model.Conta;
import main.java.V2.JDBC.Model.ContaCorrente;
import main.java.V2.JDBC.Model.ContaPoupanca;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceConta extends BaseService {
    private final DbConnection dbConnection;
    private final ClienteDAO clienteDAO;
    private final ContaDAO contaDAO;


    public ServiceConta(DbConnection dbConnection, ClienteDAO clienteDAO, ContaDAO contaDAO) {
        this.dbConnection = dbConnection;
        this.clienteDAO = clienteDAO;
        this.contaDAO = contaDAO;
    }

    //todo criar regras aqui de validações
    public void depositar(int numero, double valor) {
        Connection con = null;
        try {
            con = dbConnection.getConnection();
            con.setAutoCommit(false);

            Conta n = contaDAO.readContaPorNumero(con, numero);
            if (n == null) throw new IllegalArgumentException("Conta não encontrada.");
            if (!n.isAtivo()) throw new IllegalStateException("Conta inativa.");

            double saldoAntigo = n.getSaldo();// gravar no log+ ID/implementação futura, não agora
            n.deposito(valor);
            System.out.println(valor + ": DEPOSITADO");
            System.out.println(saldoAntigo + ": SALDO ANTIGO");

            int rows = contaDAO.depositar(con, n.getNumero(), valor);
            if (rows != 1) { //atualizou exatamente 1 conta (esperado).
                throw new IllegalStateException("Conta não encontrada ou inativa.");

            }

            con.commit();

            System.out.println("saldo atualizado de: " + saldoAntigo + " para: " + n.getSaldo());
            System.out.println("Mensagem para usuário final");

        } catch (MovimentacaoInvalidaException e) {
            // regra de negócio / validação: não precisa rollback se nada foi gravado,
            // MAS como começamos transação, é ok fazer rollback por segurança:
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ignored) {
                }
            }
            System.out.println(e.getMessage());
        } catch (Exception e) {
            encerrarConexaoComRollback(con);
            System.out.println("Operação cancelada: " + e.getMessage());
        } //caso conexão fique aberta por algum motivo
        finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar: " + e.getMessage());
            }
        }
    }



    //Captura toda exception, SQL + DEMAIS definidas
    public Conta sacar(int num, double valor) throws Exception {
        Connection con = null;
        try {
            con = dbConnection.getConnection();
            con.setAutoCommit(false);

            Conta conta = contaDAO.readContaPorNumero(con, num);
            if (conta == null) throw new IllegalArgumentException("Conta não encontrada.");
            if (!conta.isAtivo()) throw new IllegalStateException("Conta inativa.");
            double saldoAntigo = conta.getSaldo();

            System.out.println(valor + ": VALOR DE SAQUE");
            System.out.println(saldoAntigo + ": SALDO ANTIGO DA CONTA");
            conta.saque(valor); //altera em memória

            int rows = contaDAO.sacar(con, conta.getNumero(), valor);
            if (rows != 1) { //atualizou exatamente 1 conta (esperado).
                throw new IllegalStateException("Conta não encontrada ou inativa.");

            }

            con.commit();

            System.out.println("saldo atualizado de: " + saldoAntigo + " para: " + conta.getSaldo());
            System.out.println("Mensagem para usuário final");

            return conta;
        } catch (Exception e) {
            encerrarConexaoComRollback(con);
            //Implementar -> registrar no log e gravar no db
            throw e; // saida para interface
        }
        //encerra conexão também no sucesso
        finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar no finally: " + ex.getMessage());
            }
        }
    }


    public void criarConta(int clienteId, double saldo, String tipo) throws Exception {
        try (Connection c = dbConnection.getConnection()) {
            Cliente cliente = clienteDAO.readClientePorId(c, clienteId);

            if (cliente == null) {
                throw new Exception("Cliente não encontrado com ID: " + clienteId);
            }

            Conta conta;
            if ("Conta Corrente".equalsIgnoreCase(tipo)) {
                conta = new ContaCorrente(cliente);
            } else if ("Conta Poupança".equalsIgnoreCase(tipo)) {
                conta = new ContaPoupanca(cliente);
            } else {
                throw new IllegalArgumentException("Tipo de conta inválido: " + tipo);
            }

            if (saldo > 0) {
                conta.deposito(saldo);
            }else if (saldo < 0) {
                throw new NegocioException("O saldo inicial não pode ser negativo.");
            }

            contaDAO.criarConta(conta);
        }
    }

    public List<Conta> listarContaPorClienteId(int clienteId) throws Exception {
        //Implementar -> validar dados e con ser chamado pelo service
        return contaDAO.readContasPorClienteId(clienteId);
    }

    public List<Conta> listarContas() {
        //Implementar -> validar dados e con ser chamado pelo service
        return contaDAO.listarContas();
    }

    public Conta readContaPorNumeroService(int origem) throws SQLException {
        //Implementar -> saída na UI e validar
        Connection c = dbConnection.getConnection();
        return contaDAO.readContaPorNumero(c, origem);
    }


    public List<Conta> transferenciaService(Conta origem, Conta destino, double valor) throws Exception {
        //validações
        if (origem.getNumero() == destino.getNumero()) {
            throw new NegocioException("As contas de origem e destino devem ser diferentes.");
        }

        if (valor <= 0) {
            throw new NegocioException("O valor da transferência deve ser maior que zero.");
        }

        List<Conta> contas = new ArrayList<>();
        Connection con = null;

        try {
            con = dbConnection.getConnection();
            con.setAutoCommit(false);

            int rows1 = contaDAO.sacar(con, origem.getNumero(), valor);
            if (rows1 == 0) {
                throw new NegocioException("Falha no saque: Saldo insuficiente ou conta inexistente.");
            }


            int rows2 = contaDAO.depositar(con, destino.getNumero(), valor);
            if (rows2 == 0) {
                throw new NegocioException("Falha no depósito: Conta de destino não encontrada.");
            }

            con.commit();

            // Atualiza no JAVA p/ UI
            origem.setSaldo(origem.getSaldo() - valor);
            destino.setSaldo(destino.getSaldo() + valor);

            contas.add(origem);
            contas.add(destino);

            return contas;

        } catch (Exception e) {
            encerrarConexaoComRollback(con);
            System.out.println("ERRO INTERNO | Transferência não ocorreu! \n ");
            throw e;//saida do throw para UI
        }
        }


}

