package br.senai.sp.jandira.gestorestacionamento.gestorestacionamento.UI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class apagar extends VBox {

    public apagar() { // construtor
        criarTela();
    }

    private void criarTela() {
        Label titulo = new Label("Tela de Cadastro");
        Button btnVoltar = new Button("Voltar");

        this.getChildren().addAll(titulo, btnVoltar);
        this.setSpacing(20);
    }
}





/*

package br.senai.sp.jandira.gestorestacionamento.gestorestacionamento.UI;
import br.senai.sp.jandira.gestorestacionamento.gestorestacionamento.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class cadastroUI extends VBox {

    public void TelaInicial() {

        Label titulo = new Label("Bem-vindo!");
        Button btnEntrar = new Button("Entrar");


        this.getChildren().addAll(titulo, btnEntrar);
        this.setSpacing(20);
    }



}


*/

