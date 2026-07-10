package com.github.RecoverDev.j2docx.properties;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.enums.NumberingStyle;

/**
 * Свойства списка документа.
 * <p>
 * Класс определяет параметры оформления и нумерации списка.
 * Во время сериализации документа заданные свойства автоматически
 * преобразуются в соответствующие элементы OpenXML.
 * </p>
 * <p>
 * Поддерживаются следующие свойства:
 * <ul>
 *     <li>стиль нумерации списка;</li>
 *     <li>символ маркера для маркированных списков;</li>
 *     <li>шаблон отображения номера элемента;</li>
 *     <li>начальное значение нумерации.</li>
 * </ul>
 * </p>
 * <p>
 * Класс предоставляет DSL-интерфейс, позволяющий последовательно
 * настраивать свойства списка.
 * </p>
 *
 * @see com.github.RecoverDev.j2docx.block.Listing
 * @see com.github.RecoverDev.j2docx.enums.NumberingStyle 
 * @since 0.2
 */
public class ListingProperties implements DocumentElement {

    private NumberingStyle numberingStyle;

    private Character bullet;

    private String pattern;

    private Integer start;

    /**
     * Создает пустой набор свойств списка.
     */
    public ListingProperties() {}

    public ListingProperties(ListingProperties properties) {
        this.bullet = properties.bullet;
        this.numberingStyle = properties.numberingStyle;
        this.pattern = properties.pattern;
        this.start = properties.start;
    }

    /**
     * Создает независимую копию текущего набора свойств.
     *
     * @return копия объекта свойств.
     */
    public ListingProperties copy() {
        return new ListingProperties(this);
    }

    /**
     * Проверяет, содержит ли объект хотя бы одно заданное свойство.
     *
     * @return {@code true}, если задано хотя бы одно свойство,
     *         иначе {@code false}.
     */
    public boolean hasAnyProperties() {
        return hasNumberingStyle() ||
            hasBullet() ||
            hasPattern() ||
            hasStart();
    }

    // NumberingStyle
    /**
     * Устанавливает стиль нумерации списка.
     *
     * @param style стиль нумерации.
     * @return текущий объект свойств.
     */
    public ListingProperties numberingStyle(NumberingStyle style) {
        this.numberingStyle = style;
        return this;
    }

    public boolean hasNumberingStyle() {
        return this.numberingStyle != null;
    }

    public NumberingStyle getNumberingStyle() {
        return this.numberingStyle;
    }

    public ListingProperties clearNumberingStyle() {
        this.numberingStyle = null;
        return this;
    }

    // Bullet
    /**
     * Устанавливает символ маркера списка.
     * <p>
     * Используется только для маркированных списков.
     * </p>
     *
     * @param value символ маркера.
     * @return текущий объект свойств.
     */
    public ListingProperties bullet(Character value) {
        this.bullet = value;
        return this;
    }

    public boolean hasBullet() {
        return this.bullet != null;
    }

    public Character getBullet() {
        return this.bullet;
    }

    public ListingProperties clearBullet() {
        this.bullet = null;
        return this;
    }

    // Pattern
    /**
     * Устанавливает шаблон отображения номера элемента списка.
     * <p>
     * Шаблон определяет способ отображения номера элемента.
     * Например:
     * <ul>
     *     <li>{@code %1.}</li>
     *     <li>{@code (%1)}</li>
     *     <li>{@code [%1]}</li>
     * </ul>
     * </p>
     *
     * @param value шаблон отображения номера.
     * @return текущий объект свойств.
     */
    public ListingProperties pattern(String value) {
        this.pattern = value;
        return this;
    }

    public boolean hasPattern() {
        return this.pattern != null;
    }

    public String getPattern() {
        return this.pattern;
    }

    public ListingProperties clearPattern() {
        this.pattern = null;
        return this;
    }

    // Start
    /**
     * Устанавливает начальное значение нумерации списка.
     * <p>
     * Например, значение {@code 5} означает, что первый элемент списка
     * будет иметь номер {@code 5}.
     * </p>
     *
     * @param value начальное значение нумерации.
     * @return текущий объект свойств.
     */
    public ListingProperties start(int value) {
        this.start = value;
        return this;
    }

    public boolean hasStart() {
        return this.start != null;
    }

    public Integer getStart() {
        return this.start;
    }

    public ListingProperties clearStart() {
        this.start = null;
        return this;
    }








}
