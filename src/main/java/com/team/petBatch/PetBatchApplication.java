package com.team.petBatch;

import com.team.vo.regionVO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class PetBatchApplication {

    public static void main(String[] args) {
        String servieKey = "Z9nM4q9X0WrP6P45oSAZqwt1heXenYmrmXzqdEqNtwmUuucQzaH0vvpPYWLAKcoTTDVOQSzSjlwppeFWhonQHw%3D%3D";
        AbandonmentPublicAPI API = new AbandonmentPublicAPI();

        //공공 API URL 생성 (시도)
        String regionURL = API.createRegionURL(servieKey, "");
        ArrayList<regionVO> regionList = API.requestRegionAPI(regionURL, "region");

        for(int i=0; i<regionList.size(); i++){
            String cityURL = API.createRegionURL(servieKey, regionList.get(i).getId());
            ArrayList<regionVO> cityList = API.requestRegionAPI(cityURL, "city");
            System.out.println("cityURL = " + cityURL);
            System.out.println("cityList.size() = " + cityList.size());
            for(int j=0; j<cityList.size(); j++) System.out.println("cityList.get(j).getRegionNm() = " + cityList.get(j).getRegionNm());
        }

        //공공 API URL 생성(유기 동물 조회)
        String URL = API.createURL(servieKey,args[0],args[1],"1","1");

        System.out.println("URL = " + URL);

        //request API
        //API.requestAPI(URL);



        //스프링부트 서버 실행
        //SpringApplication.run(PetBatchApplication.class, args);
    }

}