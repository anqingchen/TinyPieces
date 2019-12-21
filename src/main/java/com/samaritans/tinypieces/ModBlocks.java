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
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TinyPieces.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TinyPieces.MODID)
public class ModBlocks {
    public static final Block nether_gold_ore = null;
    public static final Block quartz_ore = null;
    public static final Block nether_rod = null;
    public static final Block nether_rod_red = null;
    public static final Block mossy_stone = null;
    public static final Block ice_glaze = null;
    public static final Block water_puddle = null;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(TinyPieces.MODID, "nether_gold_ore"),
                new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(TinyPieces.MODID, "quartz_ore"),
                new NetherRodBlock().setRegistryName(TinyPieces.MODID, "nether_rod"),
                new NetherRodBlock().setRegistryName(TinyPieces.MODID, "nether_rod_red"),
                new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE)).setRegistryName(TinyPieces.MODID, "mossy_stone"),
                new ThinBlock(Block.Properties.create(Material.ICE).harvestTool(ToolType.SHOVEL).harvestLevel(1).hardnessAndResistance(2).slipperiness(0.98f).noDrops()).setRegistryName(TinyPieces.MODID, "ice_glaze"),
                new ThinBlock(Block.Properties.create(Material.WATER, MaterialColor.WATER).noDrops().hardnessAndResistance(-1.0F, 3600000.0F).tickRandomly()).setRegistryName(TinyPieces.MODID, "water_puddle")
        );
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
