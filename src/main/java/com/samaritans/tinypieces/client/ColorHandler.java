package com.samaritans.tinypieces.client;

import com.samaritans.tinypieces.TinyPieces;
import com.samaritans.tinypieces.core.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.item.BlockItem;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = TinyPieces.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ColorHandler {
    @SubscribeEvent
    public static void colorBlocks(ColorHandlerEvent.Block event) {
        event.getBlockColors().register((state, light, pos, layer) -> light != null && pos != null ? BiomeColors.getWaterColor(light, pos) : 8863, ModBlocks.water_puddle);
}

    @SubscribeEvent
    public static void colorItems(ColorHandlerEvent.Item event) {
        event.getItemColors().register((stack, layer) ->  {
            BlockState blockstate = ((BlockItem)stack.getItem()).getBlock().getDefaultState();
            return event.getBlockColors().getColor(blockstate, null, null, layer);
        }, ModBlocks.water_puddle);
    }
}
