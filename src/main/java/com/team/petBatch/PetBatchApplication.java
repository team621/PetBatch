package com.team.petBatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetBatchApplication {

    public static void main(String[] args) {
        String servieKey = "Z9nM4q9X0WrP6P45oSAZqwt1heXenYmrmXzqdEqNtwmUuucQzaH0vvpPYWLAKcoTTDVOQSzSjlwppeFWhonQHw%3D%3D";
        AbandonmentPublicAPI API = new AbandonmentPublicAPI();
        String bgnde = args[0];
        String endde = args[1];

        String URL = API.getURL(servieKey,bgnde,endde,"1","1");
        //abandonmentPublic.parseXML(URL);



        //스프링부트 서버 실행
        //SpringApplication.run(PetBatchApplication.class, args);
    }

}