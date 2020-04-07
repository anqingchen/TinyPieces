package com.samaritans.tinypieces.world;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;

public class GenerationHelper {
    public static void addOreGentoBiome(Biome biome, OreFeatureConfig.FillerBlockType filler, BlockState blockState, int size, int count, int min, int max) {
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(filler, blockState, size)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(count, min, 0, max))));
    }
}
