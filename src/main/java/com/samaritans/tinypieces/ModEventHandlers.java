package com.samaritans.tinypieces;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModEventHandlers {
    private static final Biome.SpawnListEntry shulker = new Biome.SpawnListEntry(EntityType.SHULKER, 6, 1, 1);
    private static final Biome.SpawnListEntry rabbit = new Biome.SpawnListEntry(EntityType.RABBIT, 6, 2, 4);

    @SubscribeEvent
    public static void useWaterBottleEvent(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getWorld().isRemote && event.getPlayer().isSneaking() && event.getItemStack().getItem() == Items.POTION &&
                event.getPlayer().canPlayerEdit(event.getPos(), event.getFace(), event.getItemStack())) {
            BlockPos target = event.getWorld().getBlockState(event.getPos()).getMaterial().isReplaceable() ? event.getPos() : event.getPos().offset(event.getFace());
            event.getWorld().setBlockState(target, BiomeDictionary.hasType(event.getWorld().getBiome(target), BiomeDictionary.Type.COLD) ? ModBlocks.ice_glaze.getDefaultState() : ModBlocks.water_puddle.getDefaultState());
            event.getItemStack().shrink(1);
        }
    }

    @SubscribeEvent
    public static void useBoneMealEvent(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getWorld().isRemote && event.getItemStack().getItem() == Items.BONE_MEAL &&
                event.getPlayer().canPlayerEdit(event.getPos(), event.getFace(), event.getItemStack())) {
            Block target = event.getWorld().getBlockState(event.getPos()).getBlock();
            if (target == Blocks.STONE)
                event.getWorld().setBlockState(event.getPos(), ModBlocks.mossy_stone.getDefaultState());
            else if (target == Blocks.COBBLESTONE)
                event.getWorld().setBlockState(event.getPos(), Blocks.MOSSY_COBBLESTONE.getDefaultState());
            else if (target == Blocks.STONE_BRICKS)
                event.getWorld().setBlockState(event.getPos(), Blocks.MOSSY_STONE_BRICKS.getDefaultState());
        }
    }

    @SubscribeEvent
    public static void chickenShedFeathers(LivingEvent.LivingUpdateEvent event) {
        if (Config.CHICKEN_FEATHER.get() && !event.getEntityLiving().world.isRemote && event.getEntityLiving() instanceof ChickenEntity) {
            LivingEntity chicken = event.getEntityLiving();
            if (chicken.world.isAreaLoaded(chicken.getPosition(), 1) && !chicken.isChild() && chicken.getRNG().nextInt(600) == 0) {
                InventoryHelper.spawnItemStack(chicken.world, chicken.posX, chicken.posY, chicken.posZ, new ItemStack(Items.FEATHER));
            }
        }
    }

    @SubscribeEvent
    public static void shulkerSpawn(WorldEvent.PotentialSpawns event) {
        if (Config.SHULKER_SPAWN.get() && event.getWorld().getDimension().getType() == DimensionType.THE_END && Feature.END_CITY.isPositionInsideStructure(event.getWorld(), event.getPos())) {
            event.getList().add(shulker);
        }
    }

    @SubscribeEvent
    public static void rabbitSpawn(WorldEvent.PotentialSpawns event) {
        if (Config.RABBIT_SPAWN.get() && BiomeDictionary.hasType(event.getWorld().getBiome(event.getPos()), BiomeDictionary.Type.FOREST)) {
            event.getList().add(rabbit);
        }
    }
}
