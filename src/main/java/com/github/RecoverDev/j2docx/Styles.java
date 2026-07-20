package com.github.RecoverDev.j2docx;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import com.github.RecoverDev.j2docx.styles.CharacterStyle;
import com.github.RecoverDev.j2docx.styles.ParagraphStyle;
import com.github.RecoverDev.j2docx.styles.Style;
import com.github.RecoverDev.j2docx.styles.TableStyle;

public final class Styles implements Iterable<Style<?>> {

    private final  Map<String, Style<?>> styles = new LinkedHashMap<>();

    Styles() { }

    public Styles add(Style<?> style) {

        String styleId = generateStyleId(style);
        style.assignStyleId(styleId);
        if (!style.hasName()) {
            style.name(styleId);
        }

        styles.put(styleId, style);

        return this;
    }

    public Styles paragraphStyle(Consumer<ParagraphStyle> consumer) {
        ParagraphStyle paragraphStyle = ParagraphStyle.create();
        consumer.accept(paragraphStyle);
        this.add(paragraphStyle);
        return this;
    }

    public Styles tableStyle(Consumer<TableStyle> consumer) {
        TableStyle tableStyle = TableStyle.create();
        consumer.accept(tableStyle);
        this.add(tableStyle);
        return this;
    }

    public Styles characterStyle(Consumer<CharacterStyle> consumer) {
        CharacterStyle characterStyle = CharacterStyle.create();
        consumer.accept(characterStyle);
        this.add(characterStyle);
        return this;
    }

    public Styles remove(Style<?> style) {
        this.styles.remove(style.getStyleId());
        return this;
    }

    public Styles remove(String styleName) {
        this.styles.values().removeIf(s -> s.getName().equals(styleName));
        return this;
    }

    public Optional<Style<?>> find(String styleName) {
        return this.styles.values().stream().filter(s -> s.getName().equals(styleName)).findFirst();
    }

    public boolean contains(String styleName) {
        return (this.styles.values().stream().anyMatch(s -> s.getName().equals(styleName)));
    }

    public boolean contains(Style<?> style) {
        return this.styles.containsValue(style);
    }

    public int size() {
        return this.styles.size();
    }

    public Styles clear() {
        this.styles.clear();
        return this;
    }

    @Override
    public Iterator<Style<?>> iterator() {
        return this.styles.values().iterator();
    }

    private String generateStyleId(Style<?> style) {
        String name;
        if (style.hasName()) {
            name = style.getName().replaceAll("\\s+", "")
                                .replaceAll("[^A-Za-z0-9_]", "");
        } else {
            name = switch (style) {
                case ParagraphStyle _ -> "Paragraph";
                case CharacterStyle _ -> "Character";
                case TableStyle _ -> "Table";
                default -> "Style";
            };
        }

        return createUniqueStyleId(name);

    }

    private String createUniqueStyleId(String baseId) {
        if (!styles.containsKey(baseId)) {
            return baseId;
        }

        int index = 1;
        while (styles.containsKey(baseId + String.valueOf(index))) {
            index++;
        }

        return baseId + String.valueOf(index);
    }

}
