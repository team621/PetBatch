package com.team.petBatch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.json.simple.JSONArray;

import java.io.IOException;

public class elasticSearch{

    public void elastic(JSONArray resultArrays){
        String hostName = "127.0.0.1";
        int port = 9200;
        String schema = "http";
        HttpHost host = new HttpHost(hostName, port, schema);
        RestClientBuilder restClientBuilder = RestClient.builder(host);
        RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);

        String index = "abandonment";
        String id = "1";
        GetRequest getRequest = new GetRequest(index, id);
        RequestOptions options = RequestOptions.DEFAULT;
        try {
            System.out.println(client.get(getRequest, options));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //https://shanepark.tistory.com/139



    }
}
