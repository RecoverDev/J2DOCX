package com.github.RecoverDev.j2docx.enums;

public enum NumberingStyle {

    DECIMAL("decimal"),
    UPPER_ROMAN("upperRoman"),
    LOWER_ROMAN("lowerRoman"),
    UPPER_LETTER("upperLetter"),
    LOWER_LETTER("lowerLetter"),
    BULLET("bullet"),
    ORDINAL("ordinal"),
    CARDINAL_TEXT("cardinalText"),
    HEX("hex");

    private final String xmlValue;

    NumberingStyle(String xmlValue) {
        this.xmlValue = xmlValue;
    }

    public String getXmlValue() {
        return xmlValue;
    }


}
