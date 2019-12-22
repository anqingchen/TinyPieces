package com.samaritans.tinypieces;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.stream.Collectors;

public class ModEventHandlers {
    private static final Biome.SpawnListEntry shulker = new Biome.SpawnListEntry(EntityType.SHULKER, 2, 1, 1);
    private static final Biome.SpawnListEntry rabbit = new Biome.SpawnListEntry(EntityType.RABBIT, 6, 2, 4);

    @SubscribeEvent
    public static void useWaterBottleEvent(PlayerInteractEvent.RightClickBlock event) {
        boolean iceEnabled = Config.Blocks.ICE_GLAZE.get();
        boolean waterEnabled = Config.Blocks.WATER_PUDDLE.get();
        if ((iceEnabled || waterEnabled) && !event.getWorld().isRemote && event.getPlayer().isSneaking() && PotionUtils.getPotionFromItem(event.getItemStack()) == Potions.WATER &&
                event.getPlayer().canPlayerEdit(event.getPos(), event.getFace(), event.getItemStack())) {
            BlockPos target = event.getWorld().getBlockState(event.getPos()).getMaterial().isReplaceable() ? event.getPos() : event.getPos().offset(event.getFace());
            BlockState setTo = waterEnabled ? ModBlocks.WATER_PUDDLE.getDefaultState() : ModBlocks.ICE_GLAZE.getDefaultState();
            if (iceEnabled && BiomeDictionary.hasType(event.getWorld().getBiome(target), BiomeDictionary.Type.COLD)) setTo = ModBlocks.ICE_GLAZE.getDefaultState();
            event.getWorld().setBlockState(target, setTo);
            if (!event.getPlayer().isCreative()) event.getItemStack().shrink(1);
        }
    }

    @SubscribeEvent
    public static void onBoneMealStone(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getWorld().isRemote && event.getItemStack().getItem() == Items.BONE_MEAL &&
                event.getPlayer().canPlayerEdit(event.getPos(), event.getFace(), event.getItemStack())) {
            Block target = event.getWorld().getBlockState(event.getPos()).getBlock();
            boolean flag = true;
            if (target == Blocks.STONE && Config.Blocks.MOSSY_STONE.get())
                event.getWorld().setBlockState(event.getPos(), ModBlocks.MOSSY_STONE.getDefaultState());
            else if (target == Blocks.COBBLESTONE)
                event.getWorld().setBlockState(event.getPos(), Blocks.MOSSY_COBBLESTONE.getDefaultState());
            else if (target == Blocks.STONE_BRICKS)
                event.getWorld().setBlockState(event.getPos(), Blocks.MOSSY_STONE_BRICKS.getDefaultState());
            else flag = false;
            if (!event.getPlayer().isCreative() && flag) event.getItemStack().shrink(1);
        }
    }

    @SubscribeEvent
    public static void onBoneMealFlower(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getWorld().isRemote && event.getItemStack().getItem() == Items.BONE_MEAL &&
                event.getPlayer().canPlayerEdit(event.getPos(), event.getFace(), event.getItemStack())) {
            Block target = event.getWorld().getBlockState(event.getPos()).getBlock();
            if (target instanceof FlowerBlock) {
                // todo: random flower apperance somehow
                if (!event.getPlayer().isCreative()) event.getItemStack().shrink(1);
            }
        }
    }

    @SubscribeEvent
    public static void chickenShedFeathers(LivingEvent.LivingUpdateEvent event) {
        if (Config.Tweaks.CHICKEN_FEATHER.get() && !event.getEntityLiving().world.isRemote && event.getEntityLiving() instanceof ChickenEntity) {
            LivingEntity chicken = event.getEntityLiving();
            if (chicken.world.isAreaLoaded(chicken.getPosition(), 1) && !chicken.isChild() && chicken.getRNG().nextInt(600) == 0) {
                InventoryHelper.spawnItemStack(chicken.world, chicken.posX, chicken.posY, chicken.posZ, new ItemStack(Items.FEATHER));
            }
        }
    }

    @SubscribeEvent
    public static void shulkerSpawn(WorldEvent.PotentialSpawns event) {
        if (Config.Tweaks.SHULKER_SPAWN.get() && event.getWorld().getDimension().getType() == DimensionType.THE_END && Feature.END_CITY.isPositionInsideStructure(event.getWorld(), event.getPos())) {
            event.getList().add(shulker);
        }
    }

    @SubscribeEvent
    public static void rabbitSpawn(WorldEvent.PotentialSpawns event) {
        if (Config.Tweaks.RABBIT_SPAWN.get() && BiomeDictionary.hasType(event.getWorld().getBiome(event.getPos()), BiomeDictionary.Type.FOREST)) {
            event.getList().add(rabbit);
        }
    }
}
