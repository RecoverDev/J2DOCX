package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.enums.WidthType;
import com.github.RecoverDev.j2docx.properties.CellProperties;

final class CellPropertiesSerializer {

    public static void serialize(CellProperties properties, XmlStreamWriter writer) {

        writer.startElement("w:tcPr");

        if (properties.hasWidth()) {
            WidthType type = properties.hasWidthType() ? properties.getWidthType() : WidthType.AUTO;

            writer.emptyElement("w:tcW");
            writer.attribute("w:w", properties.getWidth().toString());
            writer.attribute("w:type", type.getXmlValue());
        }

        if (properties.hasBorderStyle()) {

            int size = properties.hasBorderSize() ? properties.getBorderSize() : 1;
            String color = properties.hasBorderColor() ? properties.getBorderColor() : "000000";

            writer.startElement("w:tcBorders");

            writer.emptyElement("w:top");
            writer.attribute("w:val", properties.getBorderStyle().getXmlValue());
            writer.attribute("w:sz", String.valueOf(size));
            writer.attribute("w:color", color);

            writer.emptyElement("w:left");
            writer.attribute("w:val", properties.getBorderStyle().getXmlValue());
            writer.attribute("w:sz", String.valueOf(size));
            writer.attribute("w:color", color);


            writer.emptyElement("w:bottom");
            writer.attribute("w:val", properties.getBorderStyle().getXmlValue());
            writer.attribute("w:sz", String.valueOf(size));
            writer.attribute("w:color", color);

            writer.emptyElement("w:right");
            writer.attribute("w:val", properties.getBorderStyle().getXmlValue());
            writer.attribute("w:sz", String.valueOf(size));
            writer.attribute("w:color", color);


            writer.endElement();
        }

        if (properties.hasBackgroundColor()) {
            writer.emptyElement("w:shd");
            writer.attribute("w:fill", properties.getBackgroundColor());
        }

        if (properties.hasVerticalAlignment()) {
            writer.emptyElement("w:vAlign");
            writer.attribute("w:val", properties.getVerticalAlignment().getXmlValue());
        }

        if (properties.hasGridSpan()) {
            writer.emptyElement("w:gridSpan");
            writer.attribute("w:val", properties.getGridSpan().toString());
        }

        writer.endElement();
    }

}
