package com.samaritans.tinypieces.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SheepWoolLayer;
import net.minecraft.client.renderer.entity.model.SheepModel;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.ResourceLocation;

public class StubbledSheepRenderer extends MobRenderer<SheepEntity, SheepModel<SheepEntity>> {
    private static final ResourceLocation SHEARED_SHEEP_TEXTURES = new ResourceLocation("textures/entity/sheep/sheep.png");

    public StubbledSheepRenderer(EntityRendererManager p_i47195_1_) {
        super(p_i47195_1_, new SheepModel(), 0.7F);
        this.addLayer(new SheepWoolLayer(this));
        this.addLayer(new SheepStubbleLayer(this));
    }

    public ResourceLocation getEntityTexture(SheepEntity p_110775_1_) {
        return SHEARED_SHEEP_TEXTURES;
    }
}
