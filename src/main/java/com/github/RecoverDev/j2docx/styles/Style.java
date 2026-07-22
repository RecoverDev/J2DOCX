package com.github.RecoverDev.j2docx.styles;

import com.github.RecoverDev.j2docx.DocumentElement;

/**
 * Базовый класс для всех стилей документа.
 * <p>
 * Стиль представляет собой именованный набор параметров форматирования,
 * который может быть применен к различным элементам документа.
 * Конкретные типы стилей (например, стили абзаца, символов или таблиц)
 * наследуются от данного класса.
 * <p>
 * Каждый стиль может:
 * <ul>
 *     <li>иметь уникальный идентификатор;</li>
 *     <li>иметь отображаемое имя;</li>
 *     <li>наследовать свойства другого стиля;</li>
 *     <li>быть отмечен как стиль быстрого доступа (Quick Style);</li>
 *     <li>иметь приоритет отображения в пользовательском интерфейсе.</li>
 * </ul>
 *
 * @param <T> конкретный тип стиля
 */
public abstract class Style<T extends Style<T>> implements DocumentElement{

    private String styleId;
    private String name;
    private T baseOn;
    private Boolean qFormat;
    private Integer uiPriority;

    // StyleId
    /**
     * Назначает идентификатор стиля.
     * <p>
     * Идентификатор может быть установлен только один раз.
     * Если стиль уже содержит идентификатор, повторный вызов метода
     * не изменяет его значение.
     *
     * @param styleId уникальный идентификатор стиля
     * @return текущий экземпляр стиля
     */    
    public T assignStyleId(String styleId) {
        if (this.styleId == null) {
            this.styleId = styleId;
        }
        return self();
    }

    /**
     * Проверяет, назначен ли стилю идентификатор.
     *
     * @return {@code true}, если идентификатор установлен,
     * иначе {@code false}
     */
    public boolean hasStyleId() {
        return this.styleId != null;
    }

    /**
     * Возвращает идентификатор стиля.
     *
     * @return идентификатор стиля или {@code null},
     * если он не был назначен
     */    
    public String getStyleId() {
        return this.styleId;
    }

    // Name
    /**
     * Устанавливает отображаемое имя стиля.
     *
     * @param name имя стиля
     * @return текущий экземпляр стиля
     */    
    public T name(String name) {
        this.name = name;
        return self();
    }

    /**
     * Проверяет, задано ли имя стиля.
     *
     * @return {@code true}, если имя установлено,
     * иначе {@code false}
     */
    public boolean hasName() {
        return this.name != null;
    }

    /**
     * Возвращает отображаемое имя стиля.
     *
     * @return имя стиля или {@code null},
     * если оно не задано
     */
    public String getName() {
        return this.name;
    }

    /**
     * Удаляет имя стиля.
     *
     * @return текущий экземпляр стиля
     */
    public T clearName() {
        this.name = null;
        return self();
    }

    // BaseOn
    /**
     * Устанавливает стиль, свойства которого будут использоваться
     * в качестве базовых.
     * <p>
     * Если текущий стиль не содержит какое-либо свойство,
     * оно может быть унаследовано от базового стиля.
     *
     * @param baseOn базовый стиль
     * @return текущий экземпляр стиля
     */    
    public T basedOn(T baseOn) {
        this.baseOn = baseOn;
        return self();
    }

    /**
     * Проверяет, задан ли базовый стиль.
     *
     * @return {@code true}, если базовый стиль установлен,
     * иначе {@code false}
     */
    public boolean hasBasedOn() {
        return this.baseOn != null;
    }

    /**
     * Возвращает базовый стиль.
     *
     * @return базовый стиль или {@code null},
     * если он не задан
     */
    public T getBasedOn() {
        return this.baseOn;
    }

    /**
     * Удаляет ссылку на базовый стиль.
     *
     * @return текущий экземпляр стиля
     */
    public T clearBasedOn() {
        this.baseOn = null;
        return self();
    }

    // qFormat
    /**
     * Помечает стиль как стиль быстрого доступа (Quick Style).
     * <p>
     * Такой стиль может отображаться в галерее стилей
     * текстового редактора.
     *
     * @return текущий экземпляр стиля
     */    
    public T quickStyle() {
        this.qFormat = true;
        return self();
    }

    /**
     * Устанавливает признак стиля быстрого доступа.
     *
     * @param value {@code true}, если стиль должен считаться
     *              стилем быстрого доступа, иначе {@code false}
     * @return текущий экземпляр стиля
     */
    public T quickStyle(boolean value) {
        this.qFormat = value;
        return self();
    }

    /**
     * Проверяет, был ли явно задан признак Quick Style.
     *
     * @return {@code true}, если значение установлено,
     * иначе {@code false}
     */
    public boolean hasQuickStyle() {
        return this.qFormat != null;
    }

    /**
     * Возвращает признак стиля быстрого доступа.
     *
     * @return {@code true}, если стиль является Quick Style,
     * иначе {@code false}
     */
    public boolean getQuickStyle() {
        return Boolean.TRUE.equals(this.qFormat);
    }

    /**
     * Удаляет признак Quick Style.
     *
     * @return текущий экземпляр стиля
     */
    public T clearQuickStyle() {
        this.qFormat = null;
        return self();
    }

    // uiPriority
    /**
     * Устанавливает приоритет отображения стиля.
     * <p>
     * Приоритет используется текстовыми редакторами
     * для определения порядка отображения стилей
     * в пользовательском интерфейсе.
     *
     * @param value значение приоритета
     * @return текущий экземпляр стиля
     */    
    public T uiPriority(Integer value) {
        this.uiPriority = value;
        return self();
    }

    /**
     * Проверяет, установлен ли приоритет отображения.
     *
     * @return {@code true}, если приоритет задан,
     * иначе {@code false}
     */
    public boolean hasUiPriority() {
        return this.uiPriority != null;
    }

    /**
     * Возвращает приоритет отображения стиля.
     *
     * @return значение приоритета или {@code null},
     * если оно не задано
     */
    public Integer getUiPriority() {
        return this.uiPriority;
    }

    /**
     * Возвращает текущий экземпляр конкретного типа стиля.
     * <p>
     * Используется для реализации fluent API в базовом классе.
     *
     * @return текущий экземпляр стиля
     */
    protected abstract T self();

}
