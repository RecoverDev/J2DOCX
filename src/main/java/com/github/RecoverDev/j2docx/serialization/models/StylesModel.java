package com.github.RecoverDev.j2docx.serialization.models;

import java.util.ArrayList;
import java.util.List;

import com.github.RecoverDev.j2docx.Styles;

public class StylesModel implements Model {
    private final Styles styles;
    private List<Model> graphModels = new ArrayList<>();

    
    public StylesModel(Styles styles) {
        this.styles = styles;
    }

    public Styles getStyles() {
        return styles;
    }

    @Override
    public void analize(Model parent) {
        if (parent instanceof DocumentModel documentModel) {
            if (documentModel.getStyles().size() > 0) {
                parent.addModelToGraph(this);
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
