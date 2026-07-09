package com.github.RecoverDev.j2docx.serialization.xml;

public interface XmlStreamWriter extends AutoCloseable {

    XmlStreamWriter startDocument();

    XmlStreamWriter endDocument();

    XmlStreamWriter startElement(String name);

    XmlStreamWriter endElement();

    XmlStreamWriter emptyElement(String name);

    XmlStreamWriter attribute(String name, String value);

    XmlStreamWriter text(String text);

    XmlStreamWriter namespace(String prefix, String uri);

    XmlStreamWriter flush();

    @Override
    void close();

}
