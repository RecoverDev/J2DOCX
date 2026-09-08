package com.github.RecoverDev.j2docx.serialization.document;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.block.Cell;
import com.github.RecoverDev.j2docx.block.Listing;
import com.github.RecoverDev.j2docx.block.Paragraph;
import com.github.RecoverDev.j2docx.block.Row;
import com.github.RecoverDev.j2docx.block.Table;
import com.github.RecoverDev.j2docx.enums.BorderStyle;
import com.github.RecoverDev.j2docx.enums.NumberingStyle;
import com.github.RecoverDev.j2docx.enums.ParagraphAlignment;
import com.github.RecoverDev.j2docx.enums.TableAlignment;
import com.github.RecoverDev.j2docx.enums.VerticalAlignment;
import com.github.RecoverDev.j2docx.inline.Run;
import com.github.RecoverDev.j2docx.serialization.xml.DocxWriter;
import com.github.RecoverDev.j2docx.styles.ParagraphStyle;
import com.github.RecoverDev.j2docx.styles.TableStyle;

public class DocxWriterTest {

    @Test
    @DisplayName("Создание документа на основе объекта DocumentX")
    public void createDocXTest() {

        DocumentX docx = DocumentX.create()
                            .paragraph(Paragraph.of("Hello, J2DOCX!")
                                            .properties(p -> p
                                                .alignment(ParagraphAlignment.LEFT)
                                                .spacingBefore(12)
                                                .spacingAfter(12)
                                                .keepLines()
                                            ));

        DocxWriter.write(docx, "FirstDocx.docx");

        assertTrue(Files.exists(Path.of("FirstDocx.docx")));

    }

    @Test
    @DisplayName("Формирование документа с ParagraphProperties и RunProperties")
    public void createDocxWithRunProperties() {

        DocumentX docx = DocumentX.create()
                            .paragraph(Paragraph.create()
                                            .add(Run.of("Заголовок")
                                                    .properties(rp -> rp
                                                        .bold()
                                                        .fontFamily("Times New Roman")
                                                        .fontSize(14)
                                                        .color("FF0000")
                                                    ))   
                                            .properties(p -> p
                                                .alignment(ParagraphAlignment.CENTER)
                                                .spacingBefore(12)
                                                .spacingAfter(12)
                                                .keepLines()
                                            ));

        DocxWriter.write(docx, "rpDocx.docx");

        assertTrue(Files.exists(Path.of("rpDocx.docx")));

    }

    @Test
    @DisplayName("Формирование документа с Table")
    public void createDocxWithTable() {

        DocumentX docx = DocumentX.create()
                            .table(Table.create()
                                    .row(Row.create()
                                        .cell(Cell.of("1."))
                                        .cell(c -> c
                                            .text("Заголовок таблицы")))
                                    .row(r -> r
                                        .cell(Cell.of("2."))
                                        .cell(c -> c
                                            .add(Paragraph.of("Описание"))))
                                    .row(Row.create()
                                        .cell(Cell.of("3."))
                                        .cell(c -> c
                                            .text("Пояснения")))
                                    .row(r -> r
                                        .cell(Cell.of("Итоговая строка")
                                                .properties(cP -> cP
                                                    .backgroundColor("AAAAAA")
                                                    .borderStyle(BorderStyle.SINGLE)
                                                    .borderColor("FF0000")
                                                    .gridSpan(2)
                                                    .verticalAlignment(VerticalAlignment.CENTER)
                                                ))
                                    )   
                                    .properties(tp -> tp
                                        .alignment(TableAlignment.CENTER)
                                        .borderStyle(BorderStyle.DOUBLE)
                                        .borderSize(8)
                                        .borderColor("0000FF")
                                    ));

        DocxWriter.write(docx, "tableDocx.docx");

        assertTrue(Files.exists(Path.of("tableDocx.docx")));

    }

    @Test 
    @DisplayName("Формирование документа состилями")
    public void createDocWithStyles() {
        DocumentX docx = DocumentX.create()
                                .style(ParagraphStyle.create()
                                                .name("Мой основной текст")
                                                .quickStyle()
                                                .paragraph(par -> par
                                                        .alignment(ParagraphAlignment.BOTH)
                                                        .spacingBefore(6)
                                                        .spacingAfter(6)
                                                        .firstLineIndent(32)
                                                )
                                                .run(r -> r
                                                        .fontFamily("Times New Roman")
                                                        .fontSize(14)
                                                )
                                )
                                .style(ParagraphStyle.create()
                                                .name("Мой заголовок")
                                                .quickStyle()
                                                .paragraph(p -> p
                                                        .alignment(ParagraphAlignment.CENTER)
                                                        .spacingAfter(12)
                                                        .spacingBefore(12)
                                                )
                                                .run(r -> r
                                                        .bold()
                                                        .fontFamily("Times New Roman")
                                                        .fontSize(16)
                                                ));

        TableStyle tableStyle = TableStyle.create()
                                        .table(t -> t
                                                .borderStyle(BorderStyle.SINGLE)
                                                .alignment(TableAlignment.CENTER)
                                                .indent(6)
                                        )
                                        .name("Стиль таблицы")
                                        .paragraph(p ->p
                                                .alignment(ParagraphAlignment.CENTER)
                                        )
                                        .run(r -> r
                                                .fontFamily("Arial")
                                                .fontSize(12)
                                                .italic()
                                        );

        ParagraphStyle tableTitleStyle = ParagraphStyle.create()
                                                .name("Заголовок таблицы")
                                                .paragraph(p -> p
                                                        .alignment(ParagraphAlignment.CENTER)
                                                        .spacingAfter(12)
                                                        .spacingBefore(12)
                                                )
                                                .run(r -> r
                                                        .bold()
                                                        .italic(false)
                                                        .fontFamily("Courier")
                                                        .fontSize(12)
                                                );

        Listing listing = Listing.create()
                                        .properties(l -> l
                                                .numberingStyle(NumberingStyle.DECIMAL)
                                                .pattern("%1.")
                                        )
                                        .item("");

        docx.styles().add(tableStyle).add(tableTitleStyle);

        docx.paragraph(Paragraph.create()
                                .text("Заголовок")
                                .style(docx.styles().getParagraphStyle("Мой заголовок")))
                .paragraph(Paragraph.of("Это основной текст документа. Этот абзац должен содержать большое количество текста, чтобы было видно установленное выравнивание текста по ширине.")
                        .style(docx.styles().getParagraphStyle("Мой основной текст")))
                .table(Table.create()
                                .style(tableStyle)
                                .row(first -> first
                                        .cell(Cell.create().add(Paragraph.of("п/п").style(tableTitleStyle)))
                                        .cell(Cell.create().add(Paragraph.of("Текст").style(tableTitleStyle))))
                                .row(Row.create()
                                        .cell(Cell.create().add(listing))
                                        .cell(Cell.of("Содержание")))
                                .row(r -> r
                                        .cell(Cell.create().add(listing))
                                        .cell(Cell.of("Описание")))
                                .row(Row.create()
                                        .cell(Cell.create().add(listing))
                                        .cell(Cell.of("Данные"))));

        DocxWriter.write(docx, "stylesDocx.docx");

        assertTrue(Files.exists(Path.of("stylesDocx.docx")));
                                        

    }

}
