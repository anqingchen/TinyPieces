package com.samaritans.tinypieces.data;

import com.google.gson.JsonObject;
import com.samaritans.tinypieces.TinyPieces;
import com.samaritans.tinypieces.core.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.SingleItemRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class StonecuttingProvider extends RecipeProvider {
    public StonecuttingProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        consumer.accept(stonecutting(ModBlocks.charred_nether_bricks.get(), ModBlocks.charred_nether_brick_slab.get(), 2));
        consumer.accept(stonecutting(ModBlocks.charred_nether_bricks.get(), ModBlocks.charred_nether_brick_stairs.get()));
        consumer.accept(stonecutting(ModBlocks.charred_nether_bricks.get(), ModBlocks.charred_nether_brick_wall.get()));
    }

    @Nonnull
    @Override
    public String getName() {
        return "Tiny Pieces Stone Cutting Recipe Provider";
    }

    private static ResourceLocation idFor(IItemProvider a, IItemProvider b) {
        return new ResourceLocation(TinyPieces.MODID,"stonecutting/" + a.asItem().getRegistryName().getPath() + "_to_" + b.asItem().getRegistryName().getPath());
    }

    private static IFinishedRecipe stonecutting(IItemProvider input, IItemProvider output) {
        return stonecutting(input, output, 1);
    }

    private static IFinishedRecipe stonecutting(IItemProvider input, IItemProvider output, int count) {
        return new Result(idFor(input, output), IRecipeSerializer.STONECUTTING, Ingredient.fromItems(input), output.asItem(), count);
    }

    public static class Result extends SingleItemRecipeBuilder.Result {
        public Result(ResourceLocation id, IRecipeSerializer<?> serializer, Ingredient input, Item result, int count) {
            super(id, serializer, "", input, result, count, null, null);
        }

        @Nullable
        @Override
        public JsonObject getAdvancementJson() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementID() {
            return null;
        }
    }
}
