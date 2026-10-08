package com.github.RecoverDev.j2docx.section;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.RecoverDev.j2docx.block.Block;
import com.github.RecoverDev.j2docx.block.Paragraph;
import com.github.RecoverDev.j2docx.enums.PageOrientation;
import com.github.RecoverDev.j2docx.enums.SectionBreakType;
import com.github.RecoverDev.j2docx.headerfooter.Footer;
import com.github.RecoverDev.j2docx.headerfooter.Header;

class SectionTest {

    @Test
    @DisplayName("Создание пустой секции")
    void createEmptySection() {
        Section section = Section.create();

        assertTrue(section.getBlocks().isEmpty());
        assertTrue(section.getElements().isEmpty());

        assertFalse(section.hasOrientation());
        assertFalse(section.hasBreakType());
        assertFalse(section.hasMargins());
        assertFalse(section.hasPageSize());

        assertFalse(section.hasDefaultHeader());
        assertFalse(section.hasFirstHeader());
        assertFalse(section.hasEvenHeader());

        assertFalse(section.hasDefaultFooter());
        assertFalse(section.hasFirstFooter());
        assertFalse(section.hasEvenFooter());
    }

    @Test
    @DisplayName("Установка ориентации страницы")
    void setPageOrientation() {
        Section section = Section.create()
                .orientation(PageOrientation.LANDSCAPE);

        assertTrue(section.hasOrientation());
        assertEquals(PageOrientation.LANDSCAPE, section.getOrientation());
    }

    @Test
    @DisplayName("Установка типа разрыва секции")
    void setSectionBreakType() {
        Section section = Section.create()
                .breakType(SectionBreakType.NEXT_PAGE);

        assertTrue(section.hasBreakType());
        assertEquals(SectionBreakType.NEXT_PAGE, section.getBreakType());
    }

    @Test
    @DisplayName("Установка полей страницы объектом PageMargins")
    void setPageMargins() {
        PageMargins margins = PageMargins.create();

        Section section = Section.create()
                .margins(margins);

        assertTrue(section.hasMargins());
        assertSame(margins, section.getMargins());
    }

    @Test
    @DisplayName("Настройка полей страницы через Consumer")
    void configurePageMarginsWithConsumer() {
        Section section = Section.create()
                .margins(margins -> margins
                        .top(20)
                        .right(15)
                        .bottom(20)
                        .left(15));

        assertTrue(section.hasMargins());

        PageMargins margins = section.getMargins();

        assertTrue(margins.hasTop());
        assertTrue(margins.hasRight());
        assertTrue(margins.hasBottom());
        assertTrue(margins.hasLeft());
    }

    @Test
    @DisplayName("Установка размера страницы")
    void setPageSize() {
        PageSize pageSize = PageSize.of(210,297);

        Section section = Section.create()
                .pageSize(pageSize);

        assertTrue(section.hasPageSize());
        assertSame(pageSize, section.getPageSize());
    }

    @Test
    @DisplayName("Установка основного верхнего колонтитула")
    void setDefaultHeader() {
        Header header = new Header();

        Section section = Section.create()
                .defaultHeader(header);

        assertTrue(section.hasDefaultHeader());
        assertSame(header, section.getDefaultHeader());
    }

    @Test
    @DisplayName("Создание основного верхнего колонтитула через Consumer")
    void configureDefaultHeaderWithConsumer() {
        Section section = Section.create()
                .defaultHeader(header -> {
                });

        assertTrue(section.hasDefaultHeader());
    }

    @Test
    @DisplayName("Установка верхнего колонтитула первой страницы")
    void setFirstHeader() {
        Header header = new Header();

        Section section = Section.create()
                .firstHeader(header);

        assertTrue(section.hasFirstHeader());
        assertSame(header, section.getFirstHeader());
    }

    @Test
    @DisplayName("Создание верхнего колонтитула первой страницы через Consumer")
    void configureFirstHeaderWithConsumer() {
        Section section = Section.create()
                .firstHeader(header -> {
                });

        assertTrue(section.hasFirstHeader());
    }

    @Test
    @DisplayName("Установка верхнего колонтитула четных страниц")
    void setEvenHeader() {
        Header header = new Header();

        Section section = Section.create()
                .evenHeader(header);

        assertTrue(section.hasEvenHeader());
        assertSame(header, section.getEvenHeader());
    }

    @Test
    @DisplayName("Создание верхнего колонтитула четных страниц через Consumer")
    void configureEvenHeaderWithConsumer() {
        Section section = Section.create()
                .evenHeader(header -> {
                });

        assertTrue(section.hasEvenHeader());
    }

    @Test
    @DisplayName("Установка основного нижнего колонтитула")
    void setDefaultFooter() {
        Footer footer = new Footer();

        Section section = Section.create()
                .defaultFooter(footer);

        assertTrue(section.hasDefaultFooter());
        assertSame(footer, section.getDefaultFooter());
    }

    @Test
    @DisplayName("Создание основного нижнего колонтитула через Consumer")
    void configureDefaultFooterWithConsumer() {
        Section section = Section.create()
                .defaultFooter(footer -> {
                });

        assertTrue(section.hasDefaultFooter());
    }

    @Test
    @DisplayName("Установка нижнего колонтитула первой страницы")
    void setFirstFooter() {
        Footer footer = new Footer();

        Section section = Section.create()
                .firstFooter(footer);

        assertTrue(section.hasFirstFooter());
        assertSame(footer, section.getFirstFooter());
    }

    @Test
    @DisplayName("Создание нижнего колонтитула первой страницы через Consumer")
    void configureFirstFooterWithConsumer() {
        Section section = Section.create()
                .firstFooter(footer -> {
                });

        assertTrue(section.hasFirstFooter());
    }

    @Test
    @DisplayName("Установка нижнего колонтитула четных страниц")
    void setEvenFooter() {
        Footer footer = new Footer();

        Section section = Section.create()
                .evenFooter(footer);

        assertTrue(section.hasEvenFooter());
        assertSame(footer, section.getEvenFooter());
    }

    @Test
    @DisplayName("Создание нижнего колонтитула четных страниц через Consumer")
    void configureEvenFooterWithConsumer() {
        Section section = Section.create()
                .evenFooter(footer -> {
                });

        assertTrue(section.hasEvenFooter());
    }

    @Test
    @DisplayName("Добавление одного блока в секцию")
    void addSingleBlock() {
        Paragraph paragraph = Paragraph.of("Тестовый текст");

        Section section = Section.create()
                .add(paragraph);

        assertEquals(1, section.getBlocks().size());
        assertSame(paragraph, section.getBlocks().getFirst());
    }

    @Test
    @DisplayName("Добавление нескольких блоков в секцию")
    void addMultipleBlocks() {
        Paragraph first = Paragraph.of("Первый абзац");
        Paragraph second = Paragraph.of("Второй абзац");
        Paragraph third = Paragraph.of("Третий абзац");

        Section section = Section.create()
                .add(first, second, third);

        assertEquals(
                List.of(first, second, third),
                section.getBlocks()
        );
    }

    @Test
    @DisplayName("Блоки секции возвращаются в порядке добавления")
    void blocksPreserveInsertionOrder() {
        Paragraph first = Paragraph.of("Первый");
        Paragraph second = Paragraph.of("Второй");
        Paragraph third = Paragraph.of("Третий");

        Section section = Section.create()
                .add(first)
                .add(second)
                .add(third);

        List<Block> blocks = section.getBlocks();

        assertSame(first, blocks.get(0));
        assertSame(second, blocks.get(1));
        assertSame(third, blocks.get(2));
    }

    @Test
    @DisplayName("Коллекция блоков секции является неизменяемой")
    void blocksCollectionIsUnmodifiable() {
        Section section = Section.create()
                .add(Paragraph.of("Текст"));

        List<Block> blocks = section.getBlocks();

        assertThrows(
                UnsupportedOperationException.class,
                () -> blocks.add(Paragraph.of("Новый текст"))
        );
    }

    @Test
    @DisplayName("getElements возвращает элементы документа из блоков секции")
    void getElementsReturnsDocumentElements() {
        Paragraph first = Paragraph.of("Первый");
        Paragraph second = Paragraph.of("Второй");

        Section section = Section.create()
                .add(first, second);

        assertEquals(
                List.of(first, second),
                List.copyOf(section.getElements())
        );
    }

    @Test
    @DisplayName("Fluent API возвращает текущую секцию")
    void fluentApiReturnsSameSection() {
        Section section = Section.create();

        assertSame(
                section,
                section.orientation(PageOrientation.PORTRAIT)
        );

        assertSame(
                section,
                section.breakType(SectionBreakType.NEXT_PAGE)
        );

        assertSame(
                section,
                section.margins(PageMargins.create())
        );

        assertSame(
                section,
                section.pageSize(
                        PageSize.of(270,297)
                )
        );

        assertSame(
                section,
                section.add(Paragraph.of("Текст"))
        );
    }

    @Test
    @DisplayName("Нельзя установить null в качестве ориентации страницы")
    void orientationMustNotBeNull() {
        Section section = Section.create();

        assertThrows(
                NullPointerException.class,
                () -> section.orientation(null)
        );
    }

    @Test
    @DisplayName("Нельзя установить null в качестве типа разрыва секции")
    void breakTypeMustNotBeNull() {
        Section section = Section.create();

        assertThrows(
                NullPointerException.class,
                () -> section.breakType(null)
        );
    }

    @Test
    @DisplayName("Нельзя установить null в качестве полей страницы")
    void marginsMustNotBeNull() {
        Section section = Section.create();

        assertThrows(
                NullPointerException.class,
                () -> section.margins((PageMargins) null)
        );
    }

    @Test
    @DisplayName("Нельзя установить null в качестве размера страницы")
    void pageSizeMustNotBeNull() {
        Section section = Section.create();

        assertThrows(
                NullPointerException.class,
                () -> section.pageSize(null)
        );
    }

    @Test
    @DisplayName("Нельзя добавить null в качестве блока")
    void blockMustNotBeNull() {
        Section section = Section.create();

        assertThrows(
                NullPointerException.class,
                () -> section.add((Block) null)
        );
    }

    @Test
    @DisplayName("Нельзя добавить null в качестве массива блоков")
    void blocksMustNotBeNull() {
        Section section = Section.create();

        assertThrows(
                NullPointerException.class,
                () -> section.add((Block[]) null)
        );
    }

    @Test
    @DisplayName("Нельзя добавить null элемент в массив блоков")
    void blockArrayMustNotContainNull() {
        Section section = Section.create();

        assertThrows(
                NullPointerException.class,
                () -> section.add(
                        Paragraph.of("Первый"),
                        null
                )
        );
    }

    @Test
    @DisplayName("Нельзя установить null в качестве основного верхнего колонтитула")
    void defaultHeaderMustNotBeNull() {
        Section section = Section.create();

        assertThrows(
                NullPointerException.class,
                () -> section.defaultHeader((Header) null)
        );
    }

    @Test
    @DisplayName("Нельзя установить null в качестве верхнего колонтитула первой страницы")
    void firstHeaderMustNotBeNull() {
        Section section = Section.create();

        assertThrows(
                NullPointerException.class,
                () -> section.firstHeader((Header) null)
        );
    }

    @Test
    @DisplayName("Нельзя установить null в качестве верхнего колонтитула четных страниц")
    void evenHeaderMustNotBeNull() {
        Section section = Section.create();

        assertThrows(
                NullPointerException.class,
                () -> section.evenHeader((Header) null)
        );
    }

    @Test
    @DisplayName("Нельзя установить null в качестве основного нижнего колонтитула")
    void defaultFooterMustNotBeNull() {
        Section section = Section.create();

        assertThrows(
                NullPointerException.class,
                () -> section.defaultFooter((Footer) null)
        );
    }

    @Test
    @DisplayName("Нельзя установить null в качестве нижнего колонтитула первой страницы")
    void firstFooterMustNotBeNull() {
        Section section = Section.create();

        assertThrows(
                NullPointerException.class,
                () -> section.firstFooter((Footer) null)
        );
    }

    @Test
    @DisplayName("Нельзя установить null в качестве нижнего колонтитула четных страниц")
    void evenFooterMustNotBeNull() {
        Section section = Section.create();

        assertThrows(
                NullPointerException.class,
                () -> section.evenFooter((Footer) null)
        );
    }
}