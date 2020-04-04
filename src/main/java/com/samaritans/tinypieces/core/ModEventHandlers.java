package com.samaritans.tinypieces.core;

import com.samaritans.tinypieces.config.Config;
import net.minecraft.block.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

@SuppressWarnings("unused")
public class ModEventHandlers {
    private static final Biome.SpawnListEntry shulker = new Biome.SpawnListEntry(EntityType.SHULKER, Config.shulker_weight, Config.shulker_group_min, Config.shulker_group_max);
    private static final Biome.SpawnListEntry caveSpider = new Biome.SpawnListEntry(EntityType.CAVE_SPIDER, 40, 1, 3);

    @SubscribeEvent
    public static void useWaterBottleEvent(PlayerInteractEvent.RightClickBlock event) {
        boolean iceEnabled = Config.ice_glaze;
        boolean waterEnabled = Config.water_puddle;
        if (!event.getWorld().isRemote && (iceEnabled || waterEnabled) && event.getPlayer().isSneaking() && PotionUtils.getPotionFromItem(event.getItemStack()) == Potions.WATER &&
                event.getPlayer().canPlayerEdit(event.getPos(), event.getFace(), event.getItemStack())) {
            BlockPos target = event.getWorld().getBlockState(event.getPos()).getMaterial().isReplaceable() ? event.getPos() : event.getPos().offset(event.getFace());
            BlockState setTo = waterEnabled ? ModBlocks.water_puddle.getDefaultState() : ModBlocks.ice_glaze.getDefaultState();
            if (iceEnabled && BiomeDictionary.hasType(event.getWorld().getBiome(target), BiomeDictionary.Type.COLD)) setTo = ModBlocks.ice_glaze.getDefaultState();
            event.getWorld().setBlockState(target, setTo);
            if (!event.getPlayer().isCreative()) event.getItemStack().shrink(1);
        }
    }

    @SubscribeEvent
    public static void onBoneMealStone(PlayerInteractEvent.RightClickBlock event) {
        if (Config.bonemeal_stone && event.getItemStack().getItem() == Items.BONE_MEAL &&
                event.getPlayer().canPlayerEdit(event.getPos(), event.getFace(), event.getItemStack())) {
            Block target = event.getWorld().getBlockState(event.getPos()).getBlock();
            boolean flag = true;
            if (target == Blocks.STONE && Config.mossy_stone)
                event.getWorld().setBlockState(event.getPos(), ModBlocks.mossy_stone.getDefaultState());
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
        if (Config.bonemeal_flower && event.getItemStack().getItem() == Items.BONE_MEAL &&
                event.getPlayer().canPlayerEdit(event.getPos(), event.getFace(), event.getItemStack())) {
            Block target = event.getWorld().getBlockState(event.getPos()).getBlock();
            if (target instanceof FlowerBlock) {
                BlockPos blockpos = event.getPos();
                World worldIn = event.getWorld();
                Random rand = event.getPlayer().getRNG();
                BlockState blockstate = worldIn.getBlockState(blockpos.down());

                for(int i = 0; i < 32; ++i) {
                    BlockPos blockpos1 = blockpos;
                    int j = 0;
                    while(true) {
                        if (j >= i / 16) {
                            BlockState blockstate1;
                            blockstate1 = worldIn.getBlockState(blockpos);
                            if (blockstate1.isValidPosition(worldIn, blockpos1)) {
                                worldIn.setBlockState(blockpos1, blockstate1, 3);
                            }
                            break;
                        }
                        blockpos1 = blockpos1.add(rand.nextInt(4) - 1, (rand.nextInt(4) - 1) * rand.nextInt(4) / 2, rand.nextInt(4) - 1);
                        if (worldIn.getBlockState(blockpos1.down()) != blockstate || worldIn.getBlockState(blockpos1).func_224756_o(worldIn, blockpos1)) {
                            break;
                        }
                        ++j;
                    }
                }
                if (!event.getPlayer().isCreative()) event.getItemStack().shrink(1);
                worldIn.playEvent(2005, blockpos, 0);
                event.getPlayer().swingArm(event.getHand());
            }
        }
    }

    @SubscribeEvent
    public static void onBreedPigsorRabbits(BabyEntitySpawnEvent event) {
        if (!event.getParentA().world.isRemote && Config.pig_rabbit_litter && (event.getParentA() instanceof PigEntity || event.getParentA() instanceof RabbitEntity)) {
            int amount = event.getParentA().getRNG().nextInt(4);
            for (int i = 0; i < amount; ++i) {
                World world = event.getParentA().world;
                AnimalEntity parentA = (AnimalEntity) event.getParentA();
                AnimalEntity parentB = (AnimalEntity) event.getParentB();
                AgeableEntity ageableEntity = parentA.createChild(parentB);
                ageableEntity.setGrowingAge(-24000);
                ageableEntity.setLocationAndAngles(parentA.posX, parentA.posY, parentA.posZ, 0.0F, 0.0F);
                world.addEntity(ageableEntity);
            }
        }
    }

    @SubscribeEvent
    public static void chickenShedFeathers(LivingEvent.LivingUpdateEvent event) {
        if (!event.getEntityLiving().world.isRemote && Config.chicken_feather && event.getEntityLiving() instanceof ChickenEntity) {
            LivingEntity chicken = event.getEntityLiving();
            if (chicken.world.isAreaLoaded(chicken.getPosition(), 1) && !chicken.isChild() && chicken.getRNG().nextInt(600) == 0) {
                InventoryHelper.spawnItemStack(chicken.world, chicken.posX, chicken.posY, chicken.posZ, new ItemStack(Items.FEATHER));
            }
        }
    }

    @SubscribeEvent
    public static void shulkerSpawn(WorldEvent.PotentialSpawns event) {
        if (!event.getWorld().isRemote() && Config.shulker_spawn && event.getType() == EntityClassification.MONSTER && event.getWorld().getDimension().getType() == DimensionType.THE_END && Feature.END_CITY.isPositionInsideStructure(event.getWorld(), event.getPos())) {
            event.getList().add(shulker);
        }
    }

    @SubscribeEvent
    public static void caveSpiderSpawn(WorldEvent.PotentialSpawns event) {
        if (!event.getWorld().isRemote() && Config.cave_spider_spawn && event.getType() == EntityClassification.MONSTER &&
                (BiomeDictionary.hasType(event.getWorld().getBiome(event.getPos()), BiomeDictionary.Type.FOREST) || BiomeDictionary.hasType(event.getWorld().getBiome(event.getPos()), BiomeDictionary.Type.JUNGLE)) &&
                event.getPos().getY() < 45) {
            event.getList().add(caveSpider);
        }
    }

    @SubscribeEvent
    public static void getCake(BlockEvent.BreakEvent event) {
        if (!event.getWorld().isRemote() && Config.pickup_cake && event.getState().getBlock() == Blocks.CAKE && event.getState().get(CakeBlock.BITES) == 0 &&
                (EnchantmentHelper.getEnchantments(event.getPlayer().getHeldItem(Hand.MAIN_HAND)).containsKey(Enchantments.SILK_TOUCH) || event.getPlayer().getHeldItem(Hand.MAIN_HAND).getItem() instanceof ShearsItem)) {
            BlockPos pos = event.getPos();
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            InventoryHelper.spawnItemStack(event.getWorld().getWorld(), x, y, z, new ItemStack(event.getState().getBlock()));
        }
    }

    @SubscribeEvent
    public static void deathByWither(LivingDeathEvent event) {
        World world = event.getEntityLiving().world;
        if (!world.isRemote && event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            Entity witherSkeleton = EntityType.WITHER_SKELETON.create(world);
            witherSkeleton.setPosition(player.posX, player.posY, player.posZ);
            witherSkeleton.setCustomName(player.getDisplayName());
            world.addEntity(witherSkeleton);
        }
    }
}
