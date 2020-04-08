package com.samaritans.tinypieces.world.feature;

import com.samaritans.tinypieces.core.Util;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.samaritans.tinypieces.TinyPieces.MODID;

public class ModFeature {
    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, MODID);
    public static final RegistryObject<Feature<NoFeatureConfig>> CAVE_VINES = FEATURES.register("cave_vines", () -> new CaveVinesFeature(NoFeatureConfig::deserialize));
    public static final RegistryObject<Feature<NoFeatureConfig>> ICE_GLAZE_FLOOR = FEATURES.register("ice_glaze_floor", () -> new CaveFloorFeature(NoFeatureConfig::deserialize, true));
    public static final RegistryObject<Feature<NoFeatureConfig>> WATER_PUDDLE_FLOOR = FEATURES.register("water_puddle_floor", () -> new CaveFloorFeature(NoFeatureConfig::deserialize, false));
    public static final RegistryObject<Feature<ReplaceBlockConfig>> CAVE_WALL = FEATURES.register("cave_wall", () -> new CaveWallFeature(ReplaceBlockConfig::deserialize));
    public static final RegistryObject<Feature<NoFeatureConfig>> CAVE_DEAD_CORAL = FEATURES.register("cave_coral", () -> new CaveDeadCoralFeature(NoFeatureConfig::deserialize));
}
