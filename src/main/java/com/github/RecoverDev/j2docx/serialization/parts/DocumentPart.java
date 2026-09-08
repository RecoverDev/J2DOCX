package com.github.RecoverDev.j2docx.serialization.parts;

import java.util.List;

import com.github.RecoverDev.j2docx.serialization.models.DocumentModel;
import com.github.RecoverDev.j2docx.serialization.models.Model;
import com.github.RecoverDev.j2docx.serialization.models.StylesModel;
import com.github.RecoverDev.j2docx.serialization.xml.XmlStreamWriter;
import com.github.RecoverDev.j2docx.serialization.xml.numbering.NumberingModel;

public interface DocumentPart {

    List<DocumentPart> getParts();

    void addDependency(DocumentPart part);

    String getPath();

    String getPartName();

    String getContentType();

    String getRelationshipType();

    void serializer(XmlStreamWriter writer);

    // определим фабрику
    static DocumentPart of(Model model) {

        return switch(model) {
            case DocumentModel documentModel -> new DocumentXPart(documentModel);
            case NumberingModel numberingModel -> new NumberingPart();
            case StylesModel styleModel -> new StylesPart(styleModel);
            default -> throw new IllegalArgumentException("Неизвестный тип модели: " + model.getClass().getName());
        };
    }

    static DocumentXPart of(DocumentModel model) {
        return new DocumentXPart(model);
    }

    static StylesPart of(StylesModel model) {
        return new StylesPart(model);
    }

    static NumberingPart of(NumberingModel model) {
        return new NumberingPart();
    }

}
