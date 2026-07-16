package com.github.RecoverDev.j2docx.serialization.xml;

import java.util.Set;

final class ContentTypeSerializer {

    public static void serialize(Set<DocumentParts> documentParts, XmlStreamWriter writer) {

        writer.startDocument()
            .startElement("Types")
            .namespace("", Namespaces.CONTENT_TYPE)
            .emptyElement("Default")
            .attribute("Extension", "rels")
            .attribute("ContentType", "application/vnd.openxmlformats-package.relationships+xml")
            .emptyElement("Default")
            .attribute("Extension", "xml")
            .attribute("ContentType", "application/xml")
            .emptyElement("Override")
            .attribute("PartName", "/word/document.xml")
            .attribute("ContentType", "application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml");

        if (documentParts.contains(DocumentParts.NUMBERING)) {
            writer.emptyElement("Override")
                .attribute("PartName", "/word/numbering.xml")
                .attribute("ContentType", "application/vnd.openxmlformats-officedocument.wordprocessingml.numbering+xml");
        }

        if (documentParts.contains(DocumentParts.STYLES)) {
            writer.emptyElement("Override")
                .attribute("PartName", "/word/styles.xml")
                .attribute("ContentType", "application/vnd.openxmlformats-officedocument.wordprocessingml.styles+xml");
        }

        writer.endElement()
            .endDocument();
    }

}
