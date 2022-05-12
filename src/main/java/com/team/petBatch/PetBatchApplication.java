package com.team.petBatch;

import com.team.vo.AbandonmentVO;
import com.team.vo.cityVO;
import com.team.vo.regionVO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PetBatchApplication {

    public static void main(String[] args) {
        //API 데이터 DB 저장
        saveAPIData(args[0], args[1]);

        //DB 데이터 가져오기
        DBData db = new DBData();

        //엘라스틱 서치
        //elasticSearch es = new elasticSearch();
        //es.elastic();
        //스프링부트 서버 실행
        //SpringApplication.run(PetBatchApplication.class, args);
    }


    public static void saveAPIData(String startDt, String endDt){
        String servieKey = "Z9nM4q9X0WrP6P45oSAZqwt1heXenYmrmXzqdEqNtwmUuucQzaH0vvpPYWLAKcoTTDVOQSzSjlwppeFWhonQHw%3D%3D";
        AbandonmentPublicAPI API = new AbandonmentPublicAPI();

        //공공 API URL 생성 (시도)
        String regionURL = API.createRegionURL(servieKey, "");
        ArrayList<regionVO> regionList = API.requestRegionAPI(regionURL);
        ArrayList<AbandonmentVO> abList = new ArrayList<>();

        for(int i=0; i<regionList.size(); i++){
            //공공 API URL 생성 (시군구)
            String cityURL = API.createRegionURL(servieKey, regionList.get(i).getId());
            ArrayList<cityVO> cityList = API.requestCityAPI(cityURL);
            for(int j=0; j<cityList.size(); j++){
                //공공 API URL 생성(유기 동물 조회)
                String abandonmentURL = API.createURL(servieKey,startDt,endDt,"1","10",cityList.get(j).getId());
                API.requestAPI(abandonmentURL, regionList.get(i), cityList.get(j), abList);
            }
        }

        for(int i=0; i<abList.size(); i++) System.out.println(abList.get(i).toString());

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("batch");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            for(int i=0; i<abList.size(); i++) em.persist(abList.get(i));
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}