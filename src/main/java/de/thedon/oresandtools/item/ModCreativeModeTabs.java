package de.thedon.oresandtools.item;

import de.thedon.oresandtools.OresAndToolsMod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = OresAndToolsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTabs {
    public static CreativeModeTab MAIN_TAB;
    public static CreativeModeTab TOOLS_TAB;
    public static CreativeModeTab COMBAT_TAB;

    @SubscribeEvent
    public static void register(CreativeModeTabEvent.Register event) {
        MAIN_TAB = event.registerCreativeModeTab(new ResourceLocation(OresAndToolsMod.MOD_ID, "oat_main_tab"), builder -> builder
                .title(Component.translatable("itemGroup." + OresAndToolsMod.MOD_ID + ".oat_main_tab"))
                .icon(() -> new ItemStack(ModItems.HARDENED_DIAMOND.get())));
        TOOLS_TAB = event.registerCreativeModeTab(new ResourceLocation(OresAndToolsMod.MOD_ID, "oat_tools_tab"), List.of(), List.of(MAIN_TAB),builder -> builder
                .title(Component.translatable("itemGroup." + OresAndToolsMod.MOD_ID + ".oat_tools_tab"))
                .icon(() -> new ItemStack(ModItems.HARDENED_DIAMOND_AXE.get())));
        COMBAT_TAB = event.registerCreativeModeTab(new ResourceLocation(OresAndToolsMod.MOD_ID, "oat_combat_tab"), List.of(), List.of(TOOLS_TAB), builder -> builder
                .title(Component.translatable("itemGroup." + OresAndToolsMod.MOD_ID + ".oat_combat_tab"))
                .icon(() -> new ItemStack(ModItems.HARDENED_DIAMOND_SWORD.get())));
    }
}
