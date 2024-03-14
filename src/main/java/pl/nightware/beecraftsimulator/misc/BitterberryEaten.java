package pl.nightware.beecraftsimulator.misc;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import pl.nightware.beecraftsimulator.init.ModEffectsInit;

public class BitterberryEaten {
    public static void execute(LevelAccessor world, Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(ModEffectsInit.RADIATION.get())) {
            if (Math.random() <= 0.055 + (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModEffectsInit.RADIATION.get()) ? _livEnt.getEffect(ModEffectsInit.RADIATION.get()).getAmplifier() : 0) * 0.04) {
            MutationGainFormula.execute(world, entity);
            }
        } else {
            if (Math.random() <= 0.0175) {
                MutationGainFormula.execute(world, entity);
            }
        }
    }
}
