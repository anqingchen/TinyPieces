package com.samaritans.tinypieces.core;

import com.samaritans.tinypieces.TinyPieces;
import com.samaritans.tinypieces.data.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = TinyPieces.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(new BlockLootProvider(generator));
        generator.addProvider(new BlockTagProvider(generator));
        generator.addProvider(new ItemTagProvider(generator));
        generator.addProvider(new SmeltingProvider(generator));
        generator.addProvider(new StonecuttingProvider(generator));
    }
}
