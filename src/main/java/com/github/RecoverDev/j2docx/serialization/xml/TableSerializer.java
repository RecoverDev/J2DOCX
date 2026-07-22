package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.block.Row;
import com.github.RecoverDev.j2docx.block.Table;
import com.github.RecoverDev.j2docx.serialization.xml.Exceptions.NotRegistrationStyleException;
import com.github.RecoverDev.j2docx.serialization.xml.context.SerializerContext;
import com.github.RecoverDev.j2docx.serialization.xml.context.TableSerializerContext;
import com.github.RecoverDev.j2docx.serialization.xml.context.TableStyleContextSerializer;

final class TableSerializer {

    public static void serialize(Table table, XmlStreamWriter writer) {

        writer.startElement("w:tbl");

        if (table.getStyle() != null) {
            if (table.getStyle().getStyleId().isEmpty()) {
                throw new NotRegistrationStyleException(new Throwable("Стиль " + table.getStyle().getName() + " не зарегистрирован в списке стилей документа и не получил styleId"));
            }

            SerializerContext context = new TableSerializerContext();
            context.getSerialize().add(new TableStyleContextSerializer(table.getStyle().getStyleId()));
            SerializerDispatcher.dispatche(table.getProperties(), context, writer);
        } else {
            if (table.getProperties().hasAnyProperties()) {
                SerializerDispatcher.dispatche(table.getProperties(), writer);
            }
        }

        for (Row row : table.getRows()) {
            SerializerDispatcher.dispatche(row, writer);
        }

        writer.endElement();
    }

}
