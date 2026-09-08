package com.github.RecoverDev.j2docx.serialization.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.block.Listing;
import com.github.RecoverDev.j2docx.styles.Style;

public class DocumentModel implements Model {
    private final DocumentX document;

    private List<Listing> lisrtings = new ArrayList<>();
    private List<Style<?>> styles = new ArrayList<>();

    private List<Model> graphModels = new ArrayList<>();

    public DocumentModel(DocumentX document) {
        this.document = document;
        traversal(document.getElements());
        document.styles().forEach(s -> this.styles.add(s));
    }

    public DocumentX getDocumentX() {
        return this.document;
    }

    public List<Listing> getLisrtings() {
        return lisrtings;
    }

    public List<Style<?>> getStyles() {
        return styles;
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



    @Override
    public void analize(Model parent) {
    }

    private void traversal(Collection<DocumentElement> elements) {
        for (DocumentElement element : elements) {
             
            if (element instanceof Listing listing) {
                this.lisrtings.add(listing);
            }
            traversal(element.getElements());
        }
    }

}
