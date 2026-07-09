package com.github.RecoverDev.j2docx.serialization.packages;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DocxPackageWriter {

    public void write(Collection<PackagePack> parts, OutputStream out) throws IOException {
        
        try (ZipOutputStream zip = new ZipOutputStream(out)) {
            for (PackagePack part : parts) {
                zip.putNextEntry(new ZipEntry(part.path()));
                zip.write(part.content());
                zip.closeEntry();
            }
        } 
    }

}
