package com.github.RecoverDev.j2docx.serialization.xml.context;

import com.github.RecoverDev.j2docx.serialization.xml.XmlStreamWriter;

public class RunStyleContextSerializer implements Serializer {
    private final String styleId;

    public RunStyleContextSerializer(String styleId) {
        this.styleId = styleId;
    }

    @Override
    public void serialize(XmlStreamWriter writer) {
        writer.emptyElement("w:rStyle")
            .attribute("w:val", styleId);
    }

}
