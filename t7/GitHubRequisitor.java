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
      for(GitHubData d : getData(rep)){
        data.data.add(d);
      }
    }
    
		data.conditionNotify();
	}


  public ArrayList<GitHubData> getData(String rep) {
    ArrayList<GitHubData> info = new ArrayList<GitHubData>();

    String urlbase = rep;
    String firstcommitdata = "";
    String lastcommitdata = "";    
    Integer requestNumber = 1;
    Integer totalcommits = 0;
    Integer totalmsglength = 0;

    String author = "";
    String date = "";
    String message = "";
    String repository = "";
    String link = "";

    Boolean reachedEnd = false;

    while(!reachedEnd) {
      urlbase.replace(" ", "");
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
          .getAsJsonObject().get("author")
          .getAsJsonObject().get("name").toString().replace("\"", "");

          date = 
          e.getAsJsonObject().get("commit")
          .getAsJsonObject().get("author")
          .getAsJsonObject().get("date").toString().replace("\"", "");

          message = 
          e.getAsJsonObject().get("commit")
          .getAsJsonObject().get("message").toString().replace("\"", "");

          repository = "";

          link = 
          e.getAsJsonObject().get("commit")
          .getAsJsonObject().get("url").toString().replace("\"", "");

          info.add(new GitHubData(author, date, message, repository, link));
        } 

        in.close();
      
      } catch(IOException e) { 
        e.printStackTrace();
        reachedEnd = true;
      }

      requestNumber++;
    }
    System.out.println("Finish request from " + urlbase);
    return info;
  }
}