package com.github.RecoverDev.j2docx.serialization.document;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.block.Listing;
import com.github.RecoverDev.j2docx.block.Paragraph;
import com.github.RecoverDev.j2docx.enums.NumberingStyle;
import com.github.RecoverDev.j2docx.enums.ParagraphAlignment;
import com.github.RecoverDev.j2docx.section.Section;
import com.github.RecoverDev.j2docx.serialization.xml.DocxWriter;
import com.github.RecoverDev.j2docx.styles.ParagraphStyle;

public class DocumentXWithSection {

    @Test 
    @DisplayName("Добавление списка в колонтитул")
    public void ListingToFooterPageTest() {

        ParagraphStyle numPageStyle = ParagraphStyle.create()
                             .name("Стиль для нумерации страниц")
                             .paragraph(p ->
                                p.alignment(ParagraphAlignment.CENTER))
                             .run(r ->
                                r.fontFamily("Arial")
                                 .fontSize(10));


        Listing listingPages = Listing.create()
            .properties(p ->
                p.numberingStyle(NumberingStyle.DECIMAL)
            )
            .item(Paragraph.of("").style(numPageStyle));

        DocumentX document = DocumentX.create()
                                    .add(Section.create()
                                            .add(Paragraph.of("Текст на странице"))
                                            .defaultFooter(f ->
                                                f.add(listingPages)));
        document.style(numPageStyle);

        DocxWriter.write(document, "NumberingPage.docx");
        assertTrue(Files.exists(Path.of("NumberingPage.docx")));

    }

}
