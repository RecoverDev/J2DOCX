package com.github.RecoverDev.j2docx.serialization.packages;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class DocxPackageWriterTest {

    @Test
    @DisplayName("Создаем ZIP архив")
    public void shouldWriteAllPackagePartsIntoZip() throws ZipException, IOException {


        PackagePack pack1 = new PackagePack("file1.txt", "Hello, ".getBytes(StandardCharsets.UTF_8));
        PackagePack pack2 = new PackagePack("folder/file2.txt", "World".getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        DocxPackageWriter packageWriter = new DocxPackageWriter();
        packageWriter.write(List.of(pack1, pack2), out);

        try (ZipInputStream zip = new ZipInputStream(new ByteArrayInputStream(out.toByteArray()))) {

            ZipEntry entry = zip.getNextEntry();

            assertNotNull(entry);
            assertEquals("file1.txt", entry.getName());
            assertEquals("Hello, ", new String(zip.readAllBytes(),StandardCharsets.UTF_8));

            entry = zip.getNextEntry();

            assertNotNull(entry);
            assertEquals("folder/file2.txt", entry.getName());
            assertEquals("World", new String(zip.readAllBytes(),StandardCharsets.UTF_8));

            assertNull(zip.getNextEntry());
        }
    }

}
