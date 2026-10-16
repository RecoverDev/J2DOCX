package com.github.RecoverDev.j2docx;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;

import com.github.RecoverDev.j2docx.headerfooter.Header;

public class Headers implements Iterable<Header> {

    /**
     * Идея заключается в том, чтобы избежать дублирования при повторном добавлении одного и 
     * того же объекта, когда пользователья явно сообщил, что хочет видеть в разных разделах
     * один и тот же колонтитул. При этом мы отказаваемся от сравнения колонтитулов по содержимому,
     * если это разные объекты, т.к. в последствии, пользователь может изменить содержимое одного из них.
     * Переложим контроль уникальности объектов на стандартную бибилиотеку Java
     */
    private final Set<Header> headers = Collections.newSetFromMap(new IdentityHashMap<>());


    public Headers add(Header header) {

        String headerId = generateHeaderId(header);
        if (headerId.length() > 0) {
            header.setHeaderId(headerId);
            this.headers.add(header);
        }
        return this;
    }

    public Headers remove(Header header) {
        this.headers.remove(header);
        return this;
    }

    public Headers remove(String headerId) {
        this.headers.removeIf(h -> h.getHeaderId() == headerId);
        return this;
    }

    private String generateHeaderId(Header header) {
        String result = "";
        if (!this.headers.contains(header)) {
            result = String.valueOf(this.headers.size() + 1);
        }
        return result;
    }

	@Override
	public Iterator<Header> iterator() {
        return headers.iterator();
	}

}
