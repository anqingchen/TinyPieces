package com.samaritans.tinypieces.config;

import net.minecraftforge.common.ForgeConfigSpec;

final class ServerConfig {
    private static final String CATEGORY_TWEAKS = "tweaks";
    final ForgeConfigSpec.BooleanValue SHULKER_SPAWN;
    final ForgeConfigSpec.BooleanValue RABBIT_SPAWN;
    final ForgeConfigSpec.BooleanValue CHICKEN_FEATHER;
    final ForgeConfigSpec.BooleanValue PIG_RABBIT_LITTER;
    final ForgeConfigSpec.BooleanValue EXTRA_STAIRS;

    private static final String CATEGORY_WORLD = "world";
    final ForgeConfigSpec.BooleanValue MOSSY_FOREST;
    final ForgeConfigSpec.BooleanValue MOSSY_JUNGLE;
    final ForgeConfigSpec.BooleanValue ICY_SNOW;
    final ForgeConfigSpec.BooleanValue SANDY_DESERT;
    final ForgeConfigSpec.BooleanValue SANDY_MESA;

    private static final String CATEGORY_BLOCKS = "blocks";
    private static final String SUBCATEGORY_NETHER_GOLD_ORE = "nether gold ore";
    final ForgeConfigSpec.BooleanValue NETHER_GOLD_ORE;
    final ForgeConfigSpec.IntValue NETHER_GOLD_ORE_CHANCE;
    final ForgeConfigSpec.IntValue NETHER_GOLD_ORE_SIZE;
    final ForgeConfigSpec.IntValue NETHER_GOLD_ORE_HEIGHT_MAX;
    final ForgeConfigSpec.IntValue NETHER_GOLD_ORE_HEIGHT_MIN;
    private static final String SUBCATEGORY_QUARTZ_ORE = "quartz ore";
    final ForgeConfigSpec.BooleanValue QUARTZ_ORE;
    final ForgeConfigSpec.IntValue QUARTZ_ORE_CHANCE;
    final ForgeConfigSpec.IntValue QUARTZ_ORE_SIZE;
    final ForgeConfigSpec.IntValue QUARTZ_ORE_HEIGHT_MAX;
    final ForgeConfigSpec.IntValue QUARTZ_ORE_HEIGHT_MIN;
    final ForgeConfigSpec.BooleanValue NETHER_ROD;
    final ForgeConfigSpec.BooleanValue RED_NETHER_ROD;
    final ForgeConfigSpec.BooleanValue MOSSY_STONE;
    final ForgeConfigSpec.BooleanValue ICE_GLAZE;
    final ForgeConfigSpec.BooleanValue WATER_PUDDLE;

    private static final String CATEGORY_ITEMS = "items";
    final ForgeConfigSpec.BooleanValue COOKED_EGG;

    ServerConfig(final ForgeConfigSpec.Builder builder) {
        builder.push(CATEGORY_TWEAKS);
        SHULKER_SPAWN = builder.comment("Allow shulkers to re-spawn in end city structures")
                .define("Enable Shulkers Respawn", true);
        RABBIT_SPAWN = builder.comment("Allow rabbits to spawn in biomes tagged with FOREST")
                .define("Enable Rabbits Spawn in Forest", true);
        CHICKEN_FEATHER = builder.comment("Allow chickens to drop feathers randomly")
                .define("Enable Chickens Drop Feather", true);
        PIG_RABBIT_LITTER = builder.comment("Allow Pigs and Rabbits to Make up to 4 Babies at A Time")
                .define("Enable Pig/Rabbit Litter", true);
        EXTRA_STAIRS = builder.comment("Craft 8 wooden stairs instead of 4")
                .define("Enable Extra Stairs", true);
        builder.pop();

        builder.push(CATEGORY_WORLD);
        MOSSY_FOREST = builder.comment("Enable Mossy Stone/Cobblestone to Generate in Caves in Biomes Tagged with FOREST")
                .define("Enable Mossy Forest", true);
        MOSSY_JUNGLE = builder.comment("Enable Mossy Stone/Cobblestone and Vines to Generate in Caves in Biomes Tagged with JUNGLE")
                .define("Enable Mossy Jungle", true);
        ICY_SNOW = builder.comment("Enable Packed Ice/Blue Ice Clusters and Ice Glaze to Generate in Caves in Biomes Tagged with SNOWY")
                .define("Enable Icy Snow", true);
        SANDY_DESERT = builder.comment("Enable Sandstone/Chiseled Sandstone Caves Walls in Biomes Tagged with SANDY")
                .define("Enable Sandy Desert", true);
        SANDY_MESA = builder.comment("Enable Terracotta/Red Standstone Cave Walls in Biomes Tagged with MESA")
                .define("Enable Sandy Mesa", true);

        builder.push(CATEGORY_BLOCKS);
        builder.push(SUBCATEGORY_NETHER_GOLD_ORE);
        NETHER_GOLD_ORE = builder.comment("Enable Nether Gold Ore Blocks")
                .define("Enable Nether Gold Ores", true);
        NETHER_GOLD_ORE_CHANCE = builder.comment("Chance for Nether Gold Ore to Generate")
                .defineInRange("Nether Gold Ore Chance", 10, 0, 100);
        NETHER_GOLD_ORE_SIZE = builder.comment("Set Cluster Size of Nether Gold Ores")
                .defineInRange("Nether Gold Ore Cluster Size", 9, 0, Integer.MAX_VALUE);
        NETHER_GOLD_ORE_HEIGHT_MIN = builder.comment("Set Minimum Y-Level of Nether Gold Ores")
                .defineInRange("Nether Gold Ore Min Height", 10, 0, 127);
        NETHER_GOLD_ORE_HEIGHT_MAX = builder.comment("Set Maximum Y-Level of Nether Gold Ores")
                .defineInRange("Nether Gold Ore Max Height", 108, 0, 127);
        builder.pop();
        builder.push(SUBCATEGORY_QUARTZ_ORE);
        QUARTZ_ORE = builder.comment("Enable Overworld Quartz Ore Blocks")
                .define("Enable Quartz Ores", true);
        QUARTZ_ORE_CHANCE = builder.comment("Chance for Quartz Ore to Generate")
                .defineInRange("Quartz Ore Chance", 6, 0, 100);
        QUARTZ_ORE_SIZE = builder.comment("Set Cluster Size of Quartz Ores")
                .defineInRange("Quartz Ore Cluster Size", 14, 0, Integer.MAX_VALUE);
        QUARTZ_ORE_HEIGHT_MIN = builder.comment("Set Minimum Y-Level of Quartz Ores")
                .defineInRange("Quartz Ore Min Height", 0, 0, 127);
        QUARTZ_ORE_HEIGHT_MAX = builder.comment("Set Maximum Y-Level of Quartz Ores")
                .defineInRange("Quartz Ore Max Height", 16, 0, 127);
        builder.pop();
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

        builder.push(CATEGORY_ITEMS);
        COOKED_EGG = builder.comment("Enable Cooked Egg Items")
                .define("Enable Cooked Eggs", true);
        builder.pop();
    }
}
