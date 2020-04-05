package com.samaritans.tinypieces;

import com.samaritans.tinypieces.client.StubbledSheepRenderer;
import com.samaritans.tinypieces.config.Config;
import com.samaritans.tinypieces.config.ConfigHolder;
import com.samaritans.tinypieces.config.RecipeEnabledCondition;
import com.samaritans.tinypieces.core.ModBlocks;
import com.samaritans.tinypieces.core.ModColorManager;
import com.samaritans.tinypieces.core.ModEventHandlers;
import com.samaritans.tinypieces.world.CaveGeneration;
import com.samaritans.tinypieces.world.OreGeneration;
import com.samaritans.tinypieces.world.feature.ModFeature;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TinyPieces.MODID)
public class TinyPieces {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "tinypieces";

    public static final ItemGroup TAB = new ItemGroup("tabTinypieces") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.red_nether_rod);
        }
    };

    public TinyPieces() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        // Register rabbit spawns
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerSpawns);

        // Register the Config files
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC, "tinypieces-client.toml");
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC, "tinypieces-server.toml");

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(ModEventHandlers.class);

        ModFeature.init();
    }

    private void setup(final FMLCommonSetupEvent event) {
        // pre-init
        if (Config.sheep_stubble)
            RenderingRegistry.registerEntityRenderingHandler(SheepEntity.class, StubbledSheepRenderer::new);
        CraftingHelper.register(RecipeEnabledCondition.Serializer.INSTANCE);
        OreGeneration.setupOreGen();
        CaveGeneration.setupCaveGen();
        LOGGER.debug("Common Setup Complete!");
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        ModColorManager.registerColourHandlers();
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
//        InterModComms.sendTo("examplemod", "helloworld", () -> {
//            LOGGER.info("Hello world from the MDK");
//            return "Hello world";
//        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
//        LOGGER.info("Got IMC {}", event.getIMCStream().
//                map(m -> m.getMessageSupplier().get()).
//                collect(Collectors.toList()));
    }

    private void registerSpawns(final FMLLoadCompleteEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST)) {
                if (Config.rabbit_spawn)
                    biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(EntityType.RABBIT, Config.rabbit_weight, Config.rabbit_group_min, Config.rabbit_group_max));
            }
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
    }
}
