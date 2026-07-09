package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.inline.Run;

final class RunSerializer {

    public static void serialize(Run run, XmlStreamWriter writer) {
        writer.startElement("w:r");

        if (run.getProperties().hasAnyProperties()) {
            SerializerDispatcher.dispatche(run.getProperties(), writer);
        }

        writer.startElement("w:t")
            .attribute("xml:space", "preserve")
            .text(run.getText())
            .endElement()
            .endElement();
    }


}
