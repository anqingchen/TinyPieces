package com.samaritans.tinypieces;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TinyPieces.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TinyPieces.MODID)
public class ModItems {
    public static final Item COOKED_EGG;

    static {
        COOKED_EGG = new Item(new Item.Properties().food(new Food.Builder().fastToEat().hunger(6).saturation(10).build()));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();
        if (Config.Items.COOKED_EGG.get()) r.register(Util.setup(COOKED_EGG, "cooked_egg"));
    }
}
