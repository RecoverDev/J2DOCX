package com.github.RecoverDev.j2docx.serialization.document;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.block.Paragraph;
import com.github.RecoverDev.j2docx.section.Section;

public class DocumentXTest {

    @Test
    @DisplayName("Нельзя добавить Section в документ с прямыми элементами")
    void cannotAddSectionToDocumentWithDirectElements() {
        DocumentX document = DocumentX.create()
                .add(Paragraph.of("Обычный элемент"));

        assertThrows(
                IllegalStateException.class,
                () -> document.add(
                        Section.create()
                                .add(Paragraph.of("Элемент секции"))
                )
        );
    }

    @Test
    @DisplayName("Нельзя добавить прямой элемент в документ с секциями")
    void cannotAddDirectElementToDocumentWithSections() {
        DocumentX document = DocumentX.create()
                .add(
                        Section.create()
                                .add(Paragraph.of("Элемент секции"))
                );

        assertThrows(
                IllegalStateException.class,
                () -> document.add(
                        Paragraph.of("Обычный элемент")
                )
        );
    }

}
