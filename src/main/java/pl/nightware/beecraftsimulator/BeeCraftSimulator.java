package pl.nightware.beecraftsimulator;

import com.mojang.logging.LogUtils;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import pl.nightware.beecraftsimulator.init.*;
import pl.nightware.beecraftsimulator.sound.ModSounds;
import pl.nightware.beecraftsimulator.util.MiscUtils;

@Mod(BeeCraftSimulator.MOD_ID)
public class BeeCraftSimulator
{
    public static final String MOD_ID = "beecraftsimulator";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BeeCraftSimulator()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItemInit.ITEMS.register(eventBus);
        ModBlockInit.BLOCKS.register(eventBus);
        ModBlockEntitiesInit.BLOCK_ENTITIES.register(eventBus);
        ModEffectsInit.MOB_EFFECTS.register(eventBus);
        CreativeTabInit.TABS.register(eventBus);
        ModMenuInit.MENU_TYPES.register(eventBus);
        ModSounds.SOUND_EVENTS.register(eventBus);
        ModPotionsInit.POTIONS.register(eventBus);
        LOGGER.debug("Bee Craft Simulator Event Bus Finished!");
    }
}
