package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.block.Row;
import com.github.RecoverDev.j2docx.block.Table;

final class TableSerializer {

    public static void serialize(Table table, XmlStreamWriter writer) {

        writer.startElement("w:tbl");

        if (table.getProperties().hasAnyProperties()) {
            SerializerDispatcher.dispatche(table.getProperties(), writer);
        }

        for (Row row : table.getRows()) {
            SerializerDispatcher.dispatche(row, writer);
        }

        writer.endElement();
    }

}
