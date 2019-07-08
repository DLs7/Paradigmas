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

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.*;
import java.io.*;
import java.util.*;

public class GitHubAnalyzerGUI extends Application{

    private final int screenSize_x = 1280;
    private final int screenSize_y = 720;

    private TableView<GitHubData> table;
    private ArrayList<GitHubData> dataStore;

    private File requestFile;


    public static void main(String args[]) {
        launch(args);
    }

    @SuppressWarnings("unchecked")
    public void setTable(TableView<GitHubData> table) {

        TableColumn<GitHubData,String> authorCol = new TableColumn<GitHubData,String>("Author");
        TableColumn<GitHubData,String> dateCol = new TableColumn<GitHubData,String>("Date");
        TableColumn<GitHubData,String> messageCol = new TableColumn<GitHubData,String>("Message");
        TableColumn<GitHubData,String> repositoryCol = new TableColumn<GitHubData,String>("Repository");
        TableColumn<GitHubData,String> linkCol = new TableColumn<GitHubData,String>("Link");

        authorCol.setCellValueFactory(
        new PropertyValueFactory<GitHubData,String>("Author"));
        dateCol.setCellValueFactory(
        new PropertyValueFactory<GitHubData,String>("Date"));
        messageCol.setCellValueFactory(
        new PropertyValueFactory<GitHubData,String>("Message"));
        repositoryCol.setCellValueFactory(
        new PropertyValueFactory<GitHubData,String>("Repository"));
        linkCol.setCellValueFactory(
        new PropertyValueFactory<GitHubData,String>("Link"));

        table.getColumns().addAll(authorCol, dateCol, messageCol, repositoryCol, linkCol);

        authorCol.prefWidthProperty().bind(table.widthProperty().divide(5));
        dateCol.prefWidthProperty().bind(table.widthProperty().divide(5));
        messageCol.prefWidthProperty().bind(table.widthProperty().divide(5));
        repositoryCol.prefWidthProperty().bind(table.widthProperty().divide(5));
        linkCol.prefWidthProperty().bind(table.widthProperty().divide(5));

        table.setPrefSize(screenSize_x, screenSize_y);

        if(requestFile != null){
            DataThread dataThread = new DataThread(new ArrayList<GitHubData>());
            GitHubRequisitor requisitor = new GitHubRequisitor(dataThread, requestFile);

            System.out.println("Starting Requests");

            requisitor.start();
            dataThread.conditionWait();
            
            dataStore = dataThread.data;
            table.setItems(FXCollections.observableArrayList(dataThread.data));

        } else {
            table.setItems(FXCollections.observableArrayList());
        }

    }

    @Override
    public void start(Stage stage){
        Stage window = new Stage();

        Label avgMessage = new Label();
        Label commitNumber = new Label();

        VBox vbMenu = new VBox();
        VBox vbTable = new VBox();
        VBox vbWindow = new VBox();
        VBox vbInfo = new VBox();

        table = new TableView<GitHubData>();
        setTable(table);

        table.setRowFactory(tv-> {
            TableRow<GitHubData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    GitHubData selection = table.getSelectionModel().getSelectedItem();

                    int messageCount = 0;
                    int commitCount = 0;

                    for(GitHubData d : dataStore) {
                        if((d.propertyRepository()).toString().equals((selection.propertyRepository()).toString())){
                            messageCount += d.propertyMessage().get().length();
                            commitCount++;
                        }
                    }

                    float averageMessage = (float) messageCount/commitCount;

                    avgMessage.setText("\n\n\nMedia de caracteres: " + Float.toString(averageMessage));
                    commitNumber.setText("Total de commits: " + commitCount);

                    window.showAndWait();
                }
            });
            return row;
        });

        vbInfo.setAlignment(Pos.CENTER);
        vbInfo.getChildren().addAll(avgMessage, commitNumber);

        vbWindow.getChildren().addAll(vbInfo);

        window.setTitle("Dado selecionado");
        window.setScene(new Scene(vbWindow, 240, 180));
        window.initOwner(stage);
        window.initModality(Modality.APPLICATION_MODAL);

        MenuBar menuBar = new MenuBar();
        final Menu menu1 = new Menu("File");

        MenuItem menuItem1 = new MenuItem("Open");
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
                fileChooser.setTitle("Select a file");
                File file = fileChooser.showOpenDialog(stage);
                if(file != null)
			        requestFile = file;
            }
        });

        MenuItem menuItem2 = new MenuItem("Exit");
        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.exit(0);
            }
        });


        final Menu menu2 = new Menu("Tools");

        MenuItem menuItem3 = new MenuItem("Commit Analyzer");
        menuItem3.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                setTable(table);
            }
        });


        final Menu menu3 = new Menu("Help");

        MenuItem menuItem4 = new MenuItem("About");
        menuItem4.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("About GitHub Analyzer");
                alert.setHeaderText("Welcome to GitHub Analyzer!");
                alert.setContentText("This software was developed by Augusto Dal'Asta, a Computer Science student at UFSM, Brazil.");
                alert.showAndWait();
            }
        });

        VBox root = new VBox();

        menuBar.getMenus().addAll(menu1, menu2, menu3);
        menu1.getItems().addAll(menuItem1, menuItem2);
        menu2.getItems().addAll(menuItem3);
        menu3.getItems().addAll(menuItem4);

        vbMenu.setAlignment(Pos.TOP_CENTER);
        vbMenu.getChildren().addAll(menuBar);

        vbTable.getChildren().addAll(table);

        root.getChildren().addAll(vbMenu, vbTable);

        stage.setTitle("GitHub Analyzer");
        stage.setScene(new Scene(root, screenSize_x, screenSize_y));
        stage.show();
    }
}