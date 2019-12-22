package com.samaritans.tinypieces.config;

import com.google.gson.JsonObject;
import com.samaritans.tinypieces.TinyPieces;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

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
            case "nether_rod": return Config.nether_rod;
            case "red_nether_rod": return Config.red_nether_rod;
            case "extra_stairs": return Config.extra_stairs;
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
        public RecipeEnabledCondition read(JsonObject json)
        {
            return new RecipeEnabledCondition(JSONUtils.getString(json, "item"));
        }

        @Override
        public ResourceLocation getID()
        {
            return RecipeEnabledCondition.NAME;
        }
    }
}
