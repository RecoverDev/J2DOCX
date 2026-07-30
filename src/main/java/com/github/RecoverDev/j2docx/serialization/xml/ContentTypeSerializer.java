package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.serialization.xml.rels.ContentTypeModel;
import com.github.RecoverDev.j2docx.serialization.xml.rels.ContentTypeNode;

public final class ContentTypeSerializer {

    public static void serialize(ContentTypeModel model, XmlStreamWriter writer) {

        writer.startDocument()
            .startElement("Types")
            .namespace("", Namespaces.CONTENT_TYPE)
            .emptyElement("Default")
            .attribute("Extension", "rels")
            .attribute("ContentType", "application/vnd.openxmlformats-package.relationships+xml")
            .emptyElement("Default")
            .attribute("Extension", "xml")
            .attribute("ContentType", "application/xml");



        for (ContentTypeNode node : model.getNodes()) {
            writer.emptyElement("Override")
                    .attribute("PartName", node.getPartName())
                    .attribute("ContentType", node.getContentType());
        }

        writer.endElement()
            .endDocument();
    }

}
