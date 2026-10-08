package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.section.Section;
import com.github.RecoverDev.j2docx.serialization.xml.context.ParagraphSectionCotextSerializer;
//import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.block.Paragraph;
import com.github.RecoverDev.j2docx.serialization.xml.context.ParagraphSerializerContext;

final class SectionSerializer {

    public static void serialize(Section section, XmlStreamWriter writer) {

        DocumentElementListSerializer.serialize(section, writer);

        // for (DocumentElement element : section.getElements()) {
        //     SerializerDispatcher.dispatche(element, writer);
        // }

        ParagraphSectionCotextSerializer sectionCotextSerializer = new ParagraphSectionCotextSerializer(section);
        ParagraphSerializerContext paragraphContext = new ParagraphSerializerContext();
        paragraphContext.getSerialize().add(sectionCotextSerializer);
        ParagraphSerialiazer.serialize(Paragraph.create(), paragraphContext, writer);
    }

}
