package com.github.RecoverDev.j2docx.styles;

import java.util.function.Consumer;

import com.github.RecoverDev.j2docx.properties.ParagraphProperties;
import com.github.RecoverDev.j2docx.properties.RunProperties;

/**
 * Представляет стиль абзаца документа.
 *
 * <p>Стиль абзаца объединяет свойства форматирования абзаца
 * ({@link ParagraphProperties}) и свойства форматирования текста
 * ({@link RunProperties}), которые автоматически применяются ко всем
 * абзацам, использующим данный стиль.</p>
 *
 * <p>Стиль может наследовать свойства другого стиля с помощью
 * {@code baseOn}, а также определять стиль следующего абзаца,
 * автоматически применяемый Word после завершения текущего.</p>
 *
 * <p>Объекты {@link ParagraphProperties} и {@link RunProperties}
 * создаются лениво при первом обращении к соответствующим методам.</p>
 *
 * @since 0.2
 */
public final class ParagraphStyle extends Style<ParagraphStyle>{

    private ParagraphProperties paragraphProperties;

    private RunProperties runProperties;

    private ParagraphStyle next;

    private ParagraphStyle() {}

    /**
     * Создает новый стиль абзаца.
     *
     * @return новый экземпляр {@code ParagraphStyle}.
     */
    public static ParagraphStyle create() {
        return new ParagraphStyle();
    }

    /**
     * Возвращает свойства форматирования абзаца.
     *
     * <p>Если объект свойств еще не создан, он будет создан автоматически.</p>
     *
     * @return свойства форматирования абзаца.
     */
    public ParagraphProperties paragraph() {
        if (this.paragraphProperties == null) {
            this.paragraphProperties = new ParagraphProperties();
        }
        return this.paragraphProperties;
    }

    /**
     * Позволяет настроить свойства абзаца с помощью функционального интерфейса.
     *
     * <p>Если объект свойств еще не создан, он будет создан автоматически.</p>
     *
     * @param consumer функция настройки свойств абзаца.
     * @return текущий стиль таблицы.
     */
    public ParagraphStyle paragraph(Consumer<ParagraphProperties> consumer) {
        consumer.accept(this.paragraph());
        return self();
    }

    /**
     * Возвращает свойства форматирования текста.
     *
     * <p>Если объект свойств еще не создан, он будет создан автоматически.</p>
     *
     * @return свойства форматирования текста.
     */
    public RunProperties run() {
        if (this.runProperties == null) {
            this.runProperties = new RunProperties();
        }
        return this.runProperties;
    }

    /**
     * Позволяет настроить свойства текствого блока с помощью функционального интерфейса.
     *
     * <p>Если объект свойств еще не создан, он будет создан автоматически.</p>
     *
     * @param consumer функция настройки свойств текстового блока.
     * @return текущий стиль таблицы.
     */
    public ParagraphStyle run(Consumer<RunProperties> consumer) {
        consumer.accept(this.run());
        return self();
    }

    // Next
    /**
     * Устанавливает стиль, который Word автоматически применяет
     * к следующему абзацу после завершения текущего.
     *
     * @param nextStyle стиль следующего абзаца.
     * @return текущий объект для продолжения цепочки вызовов.
     */
    public ParagraphStyle nextStyle(ParagraphStyle nextStyle) {
        this.next = nextStyle;
        return self();
    }

    /**
     * Проверяет, установлен ли стиль следующего абзаца.
     *
     * @return {@code true}, если стиль следующего абзаца задан,
     *         иначе {@code false}.
     */
    public boolean hasNextStyle() {
        return this.next != null;
    }

    /**
     * Возвращает стиль следующего абзаца.
     *
     * @return стиль следующего абзаца или {@code null},
     *         если он не установлен.
     */
    public ParagraphStyle getNextStyle() {
        return this.next;
    }

    /**
     * Удаляет ссылку на стиль следующего абзаца.
     *
     * @return текущий объект для продолжения цепочки вызовов.
     */
    public ParagraphStyle clearNextStyle() {
        this.next = null;
        return self();
    }

    @Override
    protected ParagraphStyle self() {
        return this;
    }

}
