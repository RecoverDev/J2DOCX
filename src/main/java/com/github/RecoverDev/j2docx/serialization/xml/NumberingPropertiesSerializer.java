package com.github.RecoverDev.j2docx.serialization.xml;

public class NumberingPropertiesSerializer implements Serializer {

    private final Integer numId;
    private final Integer level;

    public NumberingPropertiesSerializer(Integer numId, Integer level) {
        this.numId = numId;
        this.level = level;
    }

    @Override
    public void serialize(XmlStreamWriter writer) {

        writer.startElement("w:numPr")
            .emptyElement("w:ilvl")
            .attribute("w:val", this.level.toString())
            .emptyElement("w:numId")
            .attribute("w:val", this.numId.toString())
            .endElement();
    }

}
