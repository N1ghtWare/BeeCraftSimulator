package pl.nightware.beecraftsimulator.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pl.nightware.beecraftsimulator.BeeCraftSimulator;

public class ModPotionsInit {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, BeeCraftSimulator.MOD_ID);
    public static final RegistryObject<Potion> LONG_RAD = POTIONS.register("long_rad", () -> new Potion(new MobEffectInstance(ModEffectsInit.RADIATION.get(), 9600, 0, false, true)));
    public static final RegistryObject<Potion> RAD = POTIONS.register("rad", () -> new Potion(new MobEffectInstance(ModEffectsInit.RADIATION.get(), 3600, 0, false, true)));
    public static final RegistryObject<Potion> STRONG_RAD = POTIONS.register("strong_rad", () -> new Potion(new MobEffectInstance(ModEffectsInit.RADIATION.get(), 1800, 1, false, true)));

}
