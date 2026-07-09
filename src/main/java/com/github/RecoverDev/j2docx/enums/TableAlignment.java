package com.github.RecoverDev.j2docx.enums;

public enum TableAlignment {

    LEFT("left"),
    CENTER("center"),
    RIGHT("right");

    private final String xmlValue;

    TableAlignment(String xmlValue) {
        this.xmlValue = xmlValue;
    }

    public String getXmlValue() {
        return xmlValue;
    }

}
