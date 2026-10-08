package com.github.RecoverDev.j2docx.section;

import com.github.RecoverDev.j2docx.utils.UnitConverter;

/**
 * Представляет размер страницы документа.
 *
 * <p>Размер страницы задается в миллиметрах, а внутреннее представление
 * хранится в твипах, используемых форматом OpenXML.</p>
 */
public final class PageSize {

    /**
     * Размер страницы формата A4.
     */
    public static final PageSize A4 = new PageSize(210, 297);

    /**
     * Размер страницы формата A5.
     */
    public static final PageSize A5 = new PageSize(148, 210);

    /**
     * Размер страницы формата Letter.
     */
    public static final PageSize LETTER = new PageSize(216, 279);

    /**
     * Размер страницы формата Legal.
     */
    public static final PageSize LEGAL = new PageSize(216, 356);

    private final int width;
    private final int height;

    private PageSize(int width, int height) {
        this.width = UnitConverter.millimetersToTwips(width);
        this.height = UnitConverter.millimetersToTwips(height);
    }

    /**
     * Создает размер страницы с указанной шириной и высотой.
     *
     * @param width ширина страницы в миллиметрах
     * @param height высота страницы в миллиметрах
     * @return размер страницы
     */
    public static PageSize of(int width, int height) {
        return new PageSize(width, height);
    }

    /**
     * Возвращает ширину страницы в миллиметрах.
     *
     * @return ширина страницы
     */
    public double getWidth() {
        return UnitConverter.twipsToMillimeters(width);
    }

    /**
     * Возвращает высоту страницы в миллиметрах.
     *
     * @return высота страницы
     */
    public double getHeight() {
        return UnitConverter.twipsToMillimeters(height);
    }

    /**
     * Возвращает ширину страницы в твипах.
     *
     * @return ширина страницы в твипах
     */
    public int getWidthTwips() {
        return width;
    }

    /**
     * Возвращает высоту страницы в твипах.
     *
     * @return высота страницы в твипах
     */
    public int getHeightTwips() {
        return height;
    }
}