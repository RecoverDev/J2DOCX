package com.github.RecoverDev.j2docx.serialization.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.serialization.models.DocumentModel;
import com.github.RecoverDev.j2docx.serialization.xml.ContentTypes;
import com.github.RecoverDev.j2docx.serialization.xml.DocumentXSerialiazer;
import com.github.RecoverDev.j2docx.serialization.xml.Relationships;
import com.github.RecoverDev.j2docx.serialization.xml.XmlStreamWriter;

public class DocumentXPart implements DocumentPart {
    private final String namePart = "document.xml";
    private final String path = "word";
    private DocumentX document;
    private final List<DocumentPart> parts = new ArrayList<>();

    public DocumentXPart(DocumentModel model) {
        this.document = model.getDocumentX();
    }

    public DocumentXPart(DocumentX documentX) {
        this.document = documentX;
    }

    @Override
    public String getPartName() {
        return namePart;
    }

    @Override
    public String getContentType() {
        return ContentTypes.DOCUMENT;
    }

    @Override
    public String getRelationshipType() {
        return Relationships.DOCUMENT;
    }

    @Override
    public void serializer(XmlStreamWriter writer) {
        DocumentXSerialiazer.serialize(document, writer);
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
