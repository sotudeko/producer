package org.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Producer {

  public String hello(){
    return "Message from the Producer... Hello World!";
  }

  public StringBuilder getData(String iqApiEndpoint) throws IOException {

    StringBuilder content = new StringBuilder();

    URL url = new URL(iqApiEndpoint);

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept", "application/json");
    conn.setRequestProperty("Authorization", "Basic YWRtaW46YWRtaW4xMjM=");

    if (conn.getResponseCode() != 200) {
      throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
    }

    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
    String line;

    while ((line = br.readLine()) != null) {
      content.append(line + "\n");
    }

    return content;
  }

}
