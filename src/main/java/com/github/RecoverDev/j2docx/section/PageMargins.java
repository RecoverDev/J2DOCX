package com.github.RecoverDev.j2docx.section;

import com.github.RecoverDev.j2docx.utils.UnitConverter;

/** Представляет поля страницы. 
 * <p>Класс используется для настройки верхнего, правого, нижнего и левого 
 * полей страницы. Значения полей задаются пользователем в миллиметрах.</p> 
 * <p>Внутри библиотеки значения могут храниться в единицах измерения, 
 * используемых форматом OpenXML. Преобразование между миллиметрами и 
 * внутренними единицами выполняется автоматически.</p> 
 * <p>Пример использования:</p> 
 * <pre>{@code 
 * PageMargins margins = PageMargins.create() 
 *                                  .top(20) 
 *                                  .right(15) 
 *                                  .bottom(20) 
 *                                  .left(15); 
 *                                  }</pre> 
 */
public final class PageMargins {

    private Integer top;
    private Integer right;
    private Integer bottom;
    private Integer left;

    
    private PageMargins() {
    }

    private PageMargins(int top, int right, int bottom, int left) {
        this.top = UnitConverter.millimetersToTwips(top);
        this.right = UnitConverter.millimetersToTwips(right);
        this.bottom = UnitConverter.millimetersToTwips(bottom);
        this.left = UnitConverter.millimetersToTwips(left);
    }

    /**
     * Создает новый объект для настройки полей страницы.
     *
     * @return новый объект полей страницы
     */
    public static PageMargins create() {
        return new PageMargins();
    }

    /**
     * Создает объект для настройки полей страницы из указанных данных
     * @param top верхнее поле страницы
     * @param right правое поле страницы
     * @param bottom нижнее поле страницы
     * @param left левое поле страницы
     * @return новый объект полей страницы
     */
    public static PageMargins of(int top, int right, int bottom, int left) {
        return new PageMargins(top, right, bottom, left);
    }

    /**
     * Создает объект настройки полей страницы с преопределенным данными
     * <p>{@code top = 25 мм}</p>
     * <p>{@code right = 25 мм}</p>
     * <p>{@code bottom = 25 мм}</p>
     * <p>{@code left = 25 мм}</p>
     * @return новый объект полей страницы
     */
    public static PageMargins normal() {
        return new PageMargins(
                25,
                25,
                25,
                25
        );
    }

    /**
     * Устанавливает размер верхнего поля страницы.
     *
     * @param top размер верхнего поля в миллиметрах
     * @return текущий объект полей страницы
     */
    public PageMargins top(int top) {
        this.top = UnitConverter.millimetersToTwips(top);
        return this;
    }

    /**
     * Возвращает размер верхнего поля страницы.
     *
     * @return размер верхнего поля в миллиметрах
     */
    public Double getTop() {
        return UnitConverter.twipsToMillimeters(this.top);
    }

    /**
     * Возвращает размер верхнего поля в twips.
     *
     * <p>Метод предназначен для внутреннего использования при формировании
     * OpenXML-документа.</p>
     *
     * @return размер верхнего поля в twips
     */
    public Integer getTopTwips() {
        return this.top;
    }

    /**
     * Проверяет, установлено ли верхнее поле страницы.
     *
     * @return {@code true}, если значение верхнего поля установлено
     */
    public boolean hasTop() {
        return this.top != null;
    }

    /**
     * Устанавливает размер правого поля страницы.
     *
     * @param right размер правого поля в миллиметрах
     * @return текущий объект полей страницы
     */
    public PageMargins right(int right) {
        this.right = UnitConverter.millimetersToTwips(right);
        return this;
    }

    /**
     * Возвращает размер правого поля страницы.
     *
     * @return размер правого поля в миллиметрах
     */
    public Double getRight() {
        return UnitConverter.twipsToMillimeters(this.right);
    }

    /**
     * Возвращает размер правого поля в twips.
     *
     * <p>Метод предназначен для внутреннего использования при формировании
     * OpenXML-документа.</p>
     *
     * @return размер правого поля в twips
     */
    public Integer getRightTwips() {
        return this.right;
    }

    /**
     * Проверяет, установлено ли правое поле страницы.
     *
     * @return {@code true}, если значение правого поля установлено
     */
    public boolean hasRight() {
        return this.right != null;
    }

    /**
     * Устанавливает размер нижнего поля страницы.
     *
     * @param bottom размер нижнего поля в миллиметрах
     * @return текущий объект полей страницы
     */
    public PageMargins bottom(int bottom) {
        this.bottom = UnitConverter.millimetersToTwips(bottom);
        return this;
    }

    /**
     * Возвращает размер нижнего поля страницы.
     *
     * @return размер нижнего поля в миллиметрах
     */
    public Double getBottom() {
        return UnitConverter.twipsToMillimeters(this.bottom);
    }

    /**
     * Возвращает размер нижнего поля в twips.
     *
     * <p>Метод предназначен для внутреннего использования при формировании
     * OpenXML-документа.</p>
     *
     * @return размер нижнего поля в twips
     */
    public Integer getBottomTwips() {
        return this.bottom;
    }

    /**
     * Проверяет, установлено ли нижнее поле страницы.
     *
     * @return {@code true}, если значение нижнего поля установлено
     */
    public boolean hasBottom() {
        return this.bottom != null;
    }
    
    /**
     * Устанавливает размер левого поля страницы.
     *
     * @param left размер левого поля в миллиметрах
     * @return текущий объект полей страницы
     */
    public PageMargins left(int left) {
        this.left = UnitConverter.millimetersToTwips(left);
        return this;
    }

    /**
     * Возвращает размер левого поля страницы.
     *
     * @return размер левого поля в миллиметрах
     */
    public Double getLeft() {
        return UnitConverter.twipsToMillimeters(this.left);
    }

    /**
     * Возвращает размер левого поля в twips.
     *
     * <p>Метод предназначен для внутреннего использования при формировании
     * OpenXML-документа.</p>
     *
     * @return размер левого поля в twips
     */
    public Integer getLeftTwips() {
        return this.left;
    }

    /**
     * Проверяет, установлено ли левое поле страницы.
     *
     * @return {@code true}, если значение левого поля установлено
     */
    public boolean hasLeft() {
        return this.left != null;
    }

}
