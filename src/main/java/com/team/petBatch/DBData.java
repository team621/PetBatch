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
        String result = "";

        for(int i=0; i<list.size(); i++){
        result += "{\"index\":{\"_id\":\""+i+"\"}}";
        result += "{\"id\":\""+list.get(i).getId()+"\"},";

        System.out.println("result = " + result);

        }
    }
}
