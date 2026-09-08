package com.github.RecoverDev.j2docx.serialization.xml.numbering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.block.Listing;
import com.github.RecoverDev.j2docx.serialization.models.DocumentModel;
import com.github.RecoverDev.j2docx.serialization.models.Model;

public final class NumberingModel implements Model{
    
    private static NumberingModel instanceModel = new NumberingModel();
    private List<Model> graphModels = new ArrayList<>();


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
            addNumberingInstance(aNumbering, list);
        }
    }

    public static void create() {
        instanceModel = new NumberingModel();
    }

    public static void create(DocumentX document) {
        instanceModel = new NumberingModel(document);
    }

    public static NumberingModel getInstance() {
        return instanceModel;
    }

    public Integer addListing(Listing listing) {
        AbstractNumbering aNumbering = addAbstractNumbering(listing);
        addNumberingInstance(aNumbering, listing);
        return this.listings.get(listing).getId();

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

    private void addNumberingInstance(AbstractNumbering abstractNumbering, Listing listing) {

        this.listings.computeIfAbsent(listing, key -> {
            NumberingInstance instance = new NumberingInstance(
                    this.numberingInstances.size() + 1,
                    abstractNumbering.getId());
    
            this.numberingInstances.add(instance);
            return instance;
        });        
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

    @Override
    public void analize(Model parent) {
        if (parent instanceof DocumentModel documentModel) {
            for (Listing list : documentModel.getLisrtings()) {
                AbstractNumbering aNumbering = addAbstractNumbering(list);
                addNumberingInstance(aNumbering, list);
            }

            if (this.listings.size() > 0 ) {
                documentModel.addModelToGraph(this);
            }
        }
    }

    @Override
    public void setGraph(List<Model> models) {
        this.graphModels = models;
    }

    @Override
    public void addModelToGraph(Model model) {
        this.graphModels.add(model);
    }

    @Override
    public List<Model> getModels() {
        return this.graphModels;
    }

}


