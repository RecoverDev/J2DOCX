package com.github.RecoverDev.j2docx.serialization.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.RecoverDev.j2docx.block.Cell;
import com.github.RecoverDev.j2docx.block.Row;
import com.github.RecoverDev.j2docx.enums.HeightRule;

public class RowSerializerTest {

    @Test
    @DisplayName("Формирование XML для строки со свойствами")
    public void RowSerializerWithPropertiesTest() {

        Row row = Row.create()
                    .cell(Cell.of("1."))
                    .cell(c -> c.text("Описание"))
                    .properties(p -> p
                            .height(200)
                            .heightRule(HeightRule.EXACT)
                            .cantSplit()
                            .repeateHeader()   
                    );

        String result = "<w:tr><w:trPr><w:trHeight w:val=\"200\" w:hRule=\"exact\"/><w:cantSplit/><w:tblHeader/></w:trPr><w:tc><w:p><w:r><w:t xml:space=\"preserve\">1.</w:t></w:r></w:p></w:tc><w:tc><w:p><w:r><w:t xml:space=\"preserve\">Описание</w:t></w:r></w:p></w:tc></w:tr>";

        OutputStream out = new ByteArrayOutputStream();

        XmlStreamWriter writer = new XmlStreamWriterImpl(out);

        SerializerDispatcher.dispatche(row, writer);

        assertEquals(result, out.toString());

    }

}
