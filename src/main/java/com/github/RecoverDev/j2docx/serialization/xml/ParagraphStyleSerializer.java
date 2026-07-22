package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.styles.ParagraphStyle;

public class ParagraphStyleSerializer {

    public static void serialize(ParagraphStyle style, XmlStreamWriter writer) {

        writer.startElement("w:style")
        .attribute("w:type", "paragraph")
        .attribute("w:styleId", style.getStyleId())
        .emptyElement("w:name")
        .attribute("w:val", style.getName());

    if (style.hasQuickStyle()) {
        writer.emptyElement("w:qFormat");
    }

    if (style.hasBasedOn()) {
        writer.emptyElement("w:baseOn")
            .attribute("w:val", style.getBasedOn().getStyleId());
    }

    if (style.hasNextStyle()) {
        writer.emptyElement("w:next")
            .attribute("w:val", style.getNextStyle().getStyleId());
    }

    if (style.hasUiPriority()) {
        writer.emptyElement("w:uiPriority")
            .attribute("w:val", style.getUiPriority().toString());
    }

    if (style.hasParagraphProperties()) {
        SerializerDispatcher.dispatche(style.getParagraphProperties(), writer);
    }

    if (style.hasRunProperties()) {
        SerializerDispatcher.dispatche(style.getRunProperties(), writer);
    }


    writer.endElement();

    }

}
