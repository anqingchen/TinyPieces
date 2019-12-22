package com.samaritans.tinypieces;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {
    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static final Tweaks TWEAKS = new Tweaks(COMMON_BUILDER);
    public static final Client CLIENT = new Client(CLIENT_BUILDER);

    public static ForgeConfigSpec COMMON_CONFIG = COMMON_BUILDER.build();
    public static ForgeConfigSpec CLIENT_CONFIG = CLIENT_BUILDER.build();

    public static class Tweaks {
        public static final String CATEGORY_TWEAKS = "tweaks";

        public static ForgeConfigSpec.BooleanValue SHULKER_SPAWN;
        public static ForgeConfigSpec.BooleanValue RABBIT_SPAWN;
        public static ForgeConfigSpec.BooleanValue CHICKEN_FEATHER;

        public Tweaks(ForgeConfigSpec.Builder builder) {
            builder.push(CATEGORY_TWEAKS);
            SHULKER_SPAWN = builder.comment("Allow shulkers to re-spawn in end city structures")
                    .define("Enable Shulkers Respawn", true);
            RABBIT_SPAWN = builder.comment("Allow rabbits to spawn in biomes tagged with FOREST")
                    .define("Enable Rabbits Spawn in Forest", true);
            CHICKEN_FEATHER = builder.comment("Allow chickens to drop feathers randomly")
                    .define("Enable Chickens Drop Feather", true);
            builder.pop();
        }
    }

    public static class Client {
        public static final String CATEGORY_CLIENT = "client";

        public static ForgeConfigSpec.BooleanValue SHEEP_STUBBLE;

        public Client(ForgeConfigSpec.Builder builder) {
            builder.push(CATEGORY_CLIENT);
            SHEEP_STUBBLE = builder.comment("Make sheep show their color after being sheared")
                    .define("Enable Sheep Stubble Color", true);
        }
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
