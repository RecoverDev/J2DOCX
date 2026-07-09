package com.github.RecoverDev.j2docx.serialization.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.RecoverDev.j2docx.block.Paragraph;
import com.github.RecoverDev.j2docx.enums.ParagraphAlignment;
import com.github.RecoverDev.j2docx.inline.Run;

public class ParagraphSerializerTest {

    @Test
    @DisplayName("Сериализация параграфа без дополнительных свойств")
    public void serializeWithoutProperties() {

        Paragraph paragraph1 = Paragraph.of("Hello, World!");
        Paragraph paragraph2 = Paragraph.create()
                                .text("Hello, World!");
        Paragraph paragraph3 = Paragraph.create()
                                .add(Run.of("Hello, World!"));

        String result = "<w:p><w:r><w:t xml:space=\"preserve\">Hello, World!</w:t></w:r></w:p>";

        OutputStream out1 = new ByteArrayOutputStream();
        OutputStream out2 = new ByteArrayOutputStream();
        OutputStream out3 = new ByteArrayOutputStream();

        XmlStreamWriter writer1 = new XmlStreamWriterImpl(out1);
        XmlStreamWriter writer2 = new XmlStreamWriterImpl(out2);
        XmlStreamWriter writer3 = new XmlStreamWriterImpl(out3);

        SerializerDispatcher.dispatche(paragraph1, writer1);
        SerializerDispatcher.dispatche(paragraph2, writer2);
        SerializerDispatcher.dispatche(paragraph3, writer3);

        assertEquals(result, out1.toString());
        assertEquals(result, out2.toString());
        assertEquals(result, out3.toString());

    }

    @Test
    @DisplayName("Сериализация параграфа со свойствами")
    public void serializeWithProperties() {

        Paragraph paragraph = Paragraph.of("Hello, World!")
                                .properties(p -> p
                                            .alignment(ParagraphAlignment.CENTER)
                                            .firstLineIndent(12)
                                            .keepLines()
                                            .leftIndent(10)
                                            .lineSpacing(12)
                                            .spacingBefore(12)
                                            .pageBreakBefore()
                                );

        String result = "<w:p><w:pPr><w:jc w:val=\"center\"/><w:ind w:firstLine=\"240\" w:left=\"200\"/><w:spacing w:before=\"240\" w:line=\"240\"/><w:keepLines/><w:pageBreakBefore/></w:pPr><w:r><w:t xml:space=\"preserve\">Hello, World!</w:t></w:r></w:p>";

        OutputStream out = new ByteArrayOutputStream();

        XmlStreamWriter writer = new XmlStreamWriterImpl(out);

        SerializerDispatcher.dispatche(paragraph, writer);

        assertEquals(result, out.toString());

    }

}
