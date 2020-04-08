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
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import static com.samaritans.tinypieces.TinyPieces.MODID;

@Mod.EventBusSubscriber(modid = TinyPieces.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> nether_gold_ore = register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "nether_gold_ore");
    public static final RegistryObject<Block> quartz_ore = register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "quartz_ore");
    public static final RegistryObject<Block> nether_rod = register(new NetherRodBlock(), "nether_rod");
    public static final RegistryObject<Block> red_nether_rod = register(new NetherRodBlock(), "red_nether_rod");
    public static final RegistryObject<Block> charred_nether_rod = register(new NetherRodBlock(), "charred_nether_rod");
    public static final RegistryObject<Block> mossy_stone = register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5).harvestLevel(0).harvestTool(ToolType.PICKAXE)), "mossy_stone");
    public static final RegistryObject<Block> ice_glaze = register(new ThinBlock(Block.Properties.create(Material.ICE).harvestTool(ToolType.PICKAXE).harvestLevel(1).hardnessAndResistance(2).slipperiness(0.98f).noDrops().tickRandomly().nonOpaque()), "ice_glaze");
    public static final RegistryObject<Block> water_puddle = register(new ThinBlock(Block.Properties.create(Material.WATER, MaterialColor.WATER).noDrops().hardnessAndResistance(-1.0F, 3600000.0F).tickRandomly().nonOpaque()), "water_puddle");
    public static final RegistryObject<Block> charred_nether_bricks = register(new Block(Block.Properties.from(Blocks.NETHER_BRICKS)), "charred_nether_bricks");
    public static final RegistryObject<Block> charred_nether_brick_wall = register(new WallBlock(Block.Properties.from(Blocks.NETHER_BRICK_WALL)), "charred_nether_brick_wall");
    public static final RegistryObject<Block> charred_nether_brick_slab = register(new SlabBlock(Block.Properties.from(Blocks.NETHER_BRICK_SLAB)), "charred_nether_brick_slab");
    public static final RegistryObject<Block> charred_nether_brick_stairs = register(new StairsBlock(() -> ModBlocks.charred_nether_bricks.get().getDefaultState(), Block.Properties.from(Blocks.NETHER_BRICK_STAIRS)), "charred_nether_brick_stairs");
    public static final RegistryObject<Block> charred_nether_brick_fence = register(new FenceBlock(Block.Properties.from(Blocks.NETHER_BRICK_FENCE)), "charred_nether_brick_fence");
    public static final RegistryObject<Block> red_nether_brick_fence = register(new FenceBlock(Block.Properties.from(Blocks.NETHER_BRICK_FENCE)), "red_nether_brick_fence");


    public static RegistryObject<Block> register(Block block, String name){
        ModItems.ITEMS.register(name, () -> new BlockItem(block, new Item.Properties().group(TinyPieces.TAB)));
        return BLOCKS.register(name, () -> block);
    }
}
