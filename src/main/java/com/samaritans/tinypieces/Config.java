package com.samaritans.tinypieces;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {

    public static final String CATEGORY_TWEAKS = "tweaks";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.BooleanValue SHULKER_SPAWN;
    public static ForgeConfigSpec.BooleanValue RABBIT_SPAWN;
    public static ForgeConfigSpec.BooleanValue CHICKEN_FEATHER;

    static {
        COMMON_BUILDER.comment("Tweaks Settings").push(CATEGORY_TWEAKS);

        SHULKER_SPAWN = COMMON_BUILDER.comment("Allow shulkers to re-spawn in end city structures")
                .define("Enable Shulkers Respawn", true);
        RABBIT_SPAWN = COMMON_BUILDER.comment("Allow rabbits to spawn in biomes tagged with FOREST")
                .define("Enable Rabbits Spawn in Forest", true);
        CHICKEN_FEATHER = COMMON_BUILDER.comment("Allow chickens to drop feathers randomly")
                .define("Enable Chickens Drop Feather", true);

        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();
        configData.load();
        spec.setConfig(configData);
    }
}
