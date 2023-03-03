package de.thedon.oresandtools.util;

import de.thedon.oresandtools.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class PropertyRegistration {
    public static void registerProperties() {
        registerShieldProperty(ModItems.OBSIDIAN_SHIELD.get());
        registerBowProperty(ModItems.VALYRIAN_BOW.get());
    }

    private static void registerShieldProperty(Item shieldItem) {
        ItemProperties.register(shieldItem, new ResourceLocation("blocking"),
                (itemStack, level, livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);
    }

    private static void registerBowProperty(Item bowItem) {
        ItemProperties.register(bowItem, new ResourceLocation("pull"), (itemStack, level, livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.getUseItem() != itemStack ? 0.0F : (float) (itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(bowItem, new ResourceLocation("pulling"),
                (itemStack, level, livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);
    }
}
