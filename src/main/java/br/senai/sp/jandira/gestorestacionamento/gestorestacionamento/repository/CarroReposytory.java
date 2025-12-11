package br.senai.sp.jandira.gestorestacionamento.gestorestacionamento.repository;

import br.senai.sp.jandira.gestorestacionamento.gestorestacionamento.model.Carro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CarroReposytory    {
    //registro é o registro.csv
    private Path registro = Paths.get("/home/cosme/projetos/Gestor-Estacionamento/dados/registro.csv");

    public void salvar (Carro carro) {
        try {
            //escreve no resgistro o conteudo de carro.separaDados pula uma linha
            Files.writeString(registro,carro.separaDados()+ "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            //trata erro
            throw new RuntimeException(e);
        }
    }



    /*
    * função de calcula
    * rebebe dados pelos parentese
    * e retorna so o valor so pra teste
    *
    * calculo de subtração basico
    * caso de errado fazer via incremento pra pegar a quantidade de minutos exata
    *
    *
    *
    * */
    public List<String> exibir (){
        List<String> carros = new ArrayList<>();

        try {
            List<String> Linhas = Files.readAllLines(registro);
            for(String linha : Linhas){
                String[] linhaCarro = linha.split(";");
                if (linhaCarro[6].equals("true")) {
                    String placa = linhaCarro[1];
                    String modelo = linhaCarro[2];
                    String nomePropietario = linhaCarro[3];
                    String data = linhaCarro[4];
                    String hora = linhaCarro[5];

                    //formata horas
                    String diaMes = data.substring(8, 10) + "-" + data.substring(5, 7);
                    // Formata hora para HH:mm
                    String horaMin = hora.substring(0, 5);

                    // Linha formatada com colunas fixas
                    String texto = String.format("%-10s %-10s %-15s %-6s %5s",
                            placa,        // placa
                            modelo,       // modelo
                            nomePropietario, // proprietário
                            diaMes,       // dia-mês
                            horaMin       // hora:minuto
                    );
                    carros.add(texto);

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return carros;


    }



    public String[] busca(String placaBuscada) {

        try {
            List<String> linhas = Files.readAllLines(registro);

            for (String linha : linhas) {
                String[] dados = linha.split(";");

                if (dados[1].equalsIgnoreCase(placaBuscada) && dados[6].equals("true")) {

                    String recebeValor =  calculo(dados[5], dados[4]);

                    return new String[]{
                            dados[1], // placa
                            dados[2], // modelo
                            dados[3], // proprietario
                            dados[4], // data
                            dados[5],  // hora
                            dados[6] = "caiu",
                            dados[7] = recebeValor
                    };
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null; // não achou
    }






    public String calculo(String horaEntrada, String dataEntrada){
        String valorPagoTexto = " ";

        LocalTime horaDeSaida = LocalTime.now();
        LocalTime horaentrada = LocalTime.parse(horaEntrada);

        Duration tempo = Duration.between(horaentrada, horaDeSaida);

        long Minutos = tempo.toMinutes();
        float recebeMinutos = Minutos ;
        float convertido = (recebeMinutos / 60);
        int intConvertido = (int) convertido;
        float resto = convertido - intConvertido;
        float valorPagamento;

        if (Minutos <65) {
            valorPagamento = 10;

        }else{
            valorPagamento = 10;
            intConvertido = (intConvertido - 1) ;
            valorPagamento = valorPagamento + (intConvertido * 5);

            if (resto > 0.083 ) {
                valorPagamento = valorPagamento + 5;
            }

        }

        valorPagoTexto = String.valueOf(valorPagamento);

        return valorPagoTexto ;
    }






    public boolean registrarSaida(String placaBuscada) {

        try {
            List<String> linhas = Files.readAllLines(registro);
            List<String> novasLinhas = new ArrayList<>();

            boolean atualizado = false;

            for (String linha : linhas) {
                String[] dados = linha.split(";");

                if (dados[1].equalsIgnoreCase(placaBuscada) && dados[6].equals("true")) {
                    // altera visibilidade para false
                    dados[6] = "false";

                    // recria a linha com dados modificados
                    linha = String.join(";", dados);

                    atualizado = true;
                }

                novasLinhas.add(linha);
            }

            // salva tudo de volta
            Files.write(registro, novasLinhas);

            return atualizado;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
