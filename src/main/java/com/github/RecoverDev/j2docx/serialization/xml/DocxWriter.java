package com.github.RecoverDev.j2docx.serialization.xml;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.serialization.packages.DocxPackageWriter;
import com.github.RecoverDev.j2docx.serialization.packages.PackagePack;

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

        Collection<PackagePack> parts = new ArrayList<>();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        parts.add(contentTypePack());
        parts.add(documentPack(document));
        parts.add(wordRelsFile());
        parts.add(relsFile());


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

    private static PackagePack contentTypePack() {

        ByteArrayOutputStream contentStream = new ByteArrayOutputStream();

        XmlStreamWriter writer = new XmlStreamWriterImpl(contentStream);

        ContentTypeSerializer.serialize(writer);

        return new PackagePack("[Content_Types].xml", contentStream.toByteArray());
    }

    private static PackagePack relsFile() {

        ByteArrayOutputStream relsStream = new ByteArrayOutputStream();

        XmlStreamWriter writer = new XmlStreamWriterImpl(relsStream);

        RelsFileSerializer.serialize(writer);

        return new PackagePack("_rels/.rels", relsStream.toByteArray());
    }

    private static PackagePack wordRelsFile() {

        ByteArrayOutputStream relsStream = new ByteArrayOutputStream();

        XmlStreamWriter writer = new XmlStreamWriterImpl(relsStream);

        WordRelsSerializer.serialize(writer);

        return new PackagePack("word/_rels/document.xml.rels", relsStream.toByteArray());
    }

    private static void writeToFile(String name, ByteArrayOutputStream out) {

        try {
            Files.write(Path.of(name), out.toByteArray());
        } catch (Exception e) {
            throw new FileWriteException(e);
        }
    } 

}
