package com.github.RecoverDev.j2docx.properties;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.enums.ParagraphAlignment;

/**
 * Представляет свойства форматирования абзаца.
 *
 * <p>Класс содержит параметры, определяющие внешний вид и поведение
 * абзаца при сериализации в документ Word.
 *
 * <p>Поддерживаются следующие группы свойств:
 * <ul>
 *   <li>выравнивание;</li>
 *   <li>отступы;</li>
 *   <li>межстрочные интервалы и интервалы до/после абзаца;</li>
 *   <li>параметры разбиения по страницам.</li>
 * </ul>
 *
 * <p>Все методы настройки поддерживают цепочку вызовов (Fluent API).
 *
 * <pre>{@code
 * ParagraphProperties properties = new ParagraphProperties()
 *     .alignment(ParagraphAlignment.CENTER)
 *     .spacingBefore(12)
 *     .spacingAfter(6)
 *     .keepLines();
 * }</pre>
 */
public final class ParagraphProperties implements DocumentElement{

    // Alignment
    private ParagraphAlignment alignment;

    // Indentation
    private Integer leftIndent;
    private Integer rightIndent;
    private Integer firstLineIndent;

    // Spacing
    private Integer spacingBefore;
    private Integer spacingAfter;
    private Integer lineSpacing;

    // Pagination
    private Boolean keepNext;
    private Boolean keepLines;
    private Boolean pageBreakBefore;

    /**
     * Создает пустой набор свойств абзаца.
     */
    public ParagraphProperties() {}
    
    // Копирующий конструктор

    /**
     * Копирующий конструктор
     * <p>Создает копию указанного набора свойств.
     *
     * @param properties копируемый объект свойств
     */    
    public ParagraphProperties(ParagraphProperties properties) {
    
        this.alignment = properties.alignment;
        this.leftIndent = properties.leftIndent;
        this.rightIndent = properties.rightIndent;
        this.firstLineIndent = properties.firstLineIndent;
        this.spacingBefore = properties.spacingBefore;
        this.spacingAfter = properties.spacingAfter;
        this.lineSpacing = properties.lineSpacing;
        this.keepNext = properties.keepNext;
        this.keepLines = properties.keepLines;
        this.pageBreakBefore = properties.pageBreakBefore;

    }

    /**
     * Создает независимую копию текущего объекта.
     *
     * @return копия текущего набора свойств
     */    
    public ParagraphProperties copy() {
        return new ParagraphProperties(this);
    }

    /**
     * Проверяет, содержит ли объект хотя бы одно установленное свойство.
     *
     * <p>Используется при сериализации документа для определения
     * необходимости формирования элемента {@code <w:pPr>}.
     *
     * @return {@code true}, если задано хотя бы одно свойство
     */
    public boolean hasAnyProperty() {
        return (hasAlignment() || 
                hasFirstLineIndent() || 
                hasKeepLines() || 
                hasKeepNext() ||
                hasLeftIndent() ||
                hasLineSpacing() ||
                hasPageBreakBefore() ||
                hasRightIndent() ||
                hasSpacingAfter() ||
                hasSpacingBefore());
    }

    // Alignment

    /**
     * Устанавливает выравнивание абзаца.
     *
     * @param alignment способ выравнивания
     * @return текущий объект свойств
     */    
    public ParagraphProperties alignment(ParagraphAlignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public boolean hasAlignment() {
        return this.alignment != null;
    }

    public ParagraphAlignment getAlignment() {
        return this.alignment;
    }

    public ParagraphProperties clearAlignment() {
        this.alignment = null;
        return this;
    }

    // LeftIndent

    /**
     * Устанавливает отступ слева.
     *
     * @param value размер отступа в twips
     * @return текущий объект свойств
     */
    public ParagraphProperties leftIndent(int value) {
        this.leftIndent = value;
        return this;
    }

    public boolean hasLeftIndent() {
        return this.leftIndent != null;
    }

    public Integer getLeftIndent() {
        return this.leftIndent;
    }

    public ParagraphProperties clearLeftIndent() {
        this.leftIndent = null;
        return this;
    }

    // RightIndent

    /**
     * Устанавливает отступ справа.
     *
     * @param value размер отступа в twips
     * @return текущий объект свойств
     */    
    public ParagraphProperties rightIndent(int value) {
        this.rightIndent = value;
        return this;
    }

    public boolean hasRightIndent() {
        return this.rightIndent != null;
    }

    public Integer getRightIndent() {
        return this.rightIndent;
    }

    public ParagraphProperties clearRightIndent() {
        this.rightIndent = null;
        return this;
    }

    // FirstIndent
    
    /**
     * Устанавливает отступ первой строки.
     *
     * @param value размер отступа в twips
     * @return текущий объект свойств
     */    
    public ParagraphProperties firstLineIndent(int value) {
        this.firstLineIndent = value;
        return this;
    }

    public boolean hasFirstLineIndent() {
        return this.firstLineIndent != null;
    }

    public Integer getFirstLineIndent() {
        return this.firstLineIndent;
    }

    public ParagraphProperties clearFirstLineIndent() {
        this.firstLineIndent = null;
        return this;
    }

    // SpacingBefore
    
    /**
     * Устанавливает интервал перед абзацем.
     *
     * @param value размер интервала в twips
     * @return текущий объект свойств
     */    
    public ParagraphProperties spacingBefore(int value) {
        this.spacingBefore = value;
        return this;
    }

    public boolean hasSpacingBefore() {
        return this.spacingBefore != null;
    }

    public Integer getSpacingBefore() {
        return this.spacingBefore;
    }

    public ParagraphProperties clearSpacingBefore() {
        this.spacingBefore = null;
        return this;
    }

    // SpacingAfter

    /**
     * Устанавливает интервал после абзаца.
     *
     * @param value размер интервала в twips
     * @return текущий объект свойств
     */
    public ParagraphProperties spacingAfter(int value) {
        this.spacingAfter = value;
        return this;
    }

    public boolean hasSpacingAfter() {
        return this.spacingAfter != null;
    }

    public Integer getSpacingAfter() {
        return this.spacingAfter;
    }

    public ParagraphProperties clearSpacingAfter() {
        this.spacingAfter = null;
        return this;
    }

    // LineSpacing

    /**
     * Устанавливает межстрочный интервал.
     *
     * @param value значение межстрочного интервала
     * @return текущий объект свойств
     */
    public ParagraphProperties lineSpacing(int value) {
        this.lineSpacing = value;
        return this;
    }

    public boolean hasLineSpacing() {
        return this.lineSpacing != null;
    }

    public Integer getLineSpacing() {
        return this.lineSpacing;
    }

    public ParagraphProperties clearLineSpacing() {
        this.lineSpacing = null;
        return this;
    }

    // KeepNext

    /**
     * Запрещает разрыв между текущим и следующим абзацем.
     *
     * @return текущий объект свойств
     */
    public ParagraphProperties keepNext() {
        return this.keepNext(true);
    }
    
    /**
     * Управляет запретом разрыва между текущим и следующим абзацем.
     *
     * @param value {@code true} — запретить разрыв,
     *              {@code false} — разрешить
     * @return текущий объект свойств
     */
    public ParagraphProperties keepNext(boolean value) {
        this.keepNext = value;
        return this;
    }
    
    public boolean hasKeepNext() {
        return this.keepNext != null;
    }
    
    public boolean isKeepNext() {
        return Boolean.TRUE.equals(this.keepNext);
    }
    
    public ParagraphProperties clearKeepNext() {
        this.keepNext = null;
        return this;
    }

    // KeepLines

    /**
     * Запрещает разрыв строк текущего абзаца между страницами.
     *
     * @return текущий объект свойств
     */    
    public ParagraphProperties keepLines() {
        return this.keepLines(true);
    }
    
    /**
     * Управляет запретом разрыва текущего абзаца между страницами.
     *
     * @param value {@code true} — запретить разрыв,
     *              {@code false} — разрешить
     * @return текущий объект свойств
     */
    public ParagraphProperties keepLines(boolean value) {
        this.keepLines = value;
        return this;
    }
    
    public boolean hasKeepLines() {
        return this.keepLines != null;
    }
    
    public boolean isKeepLines() {
        return Boolean.TRUE.equals(this.keepLines);
    }
    
    public ParagraphProperties clearKeepLines() {
        this.keepLines = null;
        return this;
    }

    // PageBreakBefore

    /**
     * Начинает абзац с новой страницы.
     *
     * @return текущий объект свойств
     */    
    public ParagraphProperties pageBreakBefore() {
        return this.pageBreakBefore(true);
    }
    
    /**
     * Управляет разрешением начала абзаца с новой страницы.
     *
     * @param value {@code true} — абзац с новой страницы,
     *              {@code false} — продолжить на предыдущей
     * @return текущий объект свойств
     */
    public ParagraphProperties pageBreakBefore(boolean value) {
        this.pageBreakBefore = value;
        return this;
    }
    
    public boolean hasPageBreakBefore() {
        return this.pageBreakBefore != null;
    }
    
    public boolean isPageBreakBefore() {
        return Boolean.TRUE.equals(this.pageBreakBefore);
    }
    
    public ParagraphProperties clearPageBreakBefore() {
        this.pageBreakBefore = null;
        return this;
    }

}
