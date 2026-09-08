package com.github.RecoverDev.j2docx.serialization.packages;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.RecoverDev.j2docx.serialization.parts.DocumentPart;
import com.github.RecoverDev.j2docx.serialization.xml.XmlStreamWriter;
import com.github.RecoverDev.j2docx.serialization.xml.XmlStreamWriterImpl;

public class PackageBuilder {

    public static List<PackagePack> Build(List<DocumentPart> parts) {

        return iteration(parts);

    }

    private static List<PackagePack> iteration(List<DocumentPart> parts) {

        List<PackagePack> packs = new  ArrayList<>();

        for (DocumentPart part : parts) {
            packs.add(createPack(part));
            if (part.getParts().size() > 0) {
                packs.addAll(iteration(part.getParts()));
            }
        }
        return packs;
    }

    private static PackagePack createPack(DocumentPart part) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        XmlStreamWriter writer = new XmlStreamWriterImpl(stream);

        part.serializer(writer);

        return new PackagePack(List.of(part.getPath(),part.getPartName()).stream().filter(s -> s != null && !s.isEmpty()).collect(Collectors.joining("/")), 
                                stream.toByteArray());
        
    }


}
