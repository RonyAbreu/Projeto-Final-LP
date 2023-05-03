package dcx.ufpb.br.banco;

import java.io.*;
import java.util.ArrayList;

public class GravadorDeContas {
    private String arquivoDeContas;
    

    public GravadorDeContas(String arquivoDeContas) {
        this.arquivoDeContas = arquivoDeContas;
    }

    public GravadorDeContas() {
        this("Conta.txt");  
    }

    public void GravaContas(ArrayList<Conta> contas) throws IOException {
        BufferedWriter escritor = null;
        try {
            escritor = new BufferedWriter(new FileWriter(this.arquivoDeContas));
            for (Conta c : contas) {
                String linhaDaConta = c.getNome() + ";" + c.getCpfTitular()
                        + ";" + c.getNumeroConta() + ";"
                        + c.getNumeroAgencia() + ";"
                        + c.getSaldo();
                escritor.write(linhaDaConta + "\n");
            }
        } finally {
            if (escritor != null) {
                escritor.close();
            }
        }

    }

    public ArrayList<Conta> LerContas() throws IOException {
        BufferedReader leitor = null;
        ArrayList<Conta> contasLidas = new ArrayList<>();
        try {
            leitor = new BufferedReader(new FileReader(this.arquivoDeContas));
            String linhaLida = null;
            do {
                linhaLida = leitor.readLine();
                if (linhaLida != null) {
                    String [] palavras = linhaLida.split(";");
                    Conta c = new Conta(palavras[0], palavras[1], palavras[2], palavras[3],
                            Double.parseDouble(palavras[4]));
                    contasLidas.add(c);
                }
            } while (linhaLida != null);
        } finally {
            if (leitor != null) {
                leitor.close();
            }
        }
        return contasLidas;
    }
}
