package de.thedon.oresandtools.item;

import de.thedon.oresandtools.config.CommonConfig;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;

public class ImprovisedReactorItem extends Item {
    public ImprovisedReactorItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
        return CommonConfig.IMPRO_REACTOR_BURN_TIME.get();
    }
}
