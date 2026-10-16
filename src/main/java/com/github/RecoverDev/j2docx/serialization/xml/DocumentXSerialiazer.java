package com.github.RecoverDev.j2docx.serialization.xml;

import java.util.List;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.RootElement;
import com.github.RecoverDev.j2docx.enums.StructureType;
import com.github.RecoverDev.j2docx.section.Section;

public final class DocumentXSerialiazer {

    public static void serialize(DocumentX document, XmlStreamWriter writer) {
        writer.startDocument()
            .startElement("w:document")
            .namespace("w", Namespaces.WORD)
            .namespace("r", Namespaces.RELATIONSHIPS)
            .startElement("w:body");

        if (document.getStructureType() == StructureType.SECTIONED) {
            sectionedSerialize(document, writer);
        } else {
            directSerialize(document, writer);
        }

        writer    
            .endElement()
            .endElement()
            .endDocument();
    }

    private static void directSerialize(DocumentX document, XmlStreamWriter writer) {

        List<RootElement> blocks = document.getBlocks();
        for (RootElement block : blocks) {
            SerializerDispatcher.dispatche((DocumentElement)block, writer);
        }

        SectionPropertiesSerializer.serialize(Section.create(), writer);

    }

    private static void sectionedSerialize(DocumentX document, XmlStreamWriter writer) {

        List<RootElement> blocks = document.getBlocks();
        for (int i = 0; i < (blocks.size() - 1); i++) {
            SerializerDispatcher.dispatche((DocumentElement)blocks.get(i), writer);
        }

        DocumentElementListSerializer.serialize((Section)blocks.getLast(), writer);
        SectionPropertiesSerializer.serialize((Section)blocks.getLast(), writer);

    }

}
