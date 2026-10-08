package com.github.RecoverDev.j2docx.section;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.RootElement;
import com.github.RecoverDev.j2docx.block.Block;
import com.github.RecoverDev.j2docx.enums.PageOrientation;
import com.github.RecoverDev.j2docx.enums.SectionBreakType;
import com.github.RecoverDev.j2docx.headerfooter.Footer;
import com.github.RecoverDev.j2docx.headerfooter.Header;

/**
 * Представляет секцию документа.
 *
 * <p>Секция позволяет объединять элементы документа и определять
 * параметры оформления страниц, включая размер и ориентацию страницы,
 * поля, тип разрыва секции, а также колонтитулы.</p>
 *
 * <p>Содержимое секции состоит из элементов, реализующих интерфейс
 * {@link Block}.</p>
 */
public final class Section implements RootElement, DocumentElement {

    private final List<Block> blocks = new ArrayList<>();

    private PageOrientation orientation;
    private SectionBreakType breakType;
    private PageMargins margins;
    private PageSize pageSize;
    private Columns columns;

    private Header defaultHeader;
    private Header firstHeader;
    private Header evenHeader;

    private Footer defaultFooter;
    private Footer firstFooter;
    private Footer evenFooter;

    private Section() {
    }

    /**
     * Создает новую секцию документа.
     *
     * @return новая секция
     */
    public static Section create() {
        return new Section();
    }

    /**
     * Устанавливает ориентацию страниц секции.
     *
     * @param orientation ориентация страницы
     * @return текущая секция
     */
    public Section orientation(PageOrientation orientation) {
        this.orientation = Objects.requireNonNull(
                orientation,
                "Page orientation must not be null"
        );

        return this;
    }

    /**
     * Возвращает ориентацию страниц секции.
     *
     * @return ориентация страницы или {@code null},
     * если значение не установлено
     */
    public PageOrientation getOrientation() {
        return orientation;
    }

    /**
     * Проверяет, установлена ли ориентация страниц секции.
     *
     * @return {@code true}, если ориентация установлена
     */
    public boolean hasOrientation() {
        return this.orientation != null;
    }

    /**
     * Устанавливает тип разрыва секции.
     *
     * @param breakType тип разрыва секции
     * @return текущая секция
     */
    public Section breakType(SectionBreakType breakType) {
        this.breakType = Objects.requireNonNull(
                breakType,
                "Section break type must not be null"
        );

        return this;
    }

    /**
     * Возвращает тип разрыва секции.
     *
     * @return тип разрыва секции или {@code null},
     * если значение не установлено
     */
    public SectionBreakType getBreakType() {
        return breakType;
    }

    /**
     * Проверяет, установлен ли тип разрыва секции.
     *
     * @return {@code true}, если тип разрыва установлен
     */
    public boolean hasBreakType() {
        return this.breakType != null;
    }

    /**
     * Устанавливает поля страниц секции.
     *
     * @param margins поля страницы
     * @return текущая секция
     */
    public Section margins(PageMargins margins) {
        this.margins = Objects.requireNonNull(
                margins,
                "Page margins must not be null"
        );

        return this;
    }

    /**
     * Создает и настраивает поля страниц секции.
     *
     * @param consumer функция настройки полей страницы
     * @return текущая секция
     */
    public Section margins(Consumer<PageMargins> consumer) {
        PageMargins pageMargins = PageMargins.create();
        consumer.accept(pageMargins);
        this.margins = pageMargins;
        return this;
    }

    /**
     * Возвращает поля страниц секции.
     *
     * @return поля страницы или {@code null},
     * если значение не установлено
     */
    public PageMargins getMargins() {
        return this.margins;
    }

    /**
     * Проверяет, установлены ли поля страниц секции.
     *
     * @return {@code true}, если поля установлены
     */
    public boolean hasMargins() {
        return this.margins != null;
    }

    /**
     * Устанавливает размер страниц секции.
     *
     * @param pageSize размер страницы
     * @return текущая секция
     */
    public Section pageSize(PageSize pageSize) {
        this.pageSize = Objects.requireNonNull(
                pageSize,
                "Page size must not be null"
        );

        return this;
    }

    /**
     * Возвращает размер страниц секции.
     *
     * @return размер страницы или {@code null},
     * если значение не установлено
     */
    public PageSize getPageSize() {
        return this.pageSize;
    }

    /**
     * Проверяет, установлен ли размер страниц секции.
     *
     * @return {@code true}, если размер страницы установлен
     */
    public boolean hasPageSize() {
        return this.pageSize != null;
    }

    /**
     * Устанавливает основной верхний колонтитул секции.
     *
     * @param header верхний колонтитул
     * @return текущая секция
     */
    public Section defaultHeader(Header header) {
        this.defaultHeader = Objects.requireNonNull(
                header,
                "Default Header must not be null"
        );

        return this;
    }

    /**
     * Создает и настраивает основной верхний колонтитул секции.
     *
     * @param consumer функция настройки верхнего колонтитула
     * @return текущая секция
     */
    public Section defaultHeader(Consumer<Header> consumer) {
        Header header = new Header();
        consumer.accept(header);
        this.defaultHeader = header;
        return this;
    }

    /**
     * Возвращает основной верхний колонтитул секции.
     *
     * @return верхний колонтитул или {@code null},
     * если значение не установлено
     */
    public Header getDefaultHeader() {
        return this.defaultHeader;
    }

    /**
     * Проверяет, установлен ли основной верхний колонтитул.
     *
     * @return {@code true}, если верхний колонтитул установлен
     */
    public boolean hasDefaultHeader() {
        return this.defaultHeader != null;
    }

    /**
     * Устанавливает верхний колонтитул первой страницы секции.
     *
     * @param header верхний колонтитул
     * @return текущая секция
     */
    public Section firstHeader(Header header) {
        this.firstHeader = Objects.requireNonNull(
                header,
                "First Header must not be null"
        );

        return this;
    }

    /**
     * Создает и настраивает верхний колонтитул первой страницы секции.
     *
     * @param consumer функция настройки верхнего колонтитула
     * @return текущая секция
     */
    public Section firstHeader(Consumer<Header> consumer) {
        Header header = new Header();
        consumer.accept(header);
        this.firstHeader = header;
        return this;
    }

    /**
     * Возвращает верхний колонтитул первой страницы секции.
     *
     * @return верхний колонтитул или {@code null},
     * если значение не установлено
     */
    public Header getFirstHeader() {
        return this.firstHeader;
    }

    /**
     * Проверяет, установлен ли верхний колонтитул первой страницы.
     *
     * @return {@code true}, если верхний колонтитул установлен
     */
    public boolean hasFirstHeader() {
        return this.firstHeader != null;
    }

    /**
     * Устанавливает верхний колонтитул четных страниц секции.
     *
     * @param header верхний колонтитул
     * @return текущая секция
     */
    public Section evenHeader(Header header) {
        this.evenHeader = Objects.requireNonNull(
                header,
                "Even Header must not be null"
        );

        return this;
    }

    /**
     * Создает и настраивает верхний колонтитул четных страниц секции.
     *
     * @param consumer функция настройки верхнего колонтитула
     * @return текущая секция
     */
    public Section evenHeader(Consumer<Header> consumer) {
        Header header = new Header();
        consumer.accept(header);
        this.evenHeader = header;
        return this;
    }

    /**
     * Возвращает верхний колонтитул четных страниц секции.
     *
     * @return верхний колонтитул или {@code null},
     * если значение не установлено
     */
    public Header getEvenHeader() {
        return this.evenHeader;
    }

    /**
     * Проверяет, установлен ли верхний колонтитул четных страниц.
     *
     * @return {@code true}, если верхний колонтитул установлен
     */
    public boolean hasEvenHeader() {
        return this.evenHeader != null;
    }

    /**
     * Устанавливает основной нижний колонтитул секции.
     *
     * @param footer нижний колонтитул
     * @return текущая секция
     */
    public Section defaultFooter(Footer footer) {
        this.defaultFooter = Objects.requireNonNull(
                footer,
                "Default Footer must not be null"
        );

        return this;
    }

    /**
     * Создает и настраивает основной нижний колонтитул секции.
     *
     * @param consumer функция настройки нижнего колонтитула
     * @return текущая секция
     */
    public Section defaultFooter(Consumer<Footer> consumer) {
        Footer footer = new Footer();
        consumer.accept(footer);
        this.defaultFooter = footer;
        return this;
    }

    /**
     * Возвращает основной нижний колонтитул секции.
     *
     * @return нижний колонтитул или {@code null},
     * если значение не установлено
     */
    public Footer getDefaultFooter() {
        return this.defaultFooter;
    }

    /**
     * Проверяет, установлен ли основной нижний колонтитул.
     *
     * @return {@code true}, если нижний колонтитул установлен
     */
    public boolean hasDefaultFooter() {
        return this.defaultFooter != null;
    }

    /**
     * Устанавливает нижний колонтитул первой страницы секции.
     *
     * @param footer нижний колонтитул
     * @return текущая секция
     */
    public Section firstFooter(Footer footer) {
        this.firstFooter = Objects.requireNonNull(
                footer,
                "First Footer must not be null"
        );

        return this;
    }

    /**
     * Создает и настраивает нижний колонтитул первой страницы секции.
     *
     * @param consumer функция настройки нижнего колонтитула
     * @return текущая секция
     */
    public Section firstFooter(Consumer<Footer> consumer) {
        Footer footer = new Footer();
        consumer.accept(footer);
        this.firstFooter = footer;
        return this;
    }

    /**
     * Возвращает нижний колонтитул первой страницы секции.
     *
     * @return нижний колонтитул или {@code null},
     * если значение не установлено
     */
    public Footer getFirstFooter() {
        return this.firstFooter;
    }

    /**
     * Проверяет, установлен ли нижний колонтитул первой страницы.
     *
     * @return {@code true}, если нижний колонтитул установлен
     */
    public boolean hasFirstFooter() {
        return this.firstFooter != null;
    }

    /**
     * Устанавливает нижний колонтитул четных страниц секции.
     *
     * @param footer нижний колонтитул
     * @return текущая секция
     */
    public Section evenFooter(Footer footer) {
        this.evenFooter = Objects.requireNonNull(
                footer,
                "Even Footer must not be null"
        );

        return this;
    }

    /**
     * Создает и настраивает нижний колонтитул четных страниц секции.
     *
     * @param consumer функция настройки нижнего колонтитула
     * @return текущая секция
     */
    public Section evenFooter(Consumer<Footer> consumer) {
        Footer footer = new Footer();
        consumer.accept(footer);
        this.evenFooter = footer;
        return this;
    }

    /**
     * Возвращает нижний колонтитул четных страниц секции.
     *
     * @return нижний колонтитул или {@code null},
     * если значение не установлено
     */
    public Footer getEvenFooter() {
        return this.evenFooter;
    }

    /**
     * Проверяет, установлен ли нижний колонтитул четных страниц.
     *
     * @return {@code true}, если нижний колонтитул установлен
     */
    public boolean hasEvenFooter() {
        return this.evenFooter != null;
    }

    /**
     * Устанавливает количество колонок в секции
     * 
     * @param columns объект описывающий количество колонок {@code null}
     * @return текущая секция
     */
    public Section columns(Columns columns) {
        this.columns = Objects.requireNonNull(
                columns,
                "Columns must not be null"
        );
    
        return this;
    }

    /**
     * Создает и настраивает объект для описания количества колонок в секции.
     *
     * @param consumer функция настройки количества колонок
     * @return текущая секция
     */
    public Section columns(Consumer<Columns> consumer) {
        Columns columns = Columns.create();
        consumer.accept(columns);
        this.columns = columns;
    
        return this;
    }

    /**
     * Возвращает объект описания количества колонок в секции.
     *
     * @return объект описания количества колонок или {@code null},
     * если значение не установлено
     */
    public Columns getColumns() {
        return this.columns;
    }

    /**
     * Проверяет, установлен ли объект описания количества колонок.
     *
     * @return {@code true}, если описание установлено
     */
    public boolean hasColumns() {
        return this.columns != null;
    }

    /**
     * Добавляет элементы в секцию.
     *
     * @param blocks элементы, которые необходимо добавить
     * @return текущая секция
     * @throws NullPointerException если массив элементов равен {@code null}
     */
    public Section add(Block... blocks) {
        Objects.requireNonNull(
                blocks,
                "Blocks must not be null"
        );

        for (Block block : blocks) {
            add(block);
        }

        return this;
    }

    /**
     * Добавляет элемент в секцию.
     *
     * @param block элемент, который необходимо добавить
     * @return текущая секция
     * @throws NullPointerException если элемент равен {@code null}
     */
    public Section add(Block block) {
        this.blocks.add(
                Objects.requireNonNull(
                        block,
                        "Block must not be null"
                )
        );

        return this;
    }

    /**
     * Возвращает элементы документа, содержащиеся в секции.
     *
     * @return коллекция элементов документа
     */
    @Override
    public Collection<DocumentElement> getElements() {
        return this.blocks.stream()
                          .filter(DocumentElement.class::isInstance)
                          .map(DocumentElement.class::cast)
                          .toList();
    }

    /**
     * Возвращает все элементы, содержащиеся в секции.
     *
     * @return неизменяемый список элементов секции
     */
    public List<Block> getBlocks() {
        return List.copyOf(this.blocks);
    }

}