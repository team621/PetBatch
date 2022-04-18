package com.team.petBatch;

import com.team.vo.AbandonmentVO;
import com.team.vo.regionVO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AbandonmentPublicAPI {
    //공공 API URL 생성 (지역 조회)
    public String createRegionURL(String ServiceKey, String uprCd){
        String requestUrl = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/";
        if(uprCd.equals("")) requestUrl += "sido?ServiceKey="+ServiceKey;
        else if(!uprCd.equals("")) requestUrl += "sigungu?upr_cd="+uprCd+"&ServiceKey="+ServiceKey;

        return requestUrl;
    }

    //공공 API URL 생성 (유기동물 조회)
    public String createURL(String ServiceKey, String bgnde, String endde, String pageNo, String numOfRows, String cityCd){
        String requestUrl = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?";
        requestUrl += "ServiceKey="+ServiceKey+"&";
        requestUrl += "bgnde="+bgnde+"&";
        requestUrl += "endde="+endde+"&";
        requestUrl += "pageNo="+pageNo+"&";
        requestUrl += "numOfRows="+numOfRows+"&";
        requestUrl += "org_cd="+cityCd;

        return requestUrl;
    }

    //request RegionAPI
    public ArrayList<regionVO> requestRegionAPI(String URL, String flag){
        ArrayList<regionVO> regionList = new ArrayList<>();
        try{
            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(URL);

            // root tag
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("item");

            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    String orgCd = getTagValue("orgCd", eElement);
                    String orgDownNm = getTagValue("orgdownNm", eElement);
                    String upperId = "";
                    if(flag.equals("city")) upperId = getTagValue("uprCd", eElement);
                    regionList.add(new regionVO(orgCd,upperId,orgDownNm));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return regionList;
    }

    //request API
    public ArrayList<AbandonmentVO> requestAPI(String URL, regionVO region, regionVO city){
        ArrayList<AbandonmentVO> abList = new ArrayList<>();
        try{
            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(URL);

            // root tag
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("item");

            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    Long id = Long.parseLong(getTagValue("desertionNo", eElement));
                    String ageStr = getTagValue("age", eElement);
                    if(ageStr.contains("년생")) ageStr = ageStr.replaceAll("\\(년생\\)","");
                    int age = Integer.parseInt(ageStr);
                    String kind = getTagValue("kindCd", eElement);
                    String animalType = (kind.substring(0,kind.indexOf("]"))).replace("[", "");
                    String kindCd = (kind.substring(kind.indexOf("]")+1)).replaceAll(" ","");
                    String happenDate = getTagValue("happenDt", eElement);
                    String happenPlace = getTagValue("happenPlace", eElement);
                    String careNm = getTagValue("careNm", eElement);
                    String careAddress = getTagValue("careAddr", eElement);
                    String thumnail = getTagValue("filename", eElement);
                    String image = getTagValue("popfile", eElement);
                    String processState = getTagValue("processState", eElement);
                    char sexCd = getTagValue("sexCd", eElement).charAt(0);
                    String specialMark = getTagValue("specialMark", eElement);
                    String weightStr = getTagValue("weight", eElement);
                    if(weightStr.contains("Kg")) weightStr = weightStr.replaceAll("\\(Kg\\)","");
                    Float weight = Float.parseFloat(weightStr);

                    abList.add(new AbandonmentVO(id, age, animalType, kindCd, region, city, happenDate, happenPlace, careNm, careAddress, thumnail, image, processState, sexCd, specialMark, weight));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return abList;
    }

    //Tag 값 불러오기
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null) return null;

        return nValue.getNodeValue();
    }
}
