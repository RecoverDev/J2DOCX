package com.github.RecoverDev.j2docx.serialization.xml.numbering;

public class NumberingInstance {

    private Integer id;
    private Integer AbstrictNumberingId;

    public NumberingInstance(Integer id, Integer abstractNumberingId) {
        this.id = id;
        this.AbstrictNumberingId = abstractNumberingId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAbstrictNumberingId() {
        return AbstrictNumberingId;
    }

    public void setAbstrictNumberingId(Integer abstrictNumberingId) {
        AbstrictNumberingId = abstrictNumberingId;
    }

}
