package com.github.RecoverDev.j2docx.serialization.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.RecoverDev.j2docx.headerfooter.Footer;
import com.github.RecoverDev.j2docx.serialization.models.FooterModel;
import com.github.RecoverDev.j2docx.serialization.xml.ContentTypes;
import com.github.RecoverDev.j2docx.serialization.xml.FooterSerializer;
import com.github.RecoverDev.j2docx.serialization.xml.Relationships;
import com.github.RecoverDev.j2docx.serialization.xml.XmlStreamWriter;

public class FooterPart implements DocumentPart {
    private final List<DocumentPart> parts = new ArrayList<>();
    private String namePart = "footer";
    private final String path = "word";
    private final Footer footer;

    public FooterPart(FooterModel footerModel) {
        this.namePart = "footer" + footerModel.getFooter().getFooterId() + ".xml";
        this.footer = footerModel.getFooter();
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
        return ContentTypes.FOOTER;
    }

    @Override
    public String getRelationshipType() {
        return Relationships.FOOTER;
    }

    @Override
    public void serializer(XmlStreamWriter writer) {
        FooterSerializer.serialize(footer, writer);
    }

    @Override
    public String getPartId() {
        return "rId" + this.footer.getFooterId();
    }

}
