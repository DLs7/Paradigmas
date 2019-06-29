import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.stage.*;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.*;
import java.io.*;
import java.util.*;

public class EnadeUFSMExplorer extends Application {
    private int screenSize_x = 1280;
    private int screenSize_y = 720;

    @Override
    @SuppressWarnings("unchecked")
    public void start(Stage stage) throws Exception {
        
        // --------------------------------- VBOXs ---------------------------------


        VBox root = new VBox();
        VBox vbMenu = new VBox();
        VBox vbTable = new VBox();

               
        // --------------------------------- MENU ---------------------------------
        
        MenuBar menu = new MenuBar();

        final Menu file = new Menu("File");
        MenuItem reload = new MenuItem("Reload");
        MenuItem source = new MenuItem("Source");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.exit(0);
            }
        });

        final Menu help = new Menu("Help");
        MenuItem about = new MenuItem("About");
        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("About ENADE-UFSM Explorer");
                alert.setHeaderText("Welcome to ENADE-UFSM Explorer!");
                alert.setContentText("This software was developed by Augusto Dal'Asta, a Computer Science student at UFSM, Brazil.");
                alert.showAndWait();
            }
        });

        menu.getMenus().addAll(file, help);
        file.getItems().addAll(reload, source, exit);
        help.getItems().addAll(about);


        // --------------------------------- TABELA ---------------------------------

        TableView table = new TableView();

        TableColumn anoCol = new TableColumn("Ano");
        TableColumn provaCol = new TableColumn("Prova");
        TableColumn tipoQuestaoCol = new TableColumn("Tipo da Questao");
        TableColumn idQuestaoCol = new TableColumn("ID da Questao");
        TableColumn objetoCol = new TableColumn("Objeto");
        TableColumn acertosCursoCol = new TableColumn("Acertos do Curso");
        TableColumn acertosRegiaoCol = new TableColumn("Acertos Regionais");
        TableColumn acertosBrasilCol = new TableColumn("Acertos Nacionais");
        TableColumn difCol = new TableColumn("Dif.");

        table.getColumns().addAll(anoCol, provaCol, tipoQuestaoCol, idQuestaoCol, objetoCol, acertosCursoCol, acertosRegiaoCol, acertosBrasilCol, difCol);

        anoCol.prefWidthProperty().bind(table.widthProperty().divide(9));
        provaCol.prefWidthProperty().bind(table.widthProperty().divide(9));
        tipoQuestaoCol.prefWidthProperty().bind(table.widthProperty().divide(9));
        idQuestaoCol.prefWidthProperty().bind(table.widthProperty().divide(9));
        objetoCol.prefWidthProperty().bind(table.widthProperty().divide(9));
        idQuestaoCol.prefWidthProperty().bind(table.widthProperty().divide(9));
        acertosCursoCol.prefWidthProperty().bind(table.widthProperty().divide(9));
        acertosRegiaoCol.prefWidthProperty().bind(table.widthProperty().divide(9));
        acertosBrasilCol.prefWidthProperty().bind(table.widthProperty().divide(9));
        difCol.prefWidthProperty().bind(table.widthProperty().divide(9));


        // --------------------------------- STAGE SETUP ---------------------------------

        vbMenu.setAlignment(Pos.TOP_CENTER);
        vbMenu.getChildren().addAll(menu);

        vbTable.setAlignment(Pos.CENTER);
        vbTable.getChildren().addAll(table);

        root.getChildren().addAll(vbMenu, vbTable);

        stage.setTitle("ENADE UFSM");
        stage.setScene(new Scene(root, screenSize_x, screenSize_y));
        stage.show();

    }

    

    public static void main(String args[]) {
        launch(args);
    }
}