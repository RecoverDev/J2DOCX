package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.serialization.xml.rels.RelationshipNode;
import com.github.RecoverDev.j2docx.serialization.xml.rels.RelsFileModel;

public final class RelsFileSerializer {

    public static void serialize(RelsFileModel model, XmlStreamWriter writer) {

        writer.startDocument()
            .startElement("Relationships")
            .namespace("", Namespaces.RELATIONSHIPS_TYPE);


        for (RelationshipNode node : model.getNodes()) {
            writer.emptyElement("Relationship")
                .attribute("Id", "rId" + node.getId())
                .attribute("Type", node.getType())
                .attribute("Target", node.getTarget());

        }

        writer.endElement()
            .endDocument();

    }

}
