package com.jacla.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.lang.Thread;
import java.lang.InterruptedException;
import java.lang.ProcessBuilder;
import java.net.HttpURLConnection;
import java.awt.Desktop; 
import java.net.URL;
import java.io.*;
import java.net.URI;
import java.util.Random;


@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args)
        //throws InterruptedException
        {
        UI frame = new UI();
        frame.pack();
        frame.setSize(300, 200);
        frame.setVisible(true);
        SpringApplication.run(ApiApplication.class, args);
        
        /*try {
        String url = "http://localhost:8080/dumpster";
        URL obj = new URL(url);
        for (int i = 0; i<6;i++){
           
            //System.out.println("Coucou");
            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String[] listip={"192.1212.5","192.1212.4","192.1212.3"};
            Random r = new Random();
            int n = r.nextInt(101);
            int d= r.nextInt(3);
            String jsonInputString = "{"+"\"filling\""+":"+ n +","+ "\"connection\""+ ":" + "true"+","+ "\"ip\""+":"+"\""+ listip[d] + "\"}";
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println(response.toString());
                }
                Thread.sleep(4000);



        }
            }
        catch ( Exception e1) {
            e1.printStackTrace();
			};
     */
            
    }

}
