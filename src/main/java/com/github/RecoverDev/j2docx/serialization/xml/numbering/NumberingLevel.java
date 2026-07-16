package com.github.RecoverDev.j2docx.serialization.xml.numbering;

import java.util.Objects;

import com.github.RecoverDev.j2docx.enums.NumberingStyle;
import com.github.RecoverDev.j2docx.properties.ListingProperties;

final class NumberingLevel {

    private final Integer level;
    private final NumberingStyle numberingStyle;
    private final String pattern;
    private final Character bullet;
    private final Integer start;
    private final Integer left;
    private final Integer hanging;

    private NumberingLevel(Integer numLevel, ListingProperties properties) {
        this.level = numLevel;
        this.numberingStyle = properties.hasNumberingStyle() ? properties.getNumberingStyle() : NumberingStyle.DECIMAL;
        this.bullet = properties.getBullet();
        this.pattern = properties.hasPattern() ? properties.getPattern() : "%" + String.valueOf(this.level + 1) + ".";
        this.start = properties.hasStart() ? properties.getStart() : 1;
        this.left = properties.hasLeft() ? properties.getLeft() : 720 * (this.level + 1);
        this.hanging = properties.hasHanging() ? properties.getHanging() : 360;
    }

    public static  NumberingLevel of(Integer numLevel, ListingProperties properties) {

        return new NumberingLevel(numLevel, properties);

    }


    public Integer getLevel() {
        return level;
    }
    
    public boolean hasLevel() {
        return this.level != null;
    }

    public NumberingStyle getNumberingStyle() {
        return numberingStyle;
    }


    public boolean hasNumberingStyle() {
        return this.numberingStyle != null;
    }

    public String getPattern() {
        return pattern;
    }

    public boolean hasParrent() {
        return this.pattern != null;
    }

    public Character getBullet() {
        return bullet;
    }

    public boolean hasBullet() {
        return this.bullet != null;
    }

    public Integer getStart() {
        return start;
    }

    public boolean hasStart() {
        return this.start != null;
    }

    public Integer getLeft() {
        return this.left;
    }

    public boolean hasLeft() {
        return this.left != null;
    }

    public Integer getHanging() {
        return this.hanging;
    }

    public boolean hasHanging() {
        return this.hanging != null;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
    
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
    
        NumberingLevel other = (NumberingLevel) obj;
    
        return Objects.equals(level, other.level)
                && numberingStyle == other.numberingStyle
                && Objects.equals(pattern, other.pattern)
                && Objects.equals(bullet, other.bullet)
                && Objects.equals(start, other.start);        
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                    level,
                    numberingStyle,
                    pattern,
                    bullet,
                    start
        );    
    }

}
