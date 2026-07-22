package com.github.RecoverDev.j2docx.serialization.xml.context;

import com.github.RecoverDev.j2docx.serialization.xml.XmlStreamWriter;

public final class TableStyleContextSerializer implements Serializer {
    private final String styleId;

    public TableStyleContextSerializer(String styleId) {
        this.styleId = styleId;
    }

    @Override
    public void serialize(XmlStreamWriter writer) {
        writer.emptyElement("w:tblStyle")
            .attribute("w:val", styleId);
    }

}
