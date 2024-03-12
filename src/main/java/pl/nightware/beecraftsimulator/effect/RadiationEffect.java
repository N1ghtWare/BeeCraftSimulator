package pl.nightware.beecraftsimulator.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import pl.nightware.beecraftsimulator.misc.RadiationSoundActivation;


public class RadiationEffect extends MobEffect {

    public RadiationEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        RadiationSoundActivation.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ());
    }

    @Override
            public boolean isDurationEffectTick (int pDuration, int pAmplifier){
                return true;
    }
}
