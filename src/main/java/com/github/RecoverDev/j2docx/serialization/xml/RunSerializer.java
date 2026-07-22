package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.inline.Run;
import com.github.RecoverDev.j2docx.serialization.xml.Exceptions.NotRegistrationStyleException;
import com.github.RecoverDev.j2docx.serialization.xml.context.RunSerializerContext;
import com.github.RecoverDev.j2docx.serialization.xml.context.RunStyleContextSerializer;
import com.github.RecoverDev.j2docx.serialization.xml.context.SerializerContext;

final class RunSerializer {

    public static void serialize(Run run, XmlStreamWriter writer) {
        writer.startElement("w:r");

        if (run.getStyle() != null) {
            if (run.getStyle().getStyleId().isEmpty()) {
                throw new NotRegistrationStyleException(new Throwable("Стиль " + run.getStyle().getName() + " не зарегистрирован в списке стилей документа и не получил styleId"));
            }
            SerializerContext context = new RunSerializerContext();
            context.getSerialize().add(new RunStyleContextSerializer(run.getStyle().getStyleId()));
            SerializerDispatcher.dispatche(run.getProperties(), context, writer);
        } else {
            if (run.getProperties().hasAnyProperties()) {
                SerializerDispatcher.dispatche(run.getProperties(), writer);
            }
        }

        writer.startElement("w:t")
            .attribute("xml:space", "preserve")
            .text(run.getText())
            .endElement()
            .endElement();
    }


}
