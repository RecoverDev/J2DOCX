package com.github.RecoverDev.j2docx.serialization.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.RecoverDev.j2docx.serialization.xml.ContentTypes;
import com.github.RecoverDev.j2docx.serialization.xml.Relationships;
import com.github.RecoverDev.j2docx.serialization.xml.XmlStreamWriter;
import com.github.RecoverDev.j2docx.serialization.xml.numbering.NumberingModel;
import com.github.RecoverDev.j2docx.serialization.xml.numbering.NumberingSerializer;

public final class NumberingPart implements DocumentPart {
    private final String namePart = "numbering.xml";
    private final String path = "word";
    private final List<DocumentPart> parts = new ArrayList<>();

    @Override
    public String getPartName() {
        return namePart;
    }

    @Override
    public String getContentType() {
        return ContentTypes.NUMBERING;
    }

    @Override
    public String getRelationshipType() {
        return Relationships.NUMBERING;
    }

    @Override
    public void serializer(XmlStreamWriter writer) {
        NumberingSerializer.serialize(NumberingModel.getInstance(), writer);
    }

    @Override
    public List<DocumentPart> getParts() {
        return Collections.unmodifiableList(this.parts);
    }

    @Override
    public void addDependency(DocumentPart part) {
        this.parts.add(part);
    }

    @Override
    public String getPath() {
        return this.path;
    }

}
