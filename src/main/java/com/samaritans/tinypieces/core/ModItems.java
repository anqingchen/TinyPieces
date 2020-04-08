package com.samaritans.tinypieces.core;

import com.samaritans.tinypieces.TinyPieces;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

import static com.samaritans.tinypieces.TinyPieces.MODID;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);
    public static final RegistryObject<Item> cooked_egg = ITEMS.register("cooked_egg", () -> new Item(new Item.Properties().food(new Food.Builder().fastToEat().hunger(6).saturation(10).build()).group(TinyPieces.TAB)));
    public static final RegistryObject<Item> wither_bone = ITEMS.register("wither_bone", () -> new Item(new Item.Properties().group(TinyPieces.TAB)));
}
