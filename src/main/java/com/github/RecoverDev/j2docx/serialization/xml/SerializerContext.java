package com.github.RecoverDev.j2docx.serialization.xml;

import java.util.List;

interface SerializerContext {

    List<Serializer> getSerialize();

    void setSerialize(List<Serializer> serialize); 

}
