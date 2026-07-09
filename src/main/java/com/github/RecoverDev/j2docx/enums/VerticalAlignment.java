package com.github.RecoverDev.j2docx.enums;

public enum VerticalAlignment {

    TOP("top"),
    CENTER("center"),
    BOTTOM("bottom");
    
    private final String xmlValue;

    VerticalAlignment(String xmlValue) {
        this.xmlValue = xmlValue;
    }

    public String getXmlValue() {
        return xmlValue;
    }

}
