package pl.nightware.beecraftsimulator;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import pl.nightware.beecraftsimulator.init.ModBlockInit;
import pl.nightware.beecraftsimulator.init.ModItemInit;

@Mod(BeeCraftSimulator.MOD_ID)
public class BeeCraftSimulator
{
    public static final String MOD_ID = "beecraftsimulator";

    public BeeCraftSimulator()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItemInit.ITEMS.register(eventBus);
        ModBlockInit.BLOCKS.register(eventBus);
    }
}
