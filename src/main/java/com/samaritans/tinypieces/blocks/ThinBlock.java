package com.samaritans.tinypieces.blocks;

import com.samaritans.tinypieces.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class ThinBlock extends Block {
    private static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 0.1D, 16.0D);

    public ThinBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isSideInvisible(BlockState blockState, BlockState adjacentBlockState, Direction direction) {
        return adjacentBlockState.getBlock() == this;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return blockState.getBlock() == ModBlocks.ICE_GLAZE ? SHAPE : VoxelShapes.empty();
    }

    @Override
    public boolean isReplaceable(BlockState p_196253_1_, BlockItemUseContext p_196253_2_) {
        return true;
    }

    @Override
    public boolean isNormalCube(BlockState p_220081_1_, IBlockReader p_220081_2_, BlockPos p_220081_3_) {
        return false;
    }

    @Override
    public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return false;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
        return VoxelShapes.empty();
    }

    @Override
    public void randomTick(BlockState blockState, World world, BlockPos pos, Random random) {
        super.randomTick(blockState, world, pos, random);
        if (blockState.getBlock() == ModBlocks.WATER_PUDDLE && !world.isRainingAt(pos) && world.canBlockSeeSky(pos)) {
            if (random.nextInt(3) == 0) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        } else if (world.getLightValue(pos) <= 5 && random.nextInt(3) == 0) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }

    @Nullable
    @Override
    public RayTraceResult getRayTraceResult(BlockState state, World world, BlockPos pos, Vec3d start, Vec3d end, RayTraceResult original) {
        return null;
    }

    @Override
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!isValidPosition(state, world, pos)) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
        super.neighborChanged(state, world, pos, blockIn, fromPos, isMoving);
    }

    @Override
    public boolean isValidPosition(BlockState blockState, IWorldReader worldIn, BlockPos pos) {
        return func_220055_a(worldIn, pos.down(), Direction.UP);
    }
}
