package com.samaritans.tinypieces.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndRodBlock;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class NetherRodBlock extends EndRodBlock {
    public NetherRodBlock() {
        super(Block.Properties.create(Material.IRON).hardnessAndResistance(5).harvestLevel(2).harvestTool(ToolType.PICKAXE));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        Direction lvt_5_1_ = (Direction)state.get(FACING);
        double posX = (double)pos.getX() + 0.55D - (double)(rand.nextFloat() * 0.1F);
        double posY = (double)pos.getY() + 0.55D - (double)(rand.nextFloat() * 0.1F);
        double posZ = (double)pos.getZ() + 0.55D - (double)(rand.nextFloat() * 0.1F);
        double mult = (double)(0.4F - (rand.nextFloat() + rand.nextFloat()) * 0.4F);
        if (rand.nextInt(5) == 0) {
            world.addParticle(ParticleTypes.FLAME, posX + (double)lvt_5_1_.getXOffset() * mult, posY + (double)lvt_5_1_.getYOffset() * mult, posZ + (double)lvt_5_1_.getZOffset() * mult, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D);
        }

    }
}
