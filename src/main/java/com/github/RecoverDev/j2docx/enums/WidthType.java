package com.github.RecoverDev.j2docx.enums;

public enum WidthType {

    AUTO("auto"),
    DXA("dxa"),
    PERCENT("percent"),
    NIL("nil");
    
    private final String xmlValue;

    WidthType(String xmlValue) {
        this.xmlValue = xmlValue;
    }

    public String getXmlValue() {
        return xmlValue;
    }

}
