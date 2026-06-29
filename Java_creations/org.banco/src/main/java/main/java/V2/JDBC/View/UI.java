package main.java.V2.JDBC.View;

import com.formdev.flatlaf.FlatLightLaf;
import main.java.V1.POO.Exception.DominioException;
import main.java.V2.JDBC.Infra.exception.NegocioException;
import main.java.V2.JDBC.Model.Cliente;
import main.java.V2.JDBC.Model.Conta;
import main.java.V2.JDBC.Service.ServiceCliente;
import main.java.V2.JDBC.Service.ServiceConta;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class UI   {
    private final ServiceCliente serviceCliente;
    private final ServiceConta serviceConta;

    public UI(ServiceCliente serviceCliente, ServiceConta serviceConta) {
        this.serviceCliente=serviceCliente;
        this.serviceConta=serviceConta;
    }

    private void estilizarBotaoCompacto(JButton btn, Color cor) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setForeground(Color.WHITE);
        btn.setBackground(cor);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    }



    public void criaUI() {
        FlatLightLaf.setup();

        JFrame frame = new JFrame("🏦 BANCO 'X' - Área do Administrador");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 700);
        frame.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        //Aba Clientes
        JPanel clientePanel = new JPanel(new BorderLayout(10, 10));
        clientePanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JTextField cpfField = new JTextField();
        JTextField nomeField = new JTextField();
        JTextField loginField = new JTextField();
        JTextField emailField = new JTextField();

        JTextArea clienteOutput = new JTextArea(10, 50);
        clienteOutput.setEditable(false);
        clienteOutput.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        clienteOutput.setBackground(new Color(245, 245, 245));

        JButton salvarClienteBtn = new JButton("💾 Salvar Cliente");
        JButton updateClientesBtn = new JButton("🔄 Atualizar Cliente");
        JButton buscarClienteBtn = new JButton("🔍 Buscar Cliente Pelo CPF");
        JButton listarClientesBtn = new JButton("📑 Listar Todos");

        JPanel clienteForm = new JPanel(new GridLayout(4, 2, 5, 5));
        clienteForm.add(new JLabel("CPF:")); clienteForm.add(cpfField);
        clienteForm.add(new JLabel("Nome:")); clienteForm.add(nomeField);
        clienteForm.add(new JLabel("Login:")); clienteForm.add(loginField);
        clienteForm.add(new JLabel("Email:")); clienteForm.add(emailField);

        JPanel clienteButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        clienteButtons.add(salvarClienteBtn);
        clienteButtons.add(updateClientesBtn);
        clienteButtons.add(buscarClienteBtn);
        clienteButtons.add(listarClientesBtn);

        clientePanel.add(clienteForm, BorderLayout.NORTH);
        clientePanel.add(clienteButtons, BorderLayout.CENTER);
        clientePanel.add(new JScrollPane(clienteOutput), BorderLayout.SOUTH);

        // Ações clientes
        buscarClienteBtn.addActionListener(e -> {
            try {
                Cliente cliente = serviceCliente.readClienteporCPF(cpfField.getText());
                clienteOutput.setText("Cliente encontrado!\n" + cliente);

                cpfField.setText("");
                nomeField.setText("");
                loginField.setText("");
                emailField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao salvar cliente: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        updateClientesBtn.addActionListener(e -> {
            try {
                Cliente novoCliente = new Cliente(
                        cpfField.getText(),
                        nomeField.getText(),
                        loginField.getText(),
                        emailField.getText(),
                        true
                );
                serviceCliente.updateCliente(novoCliente);
                clienteOutput.setText("✅ Cliente atualizado!\n" + novoCliente);

                cpfField.setText("");
                nomeField.setText("");
                loginField.setText("");
                emailField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao atualizar cliente: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        salvarClienteBtn.addActionListener(e -> {
            try {
                if (cpfField.getText().trim().isEmpty() || nomeField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "CPF e Nome são obrigatórios!",
                            "Validação", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Cliente novoCliente = new Cliente(
                        cpfField.getText().trim(),
                        nomeField.getText().trim(),
                        loginField.getText().trim(),
                        emailField.getText().trim(),
                        true
                );

                serviceCliente.createCliente(novoCliente);
                clienteOutput.setText("✅ Cliente salvo com sucesso!\n\n" + novoCliente);

                // Limpar campos SOMENTE após sucesso
                cpfField.setText("");
                nomeField.setText("");
                loginField.setText("");
                emailField.setText("");

                JOptionPane.showMessageDialog(frame, "Cliente salvo com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            } catch (DominioException de) {
                // Erro de validação do domínio (CPF, nome, email, etc.)
                JOptionPane.showMessageDialog(frame, de.getMessage(),
                        "Erro de Validação", JOptionPane.WARNING_MESSAGE);
                clienteOutput.setText("❌ Erro de validação:\n" + de.getMessage());

            } catch (Exception ex) {
                // Outros erros (banco de dados, etc.)
                JOptionPane.showMessageDialog(frame, "Erro ao salvar cliente: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                clienteOutput.setText("❌ Erro ao salvar:\n" + ex.getMessage());
            }
        });


        listarClientesBtn.addActionListener(e -> {
            try {
                java.util.List<Cliente> clientes = serviceCliente.listarClientes();
                StringBuilder sb = new StringBuilder("📑 Lista de Clientes:\n\n");
                clientes.forEach(c -> sb.append(c).append("\n"));
                clienteOutput.setText(sb.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao listar clientes: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        //Aba Contas
        JPanel contaPanel = new JPanel(new BorderLayout(10, 10));
        contaPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JTextField clienteIdField = new JTextField();
        JTextField saldoField = new JTextField();

        //
        String[] tiposConta = {"Conta Corrente", "Conta Poupança"};
        JComboBox<String> tipoContaBox = new JComboBox<>(tiposConta);

        JTextArea contaOutput = new JTextArea(12, 50);
        contaOutput.setEditable(false);
        contaOutput.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contaOutput.setBackground(new Color(245, 245, 245));


        JPanel contaForm = new JPanel(new GridLayout(3, 2, 10, 10));
        contaForm.add(new JLabel("ID Cliente:")); contaForm.add(clienteIdField);
        contaForm.add(new JLabel("Saldo Inicial:")); contaForm.add(saldoField);

        contaForm.add(new JLabel("Tipo (CC/CP):")); contaForm.add(tipoContaBox);

        JButton criarContaBtn = new JButton("🏦 Criar Conta ");
        JButton listarContasID = new JButton("\uD83D\uDCCB Listar Conta por ID");
        JButton listarContasBtn = new JButton("📂 Listar Contas");

        JPanel contaButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        contaButtons.add(criarContaBtn);
        contaButtons.add(listarContasID);
        contaButtons.add(listarContasBtn);

        contaPanel.add(contaForm, BorderLayout.NORTH);
        contaPanel.add(contaButtons, BorderLayout.CENTER);
        contaPanel.add(new JScrollPane(contaOutput), BorderLayout.SOUTH);

// 1. Definição das Cores e Fontes
        Color verdeDeposito = new Color(40, 167, 69);
        Color laranjaSaque = new Color(255, 140, 0);
        Color azulTransfer = new Color(0, 123, 255);
        Color fundoFrio = new Color(250, 250, 250);

// 2. Título
        JLabel tituloOperacoes = new JLabel("Operações da Conta");
        tituloOperacoes.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tituloOperacoes.setForeground(new Color(60, 60, 60));

// 3. Botões
        JButton depositarBtn = new JButton("<html><center>🏦 Depositar</center></html>");
        JButton sacarBtn = new JButton("<html><center>💸 Sacar</center></html>");
        JButton transferirBtn = new JButton("<html><center>🔄 Transferir</center></html>");

        estilizarBotaoCompacto(depositarBtn, verdeDeposito);
        estilizarBotaoCompacto(sacarBtn, laranjaSaque);
        estilizarBotaoCompacto(transferirBtn, azulTransfer);

// 4. Painel Superior (Título + Botões)
        JPanel topoPanel = new JPanel(new BorderLayout(0, 5));
        topoPanel.setBackground(fundoFrio);

// Usamos FlowLayout para os botões ficarem pequenos
        JPanel flowBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        flowBotoes.setBackground(fundoFrio);
        flowBotoes.add(depositarBtn);
        flowBotoes.add(sacarBtn);
        flowBotoes.add(transferirBtn);

        topoPanel.add(tituloOperacoes, BorderLayout.NORTH);
        topoPanel.add(flowBotoes, BorderLayout.CENTER);

// 5. Área de Texto (AGORA REALMENTE PEQUENA)
        JTextArea operacoesOutput = new JTextArea(4, 35); // 4 linhas, 35 colunas
        operacoesOutput.setEditable(false);
        operacoesOutput.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollOperacoes = new JScrollPane(operacoesOutput);
// Definimos um tamanho fixo pequeno
        scrollOperacoes.setPreferredSize(new Dimension(900, 100));

// scrol dentro do flat, para ele não esticar no restante da tela
        JPanel wrapperScroll = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wrapperScroll.setBackground(fundoFrio);
        wrapperScroll.add(scrollOperacoes);

// 6. Montagem Final
        JPanel operacoesPanel = new JPanel(new BorderLayout());
        operacoesPanel.setBackground(fundoFrio);
        operacoesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        operacoesPanel.add(topoPanel, BorderLayout.NORTH);
        operacoesPanel.add(wrapperScroll, BorderLayout.CENTER);


        // Ações contas
        criarContaBtn.addActionListener(e -> {
            try {
                int clienteId = Integer.parseInt(clienteIdField.getText().trim());
                double saldo = Double.parseDouble(saldoField.getText().trim().replace(",", "."));
                String tipo = (String) tipoContaBox.getSelectedItem();



                serviceConta.criarConta(clienteId, saldo, tipo);
                contaOutput.setText("✅ Conta criada com sucesso!\n" +
                        "Cliente ID: " + clienteId + "\n" +
                        "Tipo: " + tipo + "\n" +
                        "Saldo: R$ " + String.format("%.2f", saldo));

                clienteIdField.setText("");
                saldoField.setText("");

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "ID do cliente e saldo devem ser números válidos!",
                        "Entrada inválida", JOptionPane.WARNING_MESSAGE);
            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(),
                        "Entrada inválida", JOptionPane.WARNING_MESSAGE);
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao criar conta: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        listarContasID.addActionListener(e -> {
            try {
                int clienteId = Integer.parseInt(clienteIdField.getText().trim());
                List<Conta> contas = serviceConta.listarContaPorClienteId(clienteId);

                if (contas.isEmpty()) {
                    contaOutput.setText("Nenhuma conta encontrada para este cliente.");
                } else {
                    StringBuilder sb = new StringBuilder("📂 Contas do Cliente:\n\n");
                    contas.forEach(c -> sb.append(c).append("\n"));
                    contaOutput.setText(sb.toString());
                }

                clienteIdField.setText("");

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Informe um ID de cliente válido.",
                        "Entrada inválida", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao listar contas: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        listarContasBtn.addActionListener(e-> {
            try {
                List<Conta> contaas = serviceConta.listarContas();
                System.out.println("Tamanho da lista: " + contaas.size());
                StringBuilder sb = new StringBuilder("📂 Contas Totais:\n\n");
                contaas.forEach(c -> sb.append(c.getResumo()).append("\n"));
                contaOutput.setText(sb.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
            }
        });

        depositarBtn.addActionListener(e -> {
            String numeroStr = JOptionPane.showInputDialog(frame, "Número da conta:");
            if (numeroStr == null) return;

            String valorStr = JOptionPane.showInputDialog(frame, "Valor do depósito:");
            if (valorStr == null) return;

            numeroStr = numeroStr.trim();
            valorStr = valorStr.trim();
            if (numeroStr.isBlank() || valorStr.isBlank()) {
                JOptionPane.showMessageDialog(frame, "Preencha número e valor.",
                        "Entrada inválida", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                int numero = Integer.parseInt(numeroStr);
                double valor = Double.parseDouble(valorStr.replace(",", "."));

                serviceConta.depositar(numero, valor);

                operacoesOutput.setText("Depósito realizado!\nConta: " + numero + "\nValor: " + valor);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Informe número e valor válidos.",
                        "Entrada inválida", JOptionPane.WARNING_MESSAGE);
            } catch (IllegalStateException | IllegalArgumentException i) {
                JOptionPane.showMessageDialog(frame, i.getMessage(),
                        "Aviso", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro no depósito: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        sacarBtn.addActionListener(e -> {
            try {
                String numeroStr = JOptionPane.showInputDialog(frame, "Número da conta:");
                String valorStr = JOptionPane.showInputDialog(frame, "Valor do saque:");
                if (numeroStr != null && valorStr != null) {
                    int numero = Integer.parseInt(numeroStr.trim());
                    double valor = Double.parseDouble(valorStr.trim());
                    Conta conta = serviceConta.sacar(numero,valor);
                    operacoesOutput.setText("✅ Saque realizado!\n" + conta);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Conta não encontrada.",
                                "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
        } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Informe número e valor válidos.",
                        "Entrada inválida", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro no saque: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        transferirBtn.addActionListener(e -> {
            try {
                String origemStr = JOptionPane.showInputDialog(frame, "Número da conta origem:");
                String destinoStr = JOptionPane.showInputDialog(frame, "Número da conta destino:");
                String valorStr = JOptionPane.showInputDialog(frame, "Valor da transferência:");
                if (origemStr != null && destinoStr != null && valorStr != null) {
                    int origemNum = Integer.parseInt(origemStr.trim());
                    int destinoNum = Integer.parseInt(destinoStr.trim());
                    double valor = Double.parseDouble(valorStr.trim());


                        Conta origem = serviceConta.readContaPorNumeroService(origemNum);
                        Conta destino = serviceConta.readContaPorNumeroService(destinoNum);


                    //todo 1.conferir valores exatos e taxas 2.todo corrigir saida com antes e depois
                    serviceConta.transferenciaService(origem,destino,valor);
                    operacoesOutput.setText("✅ Transferência realizada!\nOrigem: " + origem + "\nDestino: " + destino);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Verifique os números das contas.",
                                "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Informe números e valor válidos.",
                        "Entrada inválida", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro na transferência: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        tabbedPane.addTab("👤 Clientes", clientePanel);
        tabbedPane.addTab("💳 Contas", contaPanel);
        tabbedPane.addTab("💸 Operações", operacoesPanel);


        frame.add(tabbedPane);
        frame.setVisible(true);
    }
}
