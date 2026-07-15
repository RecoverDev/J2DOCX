package com.github.RecoverDev.j2docx.serialization.xml.numbering;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.block.Listing;
import com.github.RecoverDev.j2docx.block.Paragraph;
import com.github.RecoverDev.j2docx.enums.NumberingStyle;
import com.github.RecoverDev.j2docx.enums.ParagraphAlignment;
import com.github.RecoverDev.j2docx.inline.Run;

public class NumberingModelTest {

    @Test
    @DisplayName("Создание numberingModel из DocumentX")
    public void NumberingModelCreateTest() {

        Listing listing = Listing.create()
                            .properties(p -> p
                                .numberingStyle(NumberingStyle.DECIMAL)
                                .pattern("(%1)"))
                            .item("Первый элемент")
                            .item(Paragraph.of("Второй элемент"))
                            .item(Listing.create()
                                    .properties(p -> p
                                        .numberingStyle(NumberingStyle.LOWER_LETTER))
                                    .item("Первый элемент вложенного списка")
                                    .item("Второй элемент вложенного списка"))
                            .item(b -> b
                                .text("Третий элемент"));

        DocumentX document = DocumentX.create()
                                    .paragraph(Paragraph.create()
                                        .text("Заголовок")
                                        .properties(prop -> prop
                                            .alignment(ParagraphAlignment.CENTER)
                                            .spacingBefore(12)
                                            .spacingAfter(12)))
                                    .add(listing)
                                    .add(Paragraph.create()
                                            .properties(p -> p
                                                .alignment(ParagraphAlignment.BOTH))
                                            .add(Run.of("Абзац текста между списками")
                                                    .properties(p -> p
                                                        .fontSize(12)
                                                        .fontFamily("Times New Roman"))))
                                    .add(Listing.create()
                                            .item("Первый элементпростого списка")
                                            .item("Второй элементпростого списка")
                                            .item(Paragraph.of("Третий элемент простого списка"))
                                            .properties(p -> p
                                                .numberingStyle(NumberingStyle.BULLET)
                                                .bullet('+')))
                                    .add(Paragraph.of("Абзац с поясняющим текстом. Здесь должно быть много текста."))
                                    .add(Listing.create()
                                            .properties(p -> p
                                                .numberingStyle(NumberingStyle.DECIMAL)
                                                .pattern("(%1)"))
                                            .item("Первый элемент завершающего списка")
                                            .item("Второй элемент завершающего списка")
                                            .item(Listing.create()
                                                    .properties(p -> p
                                                        .numberingStyle(NumberingStyle.LOWER_LETTER))
                                                    .item("Первый элемент вложенного списка")
                                                    .item("Второй элемент вложенного списка"))
                                            .item(b -> b
                                                .text("Третий элемент завершающего списка"))
                                            .item("Последний элемент завершающего списка")
                                                            );

        NumberingModel model = NumberingModel.of(document);

        assertEquals(2, model.getAbstractNumbering().size());
        assertEquals(3, model.getNumberingInstances().size());
                                    
    }

}
