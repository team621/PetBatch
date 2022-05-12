package com.team.petBatch;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class elasticSearch{

    public void elastic(){
        try{
            HttpURLConnection conn = null;
            JSONObject responseJson = null;

            conn = requestURI("abandonment", "GET", conn);

            JSONObject commands = new JSONObject();

            int responseCode = conn.getResponseCode();
            StringBuilder sb = new StringBuilder();

            System.out.println("responseCode = " + responseCode);
            if (responseCode == 400 || responseCode == 401 || responseCode == 500) {
                System.out.println(responseCode + " Error!");
            }else if(responseCode == 404){
                conn = requestURI("abandonment", "PUT", conn);
            }else {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }
                System.out.println(sb.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static HttpURLConnection requestURI(String uri, String method, HttpURLConnection conn){
        try{
            String esServer = "http://127.0.0.1:9200/";
            String ELASTICSEARCH_URL = esServer+uri;
            URL url = new URL(ELASTICSEARCH_URL);

            conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod(method);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
