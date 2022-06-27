package me.satsur.oretools.item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class ModTiers {
    public static final ForgeTier COAL = new ForgeTier(1, 150, 3.0F, 1.0F, 10,
            Tags.Blocks.NEEDS_WOOD_TOOL,
            () -> Ingredient.of(Items.COAL));

}
