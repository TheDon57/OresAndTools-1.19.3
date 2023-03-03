package de.thedon.oresandtools.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Client Configuration of OresAndToolsMod");

        SPEC = BUILDER.build();
    }
}
