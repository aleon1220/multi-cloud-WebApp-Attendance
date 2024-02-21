package ui;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class UILinks {

  /**
   * Listing URLs
   */
  @Test
  public void ListAllXHTMLS() throws IOException{
    String webappPath = "/home/ws/code";
    List<String> urls = new ArrayList<>();
  
    Files.walk(Paths.get(webappPath))
        .filter(p -> p.toString().endsWith(".xhtml"))
        .forEach(p -> {
          String url = new StringBuilder("http://localhost:8080/Attendance-0.7.7/")
                        .append(p.toString().replace(webappPath, "")) 
                        .toString();
          urls.add(url);  
        });

    System.out.println(urls);
    System.out.println("Total of XHTML files" + urls.size());
  }
}
