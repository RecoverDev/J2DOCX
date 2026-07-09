package com.github.RecoverDev.j2docx.properties;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.enums.BorderStyle;
import com.github.RecoverDev.j2docx.enums.TableAlignment;
import com.github.RecoverDev.j2docx.enums.WidthType;

/**
 * Представляет свойства форматирования таблицы.
 *
 * <p>Класс содержит параметры, определяющие внешний вид
 * и расположение таблицы при сериализации документа Word.
 *
 * <p>Поддерживаются следующие группы свойств:
 * <ul>
 *     <li>ширина таблицы;</li>
 *     <li>выравнивание;</li>
 *     <li>границы таблицы;</li>
 *     <li>расстояние между ячейками;</li>
 *     <li>отступ таблицы.</li>
 * </ul>
 *
 * <p>Все методы настройки поддерживают цепочку вызовов (Fluent API).
 *
 * <pre>{@code
 * TableProperties properties = new TableProperties()
 *     .widthPercent(100)
 *     .alignment(TableAlignment.CENTER)
 *     .borderStyle(BorderStyle.SINGLE)
 *     .borderSize(8)
 *     .borderColor("000000");
 * }</pre>
 */
public final class TableProperties implements DocumentElement {

    // Layout
    private Integer width;
    private WidthType widthType;

    // Alignment
    private TableAlignment alignment;

    // Borders
    private BorderStyle borderStyle;
    private Integer borderSize;
    private String borderColor;

    // Cell spacing
    private Integer cellSpacing;

    // Table indentation
    private Integer indent;

    /**
     * Создает пустой набор свойств таблицы.
     */
    public TableProperties() {}

    // Конструктор копирования
    /**
     * Копирующий конструктор
     * <p>Создает копию указанного набора свойств.
     *
     * @param properties копируемый объект свойств
     */    
    public TableProperties(TableProperties properties) {

        this.width = properties.width;
        this.widthType = properties.widthType;
        this.alignment = properties.alignment;
        this.borderStyle = properties.borderStyle;
        this.borderSize = properties.borderSize;
        this.borderColor = properties.borderColor;
        this.cellSpacing = properties.cellSpacing;
        this.indent = properties.indent;

    }

    /**
     * Создает независимую копию текущего объекта.
     *
     * @return копия текущего набора свойств
     */
    public TableProperties copy() {
        return new TableProperties(this);
    }

    /**
     * Проверяет, содержит ли объект хотя бы одно установленное свойство.
     *
     * <p>Используется при сериализации документа для определения
     * необходимости формирования элемента {@code <w:tblPr>}.
     *
     * @return {@code true}, если задано хотя бы одно свойство
     */
    public boolean hasAnyProperties() {

        return hasWidth() ||
                hasAlignment() ||
                hasBorderStyle() ||
                hasBorderSize() ||
                hasBorderColor() ||
                hasCellSpacing() ||
                hasIndent();
    }

    // Width
    /**
     * Устанавливает ширину таблицы в twips.
     *
     * @param width ширина таблицы
     * @return текущий объект свойств
     */    
    public TableProperties widthTwips(int width) {
        this.width = width;
        this.widthType = WidthType.DXA;
        return this;
    }

    /**
     * Устанавливает ширину таблицы в процентах.
     *
     * @param width ширина таблицы
     * @return текущий объект свойств
     */
    public TableProperties widthPercent(int width) {
        this.width = width * 50;
        this.widthType = WidthType.PERCENT;
        return this;
    }

    public boolean hasWidth() {
        return this.width != null;
    }

    public Integer getWidth() {
        return this.widthType == WidthType.PERCENT ? this.width / 50 : this.width;
    }

    public TableProperties clearWidth() {
        this.width = null;
        this.widthType = null;
        return this;
    }

    public boolean hasWidthType() {
        return this.widthType != null;
    }

    // WidthType
    public WidthType getWidthType() {
        return this.widthType;
    }

    // Alignment
    /**
     * Устанавливает выравнивание таблицы.
     *
     * @param alignment способ выравнивания таблицы
     * @return текущий объект свойств
     */    
    public TableProperties alignment(TableAlignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public boolean hasAlignment() {
        return this.alignment != null;
    }

    public TableAlignment getAlignment() {
        return this.alignment;
    }

    public TableProperties clearAlignment() {
        this.alignment = null;
        return this;
    }

    // BorderStyle
    /**
     * Устанавливает стиль границ таблицы.
     *
     * @param borderStyle стиль границ
     * @return текущий объект свойств
     */
    public TableProperties borderStyle(BorderStyle borderStyle) {
        this.borderStyle = borderStyle;
        return this;
    }

    public boolean hasBorderStyle() {
        return this.borderStyle != null;
    }

    public BorderStyle getBorderStyle() {
        return this.borderStyle;
    }

    public TableProperties clearBorderStyle() {
        this.borderStyle = null;
        return this;
    }

    // BorderSize
    /**
     * Устанавливает толщину границ таблицы.
     *
     * <p>Размер задается в единицах OpenXML
     * (1/8 пункта).
     *
     * @param value толщина границы
     * @return текущий объект свойств
     */
    public TableProperties borderSize(int value) {
        this.borderSize = value;
        return this;
    }

    public boolean hasBorderSize() {
        return this.borderSize != null;
    }

    public Integer getBorderSize() {
        return this.borderSize;
    }

    public TableProperties clearBorderSize() {
        this.borderSize = null;
        return this;
    }

    // BorderColor
    /**
     * Устанавливает цвет границ таблицы.
     *
     * <p>Цвет задается шестнадцатеричным RGB-кодом
     * без символа '#'.
     *
     * @param value цвет границ
     * @return текущий объект свойств
     */
    public TableProperties borderColor(String value) {
        this.borderColor = value;
        return this;
    }

    public boolean hasBorderColor() {
        return this.borderColor != null;
    }

    public String getBorderColor() {
        return this.borderColor;
    }

    public TableProperties clearBorderColor() {
        this.borderColor = null;
        return this;
    }

    // CellSpacing
    /**
     * Устанавливает расстояние между ячейками таблицы.
     *
     * @param value расстояние в twips
     * @return текущий объект свойств
     */
    public TableProperties cellSpacing(int value) {
        this.cellSpacing = value;
        return this;
    }

    public boolean hasCellSpacing() {
        return this.cellSpacing != null;
    }

    public Integer getCellSpacing() {
        return this.cellSpacing;
    }

    public TableProperties clearCellSpacing() {
        this.cellSpacing = null;
        return this;
    }

    // Indent
    /**
     * Устанавливает отступ таблицы от левого поля страницы.
     *
     * @param value размер отступа в twips
     * @return текущий объект свойств
     */
    public TableProperties indent(int value) {
        this.indent = value;
        return this;
    }

    public boolean hasIndent() {
        return this.indent != null;
    }

    public Integer getIndent() {
        return this.indent;
    }

    public TableProperties clearIndent() {
        this.indent = null;
        return this;
    }

}
