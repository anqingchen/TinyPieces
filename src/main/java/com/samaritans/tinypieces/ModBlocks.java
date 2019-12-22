package com.samaritans.tinypieces;

import com.samaritans.tinypieces.blocks.ThinBlock;
import com.samaritans.tinypieces.blocks.NetherRodBlock;
import net.minecraft.block.Block;
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
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TinyPieces.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TinyPieces.MODID)
public class ModBlocks {
    public static final Block NETHER_GOLD_ORE;
    public static final Block QUARTZ_ORE;
    public static final Block NETHER_ROD;
    public static final Block RED_NETHER_ROD;
    public static final Block MOSSY_STONE;
    public static final Block ICE_GLAZE;
    public static final Block WATER_PUDDLE;

    static {
        NETHER_GOLD_ORE = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5).harvestLevel(2).harvestTool(ToolType.PICKAXE));
        QUARTZ_ORE = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5).harvestLevel(2).harvestTool(ToolType.PICKAXE));
        NETHER_ROD = new NetherRodBlock();
        RED_NETHER_ROD = new NetherRodBlock();
        MOSSY_STONE = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
        ICE_GLAZE = new ThinBlock(Block.Properties.create(Material.ICE).harvestTool(ToolType.SHOVEL).harvestLevel(1).hardnessAndResistance(2).slipperiness(0.98f).noDrops());
        WATER_PUDDLE = new ThinBlock(Block.Properties.create(Material.WATER, MaterialColor.WATER).noDrops().hardnessAndResistance(-1.0F, 3600000.0F).tickRandomly());
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> r = event.getRegistry();
        if (Config.Blocks.NETHER_GOLD_ORE.get()) r.register(Util.setup(NETHER_GOLD_ORE, "nether_gold_ore"));
        if (Config.Blocks.QUARTZ_ORE.get()) r.register(Util.setup(QUARTZ_ORE, "quartz_ore"));
        if (Config.Blocks.NETHER_ROD.get()) r.register(Util.setup(NETHER_ROD, "nether_rod"));
        if (Config.Blocks.RED_NETHER_ROD.get()) r.register(Util.setup(RED_NETHER_ROD, "red_nether_rod"));
        if (Config.Blocks.MOSSY_STONE.get()) r.register(Util.setup(MOSSY_STONE, "mossy_stone"));
        if (Config.Blocks.ICE_GLAZE.get()) r.register(Util.setup(ICE_GLAZE, "ice_glaze"));
        if (Config.Blocks.WATER_PUDDLE.get()) r.register(Util.setup(WATER_PUDDLE, "water_puddle"));
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();
        ForgeRegistries.BLOCKS.getValues().parallelStream().filter(block -> block.getRegistryName().getNamespace().equals(TinyPieces.MODID)).forEach(block -> {
                final Item.Properties properties = new Item.Properties();   //.group(ModItemGroups.MOD_ITEM_GROUP);
                final BlockItem blockItem = new BlockItem(block, properties);
                registry.register(blockItem.setRegistryName(block.getRegistryName()));
        });
    }
}
