package com.samaritans.tinypieces.core;

import com.samaritans.tinypieces.TinyPieces;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TinyPieces.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TinyPieces.MODID)
public class ModItems {
    public static final Item cooked_egg = null;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                Util.setup(new Item(new Item.Properties().food(new Food.Builder().fastToEat().hunger(6).saturation(10).build())), "cooked_egg")
        );
    }
}
