package com.github.RecoverDev.j2docx.serialization.xml.rels;

public class RelationshipNode {

    private String id;
    private String type;
    private String target;

    public RelationshipNode() { }

    public RelationshipNode(String id, String type, String target) {
        this.id = id;
        this.type = type;
        this.target = target;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }
    
    public void setTarget(String target) {
        this.target = target;
    }

}
