package com.github.RecoverDev.j2docx.serialization.models;

import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.serialization.xml.numbering.NumberingModel;

public class DocumentModelFactory {

    public static DocumentModel factory(DocumentX document) {

        DocumentModel model = Model.of(document);

        model.analize(null);

        var stylesModel = Model.of(document.styles());

        stylesModel.analize(model);

        NumberingModel.create();
        NumberingModel numberingModel = NumberingModel.getInstance();
        numberingModel.analize(model);

        return model;

    }

}
