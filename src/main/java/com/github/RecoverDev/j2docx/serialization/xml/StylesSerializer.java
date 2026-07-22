package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.Styles;

public class StylesSerializer {

    public static void serialize(Styles styles, XmlStreamWriter writer) {

        if (styles.size() == 0) {
            return;
        }
        
        writer.startDocument()
            .startElement("w:styles")
            .namespace("w", Namespaces.WORD);

        styles.forEach(s -> SerializerDispatcher.dispatche(s, writer));

        writer.endElement()
            .endDocument();


    }

}
