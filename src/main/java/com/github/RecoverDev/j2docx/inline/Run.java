package com.github.RecoverDev.j2docx.inline;

import java.util.Objects;
import java.util.function.Consumer;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.Stylable;
import com.github.RecoverDev.j2docx.properties.RunProperties;

/**
 * Представляет текстовый элемент в модели документа J2DOCX
 * 
 * <p>Является блочным элементом, содержит текст и свойства его форматирования
 * <p>Основные методы работы:
 * <ul>
 *   <li>создание элемента — {@link #of(String)};</li>
 *   <li>получение текста — {@link #getText()};</li>
 *   <li>настройка форматирования — {@code properties(...)}.</li>
 * </ul>
 * 
 */
public final class Run implements Inline, Stylable, DocumentElement {

    private String text;

    private String styleId;

    private RunProperties properties = new RunProperties();

    private Run(String text) {
        this.text = Objects.requireNonNull(text);
    }

    /**
     * Создание текстового элемента на основе текста
     * @param text добавляемый текст
     * @return созданный текстовый элемент
     */
    public static Run of(String text) {
        return new Run(text);
    }

    /**
     * Возвращает текст, содержащийся в элементе
     * @return сохраненный текст
     */
    public String getText() {
        return this.text;
    }

    /**
     * Возвращает Id установленного стиля
     * @return Id установленного стиля
     */
    @Override
    public String getStyleId() {
        return this.styleId;
    }

    /**
     * Устанавливает стиль, который нужно использовать при форматировании текстового элемента
     * @param styleId идентификатор стиля
     */
    @Override
    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    
    // установка свойств

    /**
     * Устанавливает свойства форматирования текста.
     * <p>В качестве входного параметра получает объект класса {@link RunProperties}
     * @param properties объект класса {@link RunProperties}
     * @return текущий текстовый элемент
     * <pre>{@code
     * Run run = Run.of("Hello")
     *              .properties(new RunProperties()
     *                              .fontFamily("Arial"));
     * }</pre>
     */
    public Run properties(RunProperties properties) {
        this.properties = properties.copy();
        return this;
    }

    // реализация установки свойств через лябда

    /**
     * Устанавливает свойства форматирования текста.
     * @param consumer свойства текста полученные через лямбда выражение
     * @return текущий текстовый элемент
     * <pre>{@code
     * Run run = Run.of("Hello")
     *              .properties(p -> p.fontFamily("Arial"));
     * }</pre>
     */
    public Run properties(Consumer<RunProperties> consumer) {
        consumer.accept(this.properties);
        return this;
    }

    /**
     * Возвращает текущие свойства форматирования текста
     * @return объект класса {@link RunProperties}
     */
    public RunProperties getProperties() {
        return this.properties;
    }

}
