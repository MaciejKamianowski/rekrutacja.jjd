package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure{
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream()
                .filter(block -> block.getColor().equalsIgnoreCase(color))
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> matchingBlocks = new ArrayList<>();
        for (Block block : blocks) {
            if (block instanceof CompositeBlock compositeBlock) {
                List<Block> subBlocks = compositeBlock.getBlocks();
                for (var subBlock : subBlocks) {
                    if ( checkIfMaterialMatches(subBlock, material)) {
                        matchingBlocks.add(subBlock);
                    }
                }
            } else {
                if (checkIfMaterialMatches(block, material)) {
                    matchingBlocks.add(block);
                }
            }
        }
        return matchingBlocks;
    }
    private static boolean checkIfMaterialMatches(Block block, String material) {
        return block.getMaterial().equalsIgnoreCase(material);
    }

    @Override
    public int count() {
        int totalCount = 0;
        for (Block block : blocks) {
            if (block instanceof CompositeBlock compositeBlock) {
                totalCount += compositeBlock.getBlocks().size();
            } else {
                totalCount++;
            }
        }
        return totalCount;
    }
}
