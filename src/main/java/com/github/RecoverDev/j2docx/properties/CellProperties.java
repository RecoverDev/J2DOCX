package com.github.RecoverDev.j2docx.properties;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.enums.BorderStyle;
import com.github.RecoverDev.j2docx.enums.VerticalAlignment;
import com.github.RecoverDev.j2docx.enums.WidthType;

/**
 * Представляет свойства форматирования ячейки таблицы.
 *
 * <p>Класс содержит параметры, определяющие внешний вид
 * и расположение ячейки при сериализации документа Word.
 *
 * <p>Поддерживаются следующие группы свойств:
 * <ul>
 *     <li>ширина ячейки;</li>
 *     <li>вертикальное выравнивание содержимого;</li>
 *     <li>границы ячейки;</li>
 *     <li>цвет заливки;</li>
 *     <li>объединение ячеек по горизонтали.</li>
 * </ul>
 *
 * <p>Все методы настройки поддерживают цепочку вызовов (Fluent API).
 *
 * <pre>{@code
 * CellProperties properties = new CellProperties()
 *     .widthTwips(120)
 *     .verticalAlignment(CellVerticalAlignment.CENTER)
 *     .borderStyle(BorderStyle.SINGLE)
 *     .borderColor("000000")
 *     .backgroundColor("FFF2CC");
 * }</pre>
 */
public final class CellProperties implements DocumentElement {

    // Layout
    private Integer width;
    private WidthType widthType;

    // Borders
    private BorderStyle borderStyle;
    private Integer borderSize;
    private String borderColor;

    // Background
    private String backgroundColor;

    // Alignment
    private VerticalAlignment verticalAlignment;

    // Merge
    private Integer gridSpan;

    /**
     * Создает пустой набор свойств ячейки.
     */
    public CellProperties() {}

    // Копирующий конструктор
    /**
     * Копирующий конструктор
     * <p>Создает копию указанного набора свойств.
     *
     * @param properties копируемый объект свойств
     */    
    public CellProperties(CellProperties properties) {

        this.width = properties.width;
        this.widthType = properties.widthType;
        this.borderStyle = properties.borderStyle;
        this.borderSize = properties.borderSize;
        this.borderColor = properties.borderColor;
        this.backgroundColor = properties.backgroundColor;
        this.verticalAlignment = properties.verticalAlignment;
        this.gridSpan = properties.gridSpan;

    }

    /**
     * Создает независимую копию текущего объекта.
     *
     * @return копия текущего набора свойств
     */    
    public CellProperties copy() {
        return new CellProperties(this);
    }

    /**
     * Проверяет, содержит ли объект хотя бы одно установленное свойство.
     *
     * <p>Используется при сериализации документа для определения
     * необходимости формирования элемента {@code <w:tcPr>}.
     *
     * @return {@code true}, если задано хотя бы одно свойство
     */
    public boolean hasAnyProperties() {
        return hasWidth() ||
            hasBorderStyle() ||
            hasBorderSize() ||
            hasBorderColor() ||
            hasBackgroundColor() ||
            hasVerticalAlignment() ||
            hasGridSpan();
    }

    // Width
    /**
     * Устанавливает ширину ячейки в twips.
     *
     * @param width ширина ячейки
     * @return текущий объект свойств
     */
    public CellProperties widthTwips(int width) {
        this.width = width;
        this.widthType = WidthType.DXA;
        return this;
    }

    /**
     * Устанавливает ширину ячейки в процентах.
     *
     * @param percent ширина в процентах
     * @return текущий объект свойств
     */
    public CellProperties widthPercent(int width) {
        this.width = width * 50;
        this.widthType = WidthType.PERCENT;
        return this;
    }

    public boolean hasWidth() {
        return this.width != null;
    }

    public Integer getWidth() {
        return  this.widthType.equals(WidthType.PERCENT) ? this.width / 50 : this.width;
    }

    public CellProperties clearWidth() {
        this.width = null;
        this.widthType = null;
        return this;
    }

    // WidthType
    public WidthType getWidthType() {
        return this.widthType;
    }

    public boolean hasWidthType() {
        return this.widthType != null;
    }

    // Alignment
    /**
     * Устанавливает вертикальное выравнивание содержимого ячейки.
     *
     * @param alignment способ вертикального выравнивания
     * @return текущий объект свойств
     */    
    public CellProperties verticalAlignment(VerticalAlignment alignment) {
        this.verticalAlignment = alignment;
        return this;
    }

    public boolean hasVerticalAlignment() {
        return this.verticalAlignment != null;
    }

    public VerticalAlignment getVerticalAlignment() {
        return this.verticalAlignment;
    }

    public CellProperties clearVerticalAlignment() {
        this.verticalAlignment = null;
        return this;
    }

    // BorderStyle
    /**
     * Устанавливает стиль границ ячейки.
     *
     * @param borderStyle стиль границ
     * @return текущий объект свойств
     */
    public CellProperties borderStyle(BorderStyle borderStyle) {
        this.borderStyle = borderStyle;
        return this;
    }

    public boolean hasBorderStyle() {
        return this.borderStyle != null;
    }

    public BorderStyle getBorderStyle() {
        return this.borderStyle;
    }

    public CellProperties clearBorderStyle() {
        this.borderStyle = null;
        return this;
    }

    // BorderSize
    /**
     * Устанавливает толщину границ ячейки.
     *
     * <p>Размер задается в единицах OpenXML
     * (1/8 пункта).
     *
     * @param size толщина границы
     * @return текущий объект свойств
     */
    public CellProperties borderSize(int value) {
        this.borderSize = value;
        return this;
    }

    public boolean hasBorderSize() {
        return this.borderSize != null;
    }

    public Integer getBorderSize() {
        return this.borderSize;
    }

    public CellProperties clearBorderSize() {
        this.borderSize = null;
        return this;
    }

    // BorderColor
    /**
     * Устанавливает цвет границ ячейки.
     *
     * <p>Цвет задается шестнадцатеричным RGB-кодом
     * без символа '#'.
     *
     * @param color цвет границ
     * @return текущий объект свойств
     */
    public CellProperties borderColor(String value) {
        this.borderColor = value;
        return this;
    }

    public boolean hasBorderColor() {
        return this.borderColor != null;
    }

    public String getBorderColor() {
        return this.borderColor;
    }

    public CellProperties clearBorderColor() {
        this.borderColor = null;
        return this;
    }

    // BackgroundColor
    /**
     * Устанавливает цвет заливки ячейки.
     *
     * <p>Цвет задается шестнадцатеричным RGB-кодом
     * без символа '#'.
     *
     * @param color цвет заливки
     * @return текущий объект свойств
     */
    public CellProperties backgroundColor(String value) {
        this.backgroundColor = value;
        return this;
    }

    public boolean hasBackgroundColor() {
        return this.backgroundColor != null;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public CellProperties clearBackgroundColor() {
        this.backgroundColor = null;
        return this;
    }

    //gridSpan
    /**
     * Объединяет текущую ячейку с указанным количеством
     * соседних ячеек по горизонтали.
     *
     * @param span количество объединяемых столбцов
     * @return текущий объект свойств
     */
    public CellProperties gridSpan(int value) {
        this.gridSpan = value;
        return this;
    }

    public boolean hasGridSpan() {
        return this.gridSpan != null;
    }

    public Integer getGridSpan() {
        return this.gridSpan;
    }

    public CellProperties clearGridSpan() {
        this.gridSpan = null;
        return this;
    }

}
