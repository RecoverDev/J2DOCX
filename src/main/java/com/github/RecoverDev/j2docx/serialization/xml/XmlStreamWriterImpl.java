package com.github.RecoverDev.j2docx.serialization.xml;

import java.io.OutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

final class XmlStreamWriterImpl implements XmlStreamWriter {
    private static final XMLOutputFactory FACTORY =  XMLOutputFactory.newFactory();    
    private final XMLStreamWriter writer;
    private final OutputStream out;


    public XmlStreamWriterImpl(OutputStream stream) {
        this.out = stream;
        try {
            writer = FACTORY.createXMLStreamWriter(out, "UTF-8");
        } catch (XMLStreamException e) {
            throw new XmlException(e);
        }
    }

    @Override
    public XmlStreamWriter startDocument() {
        try {
            writer.writeStartDocument("UTF-8","1.0");
            return this;
        } catch (XMLStreamException e) {
            throw new XmlException(e);
        }
    }

    @Override
    public XmlStreamWriter endDocument() {
        try {
            writer.writeEndDocument();
            return this;
        } catch (XMLStreamException e) {
            throw new XmlException(e);
        }
    }

    @Override
    public XmlStreamWriter startElement(String name) {
        try {
            writer.writeStartElement(name);
            return this;
        } catch (XMLStreamException e) {
            throw new XmlException(e);
        }
    }

    @Override
    public XmlStreamWriter endElement() {
        try {
            writer.writeEndElement();
            return this;
        } catch (XMLStreamException e) {
            throw new XmlException(e);
        }
    }

    @Override
    public XmlStreamWriter attribute(String name, String value) {
        try {
            writer.writeAttribute(name, value);
            return this;
        } catch (XMLStreamException e) {
            throw new XmlException(e);
        }
    }

    @Override
    public XmlStreamWriter text(String text) {
        try {
            writer.writeCharacters(text);
            return this;
        } catch (XMLStreamException e) {
            throw new XmlException(e);
        }
    }

    @Override
    public XmlStreamWriter flush() {
        try {
            writer.flush();
            return this;
        } catch (XMLStreamException e) {
            throw new XmlException(e);
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (XMLStreamException e) {
            throw new XmlException(e);
        }
    }

    @Override
    public XmlStreamWriter namespace(String prefix, String uri) {
        try {
            writer.setPrefix(prefix, uri);
            writer.writeNamespace(prefix, uri);
            return this;
        } catch (XMLStreamException e) {
            throw new XmlException(e);
        }
    }

    @Override
    public XmlStreamWriter emptyElement(String name) {
        try {
            writer.writeEmptyElement(name);
            return this;
        } catch (XMLStreamException e) {
            throw new XmlException(e);
        }
    }

}
