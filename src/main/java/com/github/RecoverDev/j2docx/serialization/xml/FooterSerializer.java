package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.headerfooter.Footer;

public class FooterSerializer {

    public static void serialize(Footer footer,  XmlStreamWriter writer) {

        writer.startDocument()
            .startElement("w:ftr")
            .namespace("w", Namespaces.WORD)
            .namespace("r", Namespaces.RELATIONSHIPS);

        for (DocumentElement element : footer.getElements()) {
            SerializerDispatcher.dispatche(element, writer);
        }

        writer.endElement()
            .endDocument();
        
    }

}
