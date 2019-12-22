package com.samaritans.tinypieces.core;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModColorManager {
    private static final Minecraft minecraft = Minecraft.getInstance();

    /**
     * Register the colour handlers.
     */
    public static void registerColourHandlers() {
        final BlockColors blockColors = minecraft.getBlockColors();
        registerBlockColourHandlers(blockColors);
    }

    /**
     * Register the {@link IBlockColor} handlers.
     *
     * @param blockColors The BlockColors instance
     */
    private static void registerBlockColourHandlers(final BlockColors blockColors) {
        final IBlockColor waterColorHandler = (state, blockAccess, pos, tintIndex) -> {
            if (blockAccess != null && pos != null) {
                return BiomeColors.getWaterColor(blockAccess, pos);
            }
            // todo: find default water color
            return GrassColors.get(0.5d, 1.0d);
        };

        blockColors.register(waterColorHandler, ModBlocks.water_puddle);
    }
}
