package com.github.RecoverDev.j2docx.block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.properties.ListingProperties;

/**
 * Представляет список в модели документа J2DOCX.
 *
 * <p>Список является блочным элементом документа и содержит
 * последовательность других блочных элементов ({@link Block}).
 *
 * <p>Основные способы работы:
 * <ul>
 *   <li>создание пустого списка — {@link #create()};</li>
 *   <li>добавление нового элемента в список — {@link #item(Block)};</li>
 *   <li>настройка форматирования — {@code properties(...)}.</li>
 * </ul>
 */
public class Listing implements DocumentElement, Block {

    private final List<Block> items = new ArrayList<>();

    private ListingProperties properties = new ListingProperties();


    private Listing() {}

    /**
     * Создает пустой список
     * @return пустой список
     */
    public static Listing create() {
        return new Listing();
    }

    /**
     *  Добавляет новых элемент в список
     * @param block новый элемент
     * @return текущий список
     */
    public Listing item(Block block) {
        this.items.add(block);
        return this;
    }

    /**
     * <p>Создает новый элемент на основе полученной строки и добавляет его в список
     * <p>Создает на основе строки элемент {@link Paragraph}
     * @param item добавляемый элемент (строка)
     * @return текущий список
     */
    public Listing item(String item) {
        this.items.add(Paragraph.of(item));
        return this;
    }

    /**
     * На основе полученного лямбда выражения создает {@link Paragraph} и добавляет его в список
     * @param consumer создает {@link Paragraph} на основе лямбда выражения
     * @return текущий список
     */
    public Listing item(Consumer<Paragraph> consumer) {
        Paragraph paragraph = Paragraph.create();
        consumer.accept(paragraph);
        this.items.add(paragraph);
        return this;
    }

    /**
     * Возвращает элементы списка
     * @return элементы списка
     */
    public List<Block> getItems() {
        return Collections.unmodifiableList(items);
    }

    // установка свойств

    /**
     * Устанавливает свойства форматирования списка.
     * В качестве входного параметра принимает объект класса {@link ListingProperties}
     * @param properties объект класса {@link ListingProperties}
     * @return текущий список
     * 
     * <pre>{@code
     * Listing listing = Listing.create()
     *                              .properties(new ListingProperties()
     *                                              .numberingStyle(NumberingStyle.DECIMAL));
     * }</pre>
     */
    public Listing properties(ListingProperties properties) {
        this.properties = properties.copy();
        return this;
    }

    // реализация установки свойств через лябда

    /**
     * Устанавливает свойства форматирования списка.
     * @param consumer свойства списка полученные через лямбда выражение
     * <pre>{@code
     * Listing listing = Listing.create()
     *                              .properties(p -> p
     *                                              .numberingStyle(NumberingStyle.DECIMAL));
     * }</pre>
     * @return текущий список
     */
    public Listing properties(Consumer<ListingProperties> consumer) {
        consumer.accept(this.properties);
        return this;
    }

    /**
     * Возвращает текущие свойства форматирования абзаца
     * @return свойства форматирования абзаца (объект класса {@link ListingProperties})
     */
    public ListingProperties getProperties() {
        return this.properties;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Listing other)) {
            return false;
        }

        return Objects.equals(items, other.items)
                && Objects.equals(properties, other.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, properties);
    }    

}
