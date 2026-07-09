# J2DOCX

[ How to Contribute ](CONTRIBUTION.md)

## Description

**J2DOCX** is a lightweight library for generating DOCX documents using the Fluent API.

The library focuses on simplicity, readability, and a predictable architecture, while hiding the complexity of the OpenXML format.

J2DOCX provides a clear and elegant object-oriented API that allows you to create documents without working directly
with XML and OpenXML.

## Why J2DOCX

* Fluent API support
* No XML required
* No knowledge of OpenXML required
* Simple, immutable document model
* Easy to extend
* Minimal dependencies

## Key Features

Currently, you can create a simple document containing paragraphs and tables.

You can define a unique appearance for each document element.

The library allows you to set the font, text size, indentation, style (bold, italic, etc.), text color, background, define table styles, and much more.

## Usage Example

Below is an example of generating a document
containing a heading, a table, and a closing paragraph.

```java 
        DocumentX docx = DocumentX.create()
                                .paragraph(Paragraph.create()
                                        .properties(p -> p
                                                .alignment(ParagraphAlignment.CENTER)
                                                .spacingAfter(12)
                                                .spacingBefore(12))
                                        .add(Run.of("Заголовок")
                                                .properties(p -> p
                                                        .fontFamily("Times new Roman")
                                                        .bold()
                                                        .fontSize(14))))
                                .table(Table.create()
                                        .row(Row.create()
                                                .cell(Cell.of("1."))
                                                .cell(Cell.create().text("Содержание")))
                                        .row(Row.create()
                                                .cell(Cell.of("2."))
                                                .cell(Cell.create().add(Paragraph.of("Описание"))))
                                        .properties(p -> p
                                                        .alignment(TableAlignment.CENTER)
                                                        .borderStyle(BorderStyle.SINGLE)
                                                        .borderColor("FF0000")))
                                .paragraph(Paragraph.create()
                                                .add(Run.of("Последний абзац этого документа.")
                                                        .properties(p -> p
                                                                .fontFamily("Arial")
                                                                .fontSize(14)
                                                                .color("FF0000")
                                                        ))
                                                .properties(p -> p
                                                        .alignment(ParagraphAlignment.BOTH)
                                                        .spacingBefore(12)
                                                        .spacingAfter(12)
                                                ));

        DocxWriter.write(docx, "example.docx");

```

## Library architecture

DocumentX\
│\
├──Paragraph\
│  ├── ParagraphProperties\
│  └──Run\
│     └── RunProperties\
│\
└── Table \
    ├──TableProperties\
    └──Row\
       ├── RowProperties\
       └── Cell\
           └── CellProperties
           

## Roadmap

### Version 0.1

✔Paragraphs

✔Runs

✔Tables

✔ Cells

### Version 0.2

□ Styles

□ Lists

□ Headers

□ Footers

□ Sections

## How to build the project

```bash
git clone https://github.com/RecoverDev/J2DOCX.git
gradlew build
```

## License

This project is distributed under the MIT License.

For more information, see the [LICENSE](LICENSE) file.
