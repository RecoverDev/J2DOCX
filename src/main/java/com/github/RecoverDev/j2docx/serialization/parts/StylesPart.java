package com.github.RecoverDev.j2docx.serialization.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.RecoverDev.j2docx.Styles;
import com.github.RecoverDev.j2docx.serialization.models.StylesModel;
import com.github.RecoverDev.j2docx.serialization.xml.ContentTypes;
import com.github.RecoverDev.j2docx.serialization.xml.Relationships;
import com.github.RecoverDev.j2docx.serialization.xml.StylesSerializer;
import com.github.RecoverDev.j2docx.serialization.xml.XmlStreamWriter;

public final class StylesPart implements DocumentPart {
    private final String namePart = "styles.xml";
    private final String path = "word";
    private final Styles styles;
    private final List<DocumentPart> parts = new ArrayList<>();

    public StylesPart(StylesModel model) {
        this.styles = model.getStyles();
    }


    public StylesPart(Styles styles) {
        this.styles = styles;
    }

    @Override
    public String getPartName() {
        return namePart;
    }

    @Override
    public String getContentType() {
        return ContentTypes.STYLES;
    }

    @Override
    public String getRelationshipType() {
        return Relationships.STYLES;
    }

    @Override
    public void serializer(XmlStreamWriter writer) {
        StylesSerializer.serialize(styles, writer);
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
    public String getPartId() {
        return "none";
    }

}
