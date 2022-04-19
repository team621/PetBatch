package com.team.vo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="region")
public class regionVO {

    @Id
    @Column(name="region_id")
    private String id;

    private String regionNm;

    @OneToMany(mappedBy = "protectRegion")
    private List<AbandonmentVO> regions = new ArrayList<>();

    public regionVO() {}

    public regionVO(String id, String regionNm) {
        this.id = id;
        this.regionNm = regionNm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegionNm() {
        return regionNm;
    }

    public void setRegionNm(String regionNm) {
        this.regionNm = regionNm;
    }
}
