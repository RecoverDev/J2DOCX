package com.github.RecoverDev.j2docx.serialization.parts;

import java.util.ArrayList;
import java.util.List;

import com.github.RecoverDev.j2docx.serialization.models.DocumentModel;
import com.github.RecoverDev.j2docx.serialization.models.Model;
import com.github.RecoverDev.j2docx.utils.UniqueIntGenerator;

public class DocumentPartBuilder {

    public static List<DocumentPart> Build(DocumentModel model, UniqueIntGenerator generator) {
        List<DocumentPart> parts = new ArrayList<>();

        DocumentXPart documentPart = createDocumentPart(model);

        parts.add(documentPart);
        parts.addAll(RelsFilePartBuilder.relsBuild(documentPart, generator));
        parts.add(RelsFilePartBuilder.contentTypeBuild(documentPart));

        return parts;

    }

    private static DocumentXPart createDocumentPart(DocumentModel model) {
        DocumentXPart documentPart = DocumentPart.of(model);

        createDependency(model.getModels()).forEach(d -> documentPart.addDependency(d));

        return documentPart;
    }

    private static List<DocumentPart> createDependency(List<Model> models) {
        List<DocumentPart> parts = new ArrayList<>();

        for (var model : models) {
            DocumentPart part = DocumentPart.of(model);
            if (model.getModels().size() > 0) {
                createDependency(model.getModels()).forEach(d -> part.addDependency(d));
            }
            parts.add(part);
        }
        return parts;
    }

}
