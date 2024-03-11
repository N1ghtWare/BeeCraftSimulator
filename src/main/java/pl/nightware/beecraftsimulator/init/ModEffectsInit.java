package pl.nightware.beecraftsimulator.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pl.nightware.beecraftsimulator.BeeCraftSimulator;
import pl.nightware.beecraftsimulator.effect.RadiationEffect;

public class ModEffectsInit {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, BeeCraftSimulator.MOD_ID);

    public static final RegistryObject<MobEffect> RADIATION = MOB_EFFECTS.register( "radiation",
            () -> new RadiationEffect(MobEffectCategory.NEUTRAL, 65280));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
