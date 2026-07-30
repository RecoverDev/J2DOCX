package com.github.RecoverDev.j2docx.serialization.xml.rels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.RecoverDev.j2docx.serialization.parts.DocumentPart;

public final class ContentTypeModel {

    private List<ContentTypeNode> nodes = new ArrayList<>();

    public List<ContentTypeNode> getNodes() {
        return Collections.unmodifiableList(this.nodes);
    }

    public void addNodes(DocumentPart part) {
        this.nodes.add(new ContentTypeNode("/" + part.getPath() + "/" + part.getPartName(), part.getContentType()));
    }

}
