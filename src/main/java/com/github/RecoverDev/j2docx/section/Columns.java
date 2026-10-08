package com.github.RecoverDev.j2docx.section;

import com.github.RecoverDev.j2docx.utils.UnitConverter;

public final class Columns {

    private Integer count;
    private Integer spacing;

    private Columns() {
    }

    public static Columns create() {
        return new Columns();
    }

    public Columns count(int count) {
        this.count = count;
        return this;
    }

    public int getCount() {
        return this.count;
    }

    public boolean hasCount() {
        return this.count != null;
    }

    public Columns spacing(int spacing) {
        this.spacing = UnitConverter.millimetersToTwips(spacing);
        return this;
    }

    public double getSpacing() {
        return UnitConverter.twipsToMillimeters(this.spacing);
    }

    public int getSpacingTwips() {
        return this.spacing;
    }

    public boolean hasSpacing() {
        return this.spacing != null;
    }
}