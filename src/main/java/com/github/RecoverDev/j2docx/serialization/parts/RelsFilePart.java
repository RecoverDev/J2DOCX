package com.github.RecoverDev.j2docx.serialization.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.github.RecoverDev.j2docx.serialization.xml.RelsFileSerializer;
import com.github.RecoverDev.j2docx.serialization.xml.XmlStreamWriter;
import com.github.RecoverDev.j2docx.serialization.xml.rels.RelsFileModel;

public class RelsFilePart implements DocumentPart {
    private String namePart = ".rels";
    private String path = "_rels";
    private final RelsFileModel model;
    private List<DocumentPart> parts = new ArrayList<>();


    public RelsFilePart(RelsFileModel model) {
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
        RelsFileSerializer.serialize(model, writer);
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

    public void setPath(String path) {
        this.path = List.of(path,"_rels").stream().filter(s -> s != null && !s.isEmpty()).collect(Collectors.joining("/"));
    }

    
    public String getNamePart() {
        return namePart;
    }

    public void setNamePart(String namePart) {
        this.namePart = namePart + ".rels";
    }

    @Override
    public String getPartId() {
        return "none";
    }

}
