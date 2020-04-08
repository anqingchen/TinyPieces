package com.samaritans.tinypieces;

import com.samaritans.tinypieces.config.Config;
import com.samaritans.tinypieces.config.ConfigHolder;
import com.samaritans.tinypieces.config.RecipeEnabledCondition;
import com.samaritans.tinypieces.core.ModBlocks;
import com.samaritans.tinypieces.core.ModItems;
import com.samaritans.tinypieces.handler.LivingEventHandler;
import com.samaritans.tinypieces.client.RenderHandler;
import com.samaritans.tinypieces.world.CaveGeneration;
import com.samaritans.tinypieces.world.OreGeneration;
import com.samaritans.tinypieces.world.feature.ModFeature;
import net.minecraft.advancements.Advancement;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TinyPieces.MODID)
public class TinyPieces {
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "tinypieces";

    public static final ItemGroup TAB = new ItemGroup("tabTinypieces") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.red_nether_rod.get());
        }
    };

    public TinyPieces() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerSpawns);
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC, "tinypieces-client.toml");
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC, "tinypieces-server.toml");
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(LivingEventHandler.class);

        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModFeature.FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void setup(final FMLCommonSetupEvent event) {
       // ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        CraftingHelper.register(RecipeEnabledCondition.Serializer.INSTANCE);
        OreGeneration.setupOreGen();
        CaveGeneration.setupCaveGen();
        LOGGER.debug("Common Setup Complete!");
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderHandler.init();
    }

    private void registerSpawns(final FMLLoadCompleteEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST)) {
                if (Config.rabbit_spawn)
                    biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(EntityType.RABBIT, Config.rabbit_weight, Config.rabbit_group_min, Config.rabbit_group_max));
            }
        }
    }
}
