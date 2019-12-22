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
    public static final Blocks BLOCKS = new Blocks(COMMON_BUILDER);
    public static final Items ITEMS = new Items(COMMON_BUILDER);
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

    public static class Blocks {
        public static final String CATEGORY_BLOCKS = "blocks";

        public static ForgeConfigSpec.BooleanValue NETHER_GOLD_ORE;
        public static ForgeConfigSpec.BooleanValue QUARTZ_ORE;
        public static ForgeConfigSpec.BooleanValue NETHER_ROD;
        public static ForgeConfigSpec.BooleanValue RED_NETHER_ROD;
        public static ForgeConfigSpec.BooleanValue MOSSY_STONE;
        public static ForgeConfigSpec.BooleanValue ICE_GLAZE;
        public static ForgeConfigSpec.BooleanValue WATER_PUDDLE;

        public Blocks(ForgeConfigSpec.Builder builder) {
            builder.push(CATEGORY_BLOCKS);
            NETHER_GOLD_ORE = builder.comment("Enable Nether Gold Ore Blocks")
                    .define("Enable Nether Gold Ores", true);
            QUARTZ_ORE = builder.comment("Enable Overworld Quartz Ore Blocks")
                    .define("Enable Quartz Ores", true);
            NETHER_ROD = builder.comment("Enable Nether Rod Blocks")
                    .define("Enable Nether Rods", true);
            RED_NETHER_ROD = builder.comment("Enable Red Nether Rod Blocks")
                    .define("Enable Red Nether Rods", true);
            MOSSY_STONE = builder.comment("Enable Mossy Stone Blocks")
                    .define("Enable Mossy Stone", true);
            ICE_GLAZE = builder.comment("Enable Ice Glaze Blocks")
                    .define("Enable Ice Glazes", true);
            WATER_PUDDLE = builder.comment("Enable Water Puddle Blocks")
                    .define("Enable Water Puddles", true);
            builder.pop();
        }
    }

    public static class Items {
        public static final String CATEGORY_ITEMS = "items";

        public static ForgeConfigSpec.BooleanValue COOKED_EGG;

        public Items(ForgeConfigSpec.Builder builder) {
            builder.push(CATEGORY_ITEMS);
            COOKED_EGG = builder.comment("Enable Cooked Egg Items")
                    .define("Enable Cooked Eggs", true);
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
