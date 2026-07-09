package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.block.Block;
import com.github.RecoverDev.j2docx.block.Cell;

final class CellSerializer {

    public static void serialize(Cell cell, XmlStreamWriter writer) {

        writer.startElement("w:tc");

        if (cell.getProperties().hasAnyProperties()) {
            SerializerDispatcher.dispatche(cell.getProperties(), writer);
        }

        for (Block block : cell.getBlocks()) {
            SerializerDispatcher.dispatche((DocumentElement)block, writer);
        }

        writer.endElement();
    }

}
