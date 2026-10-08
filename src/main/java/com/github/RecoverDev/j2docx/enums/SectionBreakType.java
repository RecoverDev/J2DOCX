package com.github.RecoverDev.j2docx.enums;

/**
 * Определяет способ начала новой секции документа.
 */
public enum SectionBreakType {

    /**
     * Новая секция начинается с новой страницы.
     */
    NEXT_PAGE("nextPage"),

    /**
     * Новая секция начинается на текущей странице.
     */
    CONTINUOUS("continuous"),

    /**
     * Новая секция начинается с новой четной страницы.
     */
    EVEN_PAGE("evenPage"),

    /**
     * Новая секция начинается с новой нечетной страницы.
     */
    ODD_PAGE("oddPage");

    private final String xmlValue;

    SectionBreakType(String xmlValue) {
        this.xmlValue = xmlValue;
    }

    /**
     * Возвращает значение типа разрыва секции,
     * используемое в формате OpenXML.
     *
     * @return XML-представление типа разрыва секции
     */
    public String getXmlValue() {
        return xmlValue;
    }
}