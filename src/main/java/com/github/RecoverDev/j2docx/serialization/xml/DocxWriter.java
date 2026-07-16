package com.github.RecoverDev.j2docx.serialization.xml;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.serialization.packages.DocxPackageWriter;
import com.github.RecoverDev.j2docx.serialization.packages.PackagePack;
import com.github.RecoverDev.j2docx.serialization.xml.numbering.NumberingModel;
import com.github.RecoverDev.j2docx.serialization.xml.numbering.NumberingSerializer;

/**
 * Выполняет сериализацию документа J2DOCX в файл формата DOCX.
 *
 * <p>Класс формирует структуру пакета OpenXML, сериализует
 * содержимое документа и сохраняет результат в указанный файл.
 *
 * <p>Все методы класса являются статическими.
 *
 * <pre>{@code
 * DocumentX document = DocumentX.create()
 *     .paragraph("Hello, J2DOCX!");
 *
 * DocxWriter.write(document, "document.docx");
 * }</pre>
 */
public class DocxWriter {

    /**
     * Сохраняет документ в файл формата DOCX.
     *
     * <p>Метод выполняет сериализацию модели документа,
     * формирует пакет OpenXML и записывает его
     * в указанный файл.
     *
     * @param document документ для сохранения
     * @param fileName имя или путь создаваемого файла
     *
     * @throws ZipArchiveException
     *         если во время формирования DOCX-пакета
     *         произошла ошибка
     *
     * @throws FileWriteException
     *         если не удалось записать файл
     */    
    public static void write(DocumentX document, String fileName) {

        Set<DocumentParts> documentParts = new HashSet<>();

        Collection<PackagePack> parts = new ArrayList<>();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        NumberingModel.create(document);
        NumberingModel numberingModel = NumberingModel.getInstance();
        if (numberingModel.getAbstractNumbering().size() > 0) {
            parts.add(numberingFile(numberingModel));
            documentParts.add(DocumentParts.NUMBERING);
        }
        
        parts.add(contentTypePack(documentParts));
        parts.add(documentPack(document));
        parts.add(wordRelsFile(documentParts));
        parts.add(relsFile(documentParts));


        DocxPackageWriter packageWriter = new DocxPackageWriter();
        try {
            packageWriter.write(parts, out);
        } catch (Exception e) {
            throw new ZipArchiveException(e);
        }

        writeToFile(fileName, out);

    }

    private static PackagePack documentPack(DocumentX document) {

        ByteArrayOutputStream documentStream = new ByteArrayOutputStream();

        XmlStreamWriter writer = new XmlStreamWriterImpl(documentStream);

        SerializerDispatcher.dispatche(document, writer);

        return new PackagePack("word/document.xml", documentStream.toByteArray());

    }

    private static PackagePack contentTypePack(Set<DocumentParts> documentParts) {

        ByteArrayOutputStream contentStream = new ByteArrayOutputStream();

        XmlStreamWriter writer = new XmlStreamWriterImpl(contentStream);

        ContentTypeSerializer.serialize(documentParts, writer);

        return new PackagePack("[Content_Types].xml", contentStream.toByteArray());
    }

    private static PackagePack relsFile(Set<DocumentParts> documentParts) {

        ByteArrayOutputStream relsStream = new ByteArrayOutputStream();

        XmlStreamWriter writer = new XmlStreamWriterImpl(relsStream);

        RelsFileSerializer.serialize(documentParts, writer);

        return new PackagePack("_rels/.rels", relsStream.toByteArray());
    }

    private static PackagePack wordRelsFile(Set<DocumentParts> documentParts) {

        ByteArrayOutputStream relsStream = new ByteArrayOutputStream();

        XmlStreamWriter writer = new XmlStreamWriterImpl(relsStream);

        WordRelsSerializer.serialize(documentParts, writer);

        return new PackagePack("word/_rels/document.xml.rels", relsStream.toByteArray());
    }

    private static PackagePack numberingFile(NumberingModel model) {

        ByteArrayOutputStream numberingStream = new ByteArrayOutputStream();

        XmlStreamWriter writer = new XmlStreamWriterImpl(numberingStream);

        NumberingSerializer.serialize(model, writer);

        return new PackagePack("word/numbering.xml", numberingStream.toByteArray());
    }

    private static void writeToFile(String name, ByteArrayOutputStream out) {

        try {
            Files.write(Path.of(name), out.toByteArray());
        } catch (Exception e) {
            throw new FileWriteException(e);
        }
    }

}
