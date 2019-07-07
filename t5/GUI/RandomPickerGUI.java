import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.stage.*;
import javafx.scene.control.*;

import java.net.*;
import java.io.*;
import java.util.*;

public class RandomPickerGUI extends Application {

    OnlineGUI on;
    OfflineGUI off;
    
    private String readFile(File file) {
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            
            String result = new String();
            result = "";

            String str = new String();

            while ((str = br.readLine()) != null) {
                result = result + str + "\n";
            }

            return result;

        } catch(IOException e) {
            return "";
        }
    }

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {   
        VBox vbApp = new VBox();

        VBox vbTxt = new VBox();
        TextArea textArea = new TextArea();

        VBox vbMenu = new VBox();
        final Menu menu1 = new Menu("File");
        final Menu menu2 = new Menu("Help");
        MenuBar menuBar = new MenuBar();

        MenuItem menuItem1 = new MenuItem("Open");
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
                fileChooser.setTitle("Select a file");
                File file = fileChooser.showOpenDialog(stage);
                textArea.setText(readFile(file));
            }
        });

        MenuItem menuItem2 = new MenuItem("Exit");
        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.exit(0);
            }
        });

        MenuItem menuItem3 = new MenuItem("About");
        menuItem3.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("About Random Picker");
                alert.setHeaderText("Welcome to Random Picker!");
                alert.setContentText("This software was developed by Augusto Dal'Asta, a Computer Science student at UFSM, Brazil.");
                alert.showAndWait();
            }
        });

        VBox vbBtn = new VBox();
        vbBtn.setSpacing(-10);
        Label shuffle = new Label("");
        Label next = new Label("");
        Button b_shuffle = new Button("Shuffle");
        Button b_next = new Button("Next");
        b_next.setDisable(true);

        // Abaixo, classe anonima eh usada para implementar o tratamento 
        // do evento que ocorre quando usuario clica no objeto Button
        b_shuffle.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(!((textArea.getText()).isEmpty())){
                    shuffle.setText("Shuffling through 'random.org'...");
                    b_next.setDisable(true);
                    on = new OnlineGUI(textArea.getText());
                    shuffle.setText(on.getText());
                    b_next.setDisable(false);
                    b_next.setDisable(false);

                } else {
                    shuffle.setText("Could not shuffle. Your input is blank.");
                    b_next.setDisable(true);
                }
            }
        });

        b_next.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                shuffle.setText(on.getNext(b_next)); 
            }
        });

        menuBar.getMenus().addAll(menu1, menu2);
        menu1.getItems().addAll(menuItem1, menuItem2);
        menu2.getItems().addAll(menuItem3);

        vbMenu.setAlignment(Pos.TOP_LEFT);
        vbMenu.getChildren().addAll(menuBar);

        vbTxt.setAlignment(Pos.CENTER);
        vbTxt.getChildren().addAll(textArea);
        vbTxt.setMargin(textArea, new Insets(10, 10, 0, 10));

        vbBtn.setAlignment(Pos.BOTTOM_CENTER);
        vbBtn.getChildren().addAll(shuffle, b_shuffle, next, b_next);
        vbBtn.setMargin(b_next, new Insets(10, 10, 10, 10));
        vbBtn.setMargin(shuffle, new Insets(50, 50, 50, 50));

        vbApp.getChildren().addAll(vbMenu, vbTxt, vbBtn);

        stage.setTitle("Random Picker");
        stage.setScene(new Scene(vbApp, 1000, 500));
        stage.show();
    }
}