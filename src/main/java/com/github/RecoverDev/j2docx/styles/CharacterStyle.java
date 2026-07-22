package com.github.RecoverDev.j2docx.styles;

import java.util.function.Consumer;

import com.github.RecoverDev.j2docx.properties.RunProperties;

/**
 * Представляет символьный стиль документа.
 *
 * <p>Символьный стиль определяет свойства форматирования текста
 * ({@link RunProperties}), автоматически применяемые к фрагментам
 * текста, использующим данный стиль.</p>
 *
 * <p>Стиль может наследовать свойства другого символьного стиля
 * с помощью свойства {@code basedOn}, что позволяет повторно
 * использовать и расширять существующие настройки форматирования.</p>
 *
 * <p>Объект {@link RunProperties} создается лениво при первом
 * обращении к соответствующему методу.</p>
 *
 * @since 0.2
 */
public class CharacterStyle extends Style<CharacterStyle> {

    private RunProperties runProperties;

    private CharacterStyle() {}

    /**
     * Создает новый символьный стиль.
     *
     * @return новый экземпляр {@code CharacterStyle}.
     */
    public static CharacterStyle create() {
        return new CharacterStyle();
    }
    

    /**
     * Возвращает свойства форматирования текста.
     *
     * <p>Если объект свойств еще не создан, он будет создан автоматически.</p>
     *
     * @return свойства форматирования текста.
     */
    public RunProperties run() {
        if (this.runProperties == null) {
            this.runProperties = new RunProperties();
        }
        return this.runProperties;
    }

    /**
     * Позволяет настроить свойства текствого блока с помощью функционального интерфейса.
     *
     * <p>Если объект свойств еще не создан, он будет создан автоматически.</p>
     *
     * @param consumer функция настройки свойств текстового блока.
     * @return текущий стиль текста.
     */
    public CharacterStyle run(Consumer<RunProperties> consumer) {
        consumer.accept(this.run());
        return self();
    }

    public boolean hasRunProperties() {
        return this.runProperties != null;
    }

    public RunProperties getRunProperties() {
        return this.runProperties;
    }

    @Override
    protected CharacterStyle self() {
        return this;
    }

}
