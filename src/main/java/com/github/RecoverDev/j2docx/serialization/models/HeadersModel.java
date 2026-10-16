package com.github.RecoverDev.j2docx.serialization.models;

import java.util.ArrayList;
import java.util.List;

import com.github.RecoverDev.j2docx.headerfooter.Header;

public class HeadersModel implements Model {
    private List<Model> graphModels = new ArrayList<>();

    private final Header header;

    public Header getHeader() {
        return header;
    }

    public HeadersModel(Header header) {
        this.header = header;
    }

    @Override
    public void analize(Model parent) {
        if (parent instanceof DocumentModel) {
            parent.addModelToGraph(this);

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
