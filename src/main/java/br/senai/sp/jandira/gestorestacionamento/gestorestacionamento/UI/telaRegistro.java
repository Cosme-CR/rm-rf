package br.senai.sp.jandira.gestorestacionamento.gestorestacionamento.UI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class telaRegistro extends VBox {
    public telaRegistro() {
        montaTelaRegistro();
    }

    public void montaTelaRegistro() {

        // HEADER
        VBox header = new VBox();
        header.setStyle("-fx-padding: 10;-fx-background-color: blue;-fx-alignment: center;");
        Label labelTitulo = new Label("Cadatro de Entrada");
        labelTitulo.setStyle("-fx-font-size: 30px;-fx-text-fill: white; -fx-font-weight: bold;");


        //caixa onde ficara os formulario
        HBox caixaDeFormulario = new HBox();

        //caixa onde ficara os botao de  registrar e voltat
        HBox caixaDeBotao = new HBox();

        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setStyle(
                        "-fx-font-size: 18px;" +
                        "-fx-padding: 15 30 15 30;" +
                        "-fx-background-color: #d54747;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-color: black;" +
                        "-fx-border-radius: 20px;" +
                        "-fx-border-width: 1px;"
        );
        botaoVoltar.setOnAction(e -> {
            telaInicial telaPrincipal = new telaInicial();
            //criar a janela de regist
            Scene novaCena = new Scene(telaPrincipal, 400, 300);

            // Pega o stage atual (janela principal)
            Stage stage = (Stage) botaoVoltar.getScene().getWindow();
            stage.setScene(novaCena);
            stage.setTitle("Carros Estacionados");

        });

        Button botaoRegistrar = new Button("Registrar");




        //poem o titulo na caixa header/ no header
        header.getChildren().add(labelTitulo);

        //poem os botoes na caixa de botao
        caixaDeFormulario.getChildren().add(botaoRegistrar);
        caixaDeBotao.getChildren().add(botaoVoltar);
        //meio q poem tudo na tela porem a classe é a tela
        getChildren().addAll(header, caixaDeFormulario, caixaDeBotao);


    }



}
