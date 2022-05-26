package com.team.petBatch;

import com.team.vo.AbandonmentVO;
import com.team.vo.cityVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class DBData {
    public List<AbandonmentVO> getDBData() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("batch");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        AbandonmentVO vo = new AbandonmentVO();
        cityVO voc = new cityVO();

        em.flush();
        em.clear();

        String query =
                "SELECT a FROM abandonment a inner join a.protectCity c inner join a.protectRegion r";
        List<AbandonmentVO> resultList = null;
        try {
            resultList = em.createQuery(query, AbandonmentVO.class).getResultList();
            for (AbandonmentVO abandonmentVO : resultList) {
                System.out.println("abandonmentVO.getProtectCity().getId() = " + abandonmentVO.getProtectCity().getId());
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
        return resultList;
    }

    public JSONArray mkJson(List<AbandonmentVO> list){
        String result = "";
        JSONArray resultArray = new JSONArray();

        for(int i=0; i<list.size(); i++){
            JSONObject resultObject = new JSONObject();

            resultObject.put("id",list.get(i).getId());
            resultObject.put("thumnail",list.get(i).getThumnail());
            resultObject.put("specialmark",list.get(i).getSpecialMark());
            resultObject.put("processState",list.get(i).getProcessState());
            resultObject.put("weight",list.get(i).getWeight());
            resultObject.put("sexCd",list.get(i).getSexCd());
            resultObject.put("kindCd",list.get(i).getKindCd());
            resultObject.put("image",list.get(i).getImage());
            resultObject.put("happenPlace",list.get(i).getHappenPlace());
            resultObject.put("happenDate",list.get(i).getHappenDate());
            resultObject.put("careNm",list.get(i).getCareNm());
            resultObject.put("careAddress",list.get(i).getCareAddress());
            resultObject.put("age",list.get(i).getAge());
            resultObject.put("animalType",list.get(i).getAnimalType());
            resultObject.put("protectCityId",list.get(i).getProtectCity().getId());
            resultObject.put("protectCityNm",list.get(i).getProtectCity().getRegionNm());
            resultObject.put("protectRegionId",list.get(i).getProtectRegion().getId());
            resultObject.put("protectRegionNm",list.get(i).getProtectRegion().getRegionNm());

            resultArray.add(resultObject);
        }

        return resultArray;
    }
}
