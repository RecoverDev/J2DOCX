package com.github.RecoverDev.j2docx.serialization.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.block.Paragraph;
import com.github.RecoverDev.j2docx.enums.ParagraphAlignment;

public class DocumentSerialiazerTest {

    @Test
    @DisplayName("Сериализация пустого DocumentX в XML")
    public void serialiazeTest() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><w:document xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\"><w:body></w:body></w:document>";
        DocumentX docx = DocumentX.create();
        OutputStream out = new ByteArrayOutputStream();
        XmlStreamWriter writer = new XmlStreamWriterImpl(out);

        SerializerDispatcher.dispatche(docx, writer);
        writer.close();

        assertEquals(xml, out.toString());

    }

    @Test
    @DisplayName("Сериализация документа с параграфом в XML")
    public void serializeDocumentWithParagraph() {
        DocumentX docx = DocumentX.create()
                            .paragraph(Paragraph.of("Hello, World!")
                                            .properties(p -> p
                                                .alignment(ParagraphAlignment.LEFT)
                                                .spacingBefore(12)
                                                .spacingAfter(12)
                                                .keepLines()
                                            ));

        String result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><w:document xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\"><w:body><w:p><w:pPr><w:jc w:val=\"left\"/><w:spacing w:after=\"240\" w:before=\"240\"/><w:keepLines/></w:pPr><w:r><w:t xml:space=\"preserve\">Hello, World!</w:t></w:r></w:p></w:body></w:document>";
        OutputStream out = new ByteArrayOutputStream();
        XmlStreamWriter writer = new XmlStreamWriterImpl(out);

        SerializerDispatcher.dispatche(docx, writer);
        writer.close();

        assertEquals(result, out.toString());

    }

}
