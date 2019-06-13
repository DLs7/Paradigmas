import java.io.*;
import java.util.*;

public class Offline {
    private int fileSize;
    private List<String> names = new ArrayList<String>();

    public Offline(File file) throws Exception {
        this.fileSize = fileSize(file);
    }

    private int fileSize(File file) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str = new String();

        int linenumber = 0;
        while ((str = br.readLine()) != null) {
            names.add(str);
            linenumber++;
        }

        return linenumber;
    }

    public void readFile(File file) throws Exception {
        String option = "1";
        
        while(option.equals("1")) {
            Scanner scan = new Scanner(System.in);

            Collections.shuffle(names);
            for(int i = 0; i < fileSize; i++){
                System.out.println((i + 1) + ". " + names.get(i));
                scan.nextLine();
            }
            System.out.println("A sua lista acabou. Deseja embaralha-la novamente?\n[1 = sim/qualquer outra coisa = nao]\n");
            Scanner scannerOption = new Scanner(System.in);

            option = scannerOption.nextLine();

            System.out.println("\f");
        }
    }
}