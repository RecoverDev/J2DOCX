package com.github.RecoverDev.j2docx.serialization.xml;


import com.github.RecoverDev.j2docx.headerfooter.Header;
import com.github.RecoverDev.j2docx.DocumentElement;

public final class HeaderSerializer {

    public static void serialize(Header header, XmlStreamWriter writer) {

        writer.startDocument()
            .startElement("w:hdr")
            .namespace("w", Namespaces.WORD)
            .namespace("r", Namespaces.RELATIONSHIPS);

        for (DocumentElement element : header.getElements()) {
            SerializerDispatcher.dispatche(element, writer);
        }

        writer.endElement()
            .endDocument();

    }
}
