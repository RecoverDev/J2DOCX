package com.github.RecoverDev.j2docx.serialization.xml;

import java.util.Collection;
import java.util.List;

import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.serialization.models.DocumentModel;
import com.github.RecoverDev.j2docx.serialization.models.DocumentModelFactory;
import com.github.RecoverDev.j2docx.serialization.packages.DocxPackageWriter;
import com.github.RecoverDev.j2docx.serialization.packages.PackageBuilder;
import com.github.RecoverDev.j2docx.serialization.packages.PackagePack;
import com.github.RecoverDev.j2docx.serialization.parts.DocumentPart;
import com.github.RecoverDev.j2docx.serialization.parts.DocumentPartBuilder;

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
     */    
    public static void write(DocumentX document, String fileName) {


        DocumentModel documentModel = DocumentModelFactory.factory(document);

        List<DocumentPart> documentPart = DocumentPartBuilder.Build(documentModel);

        Collection<PackagePack> parts = PackageBuilder.Build(documentPart);

        DocxPackageWriter packageWriter = new DocxPackageWriter();
        packageWriter.write(parts, fileName);

    }

}
