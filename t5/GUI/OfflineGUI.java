import javafx.scene.control.Button;

import java.io.*;
import java.util.*;

public class OfflineGUI {
    private int fileSize;
    private String result;
    private List<String> stringList = new ArrayList<String>();
    
    public OfflineGUI(String string){
        readTextArea(string);
    }

    public List<String> getStringList(){
        this.result = result.replace("\n", "").replace("\r", "");
        stringList.add(0, this.result);
        return stringList;
    }

    private void readTextArea(String string){
        this.stringList = new ArrayList<String>(Arrays.asList(string.split("\n")));

        this.fileSize = stringList.size();

        Collections.shuffle(stringList);
        this.result = new String();
        this.result = stringList.get(0) + "\n";

        stringList.remove(0);
    }
}