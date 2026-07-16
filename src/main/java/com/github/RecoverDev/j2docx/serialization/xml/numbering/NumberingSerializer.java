package com.github.RecoverDev.j2docx.serialization.xml.numbering;

import com.github.RecoverDev.j2docx.enums.NumberingStyle;
import com.github.RecoverDev.j2docx.serialization.xml.XmlStreamWriter;

public final class NumberingSerializer {

    public static void serialize(NumberingModel model, XmlStreamWriter writer) {

        writer.startElement("w:numbering")
                    .namespace("w", "http://schemas.openxmlformats.org/wordprocessingml/2006/main");

        // Пишем abstractNum
        model.getAbstractNumbering().forEach((key, value) -> {
            writer.startElement("w:abstractNum ")
                        .attribute("w:abstractNumId", value.getId().toString())
                        .emptyElement("w:multiLevelType")
                        .attribute("w:val", "multilevel");


            key.forEach(level -> {
                writer.startElement("w:lvl")
                        .attribute("w:ilvl", level.getLevel().toString())
                        .emptyElement("w:start")
                        .attribute("w:val", level.getStart().toString())
                        .emptyElement("w:numFmt")
                        .attribute("w:val", level.getNumberingStyle().getXmlValue())
                        .emptyElement("w:lvlText")
                        // Описание этого аттрибута зависит от выбранного типа списка
                        .attribute("w:val", 
                                level.getNumberingStyle() == NumberingStyle.BULLET ?  
                                        (level.getBullet() == null ? "•" : level.getBullet().toString()) : level.getPattern() )
                        .emptyElement("w:lvlJc")
                        .attribute("w:val", "left")
                        // Добавляем описание отступов
                        .startElement("w:pPr")
                        .emptyElement("w:ind")
                        .attribute("w:left", level.getLeft().toString())
                        .attribute("w:hanging", level.getHanging().toString())
                        .endElement()
                        //Конец описания отступов
                        .endElement();
            });

            writer.endElement();

        });

        // Пишем num
        model.getNumberingInstances().forEach(num -> {
            writer.startElement("w:num")
                    .attribute("w:numId", num.getId().toString())
                    .emptyElement("w:abstractNumId")
                    .attribute("w:val", num.getAbstrictNumberingId().toString())
                    .startElement("w:lvlOverride")
                    .attribute("w:ilvl", "0")
                    .emptyElement("w:startOverride")
                    .attribute("w:val", "1")
                    .endElement()
                    .endElement();
        });


        writer.endElement();
    }


}
