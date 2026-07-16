package com.github.RecoverDev.j2docx.serialization.xml.numbering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.block.Listing;

public final class NumberingModel {
    private static NumberingModel instanceModel = new NumberingModel();

    private final Map<List<NumberingLevel>, AbstractNumbering> abstractNumbering = new HashMap<>();
    private final List<NumberingInstance> numberingInstances = new ArrayList<>();
    private final Map<Listing, NumberingInstance> listings = new IdentityHashMap<>();

    private NumberingModel() {}

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

    public static void create(DocumentX document) {
        instanceModel = new NumberingModel(document);
    }

    public static NumberingModel getInstance() {
        return instanceModel;
    }


    private AbstractNumbering addAbstractNumbering(Listing listing) {

        AbstractNumbering abstractNumber = AbstractNumbering.of(listing);

        List<NumberingLevel> key = List.copyOf(abstractNumber.getLevels());

        AbstractNumbering result =
            abstractNumbering.computeIfAbsent(key, k -> {
                abstractNumber.setId(abstractNumbering.size() + 1);
                return abstractNumber;
            });        

        return result;
    }

    private void addNumberingInstatce(AbstractNumbering abstractNumbering, Listing listing) {
        NumberingInstance instance = new NumberingInstance(this.numberingInstances.size() + 1, abstractNumbering.getId()); 
        this.numberingInstances.add(instance);
        this.listings.put(listing, instance);
    }

    public Map<List<NumberingLevel>, AbstractNumbering> getAbstractNumbering() {
        return abstractNumbering;
    }

    public List<NumberingInstance> getNumberingInstances() {
        return numberingInstances;
    }

    public Map<Listing, NumberingInstance> getListings() {
        return listings;
    }


}


