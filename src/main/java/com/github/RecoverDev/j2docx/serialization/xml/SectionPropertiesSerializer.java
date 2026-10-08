package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.section.Section;
import com.github.RecoverDev.j2docx.section.PageSize;
import com.github.RecoverDev.j2docx.enums.PageOrientation;

public final class SectionPropertiesSerializer {

    public static void serialize(Section section, XmlStreamWriter writer) {

        PageSize pageSize;
        PageOrientation orientation;
        
        if (section.hasPageSize()) {
            pageSize = section.getPageSize();
        } else {
            pageSize = PageSize.A4;
        }

        if (section.hasOrientation()) {
            orientation = section.getOrientation();
        } else {
            orientation = PageOrientation.PORTRAIT;
        }


        writer.startElement("w:sectPr");

        if (orientation == PageOrientation.LANDSCAPE) {
            writer.emptyElement("w:pgSz")
                  .attribute("w:w", String.valueOf(pageSize.getHeightTwips()))
                  .attribute("w:h", String.valueOf(pageSize.getWidthTwips()));
        } else {
            writer.emptyElement("w:pgSz")
                  .attribute("w:w", String.valueOf(pageSize.getWidthTwips()))
                  .attribute("w:h", String.valueOf(pageSize.getHeightTwips()));
        }

        // if (section.hasPageSize()) {
        //     writer.emptyElement("w:pgSz")
        //           .attribute("w:w", String.valueOf(section.getPageSize().getWidthTwips()))
        //           .attribute("w:h", String.valueOf(section.getPageSize().getHeightTwips()));
        //     if (section.hasOrientation()) {
        //         writer.attribute("w:orient", section.getOrientation().getXmlValue());
        //     }
        // } else {
        //     if (section.hasOrientation()) {
        //         writer.emptyElement("w:pgSz")
        //               .attribute("w:orient", section.getOrientation().getXmlValue());
        //     }

        // }
        
        if (section.hasBreakType()) {
            writer.emptyElement("w:type")
                  .attribute("w:val", section.getBreakType().getXmlValue());
        }

        if (section.hasColumns()) {
            writer.emptyElement("w:cols")
                  .attribute("w:num", String.valueOf(section.getColumns().getCount()))
                  .attribute("w:space", String.valueOf(section.getColumns().getSpacingTwips()));
        }

        if (section.hasMargins()) {
            writer.emptyElement("w:pgMar");
            if (section.getMargins().hasTop()) {
                writer.attribute("w:top", String.valueOf(section.getMargins().getTopTwips()));
            }
            if (section.getMargins().hasRight()) {
                writer.attribute("w:right", String.valueOf(section.getMargins().getRightTwips()));
            }
            if (section.getMargins().hasBottom()) {
                writer.attribute("w:bottom", String.valueOf(section.getMargins().getBottomTwips()));
            }
            if (section.getMargins().hasLeft()) {
                writer.attribute("w:left", String.valueOf(section.getMargins().getLeftTwips()));
            }
        }

        writer.endElement();
    }

}
