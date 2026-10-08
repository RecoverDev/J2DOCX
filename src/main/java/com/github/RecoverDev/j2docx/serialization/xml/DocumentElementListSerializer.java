package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.section.Section;
import com.github.RecoverDev.j2docx.DocumentElement;

public final class DocumentElementListSerializer {

    public static void serialize(Section section, XmlStreamWriter writer) {

        for (DocumentElement element : section.getElements()) {
            SerializerDispatcher.dispatche(element, writer);
        }

    }

}
