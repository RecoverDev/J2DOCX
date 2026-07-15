package com.github.RecoverDev.j2docx.serialization.xml.numbering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.block.Listing;

class NumberingModel {

    private final Map<List<NumberingLevel>, AbstractNumbering> abstractNumbering = new HashMap<>();
    private final List<NumberingInstance> numberingInstances = new ArrayList<>();
    private final Map<NumberingInstance, Listing> listings = new HashMap<>();

    private NumberingModel(DocumentX document) {
        List<Listing> listings = document.getBlocks()
                                    .stream()
                                    .filter(b -> b instanceof Listing)
                                    .map(Listing.class::cast)
                                    .collect(Collectors.toList());
        
        for (Listing list : listings) {
            AbstractNumbering aNumbering = addAbstractNumbering(list);
            addNumberingInstatce(aNumbering, list);
        }
    }

    public static NumberingModel of(DocumentX document) {
        return new NumberingModel(document);
    }


    private AbstractNumbering addAbstractNumbering(Listing listing) {

        AbstractNumbering abstractNumber = AbstractNumbering.of(listing);

        List<NumberingLevel> key = List.copyOf(abstractNumber.getLevels());

        AbstractNumbering result =
            abstractNumbering.computeIfAbsent(key, k -> {
                abstractNumber.setId(abstractNumbering.size());
                return abstractNumber;
            });        

        return result;
    }

    private void addNumberingInstatce(AbstractNumbering abstractNumbering, Listing listing) {
        NumberingInstance instance = new NumberingInstance(this.numberingInstances.size(), abstractNumbering.getId()); 
        this.numberingInstances.add(instance);
        this.listings.put(instance, listing);
    }

    public Map<List<NumberingLevel>, AbstractNumbering> getAbstractNumbering() {
        return abstractNumbering;
    }

    public List<NumberingInstance> getNumberingInstances() {
        return numberingInstances;
    }


}


