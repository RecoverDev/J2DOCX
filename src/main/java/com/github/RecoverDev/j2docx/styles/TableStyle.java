package com.github.RecoverDev.j2docx.styles;

import java.util.function.Consumer;

import com.github.RecoverDev.j2docx.properties.CellProperties;
import com.github.RecoverDev.j2docx.properties.ParagraphProperties;
import com.github.RecoverDev.j2docx.properties.RowProperties;
import com.github.RecoverDev.j2docx.properties.RunProperties;
import com.github.RecoverDev.j2docx.properties.TableProperties;

/**
 * Представляет стиль таблицы документа.
 *
 * <p>Стиль таблицы объединяет свойства форматирования таблицы
 * ({@link TableProperties}), строк ({@link RowProperties}),
 * ячеек ({@link CellProperties}), абзацев
 * ({@link ParagraphProperties}) и текста
 * ({@link RunProperties}), автоматически применяемые ко всем
 * таблицам, использующим данный стиль.</p>
 *
 * <p>Каждая группа свойств создается лениво при первом обращении
 * к соответствующему методу, что позволяет не создавать объекты,
 * если они не используются.</p>
 *
 * <p>В текущей реализации стиль содержит только общие свойства
 * оформления таблицы. Поддержка условного форматирования
 * (например, оформление первой строки, последней строки,
 * чередующихся строк и столбцов) будет реализована
 * в последующих версиях библиотеки.</p>
 *
 * @since 0.2
 */
public final class TableStyle extends Style<TableStyle> {

    private TableProperties tableProperties;

    private RowProperties rowProperties;

    private CellProperties cellProperties;

    private ParagraphProperties paragraphProperties;

    private RunProperties runProperties;


    private TableStyle() {}

    /**
     * Создает новый стиль таблицы.
     *
     * @return новый экземпляр {@code TableStyle}.
     */
    public static TableStyle create() {
        return new TableStyle();
    }

    /**
     * Возвращает свойства форматирования таблицы.
     *
     * <p>Если объект свойств еще не создан, он будет создан автоматически.</p>
     *
     * @return свойства форматирования таблицы.
     */
    public TableProperties table() {
        if (this.tableProperties == null) {
            this.tableProperties = new TableProperties();
        }
        return this.tableProperties;
    }

    /**
     * Позволяет настроить свойства таблицы с помощью функционального интерфейса.
     *
     * <p>Если объект свойств еще не создан, он будет создан автоматически.</p>
     *
     * @param consumer функция настройки свойств таблицы.
     * @return текущий стиль таблицы.
     */
    public TableStyle table(Consumer<TableProperties> consumer) {
        consumer.accept(this.table());
        return self();
    }

    /**
     * Возвращает свойства форматирования строк таблицы.
     *
     * <p>Если объект свойств еще не создан, он будет создан автоматически.</p>
     *
     * @return свойства форматирования строк таблицы.
     */
    public RowProperties row() {
        if (this.rowProperties == null) {
            this.rowProperties = new RowProperties();
        }
        return this.rowProperties;
    }

    /**
     * Позволяет настроить свойства строки таблицы с помощью функционального интерфейса.
     *
     * <p>Если объект свойств еще не создан, он будет создан автоматически.</p>
     *
     * @param consumer функция настройки свойств строки таблицы.
     * @return текущий стиль таблицы.
     */
    public TableStyle row(Consumer<RowProperties> consumer) {
        consumer.accept(this.row());
        return self();
    }

    /**
     * Возвращает свойства форматирования ячеек таблицы.
     *
     * <p>Если объект свойств еще не создан, он будет создан автоматически.</p>
     *
     * @return свойства форматирования ячеек таблицы.
     */
    public CellProperties cell() {
        if (this.cellProperties == null) {
            this.cellProperties = new CellProperties();
        }
        return this.cellProperties;
    }

    /**
     * Позволяет настроить свойства ячеек таблицы с помощью функционального интерфейса.
     *
     * <p>Если объект свойств еще не создан, он будет создан автоматически.</p>
     *
     * @param consumer функция настройки свойств ячеек таблицы.
     * @return текущий стиль таблицы.
     */
    public TableStyle cell(Consumer<CellProperties> consumer) {
        consumer.accept(this.cell());
        return self();
    }

    /**
     * Возвращает свойства форматирования абзацев внутри таблицы.
     *
     * <p>Если объект свойств еще не создан, он будет создан автоматически.</p>
     *
     * @return свойства форматирования абзацев.
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
    public TableStyle paragraph(Consumer<ParagraphProperties> consumer) {
        consumer.accept(this.paragraph());
        return self();
    }

    /**
     * Возвращает свойства форматирования текста внутри таблицы.
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
    public TableStyle run(Consumer<RunProperties> consumer) {
        consumer.accept(this.run());
        return self();
    }

    @Override
    protected TableStyle self() {
        return this;
    }

}
