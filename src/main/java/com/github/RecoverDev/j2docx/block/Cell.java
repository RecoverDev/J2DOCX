package com.github.RecoverDev.j2docx.block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.Stylable;
import com.github.RecoverDev.j2docx.properties.CellProperties;

/**
 * Представляет ячейку в строке таблицы в модели документа J2DOCX
 * 
 * <p>Ячейка является блочным элементом и может содержать 
 * последовательность других блочных элементов {@link Block}
 * (например, {@link Paragraph} и {@link Table}).
 * 
 * <p>Основные способы работы:
 * <ul>
 *   <li>создание пустой ячейки — {@link #create()};</li>
 *   <li>создание ячейки содержащей текст — {@link #of(String)};</li>
 *   <li>добавление блочного элемента — {@link #add(Block)};</li>
 *   <li>добавление тестового фрагмента — {@link #text(String)};</li>
 *   <li>настройка форматирования — {@code properties(...)}.</li>
 * </ul>
 */
public final class Cell implements Stylable, DocumentElement {

    private final List<Block> blocks = new ArrayList<>();

    private CellProperties properties = new CellProperties();

    private String styleId;

    private Cell() {}


    /**
     * Создание пустой ячейки таблицы
     * @return пустая ячейка таблицы
     */
    public static Cell create() {
        return new Cell();
    }

    /**
     * Создание ячейки таблицы, содержащей текстовый элемент {@link Paragraph}
     * @param text текст в ячейке
     * @return текущая ячейка
     */
    public static Cell of(String text) {
        return (new Cell()).text(text);
    }

    /**
     * Добавляет в ячейку блочный элемент ({@link Paragraph},  {@link Table})
     * @param block добавляемый элемент
     * @return текущая ячейка
     */
    public Cell add(Block block) {
        blocks.add(block);
        return this;
    }

    /**
     * Добавляет в ячейку текстовый элемент
     * @param text добавляемый текст
     * @return текущая ячейка
     */
    public Cell text(String text) {
        return add(Paragraph.of(text));
    }

    /**
     * Возвращает неименяемый список блочных элементов
     * @return неизменяемый список блочных элементов
     */
    public List<Block> getBlocks() {
        return (List<Block>) Collections.unmodifiableList(blocks);
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
     * Устанавливает стиль, который нужно использовать при форматировании ячейки
     * @param styleId идентификатор стиля
     */
    @Override
    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    // установка свойств

    /**
     * Устанавливает свойства форматирования ячейки.
     * В качестве входного параметра получает объект класса {@link CellProperties}
     * @param properties объект класса {@link CellProperties}
     * @return текущая ячейка
     * <pre>{@code
     * Cell cell = Cell.of("Hello")
     *                 .properties(new CellProperties()
     *                                  .verticalAlignment(VerticalAlignment.TOP));
     * }</pre>
     */
    public Cell properties(CellProperties properties) {
        this.properties = properties.copy();
        return this;
    }

    // реализация установки свойств через лябда

    /**
     * Устанавливает свойства форматирования ячейки.
     * @param consumer свойства ячейки полученные через лямбда выражение
     * @return текущая ячейка
     * <pre>{@code
     * Cell cell = Cell.of("Hello")
     *                 .properties(p -> p
     *                               .verticalAlignment(VerticalAlignment.TOP)
     *                               .borderStyle(BorderStyle.SINGLE));
     * }</pre>
     */
    public Cell properties(Consumer<CellProperties> consumer) {
        consumer.accept(this.properties);
        return this;
    }

    /**
     * Возвращает текущие свойства форматирования ячейки
     * @return свойства форматирования ячейки (объект класса {@link CellProperties})
     */
    public CellProperties getProperties() {
        return this.properties;
    }

}
