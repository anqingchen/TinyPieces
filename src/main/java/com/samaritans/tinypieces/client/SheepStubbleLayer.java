package com.samaritans.tinypieces.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.samaritans.tinypieces.TinyPieces;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ArmorLayer;
import net.minecraft.client.renderer.entity.layers.EndermanEyesLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.SheepWoolLayer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.entity.model.SheepModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class SheepStubbleLayer extends LayerRenderer<SheepEntity, SheepModel<SheepEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(TinyPieces.MODID, "textures/entities/sheep_stubble.png");
    private final SheepModel<SheepEntity> sheepModel = new SheepModel();

    public SheepStubbleLayer(IEntityRenderer<SheepEntity, SheepModel<SheepEntity>> p_i50925_1_) {
        super(p_i50925_1_);
    }

    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int p_225628_3_, SheepEntity bah, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        if (bah.getSheared() && !bah.isInvisible()) {
            float f;
            float f1;
            float f2;
            if (bah.hasCustomName() && "jeb_".equals(bah.getName().getUnformattedComponentText())) {
                int i = bah.ticksExisted / 25 + bah.getEntityId();
                int j = DyeColor.values().length;
                int k = i % j;
                int l = (i + 1) % j;
                float f3 = ((float)(bah.ticksExisted % 25) + p_225628_7_) / 25.0F;
                float[] afloat1 = SheepEntity.getDyeRgb(DyeColor.byId(k));
                float[] afloat2 = SheepEntity.getDyeRgb(DyeColor.byId(l));
                f = afloat1[0] * (1.0F - f3) + afloat2[0] * f3;
                f1 = afloat1[1] * (1.0F - f3) + afloat2[1] * f3;
                f2 = afloat1[2] * (1.0F - f3) + afloat2[2] * f3;
            } else {
                float[] afloat = SheepEntity.getDyeRgb(bah.getFleeceColor());
                f = afloat[0];
                f1 = afloat[1];
                f2 = afloat[2];
            }

            render(this.getEntityModel(), this.sheepModel, TEXTURE, matrixStack, buffer, p_225628_3_, bah, p_225628_5_, p_225628_6_, p_225628_8_, p_225628_9_, p_225628_10_, p_225628_7_, f, f1, f2);
        }

    }
}
