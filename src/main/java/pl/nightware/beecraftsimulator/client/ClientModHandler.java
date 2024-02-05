package pl.nightware.beecraftsimulator.client;


import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import pl.nightware.beecraftsimulator.BeeCraftSimulator;
import pl.nightware.beecraftsimulator.blockentity.PlasticPlanterBlockEntityRenderer;
import pl.nightware.beecraftsimulator.init.ModBlockEntitiesInit;

@Mod.EventBusSubscriber(modid = BeeCraftSimulator.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModHandler
{
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() -> {
            BeeCraftSimulator.LOGGER.warn("Entering Client Setup...");
        });
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerBlockEntityRenderer(ModBlockEntitiesInit.PLASTIC_PLANTER_ENTITY.get(), PlasticPlanterBlockEntityRenderer::new);
    }
}
