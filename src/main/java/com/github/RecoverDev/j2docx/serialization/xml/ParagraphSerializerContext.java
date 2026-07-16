package com.github.RecoverDev.j2docx.serialization.xml;

import java.util.ArrayList;
import java.util.List;

final class ParagraphSerializerContext implements SerializerContext {

    private List<Serializer> serialize = new ArrayList<>();

    @Override
    public List<Serializer> getSerialize() {
        return serialize;
    }

    @Override
    public void setSerialize(List<Serializer> serialize) {
        this.serialize = serialize;
    }

}
