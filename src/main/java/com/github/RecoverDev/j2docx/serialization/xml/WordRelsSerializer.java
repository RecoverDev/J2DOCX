package com.github.RecoverDev.j2docx.serialization.xml;

final class WordRelsSerializer {

    public static void serialize(XmlStreamWriter writer) {

        writer.startDocument()
            .emptyElement("Relationships")
            .namespace("", Namespaces.RELATIONSHIPS_TYPE)
            .endDocument();
    }

}
