package pl.nightware.beecraftsimulator.misc;

import pl.nightware.beecraftsimulator.init.ModEffectsInit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

public class NeonberryEaten {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof LivingEntity _entity)
            _entity.removeEffect(ModEffectsInit.RADIATION.get());
        if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
            _entity.addEffect(new MobEffectInstance(ModEffectsInit.RADIATION.get(), Mth.nextInt(RandomSource.create(), 2400, 6000), 0));
    }
}

