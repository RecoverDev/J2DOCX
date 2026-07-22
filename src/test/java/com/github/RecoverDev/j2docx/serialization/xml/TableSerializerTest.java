package com.github.RecoverDev.j2docx.serialization.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

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
import com.github.RecoverDev.j2docx.serialization.xml.numbering.NumberingModel;
import com.github.RecoverDev.j2docx.serialization.xml.numbering.NumberingSerializer;
import com.github.RecoverDev.j2docx.styles.ParagraphStyle;
import com.github.RecoverDev.j2docx.styles.TableStyle;

public class TableSerializerTest {

    @Test
    @DisplayName("Создание таблицы со стилями, абзацы с предопределенными стилями")
    public void CreateTableWithStyleTest() {
        TableStyle tableStyle = TableStyle.create()
                                        .table(t -> t
                                                .borderStyle(BorderStyle.SINGLE)
                                                .alignment(TableAlignment.CENTER)
                                                .cellSpacing(12)
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
                                                        .fontFamily("Comic San Serif")
                                                        .fontSize(12)
                                                );

                                            
        Listing listing = Listing.create()
                                        .properties(l -> l
                                                .numberingStyle(NumberingStyle.DECIMAL)
                                                .pattern("%1.")
                                        )
                                        .item("");

        
        DocumentX docx = DocumentX.create()
                                .style(tableTitleStyle)
                                .style(tableStyle)
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

        NumberingModel.create();

        String resultDocument = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><w:document xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\"><w:body><w:tbl><w:tblPr><w:tblStyle w:val=\"Table\"/></w:tblPr><w:tr><w:tc><w:p><w:pPr><w:pStyle w:val=\"Paragraph\"/></w:pPr><w:r><w:t xml:space=\"preserve\">п/п</w:t></w:r></w:p></w:tc><w:tc><w:p><w:pPr><w:pStyle w:val=\"Paragraph\"/></w:pPr><w:r><w:t xml:space=\"preserve\">Текст</w:t></w:r></w:p></w:tc></w:tr><w:tr><w:tc><w:p><w:pPr><w:numPr><w:ilvl w:val=\"0\"/><w:numId w:val=\"1\"/></w:numPr></w:pPr><w:r><w:t xml:space=\"preserve\"></w:t></w:r></w:p></w:tc><w:tc><w:p><w:r><w:t xml:space=\"preserve\">Содержание</w:t></w:r></w:p></w:tc></w:tr><w:tr><w:tc><w:p><w:pPr><w:numPr><w:ilvl w:val=\"0\"/><w:numId w:val=\"1\"/></w:numPr></w:pPr><w:r><w:t xml:space=\"preserve\"></w:t></w:r></w:p></w:tc><w:tc><w:p><w:r><w:t xml:space=\"preserve\">Описание</w:t></w:r></w:p></w:tc></w:tr><w:tr><w:tc><w:p><w:pPr><w:numPr><w:ilvl w:val=\"0\"/><w:numId w:val=\"1\"/></w:numPr></w:pPr><w:r><w:t xml:space=\"preserve\"></w:t></w:r></w:p></w:tc><w:tc><w:p><w:r><w:t xml:space=\"preserve\">Данные</w:t></w:r></w:p></w:tc></w:tr></w:tbl></w:body></w:document>";

        OutputStream out = new ByteArrayOutputStream();

        XmlStreamWriter writer = new XmlStreamWriterImpl(out);

        SerializerDispatcher.dispatche(docx, writer);


        String resultNumbering = "<w:numbering xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\"><w:abstractNum  w:abstractNumId=\"1\"><w:multiLevelType w:val=\"multilevel\"/><w:lvl w:ilvl=\"0\"><w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%1.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:left=\"720\" w:hanging=\"360\"/></w:pPr></w:lvl></w:abstractNum ><w:num w:numId=\"1\"><w:abstractNumId w:val=\"1\"/><w:lvlOverride w:ilvl=\"0\"><w:startOverride w:val=\"1\"/></w:lvlOverride></w:num></w:numbering>";

        OutputStream outNum = new ByteArrayOutputStream();

        XmlStreamWriter writerNum = new XmlStreamWriterImpl(outNum);

        NumberingSerializer.serialize(NumberingModel.getInstance(), writerNum);

        assertEquals(resultNumbering, outNum.toString());

        assertEquals(resultDocument, out.toString());

    }

}
