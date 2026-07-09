package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.block.Cell;
import com.github.RecoverDev.j2docx.block.Paragraph;
import com.github.RecoverDev.j2docx.block.Row;
import com.github.RecoverDev.j2docx.block.Table;
import com.github.RecoverDev.j2docx.inline.Run;
import com.github.RecoverDev.j2docx.properties.CellProperties;
import com.github.RecoverDev.j2docx.properties.ParagraphProperties;
import com.github.RecoverDev.j2docx.properties.RowProperties;
import com.github.RecoverDev.j2docx.properties.RunProperties;
import com.github.RecoverDev.j2docx.properties.TableProperties;

final class SerializerDispatcher {

        public static void dispatche(DocumentElement element, XmlStreamWriter xmlWriter) {

            switch (element) {
                case DocumentX doc -> DocumentXSerialiazer.serialize(doc, xmlWriter);
                case Paragraph paragraph -> ParagraphSerialiazer.serialize(paragraph, xmlWriter);
                case ParagraphProperties pProperties -> ParagraphPropertiesSerializer.serialize(pProperties, xmlWriter);
                case Run run -> RunSerializer.serialize(run, xmlWriter);
                case RunProperties rProperties -> RunPropertiesSerializer.serialize(rProperties, xmlWriter);
                case Table table -> TableSerializer.serialize(table, xmlWriter);
                case TableProperties tProperties -> TablePropertiesSerializer.serialize(tProperties, xmlWriter);
                case Row row -> RowSerializer.serialize(row, xmlWriter);
                case RowProperties rwProperties -> RowPropertiesSerializer.serialize(rwProperties, xmlWriter);
                case Cell cell -> CellSerializer.serialize(cell, xmlWriter);
                case CellProperties cProperties -> CellPropertiesSerializer.serialize(cProperties, xmlWriter);

            default -> {
                throw new SerializerException(new Throwable("Сериализатор объекта " + element.toString() + " не определен"));
            }
            }
        }

}
