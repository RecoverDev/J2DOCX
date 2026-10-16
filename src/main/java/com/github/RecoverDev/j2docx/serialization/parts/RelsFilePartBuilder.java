package com.github.RecoverDev.j2docx.serialization.parts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.RecoverDev.j2docx.serialization.xml.rels.ContentTypeModel;
import com.github.RecoverDev.j2docx.serialization.xml.rels.RelsFileModel;
import com.github.RecoverDev.j2docx.utils.UniqueIntGenerator;

public class RelsFilePartBuilder {

    public static List<DocumentPart> relsBuild(DocumentPart part, UniqueIntGenerator generator) {
        return createRels(List.of(part), "", "", generator);
        
    }

    private static List<DocumentPart> createRels(List<DocumentPart> parts, String path, String nameFile, UniqueIntGenerator generator) {
        List<DocumentPart> result = new ArrayList<>();

        RelsFileModel model = new RelsFileModel();
        for (DocumentPart part : parts) {
            String strId = part.getPartId() == "none" ? "rId" + String.valueOf(generator.next()) : "rId" + part.getPartId();
            model.addNodes(
                        strId,
                        part.getRelationshipType(), 
                        path.equals(part.getPath()) ?  part.getPartName() :  List.of(part.getPath(),part.getPartName()).stream().filter(s -> s != null && !s.isEmpty()).collect(Collectors.joining("/")));
            if (part.getParts().size() > 0) {
                result.addAll(createRels(part.getParts(), part.getPath(), part.getPartName(), generator));
            }
        }
        RelsFilePart relsPart = new RelsFilePart(model);
        relsPart.setNamePart( nameFile);
        relsPart.setPath(path);
        result.add(relsPart);

        return result;
    }

    public static DocumentPart contentTypeBuild(DocumentPart part) {

        ContentTypeModel model = new ContentTypeModel();
        addOverrideToContentType(model, part);
        return new ContentTypePart(model);
    }

    private static void addOverrideToContentType(ContentTypeModel model, DocumentPart part) {
        model.addNodes(part);
        for (DocumentPart node : part.getParts()) {
            model.addNodes(node);
            if (node.getParts().size() > 0) {
                addOverrideToContentType(model, node);
            }
        }
    }



}
