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

/**
 * Коллекция стилей документа.
 * <p>
 * Хранит все стили, используемые в документе, и обеспечивает их создание,
 * поиск, удаление и перебор.
 * <p>
 * При добавлении стиль автоматически получает уникальный идентификатор,
 * если он еще не был назначен. Если отображаемое имя стиля отсутствует,
 * оно автоматически устанавливается равным идентификатору.
 * <p>
 * Экземпляр данного класса создается автоматически при создании документа
 * и доступен через {@code DocumentX.styles()}.
 */
public final class Styles implements Iterable<Style<?>>, DocumentElement {

    private final  Map<String, Style<?>> styles = new LinkedHashMap<>();

    Styles() { }

    /**
     * Добавляет стиль в коллекцию.
     * <p>
     * Если стиль не имеет идентификатора, он будет автоматически сгенерирован.
     * Если имя стиля отсутствует, в качестве имени используется его идентификатор.
     *
     * @param style добавляемый стиль
     * @return текущая коллекция стилей
     */
    public Styles add(Style<?> style) {

        String styleId = generateStyleId(style);
        style.assignStyleId(styleId);
        if (!style.hasName()) {
            style.name(styleId);
        }

        styles.put(styleId, style);

        return this;
    }

    /**
     * Создает новый стиль абзаца, передает его в пользовательский обработчик
     * для настройки и автоматически добавляет в коллекцию.
     *
     * @param consumer обработчик настройки создаваемого стиля
     * @return текущая коллекция стилей
     */
    public Styles paragraphStyle(Consumer<ParagraphStyle> consumer) {
        ParagraphStyle paragraphStyle = ParagraphStyle.create();
        consumer.accept(paragraphStyle);
        this.add(paragraphStyle);
        return this;
    }

    /**
     * Создает новый стиль таблицы, передает его в пользовательский обработчик
     * для настройки и автоматически добавляет в коллекцию.
     *
     * @param consumer обработчик настройки создаваемого стиля
     * @return текущая коллекция стилей
     */
    public Styles tableStyle(Consumer<TableStyle> consumer) {
        TableStyle tableStyle = TableStyle.create();
        consumer.accept(tableStyle);
        this.add(tableStyle);
        return this;
    }

    /**
     * Создает новый символьный стиль, передает его в пользовательский обработчик
     * для настройки и автоматически добавляет в коллекцию.
     *
     * @param consumer обработчик настройки создаваемого стиля
     * @return текущая коллекция стилей
     */
    public Styles characterStyle(Consumer<CharacterStyle> consumer) {
        CharacterStyle characterStyle = CharacterStyle.create();
        consumer.accept(characterStyle);
        this.add(characterStyle);
        return this;
    }

    /**
     * Удаляет указанный стиль из коллекции.
     *
     * @param style удаляемый стиль
     * @return текущая коллекция стилей
     */
    public Styles remove(Style<?> style) {
        this.styles.remove(style.getStyleId());
        return this;
    }

    /**
     * Удаляет стиль по его отображаемому имени.
     *
     * @param styleName имя удаляемого стиля
     * @return текущая коллекция стилей
     */
    public Styles remove(String styleName) {
        this.styles.values().removeIf(s -> s.getName().equals(styleName));
        return this;
    }

    /**
     * Выполняет поиск стиля по его отображаемому имени.
     *
     * @param styleName имя искомого стиля
     * @return {@link Optional}, содержащий найденный стиль,
     * либо пустой {@link Optional}, если стиль отсутствует
     */
    public Optional<Style<?>> find(String styleName) {
        return this.styles.values().stream().filter(s -> s.getName().equals(styleName)).findFirst();
    }

    /**
     * Возвращает стиль абзаца с указанным именем.
     *
     * @param nameStyle имя стиля
     * @return найденный стиль абзаца или {@code null},
     * если стиль отсутствует либо имеет другой тип
     */
    public ParagraphStyle getParagraphStyle(String nameStyle) {
        Optional<ParagraphStyle> result = this.styles.values().stream()
                                    .filter(ParagraphStyle.class::isInstance)
                                    .map(ParagraphStyle.class::cast)
                                    .filter(s -> nameStyle.equals(s.getName()))
                                    .findFirst();

        return result.isPresent() ? result.get() : null;
    }

    /**
     * Возвращает стиль таблицы с указанным именем.
     *
     * @param nameStyle имя стиля
     * @return найденный стиль таблицы или {@code null},
     * если стиль отсутствует либо имеет другой тип
     */
    public TableStyle getTableStyle(String nameStyle) {
        Optional<TableStyle> result = this.styles.values().stream()
                                    .filter(TableStyle.class::isInstance)
                                    .map(TableStyle.class::cast)
                                    .filter(s -> nameStyle.equals(s.getName()))
                                    .findFirst();

        return result.isPresent() ? result.get() : null;
    }

    /**
     * Возвращает символьный стиль с указанным именем.
     *
     * @param nameStyle имя стиля
     * @return найденный символьный стиль или {@code null},
     * если стиль отсутствует либо имеет другой тип
     */
    public CharacterStyle getCharacterStyle(String nameStyle) {
        Optional<CharacterStyle> result = this.styles.values().stream()
                                    .filter(CharacterStyle.class::isInstance)
                                    .map(CharacterStyle.class::cast)
                                    .filter(s -> nameStyle.equals(s.getName()))
                                    .findFirst();

        return result.isPresent() ? result.get() : null;
    }

    /**
     * Проверяет наличие стиля с указанным именем.
     *
     * @param styleName имя стиля
     * @return {@code true}, если стиль найден,
     * иначе {@code false}
     */
    public boolean contains(String styleName) {
        return (this.styles.values().stream().anyMatch(s -> s.getName().equals(styleName)));
    }

    /**
     * Проверяет, содержится ли указанный стиль в коллекции.
     *
     * @param style проверяемый стиль
     * @return {@code true}, если стиль присутствует,
     * иначе {@code false}
     */
    public boolean contains(Style<?> style) {
        return this.styles.containsValue(style);
    }

    /**
     * Возвращает количество стилей в коллекции.
     *
     * @return число зарегистрированных стилей
     */
    public int size() {
        return this.styles.size();
    }

    /**
     * Удаляет все стили из коллекции.
     *
     * @return текущая коллекция стилей
     */
    public Styles clear() {
        this.styles.clear();
        return this;
    }

    /**
     * Возвращает итератор по всем стилям документа.
     * <p>
     * Порядок обхода соответствует порядку добавления стилей.
     *
     * @return итератор по коллекции стилей
     */
    @Override
    public Iterator<Style<?>> iterator() {
        return this.styles.values().iterator();
    }

    private String generateStyleId(Style<?> style) {

        String name;
            name = switch (style) {
                case ParagraphStyle _ -> "Paragraph";
                case CharacterStyle _ -> "Character";
                case TableStyle _ -> "Table";
                default -> "Style";
            };

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
