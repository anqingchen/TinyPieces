package com.samaritans.tinypieces.world.feature;

import com.mojang.datafixers.Dynamic;
import com.samaritans.tinypieces.blocks.ThinBlock;
import com.samaritans.tinypieces.core.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class CaveFloorFeature extends Feature<NoFeatureConfig> {
    private final boolean frozen;

    public CaveFloorFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn, boolean frozen) {
        super(configFactoryIn);
        this.frozen = frozen;
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random random, BlockPos blockPos, NoFeatureConfig config) {
        for (BlockPos pos : BlockPos.getAllInBoxMutable(blockPos.add(-3, -2, -3), blockPos.add(3, 2, 3))) {
            if (world.getBlockState(pos).isAir() && ThinBlock.canPlaceOn(world.getBlockState(pos), world, pos) && random.nextDouble() < 0.65) {
                world.setBlockState(pos, frozen ? ModBlocks.ice_glaze.getDefaultState() : ModBlocks.water_puddle.getDefaultState(), 2);
            }
        }
        return true;
    }
}
