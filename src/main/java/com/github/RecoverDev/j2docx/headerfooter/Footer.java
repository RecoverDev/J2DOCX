package com.github.RecoverDev.j2docx.headerfooter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.RecoverDev.j2docx.DocumentElement;
import com.github.RecoverDev.j2docx.block.Block;

public class Footer implements DocumentElement, Block {

    private final List<Block> blocks = new ArrayList<>();

    @Override
    public Collection<DocumentElement> getElements() {
        return this.blocks.stream()
                          .filter(DocumentElement.class::isInstance)
                          .map(DocumentElement.class::cast)
                          .toList();
    }

    public List<Block> getBlocks() {
        return List.copyOf(this.blocks);
    }
}
