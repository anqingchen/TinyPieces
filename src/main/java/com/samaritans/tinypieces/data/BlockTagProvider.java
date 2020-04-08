package com.samaritans.tinypieces.data;

import com.google.common.base.Predicate;
import com.samaritans.tinypieces.TinyPieces;
import net.minecraft.block.*;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;

import javax.annotation.Nonnull;
import java.util.Comparator;

public class BlockTagProvider extends BlockTagsProvider {
    public BlockTagProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void registerTags() {
        Predicate<Block> tp = b -> TinyPieces.MODID.equals(b.getRegistryName().getNamespace());

        getBuilder(BlockTags.SLABS).add(registry.stream().filter(tp)
                .filter(b -> b instanceof SlabBlock)
                .sorted(Comparator.comparing(Block::getRegistryName))
                .toArray(Block[]::new));

        getBuilder(BlockTags.STAIRS).add(registry.stream().filter(tp)
                .filter(b -> b instanceof StairsBlock)
                .sorted(Comparator.comparing(Block::getRegistryName))
                .toArray(Block[]::new));

        getBuilder(BlockTags.WALLS).add(registry.stream().filter(tp)
                .filter(b -> b instanceof WallBlock)
                .sorted(Comparator.comparing(Block::getRegistryName))
                .toArray(Block[]::new));

        getBuilder(BlockTags.FENCES).add(registry.stream().filter(tp)
                .filter(b -> b instanceof FenceBlock)
                .sorted(Comparator.comparing(Block::getRegistryName))
                .toArray(Block[]::new));

    }

    @Nonnull
    @Override
    public String getName() {
        return "Tiny Pieces Block Tag Provider";
    }
}
