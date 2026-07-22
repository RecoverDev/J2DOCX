package com.github.RecoverDev.j2docx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.RecoverDev.j2docx.block.Block;
import com.github.RecoverDev.j2docx.block.Paragraph;
import com.github.RecoverDev.j2docx.block.Table;
import com.github.RecoverDev.j2docx.styles.Style;

/**
 * Представляет DOCX-документ.
 *
 * <p>Документ является корневым элементом модели J2DOCX и
 * содержит последовательность блочных элементов {@link Block}
 * (например, {@link Paragraph} и {@link Table}).
 *
 * <p>Экземпляры создаются с помощью метода {@link #create()}.
 */
public final class DocumentX implements DocumentElement {

    private final List<Block> blocks = new ArrayList<>();

    private final Styles styles = new Styles();

    private DocumentX() {}

    /**
     * Создает новый пустой документ.
     *
     * @return новый экземпляр DocumentX
     */
    public static DocumentX create() {
        return new DocumentX();
    }

    /**
     * Возвращает коллекцию стилей документа.
     *
     * <p>Коллекция содержит все стили, зарегистрированные в документе,
     * и используется для назначения форматирования абзацам, таблицам
     * и фрагментам текста.</p>
     *
     * @return коллекция стилей документа.
     */
    public Styles styles() {
        return this.styles;
    }


    /**
     * Добавляет новый стиль в коллекцию стилей документа.
     * @param style добавляемый стиль
     * @return текущий документ
     */
    public DocumentX style(Style<?> style) {
        this.styles.add(style);
        return this;
    }



    /**
     * Добавляет блочный элемент в документ.
     *
     * @param block добавляемый элемент
     * @return текущий документ
     */
    public DocumentX add(Block block) {
        blocks.add(block);
        return this;
    }

    /**
     * Создает абзац из указанного текста
     * и добавляет его в конец документа.
     *
     * @param text текст абзаца
     * @return текущий документ
     */
    public DocumentX paragraph(String text) {
        return add(Paragraph.of(text));
    }

    /**
     * Добавляет абзац в документ.
     *
     * @param paragraph добавляемый абзац
     * @return текущий документ
     */
    public DocumentX paragraph(Paragraph paragraph) {
        return add(paragraph);
    }

    /**
     * Добавляет в список элементов новый элемент Table
     * @param table добавляемая таблица Table
     * @return текущий документ
     */
    public DocumentX table(Table table) {
        return add(table);
    }

    /**
     * Возвращает неизменяемый список
     * блочных элементов документа.
     *
     * @return неизменяемый список элементов документа
     */
    public List<Block> getBlocks() {
        return Collections.unmodifiableList(blocks);
    }

}
