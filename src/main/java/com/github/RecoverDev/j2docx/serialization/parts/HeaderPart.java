package com.github.RecoverDev.j2docx.serialization.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.RecoverDev.j2docx.serialization.xml.HeaderSerializer;
import com.github.RecoverDev.j2docx.serialization.xml.XmlStreamWriter;
import com.github.RecoverDev.j2docx.headerfooter.Header;
import com.github.RecoverDev.j2docx.serialization.xml.Relationships;
import com.github.RecoverDev.j2docx.serialization.xml.ContentTypes;
import com.github.RecoverDev.j2docx.serialization.models.HeadersModel;

public class HeaderPart implements DocumentPart {
    private final List<DocumentPart> parts = new ArrayList<>();
    private String namePart = "header";
    private final String path = "word";
    private final Header header;

    public HeaderPart(HeadersModel headerModel) {
        this.header = headerModel.getHeader();
        this.namePart = "header" + header.getHeaderId() + ".xml";
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

    @Override
    public String getPartName() {
        return namePart;
    }

    @Override
    public String getContentType() {
        return ContentTypes.HEADER;
    }

    @Override
    public String getRelationshipType() {
        return Relationships.HEADER;
    }

    @Override
    public void serializer(XmlStreamWriter writer) {
        HeaderSerializer.serialize(header, writer);
    }

    @Override
    public String getPartId() {
        return "rId" + this.header.getHeaderId();
    }

}
