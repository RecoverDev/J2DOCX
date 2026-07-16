package com.github.RecoverDev.j2docx.serialization.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.block.Listing;
import com.github.RecoverDev.j2docx.block.Paragraph;
import com.github.RecoverDev.j2docx.enums.NumberingStyle;
import com.github.RecoverDev.j2docx.inline.Run;
import com.github.RecoverDev.j2docx.serialization.xml.numbering.NumberingModel;

public class ListingSerializerTest {

    @Test
    @DisplayName("Сериализация списка")
    public void ListingSerializerNumberTest() {

        Listing listing = Listing.create()
                            .properties(p -> p
                                .numberingStyle(NumberingStyle.DECIMAL)
                                .pattern("- %1.")
                                .start(1))
                            .item("Первый пункт списка")
                            .item(Paragraph.of("Второй пункт списка"))
                            .item(pr -> pr
                                .add(Run.of("Третий пункт списка")));

        DocumentX document = DocumentX.create()
                                    .add(listing);

        NumberingModel.create(document);

        String result = "<w:p><w:pPr><w:numPr><w:ilvl w:val=\"0\"/><w:numId w:val=\"1\"/></w:numPr></w:pPr><w:r><w:t xml:space=\"preserve\">Первый пункт списка</w:t></w:r></w:p><w:p><w:pPr><w:numPr><w:ilvl w:val=\"0\"/><w:numId w:val=\"1\"/></w:numPr></w:pPr><w:r><w:t xml:space=\"preserve\">Второй пункт списка</w:t></w:r></w:p><w:p><w:pPr><w:numPr><w:ilvl w:val=\"0\"/><w:numId w:val=\"1\"/></w:numPr></w:pPr><w:r><w:t xml:space=\"preserve\">Третий пункт списка</w:t></w:r></w:p>";

        OutputStream out = new ByteArrayOutputStream();

        XmlStreamWriter writer = new XmlStreamWriterImpl(out);

        ListingSerializer.serialize(listing, writer);

        assertEquals(result, out.toString());

    }

}
