package com.github.RecoverDev.j2docx.serialization.xml;

final class RelsFileSerializer {

    public static void serialize(XmlStreamWriter writer) {

        writer.startDocument()
            .startElement("Relationships")
            .namespace("", Namespaces.RELATIONSHIPS_TYPE)
            .emptyElement("Relationship")
            .attribute("Id", "rId1")
            .attribute("Type", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument")
            .attribute("Target", "word/document.xml")
            .endElement()
            .endDocument();

    }

}
