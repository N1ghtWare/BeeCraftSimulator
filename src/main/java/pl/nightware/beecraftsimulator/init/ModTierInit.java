package pl.nightware.beecraftsimulator.init;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTierInit
{
    public static final ForgeTier FIELD_TOOL = new ForgeTier(
            1,
            1,
            2,
            1,
            0,
            ModTagInit.NEEDS_FIELD_TOOL,
            () -> Ingredient.of(ModItemInit.TREAT::get)
    );
}
