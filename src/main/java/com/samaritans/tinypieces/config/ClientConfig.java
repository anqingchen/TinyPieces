package com.samaritans.tinypieces.config;

import net.minecraftforge.common.ForgeConfigSpec;

final class ClientConfig {
    final ForgeConfigSpec.BooleanValue SHEEP_STUBBLE;

    ClientConfig(final ForgeConfigSpec.Builder builder) {
        builder.push("client");
        SHEEP_STUBBLE = builder.comment("Make sheep show their color after being sheared")
                .define("Enable Sheep Stubble Color", true);
    }
}
