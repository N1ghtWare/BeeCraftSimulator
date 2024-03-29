package pl.nightware.beecraftsimulator.client;


import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import pl.nightware.beecraftsimulator.BeeCraftSimulator;
import pl.nightware.beecraftsimulator.blockentity.*;
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
        event.registerBlockEntityRenderer(ModBlockEntitiesInit.PAPER_PLANTER_ENTITY.get(), PaperPlanterBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntitiesInit.TICKET_PLANTER_ENTITY.get(), TicketPlanterBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntitiesInit.PLASTIC_PLANTER_ENTITY.get(), PlasticPlanterBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntitiesInit.CANDY_PLANTER_ENTITY.get(), CandyPlanterBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntitiesInit.RED_CLAY_PLANTER_ENTITY.get(), RedClayPlanterBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntitiesInit.BLUE_CLAY_PLANTER_ENTITY.get(), BlueClayPlanterBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntitiesInit.TACKY_PLANTER_ENTITY.get(), TackyPlanterBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntitiesInit.PESTICIDE_PLANTER_ENTITY.get(), PesticidePlanterBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntitiesInit.HYDROPONIC_PLANTER_ENTITY.get(), HydroponicPlanterBlockEntityRenderer::new);
    }
}
