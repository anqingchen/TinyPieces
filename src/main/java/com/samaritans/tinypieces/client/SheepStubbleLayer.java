package com.samaritans.tinypieces.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.samaritans.tinypieces.TinyPieces;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.SheepModel;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SheepStubbleLayer extends LayerRenderer<SheepEntity, SheepModel<SheepEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(TinyPieces.MODID, "textures/entities/sheep_stubble.png");
    private final SheepModel<SheepEntity> sheepModel = new SheepModel();

    public SheepStubbleLayer(IEntityRenderer<SheepEntity, SheepModel<SheepEntity>> p_i50925_1_) {
        super(p_i50925_1_);
    }

    public void render(SheepEntity sheepEntity, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
        if (sheepEntity.getSheared() && !sheepEntity.isInvisible()) {
            this.bindTexture(TEXTURE);

            if (sheepEntity.hasCustomName() && "jeb_".equals(sheepEntity.getName().getUnformattedComponentText())) {
                int lvt_10_1_ = sheepEntity.ticksExisted / 25 + sheepEntity.getEntityId();
                int dyeColors = DyeColor.values().length;
                int dyeIdA = lvt_10_1_ % dyeColors;
                int dyeIdB = (lvt_10_1_ + 1) % dyeColors;
                float lvt_14_1_ = ((float)(sheepEntity.ticksExisted % 25) + p_212842_4_) / 25.0F;
                float[] lvt_15_1_ = SheepEntity.getDyeRgb(DyeColor.byId(dyeIdA));
                float[] lvt_16_1_ = SheepEntity.getDyeRgb(DyeColor.byId(dyeIdB));
                GlStateManager.color3f(lvt_15_1_[0] * (1.0F - lvt_14_1_) + lvt_16_1_[0] * lvt_14_1_, lvt_15_1_[1] * (1.0F - lvt_14_1_) + lvt_16_1_[1] * lvt_14_1_, lvt_15_1_[2] * (1.0F - lvt_14_1_) + lvt_16_1_[2] * lvt_14_1_);
            } else {
                float[] dyeRgb = SheepEntity.getDyeRgb(sheepEntity.getFleeceColor());
                GlStateManager.color3f(dyeRgb[0], dyeRgb[1], dyeRgb[2]);
            }

            ((SheepModel)this.getEntityModel()).setModelAttributes(this.sheepModel);
            this.sheepModel.setLivingAnimations(sheepEntity, p_212842_2_, p_212842_3_, p_212842_4_);
            this.sheepModel.render(sheepEntity, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);
        }
    }

    public boolean shouldCombineTextures() {
        return true;
    }
}
