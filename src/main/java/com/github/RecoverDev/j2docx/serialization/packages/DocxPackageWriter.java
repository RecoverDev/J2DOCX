package com.github.RecoverDev.j2docx.serialization.packages;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.github.RecoverDev.j2docx.serialization.xml.Exceptions.FileWriteException;

public class DocxPackageWriter {

    public void write(Collection<PackagePack> parts, String name) {
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (ZipOutputStream zip = new ZipOutputStream(out)) {
            for (PackagePack part : parts) {
                zip.putNextEntry(new ZipEntry(part.path()));
                zip.write(part.content());
                zip.closeEntry();
            }
        } catch (IOException e) {
                    e.printStackTrace();
                } 

        writeToFile(name, out);
    }

    private void writeToFile(String name, ByteArrayOutputStream out) {

        try {
            Files.write(Path.of(name), out.toByteArray());
        } catch (Exception e) {
            throw new FileWriteException(e);
        }
    }



}
