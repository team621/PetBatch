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

    public void mkJson(List<AbandonmentVO> list){
        JSONArray jsonArray = new JSONArray();
        JSONObject resultJson = new JSONObject();

        for (AbandonmentVO abandonmentVO : list) {
            JSONObject jsonObject = new JSONObject();
//리스트 안에 vo를 넘겨서 거기서 하나씩 만들기?
            jsonObject.put("id", Long.toString(abandonmentVO.getId()));
            jsonObject.put("age",Integer.toString(abandonmentVO.getAge()));
            jsonObject.put("animalType",abandonmentVO.getAnimalType());
            jsonObject.put("careAddress",abandonmentVO.getCareAddress());
            jsonObject.put("cityId",abandonmentVO.getProtectCity().getId());
            jsonObject.put("cityNm",abandonmentVO.getProtectCity().getRegionNm());
            jsonObject.put("regionId",abandonmentVO.getProtectRegion().getId());
            jsonObject.put("regionNm", abandonmentVO.getProtectRegion().getRegionNm());
            jsonObject.put("careNm",abandonmentVO.getCareNm());
            jsonObject.put("happenDate",abandonmentVO.getHappenDate());
            jsonObject.put("happenPlace",abandonmentVO.getHappenPlace());
            jsonObject.put("image",abandonmentVO.getImage());
            jsonObject.put("kindCd",abandonmentVO.getKindCd());
            jsonObject.put("sexCd",Character.toString(abandonmentVO.getSexCd()));
            jsonObject.put("weight",Float.toString(abandonmentVO.getWeight()));
            jsonObject.put("processState",abandonmentVO.getProcessState());
            jsonObject.put("specialMark",abandonmentVO.getSpecialMark());
            jsonObject.put("thumnail",abandonmentVO.getThumnail());

            resultJson.put("result",jsonObject);

            jsonArray.add(jsonObject);
        }

        System.out.println("jsonArray = " + jsonArray);
    }

}
