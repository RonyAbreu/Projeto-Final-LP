package dcx.ufpb.br.banco;

public class ContaJaExisteException extends Exception {

    public ContaJaExisteException(String msg){
        super(msg);
    }
}
