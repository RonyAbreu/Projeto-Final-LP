package dcx.ufpb.br.banco;

public class ContaEspecial extends Conta {
    
    private double credito;

    public ContaEspecial(String nome, String cpf, String numC, String numAg, double saldo, double credito){
        super(nome, cpf,numC,numAg,saldo);
        this.credito = credito;
    }

    public ContaEspecial(){
        this("","","","",0,1000);
    }

    public String toString() {
        return "Conta de número: " + getNumeroConta()+ " com a agência: " + getNumeroAgencia()
                + " do Cliente de CPF : " + getCpfTitular() + " tem um saldo atual de: " + getSaldo() + "R$, e um crédito de: "+this.credito+"R$";
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

}
