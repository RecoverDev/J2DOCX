package com.github.RecoverDev.j2docx.serialization.xml;

import java.util.Set;

final class RelsFileSerializer {

    public static void serialize(Set<DocumentParts> documentParts, XmlStreamWriter writer) {

        writer.startDocument()
            .startElement("Relationships")
            .namespace("", Namespaces.RELATIONSHIPS_TYPE)
            .emptyElement("Relationship")
            .attribute("Id", "rId1")
            .attribute("Type", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument")
            .attribute("Target", "word/document.xml");

        if (documentParts.contains(DocumentParts.NUMBERING)) {
            writer.emptyElement("Relationship")
                .attribute("Id", "rId2")
                .attribute("Type", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/numbering")
                .attribute("Target", "word/numbering.xml");
        }

        if (documentParts.contains(DocumentParts.STYLES)) {
            writer.emptyElement("Relationship")
                .attribute("Id", "rId3")
                .attribute("Type", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/styles")
                .attribute("Target", "word/styles.xml");
        }

        writer.endElement()
            .endDocument();

    }

}
