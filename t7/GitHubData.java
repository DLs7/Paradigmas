import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.*;

import javafx.beans.property.SimpleStringProperty;

import java.net.*;
import java.io.*;
import java.util.*;

public class GitHubData {
    SimpleStringProperty author;
    SimpleStringProperty date;
    SimpleStringProperty message;
    SimpleStringProperty repository;
    SimpleStringProperty link;

    public GitHubData(String author, String date, 
    String message, String repository, String link) {
      
      this.author = new SimpleStringProperty(author);
      this.date = new SimpleStringProperty(date);
      this.message = new SimpleStringProperty(message);
      this.repository = new SimpleStringProperty(repository);
      this.link = new SimpleStringProperty(link);
      
    }

    public SimpleStringProperty propertyAuthor() {
      return author; 
    }
    public String getAuthor() {
      return author.get();
    }
    public void setAuthor(String d) {
       author.set(d);
    }

    public SimpleStringProperty propertyDate() {
        return date;
      }
      public String getdate() {
        return date.get();
      }
      public void setdate(String d) {
         date.set(d);
    }

    public SimpleStringProperty propertyMessage() {
        return message;
      }
      public String getMessage() {
        return message.get();
      }
      public void setMessage(String d) {
         message.set(d);
    }

    public SimpleStringProperty propertyRepository() {
        return repository;
      }
      public String getRepository() {
        return repository.get();
      }
      public void setRepository(String d) {
         repository.set(d);
    }

    public SimpleStringProperty propertyLink() {
        return link;
      }
      public String getLink() {
        return link.get();
      }
      public void setLink(String d) {
         link.set(d);
    }

}