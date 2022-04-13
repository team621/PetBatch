package com.team.petBatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetBatchApplication {

    public static void main(String[] args) {
        String servieKey = "Z9nM4q9X0WrP6P45oSAZqwt1heXenYmrmXzqdEqNtwmUuucQzaH0vvpPYWLAKcoTTDVOQSzSjlwppeFWhonQHw%3D%3D";
        AbandonmentPublicAPI API = new AbandonmentPublicAPI();

        //공공 API URL 생성 (시도)
        String regionURL = API.createRegionURL(servieKey, "");
        System.out.println("regionURL = " + regionURL);

        //공공 API URL 생성(유기 동물 조회)
        String URL = API.createURL(servieKey,args[0],args[1],"1","1");

        System.out.println("URL = " + URL);

        //request API
        //API.requestAPI(URL);



        //스프링부트 서버 실행
        //SpringApplication.run(PetBatchApplication.class, args);
    }

}