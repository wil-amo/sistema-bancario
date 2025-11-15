package main.java.model;

import main.java.model.service.Sessao;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        Sessao sessao = new Sessao();

        Cliente cliente1 = new Cliente("123454678910", "cesar", "cesar", "1234","cesar@gmail.com");
        Conta conta1 = new ContaCorrente(cliente1);

        banco.adicionarCliente(cliente1);
        banco.adicionarConta(conta1);
        sessao.registrarNovoCliente(cliente1);
        sessao.buscarClientePorLogin(cliente1.getLogin());


        Cliente cliente2 = new Cliente("0987654321", "elton", "elton", "1234","Elton@gmail.com");
        Conta conta2 = new ContaCorrente(cliente2);

        banco.adicionarCliente(cliente2);
        banco.adicionarConta(conta2);
        sessao.registrarNovoCliente(cliente2);
        sessao.buscarClientePorLogin(cliente2.getLogin());


        JOptionPane.showMessageDialog(null, "Bem-vindo ao sistema bancário!");

        // cria cliente
//        String cpf = JOptionPane.showInputDialog("Digite o CPF:");
//        String nome = JOptionPane.showInputDialog("Digite o nome:");
//        String login = JOptionPane.showInputDialog("Digite o login:");
//        String senha = JOptionPane.showInputDialog("Digite a senha:");
//        String email = JOptionPane.showInputDialog("Digite o e-mail:");
//
//        Cliente cliente = new Cliente(cpf, nome, login, senha, email);
//        Conta conta = new ContaCorrente(cliente);
//
//        banco.adicionarCliente(cliente);
//        banco.adicionarConta(conta);
//
//
//
//        sessao.buscarClientePorLogin("w");
//        sessao.registrarNovoCliente(cliente);
//
//
//                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!\n" + banco.listarContas());

                // Login do cliente
                String loginTentativa = JOptionPane.showInputDialog("Login para acesso:");
                String senhaTentativa = JOptionPane.showInputDialog("Senha:");

                sessao.loginClienteGUI(loginTentativa, senhaTentativa);

                //  usuários conectados
                JOptionPane.showMessageDialog(null, sessao.getClienteLogado() != null
                        ? "Usuário conectado: " + sessao.getClienteLogado().getNome()
                        : "Nenhum usuário conectado.");

                // Logout
                sessao.logout();
                JOptionPane.showMessageDialog(null, "Sessão encerrada.");
            }
    }


