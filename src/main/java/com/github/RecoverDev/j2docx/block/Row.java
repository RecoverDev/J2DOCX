package com.github.RecoverDev.j2docx.block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.properties.RowProperties;

/**
 * Представляет строку таблицы в модели документа J2DOCX.
 * 
 * <p>Строка таблицы является блочным элементом документа и содержит
 * последовательность ячеек ({@link Cell}).
 *
 * <p>Основные способы работы:
 * <ul>
 *   <li>создание строки — {@link #create()};</li>
 *   <li>добавление ячейки — {@link #cell(Cell)};</li>
 * </ul>
 */
public final class Row implements DocumentElement {

    private final List<Cell> cells = new ArrayList<>();

    private RowProperties properties = new RowProperties();

    private Row() {}

    /**
     * Создает пустую строку таблицы
     * @return пустая строка таблицы
     */
    public static Row create() {
        return new Row();
    }

    /**
     * Добавляет новую ячейку в строку таблицы. В качестве параметра принимает объект класса {@link Cell}
     * @param cell добавляемая ячейка
     * @return текущая строка
     * <pre>{@code
     * Row row = Row.create()
     *              .cell(Cell.of("Hello"));
     * }</pre>
     */
    public Row cell(Cell cell) {
        cells.add(cell);
        return this;
    }

    /**
     * Добавляет новую ячейку в строку
     * @param consumer ячейка, создаваемая через лямбда выражение
     * @return текущая строка
     * <pre>{@code 
     * Row row = Row.create()
     *              .cell(c -> c.add(Paragraph.of("Hello"));
     * }</pre>
     */
    public Row cell(Consumer<Cell> consumer) {
        Cell addCell = Cell.create();
        consumer.accept(addCell);
        cells.add(addCell);
        return this;
    }

    /**
     * Возвращает неименяемый список ячеек строки таблицы
     * @return неизменяемый список ячеек
     */
    public List<Cell> getCells() {
        return (List<Cell>) Collections.unmodifiableList(cells);
    }

    // установка свойств

    /**
     * Устанавливает свойства форматирования строки таблицы.
     * В качестве входного параметра принимает объект класса {@link RowProperties}
     * @param properties объект класса {@link RowProperties}
     * @return текущая строка таблицы
     * 
     * <pre>{@code
     * Row row = Row.create()
     *                    .properties(new RowProperties()
     *                                     .height(200)
     *                                     .cantSplit());
     * }</pre>
     */
    public Row properties(RowProperties properties) {
        this.properties = properties.copy();
        return this;
    }

    // реализация установки свойств через лябда

    /**
     * Устанавливает свойства форматирования строки таблицы.
     * @param consumer свойства строки таблицы полученные через лямбда выражение
     * <pre>{@code
     * Row row = Row.create()
     *                    .properties(p -> p
     *                                     .height(200)
     *                                     .cantSplit());
     * }</pre>
     * @return текущая строка таблицы
     */
    public Row properties(Consumer<RowProperties> consumer) {
        consumer.accept(this.properties);
        return this;
    }

    /**
     * Возвращает текущие свойства форматирования строки таблицы
     * @return свойства форматирования строки таблицы (объект класса {@link RowProperties})
     */
    public RowProperties getProperties() {
        return this.properties;
    }


}
