package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.properties.RunProperties;
import com.github.RecoverDev.j2docx.serialization.xml.context.Serializer;
import com.github.RecoverDev.j2docx.serialization.xml.context.SerializerContext;

final class RunPropertiesSerializer {

    public static void serialize(RunProperties properties, XmlStreamWriter writer) {

        writer.startElement("w:rPr");

        serializeProperties(properties, writer);

        writer.endElement();
    }

    public static void serialize(RunProperties properties, SerializerContext context, XmlStreamWriter writer) {

        writer.startElement("w:rPr");

        for (Serializer serializer : context.getSerialize()) {
            serializer.serialize(writer);
        }

        serializeProperties(properties, writer);

        writer.endElement();
    }

    private static void serializeProperties(RunProperties properties, XmlStreamWriter writer) {

        if (properties.hasBold()) {
            writer.emptyElement("w:b");
        }

        if (properties.hasItalic()) {
            writer.emptyElement("w:i");
        }

        if (properties.hasUnderline()) {
            writer.emptyElement("w:u");
            writer.attribute("w:val", properties.getUnderline().getXmlValue());
        }

        if (properties.hasColor()) {
            writer.emptyElement("w:color");
            writer.attribute("w:val", properties.getColor());
        }

        if (properties.hasHighlight()) {
            writer.emptyElement("w:highlight");
            writer.attribute("w:val", properties.getHighlight().getXmlValue());
        }

        if (properties.hasFontFamily()) {
            writer.emptyElement("w:rFonts");
            writer.attribute("w:ascii", properties.getFontFamily());
            writer.attribute("w:hAnsi", properties.getFontFamily());
        }

        if (properties.hasFontSize()) {
            writer.emptyElement("w:sz");
            writer.attribute("w:val", String.valueOf(properties.getFontSize() * 2));
        }
    }

}
