package com.github.RecoverDev.j2docx.enums;

public enum Underline {

    SINGLE("single"),
    DOUBLE("double"),
    DOTTED("dotted"),
    DASHED("dashed"),
    WAVY("wavy");

    private final String xmlValue;

    Underline(String xmlValue) {
        this.xmlValue = xmlValue;
    }

    public String getXmlValue() {
        return xmlValue;
    }
    
}
