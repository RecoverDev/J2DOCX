package com.github.RecoverDev.j2docx.serialization.xml.rels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RelsFileModel {

    private List<RelationshipNode> nodes = new ArrayList<>();


    public List<RelationshipNode> getNodes() {
        return Collections.unmodifiableList(this.nodes);
    }

    public int addNodes(String id, String relationship, String path) {
        int current = this.nodes.size() + 1;
        this.nodes.add(new RelationshipNode(
                                String.valueOf(current), 
                                relationship, 
                                path));

        return current;
    }

}
