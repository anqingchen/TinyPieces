package com.samaritans.tinypieces.world.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class CaveVinesFeature extends Feature<NoFeatureConfig> {
    public CaveVinesFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random random, BlockPos blockPos, NoFeatureConfig config) {
        BlockPos.Mutable pos = new BlockPos.Mutable(blockPos);
        for (int y = blockPos.getY(); y > 0; --y) {
            pos.setPos(blockPos);
            pos.add(random.nextInt(4) - random.nextInt(4), 0, random.nextInt(4) - random.nextInt(4));
            pos.setY(y);
            if (world.getBlockState(pos).isAir()) {
                for (Direction dir : Direction.values()) {
                    if (dir != Direction.DOWN && dir != Direction.UP && VineBlock.canAttachTo(world, new BlockPos(pos.getX(), pos.getY(), pos.getZ()).offset(dir), dir)) {
                        while(world.getBlockState(pos).isAir() && y > 0) {
                            world.setBlockState(pos, Blocks.VINE.getDefaultState().with(VineBlock.getPropertyFor(dir), true), 2);
                            y--;
                            pos.setY(y);
                        }
                        break;
                    }
                }
            }
        }
        return true;
    }
}
