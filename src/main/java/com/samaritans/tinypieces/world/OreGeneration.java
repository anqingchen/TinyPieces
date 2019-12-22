package com.samaritans.tinypieces.world;

import com.samaritans.tinypieces.config.Config;
import com.samaritans.tinypieces.core.ModBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration {
    public static void setupOreGen() {
        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            if (biome.getCategory() == Biome.Category.THEEND) continue;
            if (biome.getCategory() == Biome.Category.NETHER) {
                // generate nether ores
                if (Config.nether_gold_ore) {
                    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION,
                            Biome.createDecoratedFeature(Feature.ORE,
                                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.nether_gold_ore.getDefaultState(), Config.nether_gold_ore_size),
                                    Placement.COUNT_RANGE,
                                    new CountRangeConfig(Config.nether_gold_ore_chance, Config.nether_gold_ore_height_min, 0, Config.nether_gold_ore_height_max)));
                }
            } else {
                // generate overworld ores
                if (Config.quartz_ore) {
                    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                            Biome.createDecoratedFeature(Feature.ORE,
                                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.quartz_ore.getDefaultState(), Config.quartz_ore_size),
                                    Placement.COUNT_RANGE,
                                    new CountRangeConfig(Config.quartz_ore_chance, Config.quartz_ore_height_min, 0, Config.quartz_ore_height_max)));
                }
            }
        }
    }
}
