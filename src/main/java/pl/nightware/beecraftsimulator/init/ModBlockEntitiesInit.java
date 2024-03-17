package pl.nightware.beecraftsimulator.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pl.nightware.beecraftsimulator.BeeCraftSimulator;
import pl.nightware.beecraftsimulator.blockentity.*;

public class ModBlockEntitiesInit
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BeeCraftSimulator.MOD_ID);

    public static final RegistryObject<BlockEntityType<PaperPlanterBlockEntity>> PAPER_PLANTER_ENTITY =
            BLOCK_ENTITIES.register("paper_planter",
                    () -> BlockEntityType.Builder.of(PaperPlanterBlockEntity::new, ModBlockInit.PAPER_PLANTER.get())
                            .build(null)
            );
    public static final RegistryObject<BlockEntityType<TicketPlanterBlockEntity>> TICKET_PLANTER_ENTITY =
            BLOCK_ENTITIES.register("ticket_planter",
                    () -> BlockEntityType.Builder.of(TicketPlanterBlockEntity::new, ModBlockInit.TICKET_PLANTER.get())
                            .build(null)
            );
    public static final RegistryObject<BlockEntityType<PlasticPlanterBlockEntity>> PLASTIC_PLANTER_ENTITY =
            BLOCK_ENTITIES.register("plastic_planter",
                    () -> BlockEntityType.Builder.of(PlasticPlanterBlockEntity::new, ModBlockInit.PLASTIC_PLANTER.get())
                            .build(null)
            );
    public static final RegistryObject<BlockEntityType<CandyPlanterBlockEntity>> CANDY_PLANTER_ENTITY =
            BLOCK_ENTITIES.register("candy_planter",
                    () -> BlockEntityType.Builder.of(CandyPlanterBlockEntity::new, ModBlockInit.CANDY_PLANTER.get())
                            .build(null)
            );
    public static final RegistryObject<BlockEntityType<BlueClayPlanterBlockEntity>> BLUE_CLAY_PLANTER_ENTITY =
            BLOCK_ENTITIES.register("blue_clay_planter",
                    () -> BlockEntityType.Builder.of(BlueClayPlanterBlockEntity::new, ModBlockInit.BLUE_CLAY_PLANTER.get())
                            .build(null)
            );
    public static final RegistryObject<BlockEntityType<RedClayPlanterBlockEntity>> RED_CLAY_PLANTER_ENTITY =
            BLOCK_ENTITIES.register("red_clay_planter",
                    () -> BlockEntityType.Builder.of(RedClayPlanterBlockEntity::new, ModBlockInit.RED_CLAY_PLANTER.get())
                            .build(null)
            );
    public static final RegistryObject<BlockEntityType<TackyPlanterBlockEntity>> TACKY_PLANTER_ENTITY =
            BLOCK_ENTITIES.register("tacky_planter",
                    () -> BlockEntityType.Builder.of(TackyPlanterBlockEntity::new, ModBlockInit.TACKY_PLANTER.get())
                            .build(null)
            );
    public static final RegistryObject<BlockEntityType<PesticidePlanterBlockEntity>> PESTICIDE_PLANTER_ENTITY =
            BLOCK_ENTITIES.register("pesticide_planter",
                    () -> BlockEntityType.Builder.of(PesticidePlanterBlockEntity::new, ModBlockInit.PESTICIDE_PLANTER.get())
                            .build(null)
            );
    public static final RegistryObject<BlockEntityType<HydroponicPlanterBlockEntity>> HYDROPONIC_PLANTER_ENTITY =
            BLOCK_ENTITIES.register("hydroponic_planter",
                    () -> BlockEntityType.Builder.of(HydroponicPlanterBlockEntity::new, ModBlockInit.HYDROPONIC_PLANTER.get())
                            .build(null)
            );
}
