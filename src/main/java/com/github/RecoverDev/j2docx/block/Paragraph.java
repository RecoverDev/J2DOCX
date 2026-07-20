package com.github.RecoverDev.j2docx.block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.Stylable;
import com.github.RecoverDev.j2docx.inline.Inline;
import com.github.RecoverDev.j2docx.inline.Run;
import com.github.RecoverDev.j2docx.properties.ParagraphProperties;
import com.github.RecoverDev.j2docx.styles.ParagraphStyle;

/**
 * Представляет абзац в модели документа J2DOCX.
 *
 * <p>Абзац является блочным элементом документа и содержит
 * последовательность текстовых фрагментов ({@link Run}).
 *
 * <p>Основные способы работы:
 * <ul>
 *   <li>создание пустого абзаца — {@link #create()};</li>
 *   <li>создание абзаца из текста — {@link #of(String)};</li>
 *   <li>добавление тестового фрагмента — {@link #text(String)};</li>
 *   <li>настройка форматирования — {@code properties(...)}.</li>
 * </ul>
 */
public final class Paragraph implements DocumentElement, Block, Stylable<Paragraph, ParagraphStyle>  {
    
    private final List<Inline> inlines = new ArrayList<>();

    private ParagraphProperties properties = new ParagraphProperties();

    private ParagraphStyle style;

    private Paragraph() {}

    /**
     * Создает пустой абзац
     * @return пустой абзац
     */
    public static Paragraph create() {
        return new Paragraph();
    }

    /**
     * Создает абзац из указанного текста.
     * @param text текст, из которого создатся абзац
     * @return готовый абзац
     */
    public static Paragraph of(String text) {
        return create().add(Run.of(text));
    }

    /**
     * Добавляет новый текстовый фрагмент {@link Run}
     * @param inline добавляемый фрагмент
     * @return текущий абзац
     */
    public Paragraph add(Inline inline) {
        inlines.add(inline);
        return this;
    }

    /**
     * Добавляет новый текст к содержмому абзаца
     * @param text добавляемый текст
     * @return текущий абзац
     */
    public Paragraph text(String text) {
        return add(Run.of(text));
    }

    /**
     * Возвращает текстовое содержимое абзаца.
     * @return текстовое содержимое абзаца
     */
    public String getText() {
        StringBuilder text = new StringBuilder();
        inlines
            .stream()
            .filter(inline -> inline instanceof Run)
            .forEach(run -> text.append(((Run)run).getText()));
        return text.toString();
    }

    /**
     * Возвращает список текстовых блоков (объектов {@link Run})
     * @return список текстовых блоков
     */
    public List<Inline> getInlines() {
        return Collections.unmodifiableList(this.inlines);
    }

    /**
     * Возвращает установленный стиль
     * @return установленный стиль
     */
    @Override
    public ParagraphStyle getStyle() {
        return this.style; 
    }

    /**
     * Устанавливает стиль, который нужно использовать при форматировании абзаца
     * @param style стиль
     * @return текущий абзац
     */
    @Override
    public Paragraph style(ParagraphStyle style) {
        this.style = style;
        return this;
    }

    // установка свойств

    /**
     * Устанавливает свойства форматирования абзаца.
     * В качестве входного параметра принимает объект класса {@link ParagraphProperties}
     * @param properties объект класса {@link ParagraphProperties}
     * @return текущий абзац
     * 
     * <pre>{@code
     * Paragraph paragraph = Paragraph.of("Hello, World!")
     *                              .properties(new ParagraphProperties()
     *                                              .alignment(ParagraphAlignment.CENTER));
     * }</pre>
     */
    public Paragraph properties(ParagraphProperties properties) {
        this.properties = properties.copy();
        return this;
    }

    // реализация установки свойств через лябда

    /**
     * Устанавливает свойства форматирования абзаца.
     * @param consumer свойства абзаца полученные через лямбда выражение
     * <pre>{@code
     * Paragraph paragraph = Paragraph.of("Hello")
     *     .properties(p -> p.alignment(ParagraphAlignment.CENTER));
     * }</pre>
     * @return текущий абзац
     */
    public Paragraph properties(Consumer<ParagraphProperties> consumer) {
        consumer.accept(this.properties);
        return this;
    }

    /**
     * Возвращает текущие свойства форматирования абзаца
     * @return свойства форматирования абзаца (объект класса {@link ParagraphProperties})
     */
    public ParagraphProperties getProperties() {
        return this.properties;
    }

}
