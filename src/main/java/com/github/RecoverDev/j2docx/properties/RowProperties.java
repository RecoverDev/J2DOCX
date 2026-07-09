package com.github.RecoverDev.j2docx.properties;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.enums.HeightRule;

/**
 * Представляет свойства форматирования строки таблицы.
 *
 * <p>Класс содержит параметры, определяющие внешний вид
 * и поведение строки при сериализации документа Word.
 *
 * <p>Поддерживаются следующие группы свойств:
 * <ul>
 *     <li>высота строки;</li>
 *     <li>разбиение строки между страницами;</li>
 *     <li>повторение строки в качестве заголовка таблицы.</li>
 * </ul>
 *
 * <p>Все методы настройки поддерживают цепочку вызовов (Fluent API).
 *
 * <pre>{@code
 * RowProperties properties = new RowProperties()
 *     .height(400)
 *     .heightRule(HeightRule.EXACT)
 *     .cantSplit()
 *     .repeatHeader();
 * }</pre>
 */
public class RowProperties implements DocumentElement {

    private Integer height;
    private HeightRule heightRule;
    private Boolean cantSplit;
    private Boolean repeatHeader;

    /**
     * Создает пустой набор свойств строки таблицы.
     */
    public RowProperties() {}

    /**
     * Конструктор копирования
     * <p>Создает копию указанного набора свойств.
     *
     * @param properties копируемый объект свойств
     */
    public RowProperties(RowProperties properties) {
        this.height = properties.height;
        this.heightRule = properties.heightRule;
        this.cantSplit = properties.cantSplit;
        this.repeatHeader = properties.repeatHeader;
    }

    /**
     * Создает независимую копию текущего объекта.
     *
     * @return копия текущего набора свойств
     */
    public RowProperties copy() {
        return new RowProperties(this);
    }

    /**
     * Проверяет, содержит ли объект хотя бы одно установленное свойство.
     *
     * <p>Используется при сериализации документа для определения
     * необходимости формирования элемента {@code <w:trPr>}.
     *
     * @return {@code true}, если задано хотя бы одно свойство
     */
    public boolean hasAnyProperties() {
        return hasHeight() ||
                hasHeightRule() ||
                hasCantSplit() ||
                hasRepeateHeader();
    }

    //Height
    /**
     * Устанавливает высоту строки.
     *
     * @param height высота строки в twips
     * @return текущий объект свойств
     */
    public RowProperties height(int value) {
        this.height = value;
        return this;
    }

    public boolean hasHeight() {
        return this.height != null;
    }

    public Integer getHeight() {
        return this.height;
    }

    public RowProperties clearHeight() {
        this.height = null;
        return this;
    }

    //HeightRule
    /**
     * Устанавливает режим определения высоты строки.
     *
     * @param rule правило определения высоты
     * @return текущий объект свойств
     */
    public RowProperties heightRule(HeightRule heightRule) {
        this.heightRule = heightRule;
        return this;
    }

    public boolean hasHeightRule() {
        return this.heightRule != null;
    }

    public HeightRule getHeightRule() {
        return this.heightRule;
    }

    public RowProperties clearHeightRule() {
        this.heightRule = null;
        return this;
    }

    //CantSplit
    /**
     * Запрещает разбиение строки между страницами.
     *
     * @return текущий объект свойств
     */
    public RowProperties cantSplit() {
        return this.cantSplit(true);
    }
    
    /**
     * Управляет возможностью разбиения строки между страницами.
     *
     * @param value {@code true} — запретить разбиение,
     *              {@code false} — разрешить
     * @return текущий объект свойств
     */
    public RowProperties cantSplit(boolean value) {
        this.cantSplit = value;
        return this;
    }
    
    public boolean hasCantSplit() {
        return this.cantSplit != null;
    }
    
    public boolean isCantSplit() {
        return Boolean.TRUE.equals(this.cantSplit);
    }
    
    public RowProperties clearCantSplit() {
        this.cantSplit = null;
        return this;
    }

    //RepeateHeader
    /**
     * Повторяет строку как заголовок таблицы
     * на каждой новой странице.
     *
     * @return текущий объект свойств
     */
    public RowProperties repeateHeader() {
        return this.repeateHeader(true);
    }
    
    /**
     * Управляет повторением строки в качестве заголовка таблицы.
     *
     * @param value {@code true} — повторять строку,
     *              {@code false} — не повторять
     * @return текущий объект свойств
     */
    public RowProperties repeateHeader(boolean value) {
        this.repeatHeader = value;
        return this;
    }
    
    public boolean hasRepeateHeader() {
        return this.repeatHeader != null;
    }
    
    public boolean isRepeateHeader() {
        return Boolean.TRUE.equals(this.repeatHeader);
    }
    
    public RowProperties clearRepeateHeader() {
        this.repeatHeader = null;
        return this;
    }

}
