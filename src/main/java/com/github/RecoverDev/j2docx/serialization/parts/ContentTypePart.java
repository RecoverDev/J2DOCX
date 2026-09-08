package com.github.RecoverDev.j2docx.serialization.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.RecoverDev.j2docx.serialization.xml.ContentTypeSerializer;
import com.github.RecoverDev.j2docx.serialization.xml.XmlStreamWriter;
import com.github.RecoverDev.j2docx.serialization.xml.rels.ContentTypeModel;

public class ContentTypePart implements DocumentPart {
    private final String namePart = "[Content_Types].xml";
    private final ContentTypeModel model;
    private List<DocumentPart> parts = new ArrayList<>();


    public ContentTypePart(ContentTypeModel model) {
        this.model = model;
    }


    @Override
    public String getPartName() {
        return namePart;
    }

    @Override
    public String getContentType() {
        return "";
    }

    @Override
    public String getRelationshipType() {
        return "";
    }

    @Override
    public void serializer(XmlStreamWriter writer) {
        ContentTypeSerializer.serialize(model, writer);
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
        return "";
    }

}
