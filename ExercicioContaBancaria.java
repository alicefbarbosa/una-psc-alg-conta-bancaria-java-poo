package Lista11;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExercicioContaBancaria {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContaBancaria conta = null;
        String nomePessoa;
        String numeroConta;
        double saldoInicial;
        int opcao;

        System.out.println("### Criar Nova Conta Bancária ###");


        System.out.print("Digite o Nome da Pessoa: ");
        nomePessoa = scanner.nextLine();

        System.out.print("Digite o Número da Conta: ");
        numeroConta = scanner.nextLine();

        while (true) {
            try {
                System.out.print("Digite o Saldo Inicial: R$ ");
                saldoInicial = scanner.nextDouble();
                if (saldoInicial < 0) {
                    System.out.println("O saldo inicial não pode ser negativo. Tente novamente.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número para o saldo.");
                scanner.next();
            }
        }
        scanner.nextLine();


        conta = new ContaBancaria(nomePessoa, numeroConta, saldoInicial);
        System.out.println("\nConta bancária criada com sucesso.");
        System.out.printf("Titular: %s | Conta: %s | Saldo Inicial: R$ %.2f%n",
                conta.getNomePessoa(), conta.getNumeroConta(), conta.getSaldo());


        do {
            System.out.println("\n### Menu de Operações ###");
            System.out.println("1 - Depositar Valor");
            System.out.println("2 - Sacar Valor");
            System.out.println("3 - Obter Saldo Disponível");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Por favor, digite um número.");
                scanner.next();
                opcao = -1;
            }
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    double valorDeposito;
                    while (true) {
                        try {
                            System.out.print("Digite o valor para depositar: R$ ");
                            valorDeposito = scanner.nextDouble();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Por favor, digite um número para o valor.");
                            scanner.next();
                        }
                    }
                    scanner.nextLine();
                    conta.depositar(valorDeposito);
                    break;
                case 2:
                    double valorSaque;
                    while (true) {
                        try {
                            System.out.print("Digite o valor para sacar: R$ ");
                            valorSaque = scanner.nextDouble();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Por favor, digite um número para o valor.");
                            scanner.next();
                        }
                    }
                    scanner.nextLine();
                    conta.sacar(valorSaque);
                    break;
                case 3:
                    System.out.printf("Saldo atual: R$ %.2f%n", conta.getSaldo());
                    break;
                case 0:
                    System.out.println("Saindo do programa. Obrigado por utilizar nossos serviços!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida do menu.");
            }
        } while (opcao != 0);

        scanner.close();
    }

        public static class ContaBancaria {
        private String nomePessoa;
        private String numeroConta;
        private double saldo;


        public ContaBancaria(String nomePessoa, String numeroConta, double saldoInicial) {
            this.nomePessoa = nomePessoa;
            this.numeroConta = numeroConta;
            this.saldo = saldoInicial;
        }


        public void depositar(double valor) {
            if (valor > 0) {
                this.saldo += valor;
                System.out.printf("Depósito de R$ %.2f realizado com sucesso.%n", valor);
            } else {
                System.out.println("O valor do depósito deve ser positivo.");
            }
        }


        public void sacar(double valor) {
            if (valor <= 0) {
                System.out.println("O valor do saque deve ser positivo.");
                return;
            }

            if (valor > 5000.00) {
                System.out.println("Valor do saque acima do permitido (R$ 5000,00).");
                return;
            }

            if (this.saldo >= valor) {
                this.saldo -= valor;
                System.out.printf("Saque de R$ %.2f realizado com sucesso.%n", valor);
            } else {
                System.out.println("Saldo insuficiente para realizar a operação.");
            }
        }


        public double getSaldo() {
            return this.saldo;
        }


        public String getNomePessoa() {
            return nomePessoa;
        }

        public String getNumeroConta() {
            return numeroConta;
        }
    }
}