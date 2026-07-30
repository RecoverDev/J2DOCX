package com.github.RecoverDev.j2docx.serialization.parts;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.serialization.packages.PackagePack;
import com.github.RecoverDev.j2docx.serialization.xml.XmlStreamWriter;
import com.github.RecoverDev.j2docx.serialization.xml.XmlStreamWriterImpl;
import com.github.RecoverDev.j2docx.serialization.xml.numbering.NumberingModel;
import com.github.RecoverDev.j2docx.serialization.xml.rels.ContentTypeModel;
import com.github.RecoverDev.j2docx.serialization.xml.rels.RelsFileModel;

public class DocumentPartBuilder {

    public static List<PackagePack> Build(DocumentX document) {

        List<PackagePack> packages = new ArrayList<>();
        List<DocumentPart> parts = new ArrayList<>();

        NumberingModel.create();

        ContentTypeModel contentTypeModel = new ContentTypeModel();

        DocumentXPart documentPart = new DocumentXPart(document);
        NumberingPart numberingPart = new NumberingPart();
        StylesPart stylesPart = new StylesPart(document.styles());

        documentPart.addPart(numberingPart);
        documentPart.addPart(stylesPart);

        parts.add(documentPart);
        parts.add(numberingPart);
        parts.add(stylesPart);

        parts.forEach(part -> { 
            contentTypeModel.addNodes(part);
        });

        // Формируем записи для rels файл
        List<DocumentPart> relsParts = createRels(List.of(documentPart), "", "");

        parts.addAll(relsParts);
        parts.add(new ContentTypePart(contentTypeModel));

        // Формируем stream для записи zip архива
        for (DocumentPart part : parts) {
            packages.add(createPack(part));
        }


        return packages;
        
    }

    private static List<DocumentPart> createRels(List<DocumentPart> parts, String path, String nameFile) {
        List<DocumentPart> result = new ArrayList<>();

        RelsFileModel model = new RelsFileModel();
        for (DocumentPart part : parts) {
            model.addNodes(
                        part.getRelationshipType(), 
                        path.equals(part.getPath()) ?  part.getPartName() :  List.of(part.getPath(),part.getPartName()).stream().filter(s -> s != null && !s.isEmpty()).collect(Collectors.joining("/")));
            if (part.getParts().size() > 0) {
                result.addAll(createRels(part.getParts(), part.getPath(), part.getPartName()));
            }
        }
        RelsFilePart relsPart = new RelsFilePart(model);
        relsPart.setNamePart( nameFile);
        relsPart.setPath(path);
        result.add(relsPart);

        return result;
    }

    private static PackagePack createPack(DocumentPart part) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        XmlStreamWriter writer = new XmlStreamWriterImpl(stream);

        part.serializer(writer);

        return new PackagePack(List.of(part.getPath(),part.getPartName()).stream().filter(s -> s != null && !s.isEmpty()).collect(Collectors.joining("/")), 
                                stream.toByteArray());
        
    }
    

}
