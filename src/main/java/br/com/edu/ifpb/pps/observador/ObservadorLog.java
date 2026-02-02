package br.com.edu.ifpb.pps.observador;

import br.com.edu.ifpb.pps.configuracao.Configuracao;
import br.com.edu.ifpb.pps.model.Anuncio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ObservadorLog implements Observador{

    @Override
    public void atualizar(Anuncio anuncio) {

        String arquivo = Configuracao.getInstance().getPropriedade("arquivoLog");

        if (arquivo == null) {
            arquivo = "myhomeLog.txt";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataHora = LocalDateTime.now().format(formatter);

        String mensagem = String.format("[Titulo: %s] [Data: %s] [Novo Estado: %s]", anuncio.getTitulo(),  dataHora, anuncio.getEstado().getNome());
        
        try 
        (
        FileWriter fileWriter = new FileWriter(arquivo, true);
        BufferedWriter bufferedWritter = new BufferedWriter(fileWriter);
        PrintWriter printWritter = new PrintWriter(bufferedWritter)
        ) {
            printWritter.println(mensagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
