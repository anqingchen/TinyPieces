package com.samaritans.tinypieces.client;

import com.samaritans.tinypieces.client.render.StubbledSheepRenderer;
import com.samaritans.tinypieces.config.Config;
import com.samaritans.tinypieces.core.ModBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
    public static void init(){
        if (Config.sheep_stubble)
            RenderingRegistry.registerEntityRenderingHandler(EntityType.SHEEP, StubbledSheepRenderer::new);

        RenderTypeLookup.setRenderLayer(ModBlocks.ice_glaze, RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.water_puddle, RenderType.getTranslucent());
    }
}
