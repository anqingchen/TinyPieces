package com.samaritans.tinypieces.config;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.samaritans.tinypieces.TinyPieces;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.common.util.JsonUtils;

public class RecipeEnabledCondition implements ICondition {
    private static final ResourceLocation NAME = new ResourceLocation(TinyPieces.MODID, "recipe_enabled");
    private final String item;

    public RecipeEnabledCondition(String item) {
        this.item = item;
    }

    @Override
    public ResourceLocation getID() {
        return NAME;
    }

    @Override
    public boolean test() {
        switch (this.item) {
            case "cooked_egg": return Config.cooked_egg;
            case "mossy_stone": return Config.mossy_stone;
            case "nether_rod": return Config.nether_rods;
            case "charred_suite": return Config.charred_suite;
            case "red_nether_brick_fence": return Config.red_nether_brick_fence;
            case "wither_bone": return Config.wither_bone;
        }
        return true;
    }

    public static class Serializer implements IConditionSerializer<RecipeEnabledCondition>
    {
        public static final RecipeEnabledCondition.Serializer INSTANCE = new RecipeEnabledCondition.Serializer();

        @Override
        public void write(JsonObject json, RecipeEnabledCondition value)
        {
            json.addProperty("item", value.item);
        }

        @Override
        public RecipeEnabledCondition read(JsonObject json) {
            return new RecipeEnabledCondition(JSONUtils.getString(json, "item"));
        }

        @Override
        public ResourceLocation getID()
        {
            return RecipeEnabledCondition.NAME;
        }
    }
}
