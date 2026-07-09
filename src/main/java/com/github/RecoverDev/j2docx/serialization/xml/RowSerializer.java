package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.block.Cell;
import com.github.RecoverDev.j2docx.block.Row;

final class RowSerializer {

    public static void serialize(Row row, XmlStreamWriter writer) {

        writer.startElement("w:tr");

        if (row.getProperties().hasAnyProperties()) {
            SerializerDispatcher.dispatche(row.getProperties(), writer);
        }

        for (Cell cell : row.getCells()) {
            SerializerDispatcher.dispatche(cell, writer);
        }

        writer.endElement();
    }

}
