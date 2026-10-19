package com.github.RecoverDev.j2docx.serialization.models;

import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.serialization.xml.numbering.NumberingModel;
import com.github.RecoverDev.j2docx.utils.UniqueIntGenerator;
import com.github.RecoverDev.j2docx.headerfooter.Footer;
import com.github.RecoverDev.j2docx.headerfooter.Header;


public class DocumentModelFactory {

    public static DocumentModel factory(DocumentX document, UniqueIntGenerator generator) {

        DocumentModel model = Model.of(document);

        model.analize(null);

        for (Header header : model.getHeaders()) {
            header.setHeaderId(String.valueOf(generator.next()));
            HeadersModel headersModel = Model.of(header);
            headersModel.analize(model);
        }

        for (Footer footer : model.getFooters()) {
            footer.setFooterId(String.valueOf(generator.next()));
            FooterModel footerModel = Model.of(footer);
            footerModel.analize(model);
        }

        StylesModel stylesModel = Model.of(document.styles());

        stylesModel.analize(model);



        NumberingModel.create();
        NumberingModel numberingModel = NumberingModel.getInstance();
        numberingModel.analize(model);

        return model;

    }

}
