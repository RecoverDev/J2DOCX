package com.github.RecoverDev.j2docx.serialization.xml.context;

import com.github.RecoverDev.j2docx.serialization.xml.XmlStreamWriter;

public final class ParagraphStyleContextSerializer implements Serializer{
    private final String styleId;

    public ParagraphStyleContextSerializer(String styleId) {
        this.styleId = styleId;
    }


    @Override
    public void serialize(XmlStreamWriter writer) {

        writer.emptyElement("w:pStyle")
            .attribute("w:val", styleId);
    }

}
