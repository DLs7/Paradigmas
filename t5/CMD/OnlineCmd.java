import java.net.*;
import java.io.*;
import java.util.*;

public class OnlineCmd {
    private int fileSize;
    private List<String> names = new ArrayList<String>();

    public OnlineCmd(File file) throws Exception {
        this.fileSize = fileSize(file, names);
    }

    private int fileSize(File file, List<String> names) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str = new String();

        int linenumber = 0;
        while ((str = br.readLine()) != null) {
            names.add(str);
            linenumber++;
        }

        return linenumber;
    }

    private String createRequest(List<String> strings) throws Exception {
        String result = new String();
        result = "list=";

        for(int i = 0; i < fileSize; i++){
            result = result + strings.get(i) + "%0D%0A";    
        }
        result = result + "&format=plain&rnd=new";
        //System.out.println(result);

        return result;

    }

    public void getRequest(File file) throws Exception {

  	    try {
            String option = "1";

            while(option.equals("1")) {
                String urlstr = "https://www.random.org/lists/?mode=advanced";
                URL url = new URL(urlstr);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                con.setDoOutput(true);
                
                // Configura dados que serão enviados na requisição
                // Nesse exemplo, os dados são as seguintes 3 strings:
                // Fulano
                // Beltrano
                // Sicrano
                // Os dados devem ser separados por %0D%0A, 
                // que corresponde a CR=LF usando HTML URL Encoding 
                // (https://www.w3schools.com/tags/ref_urlencode.asp)
                // Obs.: O formato desta requisição não é documentado pelo site random.org.
                // Esse formato foi descoberto usando uma ferramenta que navegadores 
                // oferecem para inspecionar o tráfego de requisições pela rede:
                // https://developers.google.com/web/tools/chrome-devtools/network/
                // ATENÇÃO: seu programa não deve enviar requisições em uma frequência maior
                // do que aquela que um humano conseguiria reproduzir :-) 
                // Caso haja mau uso do serviço, o site random.org vai bloquear suas requisições.
                // Veja regras para clientes automatizados em: https://www.random.org/clients/
                String data = createRequest(names);
                // Envia dados pela conexão aberta
                con.getOutputStream().write(data.getBytes("UTF-8"));

                // Cria objeto que fará leitura da resposta pela conexão aberta
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

                // Lê resposta, linha por linha
                String responseLine;
                List<String> responseList = new ArrayList<String>();

                while ((responseLine = in.readLine()) != null) {
                    responseList.add(responseLine);
                }
                // Mostra resposta
                Scanner scanner = new Scanner(System.in);
                for(int i = 0; i < fileSize; i++){
                    System.out.println((i + 1) + ". " + responseList.get(i));
                    scanner.nextLine();
                }

                System.out.println("A sua lista acabou. Deseja embaralha-la novamente?\n[1 = sim/qualquer outra coisa = nao]\n");
                Scanner scannerOption = new Scanner(System.in);

                option = scannerOption.nextLine();
                
                if(option != "1"){
                    in.close();
                }

                System.out.println("\f");
            }

        } catch (IOException e) {
            System.out.println("Usando metodo de embaralhamento offline. Conexao com 'random.org' nao pode ser estabelecida.");
            OfflineCmd off = new OfflineCmd(file);
            off.readFile(file);
        }
    }
}