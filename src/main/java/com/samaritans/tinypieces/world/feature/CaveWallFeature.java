package com.samaritans.tinypieces.world.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;

import java.util.Random;
import java.util.function.Function;

public class CaveWallFeature extends Feature<ReplaceBlockConfig> {
    public CaveWallFeature(Function<Dynamic<?>, ? extends ReplaceBlockConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, ReplaceBlockConfig config) {
        if (shouldReplace(world.getBlockState(pos).getBlock()) && isExposed(world, pos) && pos.getY() > 49) {
            world.setBlockState(pos, rand.nextDouble() < 0.3 ? config.target : config.state, 2);
            return true;
        }
        return false;
    }

    private boolean shouldReplace(Block block) {
        return block == Blocks.STONE || block == Blocks.GRANITE || block == Blocks.DIORITE || block == Blocks.ANDESITE;
    }

    private boolean isExposed(IWorld world, BlockPos pos) {
        for (Direction dir : Direction.values()) {
            if (world.getBlockState(pos.offset(dir)).isAir()) {
                return true;
            }
        }
        return false;
    }
}
