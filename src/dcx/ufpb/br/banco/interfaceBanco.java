package dcx.ufpb.br.banco;
import java.util.ArrayList;

public interface interfaceBanco {
    void abrirConta(String nome, String cpf, String numC, String numAg, double saldo) throws ContaJaExisteException;
    void abrirContaEspecial(String nome, String cpf, String numC, String numAg, double saldo, double credito) throws ContaJaExisteException;
    void removerConta(String cpfTitular) throws ContaInvalidaException;
    ArrayList<Conta> pesquisarContasDoCliente(String cpf) throws ContaInvalidaException;
    ArrayList<Conta> getContas();
    ArrayList<Conta> pesquisarContasComSaldoNegativo();
    double consultarSaldoDeConta(String cpf,String numConta, String numAgencia) throws ContaInvalidaException;
    void transferir(String cpfO,String numContaO, String numAgO,String cpfD, String numContaD, String numAgenciaD, double valor) throws ContaInvalidaException;
    void sacarDeConta(String cpf,String numConta, String numAgencia, double valor) throws ContaInvalidaException;
    void depositarEmConta(String cpf,String numConta, String numAgencia, double valor) throws ContaInvalidaException;
    boolean contaNula(String nome, String cpf, String numC, String numAg);
    boolean contaVazia(String nome, String cpf, String numC, String numAg);
}
