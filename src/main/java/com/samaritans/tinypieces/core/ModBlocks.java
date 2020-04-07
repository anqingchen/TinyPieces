package com.samaritans.tinypieces.core;

import com.samaritans.tinypieces.TinyPieces;
import com.samaritans.tinypieces.block.NetherRodBlock;
import com.samaritans.tinypieces.block.ThinBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TinyPieces.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TinyPieces.MODID)
public class ModBlocks {
    //todo: use deferred register
    public static final Block nether_gold_ore = null;
    public static final Block quartz_ore = null;
    public static final Block nether_rod = null;
    public static final Block red_nether_rod = null;
    public static final Block charred_nether_rod = null;
    public static final Block mossy_stone = null;
    public static final Block ice_glaze = null;
    public static final Block water_puddle = null;
    public static final Block charred_nether_bricks = null;
    public static final Block charred_nether_brick_wall = null;
    public static final Block charred_nether_brick_slab = null;
    public static final Block charred_nether_brick_stairs = null;
    public static final Block charred_nether_brick_fence = null;
    public static final Block red_nether_brick_fence = null;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                Util.setup(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "nether_gold_ore"),
                Util.setup(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "quartz_ore"),
                Util.setup(new Block(Block.Properties.from(Blocks.NETHER_BRICKS)), "charred_nether_bricks"),
                Util.setup(new WallBlock(Block.Properties.from(Blocks.NETHER_BRICK_WALL)), "charred_nether_brick_wall"),
                Util.setup(new SlabBlock(Block.Properties.from(Blocks.NETHER_BRICK_SLAB)), "charred_nether_brick_slab"),
                Util.setup(new StairsBlock(() -> ModBlocks.charred_nether_bricks.getDefaultState(), Block.Properties.from(Blocks.NETHER_BRICK_STAIRS)), "charred_nether_brick_stairs"),
                Util.setup(new FenceBlock(Block.Properties.from(Blocks.NETHER_BRICK_FENCE)), "charred_nether_brick_fence"),
                Util.setup(new FenceBlock(Block.Properties.from(Blocks.NETHER_BRICK_FENCE)), "red_nether_brick_fence"),
                Util.setup(new NetherRodBlock(), "charred_nether_rod"),
                Util.setup(new NetherRodBlock(), "nether_rod"),
                Util.setup(new NetherRodBlock(), "red_nether_rod"),
                Util.setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "mossy_stone"),
                Util.setup(new ThinBlock(Block.Properties.create(Material.ICE).harvestTool(ToolType.PICKAXE).harvestLevel(1).hardnessAndResistance(2).slipperiness(0.98f).noDrops().tickRandomly()), "ice_glaze"),
                Util.setup(new ThinBlock(Block.Properties.create(Material.WATER, MaterialColor.WATER).noDrops().hardnessAndResistance(-1.0F, 3600000.0F).tickRandomly()), "water_puddle")
        );
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();
        ForgeRegistries.BLOCKS.getValues().parallelStream().filter(block -> block.getRegistryName().getNamespace().equals(TinyPieces.MODID)).forEach(block -> {
                final Item.Properties properties = new Item.Properties().group(TinyPieces.TAB);
                final BlockItem blockItem = new BlockItem(block, properties);
                registry.register(blockItem.setRegistryName(block.getRegistryName()));
        });
    }
}
