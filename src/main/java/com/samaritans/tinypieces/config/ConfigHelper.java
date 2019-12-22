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
        Config.rabbit_spawn = ConfigHolder.SERVER.RABBIT_SPAWN.get();
        Config.chicken_feather = ConfigHolder.SERVER.CHICKEN_FEATHER.get();
        Config.extra_stairs = ConfigHolder.SERVER.EXTRA_STAIRS.get();

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
