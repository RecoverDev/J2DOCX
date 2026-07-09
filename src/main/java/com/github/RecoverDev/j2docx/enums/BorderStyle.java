package com.github.RecoverDev.j2docx.enums;

public enum BorderStyle {

    NONE("none"),
    SINGLE("single"),
    DOUBLE("double"),
    DOTTED("dotted"),
    DASHED("dashed"),
    THICK("thick"),
    WAVE("wave"),
    INSET("inset"),
    OUTSET("outset");
    
    private final String xmlValue;

    BorderStyle(String xmlValue) {
        this.xmlValue = xmlValue;
    }

    public String getXmlValue() {
        return xmlValue;
    }
    
}
