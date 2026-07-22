package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.styles.CharacterStyle;

public class CharacterStyleSerializer {

    public static void serialize(CharacterStyle style,  XmlStreamWriter writer) {

        writer.startElement("w:style")
        .attribute("w:type", "character")
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

        if (style.hasRunProperties()) {
            SerializerDispatcher.dispatche(style.getRunProperties(), writer);
        }


        writer.endElement();
    }

}
