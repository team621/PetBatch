package com.team.petBatch;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class AbandonmentPublicAPI {
    //공공 API URL 생성 (지역 조회)
    public String createRegionURL(String ServiceKey, String uprCd){
        String requestUrl = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/";
        if(uprCd.equals("")) requestUrl += "sido?ServiceKey="+ServiceKey;
        else if(!uprCd.equals("")) requestUrl += "sigungu?upr_cd=uprCd&ServiceKey="+ServiceKey;

        return requestUrl;
    }

    //공공 API URL 생성 (유기동물 조회)
    public String createURL(String ServiceKey, String bgnde, String endde, String pageNo, String numOfRows){
        String requestUrl = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?";
        requestUrl += "ServiceKey="+ServiceKey+"&";
        requestUrl += "bgnde="+bgnde+"&";
        requestUrl += "endde="+endde+"&";
        requestUrl += "pageNo="+pageNo+"&";
        requestUrl += "numOfRows="+numOfRows;

        return requestUrl;
    }

    //request API
    public void requestAPI(String URL){
        try{
            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(URL);

            // root tag
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("item");

            for(int temp = 0; temp < nList.getLength(); temp++){
                Node nNode = nList.item(temp);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    System.out.println("######################");
                    System.out.println(eElement.getTextContent());
                    System.out.println("careNm  : " + getTagValue("careNm", eElement));
                    System.out.println("colorCd  : " + getTagValue("colorCd", eElement));
                    System.out.println("happenPlace : " + getTagValue("happenPlace", eElement));
                    System.out.println("kindCd  : " + getTagValue("kindCd", eElement));
                    System.out.println("specialMark  : " + getTagValue("specialMark", eElement));
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    //Tag 값 불러오기
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null) return null;

        return nValue.getNodeValue();
    }
}
