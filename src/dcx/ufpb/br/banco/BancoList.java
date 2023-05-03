package dcx.ufpb.br.banco;
import java.util.ArrayList;

public class BancoList implements interfaceBanco{
    private ArrayList<Conta> contas;

    public BancoList(ArrayList<Conta> contas) {
        this.contas = contas;
    }

    public void transferir(String cpfO,String numContaO, String numAgO,String cpfD, String numContaD, String numAgenciaD, double valor) throws ContaInvalidaException {
        if(existeConta(cpfO) && existeConta(cpfD)){
            Conta contaO  = null;
            Conta contaD = null;
            for(Conta c: this.contas){
                if(c.getNumeroConta().equals(numContaO) && c.getNumeroAgencia().equals(numAgO)){
                    contaO = c;
                }

                if(c.getNumeroConta().equals(numContaD) && c.getNumeroAgencia().equals(numAgenciaD)){
                    contaD = c;
                }
            }
            if(contaO!=null && contaD!= null && contaO!=contaD){
                contaO.debitar(valor);
                contaD.creditar(valor);
            }
        } throw new ContaInvalidaException("Uma das contas ou as duas são(é) inválida(s)");
        
    }

    public void abrirConta(String nome, String cpf, String numC, String numAg, double saldo) throws ContaJaExisteException {
            if(existeConta(cpf)){
                throw new ContaJaExisteException("Este contato já existe no sistema, tente novamente!");
            }
        Conta conta = new Conta(nome, cpf, numC, numAg, 0);
        this.contas.add(conta);
    }

    public void removerConta(String cpfTitular) throws ContaInvalidaException{
        for(Conta c: this.contas){
            if(c.getCpfTitular().equals(cpfTitular)){
                contas.remove(c);
                break;
            }
            throw new ContaInvalidaException("Conta inválida!");
        }
        
    }

    public ArrayList<Conta> pesquisarContasComSaldoNegativo() {
        ArrayList<Conta> saldoNegativo = new ArrayList<>();
        for (Conta c : this.contas) {
            if (c.getSaldo() < 0) {
                saldoNegativo.add(c);
            }
        }
        return saldoNegativo;
    }

    public double consultarSaldoDeConta(String cpf,String numConta, String numAgencia) throws ContaInvalidaException{
        if(existeConta(cpf)){
            double saldo = 0;
            for (Conta c : this.contas) {
                if (c.getNumeroConta().equals(numConta) && c.getNumeroAgencia().equals(numAgencia)) {
                    saldo = c.getSaldo();
                }
            }
            return saldo;
        }
        throw new ContaInvalidaException("Não foi possível consultar o saldo de sua conta! Tente novamente com uma conta válida");
    }

    public void sacarDeConta(String cpf, String numConta, String numAgencia, double valor) throws ContaInvalidaException{
        if(existeConta(cpf)){
            for (Conta c: this.contas){
                if(c.getNumeroConta().equals(numConta) && (c.getNumeroAgencia().equals(numAgencia))){
                    c.debitar(valor);
                }
                throw new ContaInvalidaException("Não foi possível sacar de sua conta! Tente novamente com uma conta válida!");
            }
        }
        
    }

    public void depositarEmConta(String cpf, String numConta, String numAgencia, double valor) throws ContaInvalidaException{
        if(existeConta(cpf)){
            for (Conta c: contas){
                if(c.getNumeroConta().equals(numConta) && (c.getNumeroAgencia().equals(numAgencia))){
                    c.creditar(valor);
                }
                throw new ContaInvalidaException("Não foi possível depositar de sua conta! Tente novamente com uma conta válida!");
            }
        }
        

    }

    public ArrayList<Conta> pesquisarContasDoCliente(String cpf) throws ContaInvalidaException{
        ArrayList <Conta> contaCliente = new ArrayList<>();
        for(Conta c: this.contas){
            if(c.getCpfTitular().equals(cpf)){
                contaCliente.add(c);
                return contaCliente;
            }
        }
        throw new ContaInvalidaException("Esta conta é inválida, tente novamente com uma conta válida!");
    }

    public ArrayList<Conta> getContas(){
        return  this.contas;
    }

    public void abrirContaEspecial(String nome, String cpf, String numC, String numAg, double saldo, double credito) throws ContaJaExisteException{
        for(Conta c: this.contas){
            if(c.getCpfTitular().equals(cpf)){
                throw new ContaJaExisteException("Está conta já foi cadastrada. Tente novamente com outra conta!");
            }
        }
        ContaEspecial conta = new ContaEspecial(nome, cpf, numC, numAg, 0,1000);
        this.contas.add(conta);
    }

    public boolean contaNula(String nome, String cpf, String numC, String numAg){
        if(nome == null | cpf == null | numC == null | numAg == null){
            return true;
        } 
        return false;
    }

    public boolean contaVazia(String nome, String cpf, String numC, String numAg) {
        if(nome.equals("") | cpf.equals("") | numC.equals("") | numAg.equals("")){
            return true;
        }
        return false;
    }
    
    public boolean existeConta(String cpf){
        for(Conta c: this.contas){
            if(c.getCpfTitular().equals(cpf)){
                return true;
            }
        }
        return false;
    }

}