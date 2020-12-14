/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radiateur;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author Salma Insaf
 */
public class Radiateur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, MalformedURLException, IOException {
        // TODO code application logic here
          int c=0;
        while (c<5000)
        {
            Thread.sleep(1000);
            System.out.println("Running...");
            c++;
            
            String charset = java.nio.charset.StandardCharsets.UTF_8.name();
            String url = "http://localhost:4567/radiateur";
            URLConnection connection = new URL(url).openConnection();
            connection.setDoOutput(false); 
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            
            InputStream response = connection.getInputStream();

            try (Scanner scanner = new Scanner(response)) 
            {
                String responseBody = scanner.useDelimiter("\\A").next();
                //System.out.println("Reponse de la requete HTTP POST:"+responseBody);
                
                // Recupere l'information pour le fonctionnement du radiateur
                JSONObject obj = new JSONObject(responseBody);
                String radiateurMarche=obj.getString("radiateur");

                if (radiateurMarche.contains("true"))
                     System.out.println("Radiateur On");
                else
                     System.out.println("Radiateur Off");
            }

        }
    }
    
}
