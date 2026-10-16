package com.github.RecoverDev.j2docx.serialization.models;

import java.util.List;

import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.Styles;
import com.github.RecoverDev.j2docx.headerfooter.Header;

public interface Model {

    void analize(Model parent);
    void setGraph(List<Model> models);
    void addModelToGraph(Model model);
    List<Model> getModels();

    static DocumentModel of(DocumentX document) {
        return new DocumentModel(document);
    }

    static StylesModel of(Styles styles) {
        return new StylesModel(styles);
    }

    static HeadersModel of(Header header) {
        return new HeadersModel(header);
    }

}
