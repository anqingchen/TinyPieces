package com.samaritans.tinypieces.world;

import com.samaritans.tinypieces.config.Config;
import com.samaritans.tinypieces.core.ModBlocks;
import com.samaritans.tinypieces.world.feature.ModFeature;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
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
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ModFeature.CAVE_WALL
                        .configure(new ReplaceBlockConfig(Blocks.MOSSY_COBBLESTONE.getDefaultState(), Config.mossy_stone ? ModBlocks.mossy_stone.getDefaultState() : Blocks.MOSSY_COBBLESTONE.getDefaultState()))
                        .createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(2048, 30, 0, 64))));
            }
            if (Config.mossy_jungle && BiomeDictionary.hasType(biome, BiomeDictionary.Type.JUNGLE)) {
                biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModFeature.CAVE_VINES
                        .configure(IFeatureConfig.NO_FEATURE_CONFIG)
                        .createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(25, 0, 0, 64))));

                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ModFeature.CAVE_WALL
                        .configure(new ReplaceBlockConfig(Blocks.MOSSY_COBBLESTONE.getDefaultState(), Config.mossy_stone ? ModBlocks.mossy_stone.getDefaultState() : Blocks.MOSSY_COBBLESTONE.getDefaultState()))
                        .createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(2048, 25, 0, 64))));
            }
            if (Config.icy_snow && BiomeDictionary.hasType(biome, BiomeDictionary.Type.SNOWY)) {
                GenerationHelper.addOreGentoBiome(biome, OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.PACKED_ICE.getDefaultState(), 24, 8, 0, 64);
                GenerationHelper.addOreGentoBiome(biome, OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.BLUE_ICE.getDefaultState(), 16, 4, 0, 64);

                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ModFeature.ICE_GLAZE_FLOOR
                        .configure(IFeatureConfig.NO_FEATURE_CONFIG)
                        .createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 0, 0, 64))));
            }
            if (Config.wet_ocean && BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN)) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ModFeature.WATER_PUDDLE_FLOOR
                        .configure(IFeatureConfig.NO_FEATURE_CONFIG)
                        .createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 64))));

                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ModFeature.CAVE_WALL
                        .configure(new ReplaceBlockConfig(Blocks.MOSSY_COBBLESTONE.getDefaultState(), Config.mossy_stone ? ModBlocks.mossy_stone.getDefaultState() : Blocks.MOSSY_COBBLESTONE.getDefaultState()))
                        .createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(2048, 30, 0, 64))));

                biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModFeature.CAVE_DEAD_CORAL
                        .configure(IFeatureConfig.NO_FEATURE_CONFIG)
                        .createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(100, 0, 0, 64))));

                GenerationHelper.addOreGentoBiome(biome, OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DEAD_BRAIN_CORAL_BLOCK.getDefaultState(), 16, 1, 0, 40);
                GenerationHelper.addOreGentoBiome(biome, OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DEAD_BUBBLE_CORAL_BLOCK.getDefaultState(), 16, 1, 0, 40);
                GenerationHelper.addOreGentoBiome(biome, OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DEAD_FIRE_CORAL_BLOCK.getDefaultState(), 16, 1, 0, 40);
                GenerationHelper.addOreGentoBiome(biome, OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DEAD_HORN_CORAL_BLOCK.getDefaultState(), 16, 1, 0, 40);
                GenerationHelper.addOreGentoBiome(biome, OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DEAD_TUBE_CORAL_BLOCK.getDefaultState(), 16, 1, 0, 40);
            }
            if (Config.sandy_mesa && BiomeDictionary.hasType(biome, BiomeDictionary.Type.MESA)) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ModFeature.CAVE_WALL
                        .configure(new ReplaceBlockConfig(Blocks.TERRACOTTA.getDefaultState(), Blocks.TERRACOTTA.getDefaultState()))
                        .createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(8192, 49, 0, 64))));
            }
            if (Config.sandy_desert && BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY)) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ModFeature.CAVE_WALL
                        .configure(new ReplaceBlockConfig(Blocks.SANDSTONE.getDefaultState(), Blocks.SANDSTONE.getDefaultState()))
                        .createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(8192, 49, 0, 64))));
            }
        }
    }
}
