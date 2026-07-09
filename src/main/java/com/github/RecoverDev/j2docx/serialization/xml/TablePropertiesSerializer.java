package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.enums.WidthType;
import com.github.RecoverDev.j2docx.properties.TableProperties;

final class TablePropertiesSerializer {

    public static void serialize(TableProperties properties, XmlStreamWriter writer) {

        writer.startElement("w:tblPr");

        if (properties.hasWidth()) {
            WidthType type = properties.hasWidthType() ? properties.getWidthType() : WidthType.AUTO;
            writer.emptyElement("w:tblW");
            writer.attribute("w:w", properties.getWidth().toString());
            writer.attribute("w:type", type.getXmlValue());
        }

        if (properties.hasAlignment()) {
            writer.emptyElement("w:jc");
            writer.attribute("w:val", properties.getAlignment().getXmlValue());
        }

        if (properties.hasBorderStyle()) {

            int size = properties.hasBorderSize() ? properties.getBorderSize() : 1;
            String color = properties.hasBorderColor() ? properties.getBorderColor() : "000000";

            writer.startElement("w:tblBorders");

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

            writer.emptyElement("w:insideH");
            writer.attribute("w:val", properties.getBorderStyle().getXmlValue());
            writer.attribute("w:sz", String.valueOf(size));
            writer.attribute("w:color", color);

            writer.emptyElement("w:insideV");
            writer.attribute("w:val", properties.getBorderStyle().getXmlValue());
            writer.attribute("w:sz", String.valueOf(size));
            writer.attribute("w:color", color);

            writer.endElement();
        }

        if (properties.hasCellSpacing()) {
            writer.emptyElement("w:tblCellSpacing");
            writer.attribute("w:w", properties.getCellSpacing().toString());
            writer.attribute("w:type", "dxa");
        }

        if (properties.hasIndent()) {
            writer.emptyElement("w:tblInd");
            writer.attribute("w:w", properties.getIndent().toString());
            writer.attribute("w:type", "dxa");
        }


        writer.endElement();
    }

}
