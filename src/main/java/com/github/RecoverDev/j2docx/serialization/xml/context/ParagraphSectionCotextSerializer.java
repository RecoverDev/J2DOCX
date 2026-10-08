package com.github.RecoverDev.j2docx.serialization.xml.context;

import com.github.RecoverDev.j2docx.serialization.xml.SectionPropertiesSerializer;
import com.github.RecoverDev.j2docx.serialization.xml.XmlStreamWriter;

import com.github.RecoverDev.j2docx.section.Section;

public class ParagraphSectionCotextSerializer implements  Serializer{
    private final Section section;

    public ParagraphSectionCotextSerializer(Section section) {
        this.section = section;
    }

    @Override
    public void serialize(XmlStreamWriter writer) {
        SectionPropertiesSerializer.serialize(section, writer);
    }

}
