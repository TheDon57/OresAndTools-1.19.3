package de.thedon.oresandtools.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    /* Item variables */
    public static final ForgeConfigSpec.ConfigValue<Integer> IMPRO_REACTOR_BURN_TIME;

    /* Event variables */
    public static final ForgeConfigSpec.ConfigValue<Integer> URANIUM_DAMAGE_TICKRATE;
    public static final ForgeConfigSpec.ConfigValue<Integer> URANIUM_DAMAGE_AMOUNT;
    public static final ForgeConfigSpec.ConfigValue<Boolean> DISABLE_SET_BONUSES;
    public static final ForgeConfigSpec.ConfigValue<Integer> HOT_DIA_FIRE_REFLECT_DURATION;
    public static final ForgeConfigSpec.ConfigValue<Boolean> DISABLE_HOT_DIA_FIRE_ASPECT;
    public static final ForgeConfigSpec.ConfigValue<Integer> HOT_DIA_FIRE_ASPECT_DURATION;
    public static final ForgeConfigSpec.ConfigValue<Double> VILLAGER_EMERALD_DROP_CHANCE;

    static {
        BUILDER.push("Common Configuration of OresAndToolsMod");

        /* Item variables */
        IMPRO_REACTOR_BURN_TIME = BUILDER.comment("Burn time of the improvised reactor in the furnace").defineInRange("impro_reactor_burn_time", 204800, 1200, Integer.MAX_VALUE);

        /* Event variables */
        URANIUM_DAMAGE_TICKRATE = BUILDER.comment("After how many ticks the player will get damage from the uranium ingot").defineInRange("uranium_damage_tickrate", 100, 50, 1200);
        URANIUM_DAMAGE_AMOUNT = BUILDER.comment("How much damage the player will get damage from the uranium ingot in the specified tickrate").defineInRange("uranium_damage_amount", 3, 1, 10);
        DISABLE_SET_BONUSES = BUILDER.comment("Disables all armor set bonuses").define("disable_set_bonuses", false);
        HOT_DIA_FIRE_REFLECT_DURATION = BUILDER.comment("How long enemies will burn if the hit you when you wear full hot diamond armor").defineInRange("hot_dia_fire_reflect_duration", 1, 1, Integer.MAX_VALUE);
        DISABLE_HOT_DIA_FIRE_ASPECT = BUILDER.comment("Disables the fire aspect effect for hot dia tool or sword").define("disable_hot_dia_fire_aspect", false);
        HOT_DIA_FIRE_ASPECT_DURATION = BUILDER.comment("How long enemies will burn if you them with hot dia tool or sword").defineInRange("hot_dia_fire_aspect_duration", 5, 1, Integer.MAX_VALUE);
        VILLAGER_EMERALD_DROP_CHANCE = BUILDER.comment("Probability that a villager will drop a emerald if killy be emerald sword").defineInRange("villager_emerald_drop_chance", 0.15, 0.01, 1.0);

        SPEC = BUILDER.build();
    }
}
