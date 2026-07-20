package com.github.RecoverDev.j2docx.block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.Stylable;
import com.github.RecoverDev.j2docx.properties.TableProperties;
import com.github.RecoverDev.j2docx.styles.TableStyle;

/**
 * Представляет таблицу в модели документа J2DOCX.
 * 
 * <p>Таблица является блочным элементом документа и содержит
 * последовательность строк ({@link Row}).
 *
 * <p>Основные способы работы:
 * <ul>
 *   <li>создание таблицы — {@link #create()};</li>
 *   <li>добавление строки - {@link #row(Row)};</li>
 *   <li>настройка форматирования — {@code properties(...)}.</li>
 * </ul>
 */
public final class Table implements Block, Stylable<Table, TableStyle>, DocumentElement {

    private final List<Row> rows = new ArrayList<>();

    private TableProperties properties = new TableProperties();

    private TableStyle style;

    private Table() {}


    /**
     * Создает пустую таблицу
     * @return пустая таблица
     */
    public static Table create() {
        return new Table();
    }

    /**
     * Добавляет новую строку в таблицу. В качестве параметра принимает объект класса {@link Row}
     * @param row добавляемая строка
     * @return текущая таблица
     * <pre>{@code
     * Table table = Table.create()
     *                  .row(Row.create().cell(Cell.of("Hello")));
     * }</pre>
     */
    public Table row(Row row) {
        rows.add(row);
        return this;
    }

    /**
     * Добавляет новую строку в таблицу. 
     * @param consumer строка, создаваемая через лямбда выражение
     * @return текущая таблица
     * <pre>{@code
     * Table table = Table.create()
     *                  .row(r -> r
     *                      .cell(Cell.of("Hello")));
     * }</pre>
     */
    public Table row(Consumer<Row> consumer) {
        Row addRows = Row.create();
        consumer.accept(addRows);
        this.rows.add(addRows);
        return this;
    }

    /**
     * Возвращает неизменяемый список строк таблицы
     * @return неизменяемый список строк таблицы
     */
    public List<Row> getRows() {
        return Collections.unmodifiableList(rows);
    }

    /**
     * Возвращает установленный стиль
     * @return установленный стиль
     */
    @Override
    public TableStyle getStyle() {
        return this.style; 
    }

    /**
     * Устанавливает стиль, который нужно использовать при форматировании таблицы
     * @param style стиль
     * @return текущая таблица
     */
    @Override
    public Table style(TableStyle style) {
        this.style = style;
        return this;
    }

    // установка свойств

    /**
     * Устанавливает свойства форматирования таблицы.
     * В качестве входного параметра принимает объект класса {@link TableProperties}
     * @param properties объект класса {@link TableProperties}
     * @return текущая таблица
     * 
     * <pre>{@code
     * Table table = Table.create()
     *                    .properties(new TableProperties()
     *                                     .alignment(TableAlignment.CENTER));
     * }</pre>
     */
    public Table properties(TableProperties properties) {
        this.properties = properties.copy();
        return this;
    }

    // реализация установки свойств через лябда

    /**
     * Устанавливает свойства форматирования таблицы.
     * @param consumer свойства таблицы полученные через лямбда выражение
     * <pre>{@code
     * Table table = Table.create()
     *                    .properties(p -> p
     *                                     .alignment(TableAlignment.CENTER));
     * }</pre>
     * @return текущая таблица
     */
    public Table properties(Consumer<TableProperties> consumer) {
        consumer.accept(this.properties);
        return this;
    }

    /**
     * Возвращает текущие свойства форматирования таблицы
     * @return свойства форматирования таблицы (объект класса {@link TableProperties})
     */
    public TableProperties getProperties() {
        return this.properties;
    }

}
