package pl.nightware.beecraftsimulator.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pl.nightware.beecraftsimulator.BeeCraftSimulator;
import pl.nightware.beecraftsimulator.blockentity.CandyPlanterBlockEntity;
import pl.nightware.beecraftsimulator.blockentity.PlasticPlanterBlockEntity;

public class ModBlockEntitiesInit
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BeeCraftSimulator.MOD_ID);

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
}
