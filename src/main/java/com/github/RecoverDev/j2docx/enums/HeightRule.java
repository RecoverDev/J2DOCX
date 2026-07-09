package com.github.RecoverDev.j2docx.enums;

public enum HeightRule {

    AUTO("auto"),
    AT_LEAST("atLeast"),
    EXACT("exact");

    private final String xmlValue;

    HeightRule(String xmlValue) {
        this.xmlValue = xmlValue;
    }

    public String getXmlValue() {
        return xmlValue;
    }

}
