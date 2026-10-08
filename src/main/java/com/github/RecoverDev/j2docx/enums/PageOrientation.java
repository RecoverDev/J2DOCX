package com.github.RecoverDev.j2docx.enums;

/**
 * Определяет ориентацию страницы документа.
 */
public enum PageOrientation {

    /**
     * Книжная ориентация страницы.
     */
    PORTRAIT("portrait"),

    /**
     * Альбомная ориентация страницы.
     */
    LANDSCAPE("landscape");

    private final String xmlValue;

    PageOrientation(String xmlValue) {
        this.xmlValue = xmlValue;
    }

    /**
     * Возвращает значение ориентации страницы,
     * используемое в формате OpenXML.
     *
     * @return XML-представление ориентации страницы
     */
    public String getXmlValue() {
        return xmlValue;
    }
}


