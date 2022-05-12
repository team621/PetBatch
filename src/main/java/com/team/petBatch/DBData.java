package com.team.petBatch;

import com.team.vo.AbandonmentVO;
import com.team.vo.cityVO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class DBData {
//em이 초기화되면 데이터가 안보임
    public void getDBData(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("batch");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        AbandonmentVO vo = new AbandonmentVO();
        cityVO voc = new cityVO();

/*        voc.setId("1");
        em.persist(voc);
        vo.setId(1L);
        vo.setProtectCity(voc);

        em.persist(vo);*/


        em.flush();
        em.clear();

        String query = "SELECT a FROM abandonment a inner join a.protectCity c inner join a.protectRegion r";
        try{
            List<AbandonmentVO> resultList = em.createQuery(query, AbandonmentVO.class).getResultList();
            for (AbandonmentVO abandonmentVO : resultList) {
                System.out.println("abandonmentVO = " + abandonmentVO);
                System.out.println("abandonmentVO.getProtectCity().getId() = " + abandonmentVO.getProtectCity().getId());
            }

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
