package com.team.vo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="city")
public class cityVO {

    @Id
    @Column(name="city_id")
    private String id;

    private String upperId;
    private String regionNm;

    @OneToMany(mappedBy = "protectCity")
    private List<AbandonmentVO> cities = new ArrayList<>();

    public cityVO() {}

    public cityVO(String id, String upperId, String regionNm) {
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
