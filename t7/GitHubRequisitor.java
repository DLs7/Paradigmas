import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.*;

import javafx.beans.property.SimpleStringProperty;

import java.net.*;
import java.io.*;
import java.util.*;

// javac -cp .:gson-2.8.5.jar DemoParseGithubWithGson.java
// java -cp .:gson-2.8.5.jar DemoParseGithubWithGson

public class GitHubRequisitor extends Thread {

  private DataThread data;
  private File file;

  private String url;

  public GitHubRequisitor(DataThread data, File file) {
    this.data = data;
    this.file = file;
  }

  @Override
  public void run() {
    
    BufferedReader reader = null;
    
    try {
      reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {}
		
		ArrayList<String> repos = new ArrayList<String>();
    
    String aux;
    
    try {
			while((aux = reader.readLine()) != null){
				System.out.println("Aux: " + aux);
				if(aux!=null){
					System.out.println("Added to rep");
					repos.add(aux);
				}
			}
		} catch (IOException e) {}


		for(String rep : repos) {
			System.out.println("Analyzing rep: " + rep);
			data.data.add(getData(rep));
    }
    
		data.conditionNotify();
	}


  public GitHubData getData(String rep) {
    String urlbase = rep;
    String firstcommitdata = "";
    String lastcommitdata = "";    
    Integer requestNumber = 1;
    Integer totalcommits = 0;
    Integer totalmsglength = 0;
    String repname = getRepName(rep);

    String author = "";
    String date = "";
    String message = "";
    String repository = "";
    String link = "";

    Boolean reachedEnd = false;

    while(!reachedEnd) {
      String urltorequest = urlbase + requestNumber.toString();
      
      try {
        URL url = new URL(urltorequest);
      
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
      
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        JsonParser parser = new JsonParser();
        JsonArray results = parser.parse(in.readLine()).getAsJsonArray();
        
        if(results.size() < 30){
          reachedEnd = true;
        } 
        
        totalcommits += results.size();
        
        for (JsonElement e : results) {
          
          author = 
          e.getAsJsonObject().get("commit")
          .getAsJsonObject().get("author").getAsString();

          date = 
          e.getAsJsonObject().get("commit")
          .getAsJsonObject().get("author")
          .getAsJsonObject().get("date").getAsString();

          message = 
          e.getAsJsonObject().get("commit")
          .getAsJsonObject().get("message").getAsString();

          repository = "";

          link = 
          e.getAsJsonObject().get("commit")
          .getAsJsonObject().get("link").getAsString();
        } 

        in.close();
      
      }catch(IOException e) { 
        System.out.println("Error while getting repository data");
        reachedEnd = true;
      }

      requestNumber++;
    }
    /*Float f = (float)totalmsglength/totalcommits;
    String mediatammsg = f.toString();*/
    System.out.println("Finish request from " + urlbase);
    return new GitHubData(author, date, message, repository, link);
  }

  private String getRepName(String rep) {
		String[] urlparts = rep.split("/");
		System.out.println(urlparts);
		try {
			return urlparts[6];
		}catch(IndexOutOfBoundsException e) {System.out.println("Error while getting rep name");}
		return "ERROR NAME";
	}
}