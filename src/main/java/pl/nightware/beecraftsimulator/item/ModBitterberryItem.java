package pl.nightware.beecraftsimulator.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import pl.nightware.beecraftsimulator.misc.BitterberryEaten;
import pl.nightware.beecraftsimulator.misc.MutationGainFormula;

public class ModBitterberryItem extends Item {
    public ModBitterberryItem() {
        super(new Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(3).saturationMod(0.3f).alwaysEat().build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        ItemStack retval = super.finishUsingItem(itemstack, world, entity);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        BitterberryEaten.execute(world, entity);
        return retval;
    }
}
