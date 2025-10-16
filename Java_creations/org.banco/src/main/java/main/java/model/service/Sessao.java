package main.java.model.service;

import main.java.model.Cliente;
import main.java.model.Conta;
import main.java.model.ContaCorrente;
import main.java.model.infra.EmailUtil;
import main.java.model.infra.HashUtil;
import main.java.model.infra.LoggerUtil;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Sessao {
    private Cliente clienteLogado;


    private int totalLogados;

    public void usuariosConectados(){
        System.out.println(totalLogados + " --- Usuários conectados ao BANCO");
    }
    public void registrarNovoCliente(Cliente cliente) {
        try (FileWriter fw = new FileWriter("Clientes-criados.txt", true)) {
            fw.write(String.format("%s;%s;%s;%s;%s;%s\n",
                    cliente.getCPF(),
                    cliente.getNome(),
                    cliente.getLogin(),
                    cliente.getSenhaHash(),
                    cliente.getCodigoVerificacao(),
                    cliente.getEmail()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Cliente " + cliente.getNome()+ " foi Registrado");
    }

    public boolean autenticar(String login, String senha) {
        try (BufferedReader br = new BufferedReader(new FileReader("Clientes-criados.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 4) { //Verifica pelo menos 4 campos
                    String loginArquivo = partes[2];
                    String senhaHashArquivo = partes[3];

                    if (loginArquivo.equals(login) && senhaHashArquivo.equals(HashUtil.gerarHash(senha))) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            LoggerUtil.logSevere("Erro ao autenticar " + login + senha, e);

        }
        return false;
    }

    public Cliente buscarClientePorLogin(String login) {
        try (BufferedReader br = new BufferedReader(new FileReader("Clientes-criados.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 6 && partes[2].equals(login)) {
                    Cliente cliente = new Cliente(partes[0], partes[1], partes[2], "", partes[5]);
                    cliente.setSenhaHash(partes[3]);
                    cliente.setCodigoVerificacao(partes[4]);
                    return cliente;
                }
            }
        } catch (IOException e) {
            LoggerUtil.logSevere("Erro ao buscar cliente por login '" + login + "'", e);
        }

        LoggerUtil.logInfo("Cliente com login '" + login + "' não encontrado em " + LocalDateTime.now());
        return null;
    }

    private void registrarLogin() {
        if (clienteLogado != null) {
            try (FileWriter fw = new FileWriter("Registro-logins.txt", true)) {
                fw.write("LOGIN: " + clienteLogado.getLogin() + " em " + LocalDateTime.now() + "\n");
            } catch (IOException e) {
                LoggerUtil.logSevere("Erro ao registrar login de " + clienteLogado.getLogin(), e);

            }
        }
    }

    private void abrirMenuBancario(Conta conta) {
        while (true) {
            String[] opcoes = {"Ver saldo", "Depositar", "Sacar", "AINDA EM TESTES ###Transferir#####", "Sair"};
            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma operação:",
                    "Menu Bancário",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            switch (escolha) {
                case 0:
                    JOptionPane.showMessageDialog(null,
                            String.format("Saldo atual: R$ %.2f", conta.getSaldo()));
                    break;

                case 1:
                    String valorDepositoStr = JOptionPane.showInputDialog("Digite o valor para depósito:");
                    if (valorDepositoStr == null || valorDepositoStr.trim().isEmpty()) break;
                    try {
                        double valorDeposito = Double.parseDouble(valorDepositoStr);
                        conta.deposito(valorDeposito);
                        JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso.");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, "fora do esperado" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case 2:
                    String valorSaqueStr = JOptionPane.showInputDialog("Digite o valor para saque:");
                    if (valorSaqueStr == null || valorSaqueStr.trim().isEmpty()) break;
                    try {
                        double valorSaque = Double.parseDouble(valorSaqueStr);
                        conta.saque(valorSaque);
                        JOptionPane.showMessageDialog(null, "Saque realizado com sucesso.");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        JOptionPane.showMessageDialog(null, "fora do esperado" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case 3: // Transferir
                    String valorTransfStr = JOptionPane.showInputDialog("Valor para transferir:");
                    String loginDestino = JOptionPane.showInputDialog("Login do destinatário:");
                    if (valorTransfStr == null || loginDestino == null) break;

                    try {
                        double valorTransf = Double.parseDouble(valorTransfStr);

                        //busca conta por login
                        Cliente destino = buscarClientePorLogin(loginDestino);

                        if (destino == null || destino.getConta() == null) {
                            JOptionPane.showMessageDialog(null, "Conta de destino inválida.");
                            break;
                        }

                        conta.transferencia(valorTransf, destino.getConta());
                        JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                    }
                    break;



                case 4:
                    JOptionPane.showMessageDialog(null, "Encerrando sessão...");
                    logout();
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public void loginClienteGUI(String login, String senha) {
        if (!autenticar(login, senha)) {
            JOptionPane.showMessageDialog(null, "Login ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = buscarClientePorLogin(login);
        if (cliente == null || !EmailUtil.emailValido(cliente.getEmail())) {
            JOptionPane.showMessageDialog(null, "E-mail inválido ou cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String codigo = gerarCodigoVerificacao();
        cliente.setCodigoVerificacao(HashUtil.gerarHash(codigo));
        EmailUtil.enviarCodigo(cliente.getEmail(), codigo);

        String codigoDigitado = JOptionPane.showInputDialog("Digite o código recebido por e-mail:");
        if (codigoDigitado == null || codigoDigitado.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código não informado. Login cancelado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!HashUtil.gerarHash(codigoDigitado).equals(cliente.getCodigoVerificacao())) {
            JOptionPane.showMessageDialog(null, "Código incorreto. Login não registrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        clienteLogado = cliente;
        registrarLogin();
        JOptionPane.showMessageDialog(null, "Login registrado com sucesso.");

        Conta conta = cliente.getConta();
        if (conta == null) {
            conta = new ContaCorrente(cliente);
            cliente.setConta(conta);
        }

        abrirMenuBancario(conta);
    }


    public void logout() {
        if (clienteLogado != null) {
            registrarLogout(clienteLogado);
            JOptionPane.showMessageDialog(null, "Logout realizado com sucesso para " + clienteLogado.getLogin());
            clienteLogado = null;
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum usuário está logado no momento.");
        }
    }


    private void registrarLogout(Cliente cliente) {
        try (FileWriter fw = new FileWriter("Logins-registrados.txt", true)) {
            fw.write("LOGOUT: " + cliente.getLogin() + " em " + LocalDateTime.now() + "\n");
            totalLogados--;
        } catch (IOException e) {
            LoggerUtil.logSevere("Erro ao registrar lougout " + cliente.getLogin(), e);

        }
    }

    public String gerarCodigoVerificacao() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = (int) (Math.random() * caracteres.length());
            codigo.append(caracteres.charAt(index));
        }
        return codigo.toString();
    }



    public Cliente getClienteLogado() {
        return clienteLogado;
    }
}