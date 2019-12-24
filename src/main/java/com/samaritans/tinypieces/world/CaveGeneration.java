package com.samaritans.tinypieces.world;

import com.samaritans.tinypieces.config.Config;
import com.samaritans.tinypieces.core.ModBlocks;
import com.samaritans.tinypieces.world.feature.ModFeature;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.registries.ForgeRegistries;

public class CaveGeneration {
    public static void setupCaveGen() {
        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            if (Config.mossy_forest && BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST)) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION,
                        Biome.createDecoratedFeature(ModFeature.CAVE_WALL,
                                new ReplaceBlockConfig(Blocks.MOSSY_COBBLESTONE.getDefaultState(), Config.mossy_stone ? ModBlocks.mossy_stone.getDefaultState() : Blocks.MOSSY_COBBLESTONE.getDefaultState()),
                                Placement.COUNT_RANGE,
                                new CountRangeConfig(2048, 30, 0, 80)));
            }
            if (Config.mossy_jungle && BiomeDictionary.hasType(biome, BiomeDictionary.Type.JUNGLE)) {
                biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                        Biome.createDecoratedFeature(ModFeature.JUNGLE_CAVE_VINES,
                                IFeatureConfig.NO_FEATURE_CONFIG,
                                Placement.COUNT_RANGE,
                                new CountRangeConfig(25, 0, 0, 64)));
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION,
                        Biome.createDecoratedFeature(ModFeature.CAVE_WALL,
                                new ReplaceBlockConfig(Blocks.MOSSY_COBBLESTONE.getDefaultState(), Config.mossy_stone ? ModBlocks.mossy_stone.getDefaultState() : Blocks.MOSSY_COBBLESTONE.getDefaultState()),
                                Placement.COUNT_RANGE,
                                new CountRangeConfig(2048, 25, 0, 64)));
            }
            if (Config.icy_snow && BiomeDictionary.hasType(biome, BiomeDictionary.Type.SNOWY)) {
                GenerationHelper.addOreGentoBiome(biome, OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.PACKED_ICE.getDefaultState(), 24, 8, 0, 64);
                GenerationHelper.addOreGentoBiome(biome, OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.BLUE_ICE.getDefaultState(), 16, 4, 0, 64);
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION,
                        Biome.createDecoratedFeature(ModFeature.ICE_GLAZE_FLOOR,
                                IFeatureConfig.NO_FEATURE_CONFIG,
                                Placement.COUNT_RANGE,
                                new CountRangeConfig(8, 0, 0, 64)));
            }
            if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN)) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION,
                        Biome.createDecoratedFeature(ModFeature.WATER_PUDDLE_FLOOR,
                                IFeatureConfig.NO_FEATURE_CONFIG,
                                Placement.COUNT_RANGE,
                                new CountRangeConfig(10, 0, 0, 64)));
            }
            if (Config.sandy_mesa && BiomeDictionary.hasType(biome, BiomeDictionary.Type.MESA)) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION,
                        Biome.createDecoratedFeature(ModFeature.CAVE_WALL,
                                new ReplaceBlockConfig(Blocks.TERRACOTTA.getDefaultState(), Blocks.TERRACOTTA.getDefaultState()),
                                Placement.COUNT_RANGE,
                                new CountRangeConfig(8192, 49, 0, 64)));
            }
            if (Config.sandy_desert && BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY)) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION,
                        Biome.createDecoratedFeature(ModFeature.CAVE_WALL,
                                new ReplaceBlockConfig(Blocks.SANDSTONE.getDefaultState(), Blocks.SANDSTONE.getDefaultState()),
                                Placement.COUNT_RANGE,
                                new CountRangeConfig(8192, 49, 0, 64)));
            }
        }
    }
}
