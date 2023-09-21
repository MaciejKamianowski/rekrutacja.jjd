package org.example;

import java.util.List;

public class ComplexBlock implements CompositeBlock {
    private final List<Block> blocks;
    private final String material;

    public ComplexBlock(String material, List<Block> blocks) {
        this.material = material;
        this.blocks = blocks;
    }
    @Override
    public String getColor() {
        // For a composite block, we can return the color of the first sub-block
        return !blocks.isEmpty() ? blocks.get(0).getColor() : "default";
    }
    @Override
    public String getMaterial() {
        return material;
    }
    @Override
    public List<Block> getBlocks() {
        return blocks;
    }
}