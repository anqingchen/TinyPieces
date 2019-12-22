package com.samaritans.tinypieces.core;

import com.google.common.base.Preconditions;
import com.samaritans.tinypieces.TinyPieces;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;

public class Util {
    /**
     * Performs setup on a registry entry
     *
     * @param name The path of the entry's name. Used to make a name who's domain is our mod's modid
     */
    @Nonnull
    public static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final String name) {
        Preconditions.checkNotNull(name, "Name to assign to entry cannot be null!");
        return setup(entry, new ResourceLocation(TinyPieces.MODID, name));
    }

    /**
     * Performs setup on a registry entry
     *
     * @param registryName The full registry name of the entry
     */
    public static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName) {
        Preconditions.checkNotNull(entry, "Entry cannot be null!");
        Preconditions.checkNotNull(registryName, "Registry name to assign to entry cannot be null!");
        entry.setRegistryName(registryName);
        return entry;
    }
}
