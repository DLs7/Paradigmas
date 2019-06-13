import java.io.*;
import java.util.*;

class RandomPickerCmd {

    public static void main(String args[]) throws Exception{
        File file = new File(args[0]);
        
        System.out.println("Estabelecendo conexao com 'random.org' para embaralhar a lista presente em " + args[0]);
        Online on = new Online(file);
        on.getRequest(file);
    }
}