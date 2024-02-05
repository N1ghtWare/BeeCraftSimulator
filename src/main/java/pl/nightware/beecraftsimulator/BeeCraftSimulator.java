package pl.nightware.beecraftsimulator;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import pl.nightware.beecraftsimulator.init.*;

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
        CreativeTabInit.TABS.register(eventBus);
        ModMenuInit.MENU_TYPES.register(eventBus);
        LOGGER.debug("Bee Craft Simulator Event Bus Finished!");
    }
}
