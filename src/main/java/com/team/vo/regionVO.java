package com.team.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class regionVO {

    @Id
    @GeneratedValue
    private String id;

    private String upperId;
    private String regionNm;

    public regionVO() {}

    public regionVO(String id, String upperId, String regionNm) {
        this.id = id;
        this.upperId = upperId;
        this.regionNm = regionNm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpperId() {
        return upperId;
    }

    public void setUpperId(String upperId) {
        this.upperId = upperId;
    }

    public String getRegionNm() {
        return regionNm;
    }

    public void setRegionNm(String regionNm) {
        this.regionNm = regionNm;
    }
}
