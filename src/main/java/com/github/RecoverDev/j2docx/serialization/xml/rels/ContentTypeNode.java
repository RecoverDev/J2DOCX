package com.github.RecoverDev.j2docx.serialization.xml.rels;

public class ContentTypeNode {

    private String partName;
    private String contentType;
    
    public ContentTypeNode(String partName, String contentType) {
        this.partName = partName;
        this.contentType = contentType;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
