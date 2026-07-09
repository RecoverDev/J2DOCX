package com.github.RecoverDev.j2docx.properties;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.enums.HighlightColor;
import com.github.RecoverDev.j2docx.enums.Underline;

/**
 * Представляет свойства форматирования текстового фрагмента.
 *
 * <p>Класс содержит параметры, определяющие внешний вид текста
 * при сериализации документа Word.
 *
 * <p>Поддерживаются следующие группы свойств:
 * <ul>
 *     <li>начертание текста;</li>
 *     <li>тип и размер шрифта;</li>
 *     <li>цвет текста;</li>
 *     <li>подчеркивание;</li>
 *     <li>зачеркивание;</li>
 *     <li>вертикальное положение текста.</li>
 * </ul>
 *
 * <p>Все методы настройки поддерживают цепочку вызовов (Fluent API).
 *
 * <pre>{@code
 * RunProperties properties = new RunProperties()
 *     .fontFamily("Calibri")
 *     .fontSize(24)
 *     .bold()
 *     .italic()
 *     .color("FF0000");
 * }</pre>
 */
public final class RunProperties implements DocumentElement{

    // Characters formating

    private Boolean bold;
    private Boolean italic;
    private Underline underline;

    //Font

    private String  fontFamily;
    private Integer fontSize;
    private String  color;
    private HighlightColor  highlight;

    /**
     * Создает пустой набор свойств текстового фрагмента.
     */
    public RunProperties() {}

    // Крпирующий конструктор
    /**
     * Копирующий конструктор
     * <p>Создает копию указанного набора свойств.
     *
     * @param properties копируемый объект свойств
     */    
    public RunProperties(RunProperties properties) {
        this.bold = properties.bold;
        this.italic = properties.italic;
        this.underline = properties.underline;
        this.fontFamily = properties.fontFamily;
        this.fontSize = properties.fontSize;
        this.color = properties.color;
        this.highlight = properties.highlight;
    }

    /**
     * Создает независимую копию текущего объекта.
     *
     * @return копия текущего набора свойств
     */    
    public RunProperties copy() {
        return new RunProperties(this);
    }


    /**
     * Проверяет, содержит ли объект хотя бы одно установленное свойство.
     *
     * <p>Используется при сериализации документа для определения
     * необходимости формирования элемента {@code <w:rPr>}.
     *
     * @return {@code true}, если задано хотя бы одно свойство
     */
    public boolean hasAnyProperties() {

        return hasBold() ||
                hasItalic() ||
                hasUnderline() ||
                hasFontFamily() ||
                hasFontSize() ||
                hasColor() ||
                hasHighlight();
    }

    // Bold
    /**
     * Делает текст полужирным.
     *
     * @return текущий объект свойств
     */    
    public RunProperties bold(boolean bold) {
        this.bold = bold;
        return this;
    }

    /**
     * Управляет полужирным начертанием текста.
     *
     * @param value {@code true} — включить полужирное начертание,
     *              {@code false} — отключить
     * @return текущий объект свойств
     */
    public RunProperties bold() {
        this.bold = true;
        return this;
    }

    public boolean hasBold() {
        return this.bold != null;
    }

    public boolean isBold() {
        return Boolean.TRUE.equals(bold);
    }

    public RunProperties clearBold() {
        this.bold = null;
        return this;
    }

    // Italic
    /**
     * Делает текст курсивным.
     *
     * @return текущий объект свойств
     */    
    public RunProperties italic() {
        this.italic = true;
        return this;
    }

    /**
     * Управляет курсивным начертанием текста.
     *
     * @param value {@code true} — включить курсивное начертание,
     *              {@code false} — отключить
     * @return текущий объект свойств
     */
    public RunProperties italic(boolean value) {
        this.italic = value;
        return this;
    }

    public boolean hasItalic() {
        return this.italic != null;
    }

    public boolean isItalic() {
        return Boolean.TRUE.equals(this.italic);
    }

    public RunProperties clearItalic() {
        this.italic = null;
        return this;
    }

    // Underline
    /**
     * Добавляет подчеркивание текста.
     *
     * @param underline тип подчеркивания
     * @return текущий объект свойств
     */
    public RunProperties underline() {
        this.underline = Underline.SINGLE;
        return this;
    }

    /**
     * Управляет подчеркнутым начертанием текста.
     *
     * @param value {@code SINGLE} — подчеркивание одной линией,
     *              {@code DOUBLE} — подчеркивание двумя линиями и так далее
     * @return текущий объект свойств
     */
    public RunProperties underline(Underline underline) {
        this.underline = underline;
        return this;
    }

    public boolean hasUnderline() {
        return this.underline != null;
    }

    public Underline getUnderline() {
        return this.underline;
    }

    public RunProperties clearUnderline() {
        this.underline = null;
        return this;
    }

    //fontFamily
    /**
     * Устанавливает семейство шрифта.
     *
     * @param family название семейства шрифта
     * @return текущий объект свойств
     */    
    public RunProperties fontFamily(String value) {
        this.fontFamily = value;
        return this;
    }

    public boolean hasFontFamily() {
        return this.fontFamily != null;
    }

    public String getFontFamily() {
        return this.fontFamily;
    }

    public RunProperties clearFontFamily() {
        this.fontFamily = null;
        return this;
    }

    //fontSize
    /**
     * Устанавливает размер шрифта.
     *
     * <p>Размер задается в пунктах.
     * При сериализации значение автоматически преобразуется
     * в формат OpenXML.
     *
     * @param size размер шрифта в пунктах
     * @return текущий объект свойств
     */
    public RunProperties fontSize(int value) {
        this.fontSize = value;
        return this;
    }

    public boolean hasFontSize() {
        return this.fontSize != null;
    }

    public int getFontSize() {
        return this.fontSize;
    }

    public RunProperties clearFontSize() {
        this.fontSize = null;
        return this;
    }

    // Color
    /**
     * Устанавливает цвет текста.
     *
     * @param color цвет в формате RGB без символа '#'
     *              (например, {@code FF0000})
     * @return текущий объект свойств
     */
    public RunProperties color(String value) {
        this.color = value;
        return this;
    }

    public boolean hasColor() {
        return this.color != null;
    }

    public String getColor() {
        return this.color;
    }

    public RunProperties clearColor() {
        this.color = null;
        return this;
    }

    //HighlightColor
    /**
     * Выделяет текст цветом.
     *
     * @param color цвет выделения
     * @return текущий объект свойств
     */
    public RunProperties highlight(HighlightColor highlightColor) {
        this.highlight = highlightColor;
        return this;
    }

    public RunProperties highlight() {
        this.highlight = HighlightColor.WHITE;
        return this;
    }

    public boolean hasHighlight () {
        return this.highlight != null;
    }

    public HighlightColor getHighlight () {
        return this.highlight;
    }

    public RunProperties clearHighlight () {
        this.highlight = null;
        return this;
    }

}
