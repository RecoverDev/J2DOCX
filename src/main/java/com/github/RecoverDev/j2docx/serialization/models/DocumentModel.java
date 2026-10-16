package com.github.RecoverDev.j2docx.serialization.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.DocumentX;
import com.github.RecoverDev.j2docx.block.Listing;
import com.github.RecoverDev.j2docx.styles.Style;
import com.github.RecoverDev.j2docx.headerfooter.Header;
import com.github.RecoverDev.j2docx.section.Section;

public class DocumentModel implements Model {
    private final DocumentX document;

    private List<Listing> lisrtings = new ArrayList<>();
    private List<Style<?>> styles = new ArrayList<>();

    private List<Model> graphModels = new ArrayList<>();

    /**
     * Идея заключается в том, чтобы избежать дублирования при повторном добавлении одного и 
     * того же объекта, когда пользователья явно сообщил, что хочет видеть в разных разделах
     * один и тот же колонтитул. При этом мы отказаваемся от сравнения колонтитулов по содержимому,
     * если это разные объекты, т.к. в последствии, пользователь может изменить содержимое одного из них.
     * Переложим контроль уникальности объектов на стандартную бибилиотеку Java
     */
    private final Set<Header> headers = Collections.newSetFromMap(new IdentityHashMap<>());

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

    public Set<Header> getHeaders() {
        return headers;
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
            if (element instanceof Section section) {
                if (section.hasDefaultHeader()) {
                    this.headers.add(section.getDefaultHeader());
                    //addHeader(section.getDefaultHeader());
                }
                if (section.hasEvenHeader()) {
                    this.headers.add(section.getEvenHeader());
                    //addHeader(section.getEvenHeader());
                }
                if (section.hasFirstHeader()) {
                    this.headers.add(section.getFirstHeader());
                    //addHeader(section.getFirstHeader());
                }
            }

            traversal(element.getElements());
        }
    }

    // private void addHeader(Header header) {

    //     String headerId = generateHeaderId(header);
    //     if (headerId.length() > 0) {
    //         header.setHeaderId(headerId);
    //         this.headers.add(header);
    //     }
    // }

    // private String generateHeaderId(Header header) {
    //     String result = "";
    //     if (!this.headers.contains(header)) {
    //         result = String.valueOf(this.headers.size() + 1);
    //     }
    //     return result;
    // }


}
