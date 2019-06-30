import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.stage.*;
import javafx.scene.control.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.*;
import java.io.*;
import java.util.*;

public class EnadeUFSMExplorer extends Application {
    private int screenSize_x = 1280;
    private int screenSize_y = 720;

    private int windowSize_x = 1024;
    private int windowSize_y = 680;

    private final ObservableList<DataEntry> data = FXCollections.observableArrayList();

    private String FILE_NAME = "enade.csv";
    private String FILE_URL = "https://docs.google.com/spreadsheets/d/e/2PACX-1vTO06Jdr3J1kPYoTPRkdUaq8XuslvSD5--FPMht-ilVBT1gExJXDPTiX0P3FsrxV5VKUZJrIUtH1wvN/pub?gid=0&single=true&output=csv";

    public void loadFile(){
        try (BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception");
            alert.setHeaderText("Sua URL e invalida.");
            alert.setContentText("Nao foi possivel baixar um .csv a partir da URL " + FILE_URL);

            Exception ex = new FileNotFoundException("Nao foi possível baixar um .csv a partir da URL " + FILE_URL);

            // Create expandable Exception.
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label("The exception stacktrace was:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            // Set expandable Exception into the dialog pane.
            alert.getDialogPane().setExpandableContent(expContent);

            alert.showAndWait();
        }
    }

    @SuppressWarnings("unchecked")
    public void setTable(TableView<DataEntry> table) {

        TableColumn<DataEntry,String> anoCol = new TableColumn<DataEntry,String>("Ano");
        TableColumn<DataEntry,String> provaCol = new TableColumn<DataEntry,String>("Prova");
        TableColumn<DataEntry,String> tipoQuestaoCol = new TableColumn<DataEntry,String>("Tipo da questao");
        TableColumn<DataEntry,String> idQuestaoCol = new TableColumn<DataEntry,String>("ID da questao");
        TableColumn<DataEntry,String> objetoCol = new TableColumn<DataEntry,String>("Objeto");
        TableColumn<DataEntry,String> acertosCursoCol = new TableColumn<DataEntry,String>("Acertos do curso");
        TableColumn<DataEntry,String> acertosRegiaoCol = new TableColumn<DataEntry,String>("Acertos regionais");
        TableColumn<DataEntry,String> acertosBrasilCol = new TableColumn<DataEntry,String>("Acertos nacionais");
        TableColumn<DataEntry,String> difCol = new TableColumn<DataEntry,String>("Dif.");
        TableColumn<DataEntry,String> gabaritoCol = new TableColumn<DataEntry,String>("Gabarito");
        TableColumn<DataEntry,String> imagemCol = new TableColumn<DataEntry,String>("Imagem");

        anoCol.setCellValueFactory(
        new PropertyValueFactory<DataEntry,String>("1"));
        provaCol.setCellValueFactory(
        new PropertyValueFactory<DataEntry,String>("2"));
        tipoQuestaoCol.setCellValueFactory(
        new PropertyValueFactory<DataEntry,String>("3"));
        idQuestaoCol.setCellValueFactory(
        new PropertyValueFactory<DataEntry,String>("4"));
        objetoCol.setCellValueFactory(
        new PropertyValueFactory<DataEntry,String>("5"));
        acertosCursoCol.setCellValueFactory(
        new PropertyValueFactory<DataEntry,String>("6"));
        acertosRegiaoCol.setCellValueFactory(
        new PropertyValueFactory<DataEntry,String>("7"));
        acertosBrasilCol.setCellValueFactory(
        new PropertyValueFactory<DataEntry,String>("8"));
        difCol.setCellValueFactory(
        new PropertyValueFactory<DataEntry,String>("9"));
        gabaritoCol.setCellValueFactory(
        new PropertyValueFactory<DataEntry,String>("10"));
        imagemCol.setCellValueFactory(
        new PropertyValueFactory<DataEntry,String>("11"));

        table.setItems(data);
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

        table.setPrefSize(screenSize_x, screenSize_y);
    }

    @Override
    public void start(Stage stage) throws Exception {

        loadFile();

        Stage window = new Stage();

        Label ano = new Label();
        Label prova = new Label();
        Label tipoQuestao = new Label();
        Label idQuestao = new Label();
        Label objeto = new Label();
        Label acertosCurso = new Label();
        Label acertosRegiao = new Label();
        Label acertosBrasil = new Label();
        Label dif = new Label();
        Label gabarito = new Label();
        Label fail = new Label();

        TableView<DataEntry> table = new TableView<DataEntry>();

        // --------------------------------- VBOXs ---------------------------------


        VBox root = new VBox();
        VBox vbMenu = new VBox();
        VBox vbTable = new VBox();

        VBox vbWindow = new VBox();
        VBox info = new VBox();
        VBox img = new VBox();

               
        // --------------------------------- MENU ---------------------------------
        
        MenuBar menu = new MenuBar();

        final Menu file = new Menu("File");

        MenuItem reload = new MenuItem("Reload");
        reload.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                loadFile();
                data.removeAll(data);
                readCSV();
                setTable(table);
            }
        });
        
        MenuItem source = new MenuItem("Source");
        source.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                TextInputDialog dialog = new TextInputDialog(FILE_URL);
                dialog.setTitle("Nova URL");
                dialog.setHeaderText("Escolha a URL para baixar o seu CSV");
                dialog.setContentText("URL:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    FILE_URL = result.get();
                }

                System.out.println(FILE_URL);
            }
        });

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

        setTable(table);

        table.setRowFactory(tv -> {
            TableRow<DataEntry> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {

                    DataEntry selection = table.getSelectionModel().getSelectedItem();

                    gabarito.setText("GABARITO: " + selection.get10() + "\n");
                    ano.setText("\n\nAno: " + selection.get1());
                    prova.setText("Prova: " + selection.get2());
                    tipoQuestao.setText("Tipo da Questao: " + selection.get3());
                    idQuestao.setText("ID da Questao: " + selection.get4());
                    objeto.setText("Objeto: " + selection.get5());
                    acertosCurso.setText("Acertos do curso: " + selection.get6());
                    acertosRegiao.setText("Acertos regionais: " + selection.get7());
                    acertosBrasil.setText("Acertos nacionais: " + selection.get8());
                    dif.setText("Dif.: " + selection.get9());

                    window.initOwner(stage);
                    window.initModality(Modality.APPLICATION_MODAL); 
                    window.showAndWait();
                }
            });
            return row ;
        });

        // --------------------------------- WINDOW SETUP ---------------------------------

        img.setAlignment(Pos.CENTER);
        img.getChildren().addAll(fail);

        info.setAlignment(Pos.BOTTOM_CENTER);
        info.getChildren().addAll(gabarito, ano, prova, tipoQuestao, idQuestao, objeto, acertosCurso, acertosRegiao, acertosBrasil, dif);

        vbWindow.getChildren().addAll(img, info);

        window.setTitle("Dado selecionado");
        window.setScene(new Scene(vbWindow, windowSize_x, windowSize_y));

        // --------------------------------- STAGE SETUP ---------------------------------

        vbMenu.setAlignment(Pos.TOP_CENTER);
        vbMenu.getChildren().addAll(menu);

        vbTable.setAlignment(Pos.CENTER);
        vbTable.getChildren().addAll(table);

        root.getChildren().addAll(vbMenu, vbTable);

        stage.setTitle("ENADE UFSM");
        stage.setScene(new Scene(root, screenSize_x, screenSize_y));
        stage.show();

        readCSV();
    }

        public String[] parseLine(String line) {

        List<String> result = new ArrayList<>();
        char separador = ',';
        char aspas = '"';

        if (line == null || line.isEmpty()) {
            String[] simpleArray = new String[result.size()];
            return result.toArray(simpleArray);
        }

        StringBuffer stringBuffer = new StringBuffer();
        boolean entreAspas = false;
        boolean pegarChars = false;

        char[] chars = line.toCharArray();

        for (char ch : chars) {

            if (entreAspas) {
                pegarChars = true;
                if (ch == aspas) {
                    entreAspas = false;
                } else {

                    stringBuffer.append(ch);

                }
            } else {
                if (ch == aspas) {
                    entreAspas = true;

                    if (pegarChars) {
                        stringBuffer.append('"');
                    }

                } else if (ch == separador) {

                    result.add(stringBuffer.toString());

                    stringBuffer = new StringBuffer();
                    pegarChars = false;

                } else if (ch == '\r') {
                    continue;
                } else if (ch == '\n') {
                    break;
                } else {
                    stringBuffer.append(ch);
                }
            }

        }

        result.add(stringBuffer.toString());
        
        String[] simpleArray = new String[result.size()];
        return result.toArray(simpleArray);
    }

    public void readCSV(){
        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(FILE_NAME);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if(tokens[0].equals("CC")){
                    for(String t : tokens) {
                        data.add(new DataEntry(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5],
                        tokens[8], tokens[9], tokens[10], tokens[11], tokens[7], tokens[13]));
                    }
                }
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception");
            alert.setHeaderText("Seu arquivo de entrada nao existe");
            alert.setContentText("Nao foi possivel abrir um .csv atraves do path ./" + FILE_NAME);

            Exception ex = new FileNotFoundException("Nao foi possivel abrir um .csv atraves do path ./" + FILE_NAME);

            // Create expandable Exception.
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label("The exception stacktrace was:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            // Set expandable Exception into the dialog pane.
            alert.getDialogPane().setExpandableContent(expContent);

            alert.showAndWait();             
        }
        catch(IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception");
            alert.setHeaderText("Erro ao ler o arquivo.");
            alert.setContentText("Ocorreu um erro ao ler o .csv atraves do path ./" + FILE_NAME);

            Exception ex = new FileNotFoundException("Nao foi possível baixar um .csv a partir da URL " + FILE_URL);

            // Create expandable Exception.
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label("The exception stacktrace was:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            // Set expandable Exception into the dialog pane.
            alert.getDialogPane().setExpandableContent(expContent);

            alert.showAndWait();
        }
    }

    public static void main(String args[]) {
        launch(args);
    }
}