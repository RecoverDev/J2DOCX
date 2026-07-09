package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.enums.HeightRule;
import com.github.RecoverDev.j2docx.properties.RowProperties;

final class RowPropertiesSerializer {

    public static void serialize(RowProperties properties, XmlStreamWriter writer) {

        writer.startElement("w:trPr");

        if (properties.hasHeight()) {
            HeightRule rule = properties.hasHeightRule() ? properties.getHeightRule() : HeightRule.AUTO;
            writer.emptyElement("w:trHeight");
            writer.attribute("w:val", properties.getHeight().toString());
            writer.attribute("w:hRule", rule.getXmlValue());
        }

        if (properties.hasCantSplit()) {
            if (properties.isCantSplit()) {
                writer.emptyElement("w:cantSplit");
            }
        }

        if (properties.hasRepeateHeader()) {
            if (properties.isRepeateHeader()) {
                writer.emptyElement("w:tblHeader");
            }
        }

        writer.endElement();
    }

}
