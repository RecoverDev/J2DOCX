package com.github.RecoverDev.j2docx.serialization.xml.context;

import java.util.List;

public interface SerializerContext {

    List<Serializer> getSerialize();

    void setSerialize(List<Serializer> serialize); 

}
