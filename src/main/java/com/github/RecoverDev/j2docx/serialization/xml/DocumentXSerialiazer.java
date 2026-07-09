package com.github.RecoverDev.j2docx.serialization.xml;

import java.util.List;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.block.Block;

final class DocumentXSerialiazer {

    public static void serialize(DocumentX document, XmlStreamWriter writer) {
        writer.startDocument()
            .startElement("w:document")
            .namespace("w", Namespaces.WORD)
            .startElement("w:body");

        List<Block> blocks = document.getBlocks();
        for (Block block : blocks) {
            SerializerDispatcher.dispatche((DocumentElement)block, writer);
        }

        writer    
            .endElement()
            .endElement()
            .endDocument();
    }

}
