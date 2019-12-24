package com.samaritans.tinypieces.config;

import net.minecraftforge.fml.config.ModConfig;

public class ConfigHelper {
    private static ModConfig clientConfig;
    private static ModConfig serverConfig;

    public static void bakeClient(final ModConfig config) {
        clientConfig = config;
        Config.sheep_stubble = ConfigHolder.CLIENT.SHEEP_STUBBLE.get();
    }

    public static void bakeServer(final ModConfig config) {
        serverConfig = config;
        Config.shulker_spawn = ConfigHolder.SERVER.SHULKER_SPAWN.get();
        Config.shulker_weight = ConfigHolder.SERVER.SHULKER_SPAWN_WEIGHT.get();
        Config.shulker_group_min = ConfigHolder.SERVER.SHULKER_SPAWN_GROUP_MIN.get();
        Config.shulker_group_max = ConfigHolder.SERVER.SHULKER_SPAWN_GROUP_MAX.get();
        Config.rabbit_spawn = ConfigHolder.SERVER.RABBIT_SPAWN.get();
        Config.rabbit_weight = ConfigHolder.SERVER.RABBIT_SPAWN_WEIGHT.get();
        Config.rabbit_group_min = ConfigHolder.SERVER.RABBIT_SPAWN_GROUP_MIN.get();
        Config.rabbit_group_max = ConfigHolder.SERVER.RABBIT_SPAWN_GROUP_MAX.get();
        Config.cave_spider_spawn = ConfigHolder.SERVER.CAVE_SPIDER_SPAWN.get();
        Config.cave_spider_weight = ConfigHolder.SERVER.CAVE_SPIDER_WEIGHT.get();
        Config.cave_spider_spawn_group_min = ConfigHolder.SERVER.CAVE_SPIDER_SPAWN_GROUP_MIN.get();
        Config.cave_spider_spawn_group_max = ConfigHolder.SERVER.CAVE_SPIDER_SPAWN_GROUP_MAX.get();
        Config.chicken_feather = ConfigHolder.SERVER.CHICKEN_FEATHER.get();
        Config.pig_rabbit_litter = ConfigHolder.SERVER.PIG_RABBIT_LITTER.get();
        Config.extra_stairs = ConfigHolder.SERVER.EXTRA_STAIRS.get();
        Config.bonemeal_stone = ConfigHolder.SERVER.BONEMEAL_STONE.get();
        Config.bonemeal_flower = ConfigHolder.SERVER.BONEMEAL_FLOWER.get();
        Config.pickup_cake = ConfigHolder.SERVER.PICKUP_CAKE.get();

        Config.mossy_forest = ConfigHolder.SERVER.MOSSY_FOREST.get();
        Config.mossy_jungle = ConfigHolder.SERVER.MOSSY_JUNGLE.get();
        Config.icy_snow = ConfigHolder.SERVER.ICY_SNOW.get();
        Config.sandy_desert = ConfigHolder.SERVER.SANDY_DESERT.get();
        Config.sandy_mesa = ConfigHolder.SERVER.SANDY_MESA.get();

        Config.nether_gold_ore = ConfigHolder.SERVER.NETHER_GOLD_ORE.get();
        Config.nether_gold_ore_chance = ConfigHolder.SERVER.NETHER_GOLD_ORE_CHANCE.get();
        Config.nether_gold_ore_size = ConfigHolder.SERVER.NETHER_GOLD_ORE_SIZE.get();
        Config.nether_gold_ore_height_min = ConfigHolder.SERVER.NETHER_GOLD_ORE_HEIGHT_MIN.get();
        Config.nether_gold_ore_height_max = ConfigHolder.SERVER.NETHER_GOLD_ORE_HEIGHT_MAX.get();

        Config.quartz_ore = ConfigHolder.SERVER.QUARTZ_ORE.get();
        Config.quartz_ore_chance = ConfigHolder.SERVER.QUARTZ_ORE_CHANCE.get();
        Config.quartz_ore_size = ConfigHolder.SERVER.QUARTZ_ORE_SIZE.get();
        Config.quartz_ore_height_min = ConfigHolder.SERVER.QUARTZ_ORE_HEIGHT_MIN.get();
        Config.quartz_ore_height_max = ConfigHolder.SERVER.QUARTZ_ORE_HEIGHT_MAX.get();

        Config.nether_rod = ConfigHolder.SERVER.NETHER_ROD.get();
        Config.red_nether_rod = ConfigHolder.SERVER.RED_NETHER_ROD.get();
        Config.mossy_stone = ConfigHolder.SERVER.MOSSY_STONE.get();
        Config.ice_glaze = ConfigHolder.SERVER.ICE_GLAZE.get();
        Config.water_puddle = ConfigHolder.SERVER.WATER_PUDDLE.get();

        Config.cooked_egg = ConfigHolder.SERVER.COOKED_EGG.get();
    }

    /**
     * Helper method to set a value on a config and then save the config.
     *
     * @param modConfig The ModConfig to change and save
     * @param path      The name/path of the config entry
     * @param newValue  The new value of the config entry
     */
    public static void setValueAndSave(final ModConfig modConfig, final String path, final Object newValue) {
        modConfig.getConfigData().set(path, newValue);
        modConfig.save();
    }
}
