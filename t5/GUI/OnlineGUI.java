import javafx.scene.control.Button;

import java.net.*;
import java.io.*;
import java.util.*;

public class OnlineGUI {
    private int fileSize;
    private String result;
    private List<String> stringList = new ArrayList<String>();

    public OnlineGUI(String string) {
        readTextArea(string);
    }

    public String getText(){
        return result;
    }

    public String getNext(Button button){
        next(button);
        return result;
    }
    
    private String createRequest(List<String> strings) {
        String result = new String();
        result = "list=";

        for(int i = 0; i < fileSize; i++){
            result = result + strings.get(i) + "%0D%0A";    
        }
        result = result + "&format=plain&rnd=new";

        return result;
    }

    private void readTextArea(String string) {
        this.stringList = new ArrayList<String>(Arrays.asList(string.split("\n")));
        this.fileSize = stringList.size();

        getRequest(stringList);

        this.result = new String();
        this.result = stringList.get(0) + "\n";

        stringList.remove(0);
    }

    private void next(Button button){
        if(!(stringList.isEmpty())){
            this.result = stringList.get(0) + "\n";
            stringList.remove(0);
            if(stringList.isEmpty()){
                button.setDisable(true);
            }
        } else {
            button.setDisable(true);
        }
    }
    
    public void getRequest(List<String> stringList) {
        try {
            String urlstr = "https://www.random.org/lists/?mode=advanced";
            URL url = new URL(urlstr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setDoOutput(true);
              
            String data = createRequest(stringList);
            // Envia dados pela conexão aberta
            con.getOutputStream().write(data.getBytes("UTF-8"));

            // Cria objeto que fará leitura da resposta pela conexão aberta
            BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

            // Lê resposta, linha por linha
            String responseLine;

            stringList.clear();

            while ((responseLine = in.readLine()) != null) {
                stringList.add(responseLine);
            }

            System.out.println("Shuffling through 'random.org'...");

            in.close();

        } catch (IOException e) {
            String string = new String("");

            for(int i = 0; i < fileSize; i++){
                string = string + stringList.get(i) + "\n";
            }

            System.out.println("Shuffling offline...");

            OfflineGUI off = new OfflineGUI(string);
            this.stringList = off.getStringList();
        }
    }
}