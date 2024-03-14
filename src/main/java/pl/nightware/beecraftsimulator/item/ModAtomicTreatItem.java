package pl.nightware.beecraftsimulator.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import pl.nightware.beecraftsimulator.misc.MutationGainFormula;
import pl.nightware.beecraftsimulator.misc.NeonberryEaten;

public class ModAtomicTreatItem extends Item {
    public ModAtomicTreatItem() {
        super(new Properties().stacksTo(64).rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.1f).alwaysEat().build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        ItemStack retval = super.finishUsingItem(itemstack, world, entity);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        MutationGainFormula.execute(world, entity);
        return retval;
    }

}
