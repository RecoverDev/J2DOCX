package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.styles.TableStyle;

public class TableStyleSerializer {

    public static void serialize(TableStyle style, XmlStreamWriter writer) {

        writer.startElement("w:style")
        .attribute("w:type", "table")
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
            
        if (style.hasUiPriority()) {
            writer.emptyElement("w:uiPriority")
                .attribute("w:val", style.getUiPriority().toString());
        }

        if (style.hasTableProperties()) {
            SerializerDispatcher.dispatche(style.getTableProperties(), writer);
        }

        if (style.hasRowProperties()) {
            SerializerDispatcher.dispatche(style.getRowProperties(), writer);
        }

        if (style.hasCellProperties()) {
            SerializerDispatcher.dispatche(style.getCellProperties(), writer);
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
