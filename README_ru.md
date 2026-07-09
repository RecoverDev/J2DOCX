
# J2DOCX

[ Как принять участие в разработке ](CONTRIBUTION.md)


## Описание

**J2DOCX** - это легковесная библиотека для формирования документов в формате DOCX использующая Fluent API. 
Библиотека ориентирована на простоту, читаемость и предсказуемую архитектуру, скрывая при этом сложность формата OpenXML. 
J2DOCX предоставляет понятный и элегантный объектно-ориентированный API, который позволяет создавать документы, не работая напрямую
с XML и OpenXML.

## Почему J2DOCX

* Поддержка Fluent API
* Никакого XML
* Вам не нужно знать об OpenXML
* Простая неизменяемая модель документа
* Легкость расширения
* Минимальное количество зависимостей

## Основные возможности

В настоящее время вы можете создать простой документ содержащий абзацы и таблицы. 
Вы можете задать уникальный внешний вид каждому элементу документа. 
Библиотека позволяет задавать шрифт, размеры текста, отступы, начертание (полужирный, курсив ...), цвет текста, фон, определять начертание таблицы и многое другое.

## Пример использования

Ниже приведен пример формирования документа,
содержащего заголовок, таблицу и заключительный абзац.

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

## Архитектура библиотеки

DocumentX\
│\
├── Paragraph\
│   ├── ParagraphProperties\
│   └── Run\
│       └── RunProperties\
│\
└── Table\
    ├── TableProperties\
    └── Row\
        ├── RowProperties\
        └── Cell\
            └── CellProperties


## Roadmap

### Version 0.1

✔ Paragraphs

✔ Runs

✔ Tables

✔ Cells

### Version 0.2

□ Styles

□ Lists

□ Headers

□ Footers

□ Sections

## Как собрать проект

```bash
   git clone https://github.com/RecoverDev/J2DOCX.git
   gradlew build 
```


## Лицензия

Проект распространяется по лицензии MIT.

Подробнее см. файл [LICENSE](LICENSE).
