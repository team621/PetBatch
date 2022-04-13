package com.team.vo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "abandonment")
public class AbandonmentVO {

    @Id
    @GeneratedValue
    private Long id;
    //나이
    private int age;
    //동물 타입
    @Column(nullable = false)
    private String animalType;
    //품종
    private String kindCd;
    //보호 지역 (코드)
    @Column(nullable = false)
    private String regionCd;
    //보호 지역 (지역명)
    @Column(nullable = false)
    private String regionNm;
    //보호 도시 (코드)
    @Column(nullable = false)
    private String cityCd;
    //보호 도시 (도시명)
    @Column(nullable = false)
    private String cityNm;
    //발견 날짜
    private String happenDate;
    //발견 장소
    private String happenPlace;
    //보호 장소
    private String careNm;
    //보호 장소 주소
    private String careAddress;
    //썸네일 이미지
    private String thumnail;
    //이미지
    private String image;
    //상태
    @Column(nullable = false)
    private String processState;
    //성별
    @Column(length = 1)
    private char sexCd;
    //특이사항
    private String specialMark;
    //무게
    private int weight;

    public AbandonmentVO(Long id, int age, String animalType, String kindCd, String regionCd, String regionNm, String cityCd, String cityNm, String happenDate, String happenPlace, String careNm, String careAddress, String thumnail, String image, String processState, char sexCd, String specialMark, int weight) {
        this.id = id;
        this.age = age;
        this.animalType = animalType;
        this.kindCd = kindCd;
        this.regionCd = regionCd;
        this.regionNm = regionNm;
        this.cityCd = cityCd;
        this.cityNm = cityNm;
        this.happenDate = happenDate;
        this.happenPlace = happenPlace;
        this.careNm = careNm;
        this.careAddress = careAddress;
        this.thumnail = thumnail;
        this.image = image;
        this.processState = processState;
        this.sexCd = sexCd;
        this.specialMark = specialMark;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getKindCd() {
        return kindCd;
    }

    public void setKindCd(String kindCd) {
        this.kindCd = kindCd;
    }

    public String getRegionCd() {
        return regionCd;
    }

    public void setRegionCd(String regionCd) {
        this.regionCd = regionCd;
    }

    public String getRegionNm() {
        return regionNm;
    }

    public void setRegionNm(String regionNm) {
        this.regionNm = regionNm;
    }

    public String getCityCd() {
        return cityCd;
    }

    public void setCityCd(String cityCd) {
        this.cityCd = cityCd;
    }

    public String getCityNm() {
        return cityNm;
    }

    public void setCityNm(String cityNm) {
        this.cityNm = cityNm;
    }

    public String getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(String happenDate) {
        this.happenDate = happenDate;
    }

    public String getHappenPlace() {
        return happenPlace;
    }

    public void setHappenPlace(String happenPlace) {
        this.happenPlace = happenPlace;
    }

    public String getCareNm() {
        return careNm;
    }

    public void setCareNm(String careNm) {
        this.careNm = careNm;
    }

    public String getCareAddress() {
        return careAddress;
    }

    public void setCareAddress(String careAddress) {
        this.careAddress = careAddress;
    }

    public String getThumnail() {
        return thumnail;
    }

    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProcessState() {
        return processState;
    }

    public void setProcessState(String processState) {
        this.processState = processState;
    }

    public char getSexCd() {
        return sexCd;
    }

    public void setSexCd(char sexCd) {
        this.sexCd = sexCd;
    }

    public String getSpecialMark() {
        return specialMark;
    }

    public void setSpecialMark(String specialMark) {
        this.specialMark = specialMark;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
