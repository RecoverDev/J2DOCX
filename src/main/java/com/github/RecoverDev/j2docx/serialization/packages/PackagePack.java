package com.github.RecoverDev.j2docx.serialization.packages;

import java.util.Objects;

public record PackagePack(String path, byte[] content) {
    
    public PackagePack {
        Objects.requireNonNull(path);
        Objects.requireNonNull(content);
    }
}
