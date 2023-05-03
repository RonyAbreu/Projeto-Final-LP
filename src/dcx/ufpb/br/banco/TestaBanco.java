package dcx.ufpb.br.banco;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TestaBanco {
    public static void main(String[] args){
        interfaceBanco meuBanco = null;
        GravadorDeContas gravador = new GravadorDeContas();
        try{
            ArrayList<Conta> contasRecuperadas = gravador.LerContas();
            JOptionPane.showMessageDialog(null, "Contas Recuperadas Com Sucesso!");
            meuBanco = new BancoList(contasRecuperadas);
        } catch (IOException e){
            meuBanco = new BancoList(new ArrayList<>());
            JOptionPane.showMessageDialog(null, "Sistema iniciado sem dados");
            JOptionPane.showMessageDialog(null,"Não foi possível recuperar os dados. Detalhe do erro:"+ e.getMessage());
        }
        Boolean iniciar = true;
        while (iniciar) {
            String opcao = JOptionPane.showInputDialog(null,
                    "1. Abrir conta \n2. Pesquisar conta\n3. Pesquisar contas com saldo negativo\n4. Consultar saldo\n5. Saque\n6. Deposito\n7. Transferir dinheiro\n8. Salvar\n9. Remover Conta\n10. Sair");
            if (opcao.equals("1")) {
                int tipoConta = JOptionPane.showConfirmDialog(null,
                        "Sua conta é do tipo Especial?");
                if (tipoConta == JOptionPane.YES_OPTION) {
                    try {
                        String nome = JOptionPane.showInputDialog(null, "Digite seu Nome: ");
                        String cpf = JOptionPane.showInputDialog(null, "Digite seu CPF: ");
                        String numConta = JOptionPane.showInputDialog(null,
                                "Digite o número de sua conta: ");
                        String numAg = JOptionPane.showInputDialog(null,
                                "Digite o número de sua Agência: ");
                        double saldo = 0;
                        Double credito = 1000.0;
                        if(meuBanco.contaNula(nome, cpf, numConta, numAg) | meuBanco.contaVazia(nome, cpf, numConta, numAg)){
                            JOptionPane.showMessageDialog(null, "Verifique se preencheu todos os campos coretamente!");
                        } else {
                            meuBanco.abrirContaEspecial(nome, cpf, numConta, numAg, saldo, credito);
                            JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
                        }
                    } catch (ContaJaExisteException e) {
                        JOptionPane.showMessageDialog(null, "Está conta já existe ou é nula!");

                    }
                } else {

                    try {
                        String nome = JOptionPane.showInputDialog(null, "Digite seu Nome: ");
                        String cpf = JOptionPane.showInputDialog(null, "Digite seu CPF: ");
                        String numConta = JOptionPane.showInputDialog(null,
                                "Digite o número de sua conta: ");
                        String numAg = JOptionPane.showInputDialog(null,
                                "Digite o número de sua Agência: ");
                        double saldo = 0;
                        if(meuBanco.contaNula(nome, cpf, numConta, numAg) | meuBanco.contaVazia(nome, cpf, numConta, numAg)){
                            JOptionPane.showMessageDialog(null, "Verifique se preencheu todos os campos coretamente!");
                        } else {
                            meuBanco.abrirConta(nome,cpf,numConta,numAg,saldo);
                            JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
                        }
                    } catch (ContaJaExisteException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }
            }

            if (opcao.equals("2")) {
                String cpfTitular = JOptionPane.showInputDialog(null, "Digite seu cpf: ");
                try {
                    JOptionPane.showMessageDialog(null,
                            meuBanco.pesquisarContasDoCliente(cpfTitular));
                } catch (ContaInvalidaException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    continue;

                }
            }

            if (opcao.equals("3")) {
                ArrayList<Conta> contNeg = meuBanco.pesquisarContasComSaldoNegativo();
                for (Conta c : contNeg) {
                    JOptionPane.showMessageDialog(null, c.toString());
                }
            }

            if (opcao.equals("4")) {
                String cpf = JOptionPane.showInputDialog(null, "Digite seu CPF: ");
                String numConta = JOptionPane.showInputDialog(null,
                        "Digite o número de sua Conta: ");
                String numAgencia = JOptionPane.showInputDialog(null,
                        "Digite o número de sua Agência: ");
                try {
                    meuBanco.consultarSaldoDeConta(cpf,numConta, numAgencia);
                    JOptionPane.showMessageDialog(null,
                        meuBanco.consultarSaldoDeConta(cpf,numConta, numAgencia));
                } catch (ContaInvalidaException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                
            }

            if (opcao.equals("5")) {
                Boolean valorValido = true;
                String cpf = JOptionPane.showInputDialog(null, "Digite seu CPF: ");
                String numConta = JOptionPane.showInputDialog(null,
                        "Digite o número da conta que deseja efetuar o saque: ");
                String numAgencia = JOptionPane.showInputDialog(null,
                        "Digite o número de sua Agência: ");
                while (valorValido) {
                    try {
                        double valor = Double.parseDouble(
                                JOptionPane.showInputDialog(null,
                                        "Digite o valor que deseja sacar: "));
                        try {
                            meuBanco.sacarDeConta(cpf,numConta, numAgencia, valor);
                            JOptionPane.showMessageDialog(null, "Seu saldo atual é de: "
                                + meuBanco.consultarSaldoDeConta(cpf,numConta, numAgencia));
                        } catch (ContaInvalidaException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                        valorValido = false;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null,
                                "Erro! Digite apenas números aqui. Tipo do erro: "
                                        + e.getMessage());
                        continue;
                    }
                }

            }

            if (opcao.equals("6")) {
                Boolean valorValido = true;
                String cpf = JOptionPane.showInputDialog(null, "Digite seu CPF: ");
                String numConta = JOptionPane.showInputDialog(null,
                        "Digite o número da conta que deseja depositar : ");
                String numAgencia = JOptionPane.showInputDialog(null,
                        "Digite o número de sua Agência: ");
                while (valorValido) {
                    try {
                        double valor = Double.parseDouble(
                                JOptionPane.showInputDialog(null,
                                        "Digite o valor que deseja depositar: "));
                        try {
                            meuBanco.depositarEmConta(cpf,numConta, numAgencia, valor);
                            JOptionPane.showMessageDialog(null, "Seu saldo atual é de: "
                                + meuBanco.consultarSaldoDeConta(cpf,numConta, numAgencia));
                        } catch (ContaInvalidaException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                        
                        valorValido = false;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null,
                                "Erro! Digite apenas números aqui. Tipo do erro: "
                                        + e.getMessage());
                        continue;
                    }
                }
            }

            if (opcao.equals("7")) {
                Boolean valorValido = true;
                String cpfA = JOptionPane.showInputDialog(null, "Digite o CPF da conta que deseja transferir o dinheiro: ");
                String numContaA = JOptionPane.showInputDialog(null,
                        "Digite o número da conta que deseja transferir o dinheiro : ");
                String numAgenciaA = JOptionPane.showInputDialog(null,
                        "Digite o número de sua Agência: ");
                String cpfB = JOptionPane.showInputDialog(null, "Digite o CPF da conta que deseja receber o dinheiro: ");
                String numContaB = JOptionPane.showInputDialog(null,
                        "Digite o número da conta que deseja receber o dinheiro : ");
                String numAgenciaB = JOptionPane.showInputDialog(null,
                        "Digite o número de sua Agência: ");
                while (valorValido) {
                    try {
                        double valor = Double.parseDouble(
                                JOptionPane.showInputDialog(null,
                                        "Digite o valor que deseja transferir: "));
                        try {
                            meuBanco.transferir(cpfA,numContaA,numAgenciaA,cpfB,numContaB,numAgenciaB,valor);
                        } catch (ContaInvalidaException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                        JOptionPane.showMessageDialog(null,
                                "Transferência realizada com sucesso!");
                        valorValido = false;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null,
                                "Erro! Digite apenas números aqui. Tipo do erro: "
                                        + e.getMessage());
                        continue;
                    }
                }
            }

            if(opcao.equals("8")){
                try {
                    gravador.GravaContas(meuBanco.getContas());
                    JOptionPane.showMessageDialog(null,"Dados Gravados com Sucesso!");
                }catch (IOException e){
                    JOptionPane.showMessageDialog(null,"Erro! Tente navamente!");
                }
            }

            if (opcao.equals("9")) {
                    try {
                        String cpf = JOptionPane.showInputDialog(null, "Digite seu CPF: ");
                        meuBanco.removerConta(cpf);
                        JOptionPane.showMessageDialog(null,"Conta removida com sucesso!");
                    } catch (ContaInvalidaException e) {
                        JOptionPane.showMessageDialog(null, "Está conta não é válida ou não está cadastrada no sistema!");
                    }
                    
            }

            if (opcao.equals("10")) {
                iniciar = false;
                JOptionPane.showMessageDialog(null, "Volte sempre!");
            }
        }
    }
}
