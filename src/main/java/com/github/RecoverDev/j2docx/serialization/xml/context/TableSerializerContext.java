package com.github.RecoverDev.j2docx.serialization.xml.context;

import java.util.ArrayList;
import java.util.List;

public final class TableSerializerContext implements SerializerContext{
    private List<Serializer> serializers = new ArrayList<>();

    @Override
    public List<Serializer> getSerialize() {
        return this.serializers;
    }

    @Override
    public void setSerialize(List<Serializer> serialize) {
        this.serializers = serialize;
    }

}
