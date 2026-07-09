package com.github.RecoverDev.j2docx.enums;

public enum HighlightColor {

    NONE("none"),
    YELLOW("yellow"),
    DARKYELLOW("darkyellow"),
    GREEN("green"),
    DARKGREEN("darkgreen"),
    CYAN("cyan"),
    DARKCYAN("darkcyan"),
    MAGENTA("magenta"),
    DARKMAGENTA("darkmagenta"),
    BLACK("black"),
    BLUE("blue"),
    DARKBLUE("darkblue"),
    RED("red"),
    DARKRED("darkred"),
    WHITE("white"),
    GRAY("gray"),
    DARKGRAY("darkgray");

    private final String xmlValue;

    HighlightColor(String xmlValue) {
        this.xmlValue = xmlValue;
    }

    public String getXmlValue() {
        return xmlValue;
    }

}
