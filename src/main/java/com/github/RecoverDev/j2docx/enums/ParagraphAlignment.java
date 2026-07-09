package com.github.RecoverDev.j2docx.enums;

public enum ParagraphAlignment {

    LEFT("left"),
    CENTER("center"),
    RIGHT("right"),
    BOTH("both"),
    DISTRIBUTE("distribute"),
    THAI_DISTRIBUTE("thaiDistribute");

    private final String xmlValue;

    ParagraphAlignment(String xmlValue) {
        this.xmlValue = xmlValue;
    }

    public String getXmlValue() {
        return xmlValue;
    }
    
}
