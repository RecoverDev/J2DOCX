package com.github.RecoverDev.j2docx.serialization.xml.numbering;

import java.util.ArrayList;
import java.util.List;

import com.github.RecoverDev.j2docx.block.Listing;

class AbstractNumbering {

    private Integer id;
    private List<NumberingLevel> levels = new ArrayList<>();

    public static  AbstractNumbering of(Listing listings) {
        AbstractNumbering abstractNumbering = new AbstractNumbering();
        getNumberingLevel(abstractNumbering, listings);
        return abstractNumbering;
    }

    public static void getNumberingLevel(AbstractNumbering numbering, Listing listing) {
        NumberingLevel level = NumberingLevel.of(listing.getProperties());
        level.setLevel(numbering.getLevels().size());
        numbering.getLevels().add(level);
        listing.getItems().stream().filter(l -> l instanceof Listing).forEach(l -> getNumberingLevel(numbering, (Listing)l));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<NumberingLevel> getLevels() {
        return levels;
    }

    public void setLevels(List<NumberingLevel> levels) {
        this.levels = levels;
    }

}
