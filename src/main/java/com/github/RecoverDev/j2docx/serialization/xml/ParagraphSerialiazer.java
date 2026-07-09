package com.github.RecoverDev.j2docx.serialization.xml;

import java.util.List;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.block.Paragraph;
import com.github.RecoverDev.j2docx.inline.Inline;

final class ParagraphSerialiazer {

    public static void serialize(Paragraph paragraph, XmlStreamWriter writer) {
        writer.startElement("w:p");

        if (paragraph.getProperties().hasAnyProperty()) {
            SerializerDispatcher.dispatche(paragraph.getProperties(), writer);
        }

        List<Inline> inlines = paragraph.getInlines();
        for (Inline inline : inlines) {
            SerializerDispatcher.dispatche((DocumentElement)inline, writer);
        }

        writer.endElement();
    }

}
