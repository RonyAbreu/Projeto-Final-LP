package dcx.ufpb.br.banco;

public class Conta {
    private String nome;
    private String cpfTitular;
    private String numeroConta;
    private String numeroAgencia;
    private double saldo;

    public Conta() {
        this("", "", "", "", 0.0);
    }

    public Conta(String nome, String cpf, String numC, String numAg, double saldo) {
        this.nome = nome;
        this.cpfTitular = cpf;
        this.numeroConta = numC;
        this.numeroAgencia = numAg;
        this.saldo = saldo;
    }

    public String toString() {
        return "O cliente de nome: "+this.nome+" possui uma conta de número: " + this.numeroConta + " com a agência: " + this.numeroAgencia
                + " com CPF de número: " + this.cpfTitular + " e tem um saldo atual de: " + this.saldo + "R$";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpfTitular == null) ? 0 : cpfTitular.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Conta other = (Conta) obj;
        if (cpfTitular == null) {
            if (other.cpfTitular != null)
                return false;
        } else if (!cpfTitular.equals(other.cpfTitular))
            return false;
        return true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroConta() {
        return this.numeroConta;
    }

    public String getNumeroAgencia() {
        return this.numeroAgencia;
    }

    public String getCpfTitular() {
        return this.cpfTitular;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setCpfTitular(String cpf) {
        this.cpfTitular = cpf;
    }

    public void setNumeroConta(String novoNumero) {
        this.numeroConta = novoNumero;
    }

    public void setNumeroAgencia(String numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public double creditar(double valor) {
        this.saldo += valor;
        return valor;
    }

    public double debitar(double valor) {
        this.saldo -= valor;
        return valor;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}