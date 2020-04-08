package com.samaritans.tinypieces.data;

import com.samaritans.tinypieces.TinyPieces;
import com.samaritans.tinypieces.core.ModItems;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.function.Consumer;

public class SmeltingProvider extends net.minecraft.data.RecipeProvider{
    public SmeltingProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

    }

    private void cookFood(Consumer<IFinishedRecipe> consumer, IItemProvider output, IItemProvider input) {
         CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(input), output, 0.35F, 200, CookingRecipeSerializer.SMELTING)
                .addCriterion("has_item", hasItem(input))
                .build(consumer, new ResourceLocation(TinyPieces.MODID, "smelting/" + input.asItem().getRegistryName().getPath()));
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(input), output, 0.35F, 100, CookingRecipeSerializer.SMOKING)
                .addCriterion("has_item", hasItem(input))
                .build(consumer, new ResourceLocation(TinyPieces.MODID, "smelting/" + input.asItem().getRegistryName().getPath()) + "_from_smoking");
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(input), output, 0.35F, 600, CookingRecipeSerializer.CAMPFIRE_COOKING)
                .addCriterion("has_item", hasItem(input))
                .build(consumer, new ResourceLocation(TinyPieces.MODID, "smelting/" + input.asItem().getRegistryName().getPath())  + "_from_campfire_cooking");
    }

    @Override
    public String getName() {
        return "Tiny Pieces Smelting Recipe Provider";
    }
}
