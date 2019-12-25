package com.samaritans.tinypieces.world.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DeadCoralPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CaveDeadCoralFeature extends Feature<NoFeatureConfig> {
    public CaveDeadCoralFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        List<Block> deadCorals = ForgeRegistries.BLOCKS.getValues().parallelStream().filter(p -> p instanceof DeadCoralPlantBlock).collect(Collectors.toList());
        if (deadCorals.size() > 0 && worldIn.getBlockState(pos.up()).isAir()) {
            for(int i = 0; i < 32; ++i) {
                BlockPos blockpos1 = pos;
                int j = 0;
                while(true) {
                    if (j >= i / 16) {
                        BlockState blockstate1 = deadCorals.get(rand.nextInt(deadCorals.size())).getDefaultState().with(DeadCoralPlantBlock.WATERLOGGED, false);
                        if (blockstate1.isValidPosition(worldIn, blockpos1)) {
                            worldIn.setBlockState(blockpos1, blockstate1, 3);
                        }
                        break;
                    }
                    blockpos1 = blockpos1.add(rand.nextInt(4) - 1, (rand.nextInt(4) - 1) * rand.nextInt(4) / 2, rand.nextInt(4) - 1);
                    if (worldIn.getBlockState(blockpos1).func_224756_o(worldIn, blockpos1)) {
                        break;
                    }
                    ++j;
                }
            }
        }
        return false;
    }
}
