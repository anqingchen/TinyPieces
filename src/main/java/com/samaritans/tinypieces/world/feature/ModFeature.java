package com.samaritans.tinypieces.world.feature;

import com.samaritans.tinypieces.core.Util;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFeature {
    public static final Feature<NoFeatureConfig> JUNGLE_CAVE_VINES = new JungleCaveVinesFeature(NoFeatureConfig::deserialize);
    public static final Feature<NoFeatureConfig> ICE_GLAZE_FLOOR = new CaveFloorFeature(NoFeatureConfig::deserialize, true);
    public static final Feature<NoFeatureConfig> WATER_PUDDLE_FLOOR = new CaveFloorFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<ReplaceBlockConfig> CAVE_WALL = new CaveWallFeature(ReplaceBlockConfig::deserialize);

    public static void init() {
        ForgeRegistries.FEATURES.register(Util.setup(JUNGLE_CAVE_VINES, "jungle_cave_vines"));
        ForgeRegistries.FEATURES.register(Util.setup(ICE_GLAZE_FLOOR, "ice_glaze_floor"));
        ForgeRegistries.FEATURES.register(Util.setup(WATER_PUDDLE_FLOOR, "water_puddle_floor"));
        ForgeRegistries.FEATURES.register(Util.setup(CAVE_WALL, "cave_wall"));
    }
}
