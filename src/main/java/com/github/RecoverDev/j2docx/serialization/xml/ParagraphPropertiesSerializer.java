package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.properties.ParagraphProperties;

final class ParagraphPropertiesSerializer {

    public static void serialize(ParagraphProperties properties, XmlStreamWriter writer) {
        writer.startElement("w:pPr");

        if (properties.hasAlignment()) {
            writer.emptyElement("w:jc")
                .attribute("w:val", properties.getAlignment().getXmlValue());
        }

        if (properties.hasFirstLineIndent() ||
            properties.hasRightIndent() ||
            properties.hasLeftIndent()) {

            writer.emptyElement("w:ind");

            if (properties.hasFirstLineIndent()) {
                writer.attribute("w:firstLine", String.valueOf(properties.getFirstLineIndent() * 20));
            }

            if (properties.hasLeftIndent()) {
                writer.attribute("w:left", String.valueOf(properties.getLeftIndent() * 20));
            }

            if (properties.hasRightIndent()) {
                writer.attribute("w:right", String.valueOf(properties.getRightIndent() * 20));
            }
            
        }

        if (properties.hasSpacingAfter() ||
            properties.hasSpacingBefore() ||
            properties.hasLineSpacing()) {

            writer.emptyElement("w:spacing");

            if (properties.hasSpacingAfter()) {
                writer.attribute("w:after", String.valueOf(properties.getSpacingAfter() * 20));
            }

            if (properties.hasSpacingBefore()) {
                writer.attribute("w:before", String.valueOf(properties.getSpacingBefore() * 20));
            }

            if (properties.hasLineSpacing()) {
                writer.attribute("w:line", String.valueOf(properties.getLineSpacing() * 20));
            }

        }

        if (properties.hasKeepLines()) {
            if (properties.isKeepLines()) {
                writer.emptyElement("w:keepLines");
            }
        }

        if (properties.hasKeepNext()) {
            if (properties.isKeepNext()) {
                writer.emptyElement("w:keepNext");
            }
        }

        if (properties.hasPageBreakBefore()) {
            if (properties.isPageBreakBefore()) {
                writer.emptyElement("w:pageBreakBefore");
            }
        }

        writer.endElement();
    }

}
