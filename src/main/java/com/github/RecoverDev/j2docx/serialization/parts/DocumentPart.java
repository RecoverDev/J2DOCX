package com.github.RecoverDev.j2docx.serialization.parts;

import java.util.List;

import com.github.RecoverDev.j2docx.serialization.xml.XmlStreamWriter;

public interface DocumentPart {

    List<DocumentPart> getParts();

    void addPart(DocumentPart part);

    String getPath();

    String getPartName();

    String getContentType();

    String getRelationshipType();

    void serializer(XmlStreamWriter writer);

}
