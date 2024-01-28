package pl.nightware.beecraftsimulator.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import pl.nightware.beecraftsimulator.BeeCraftSimulator;
import pl.nightware.beecraftsimulator.block.ModBlocks;

public class ModCreativeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BeeCraftSimulator.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BEECRAFTSIMULATOR_TAB =
            CREATIVE_MODE_TABS.register("beecraftsimulator_tab", () ->
                    CreativeModeTab
                            .builder()
                            .title(Component.translatable("creativetab.beecraftsimulator_tab"))
                            .icon(() -> new ItemStack(ModItems.ROYAL_JELLY.get()))
                            .displayItems((itemDisplayParameters, output) -> {
                                output.accept(ModItems.ROYAL_JELLY.get());
                                output.accept(ModItems.STAR_JELLY.get());

                                output.accept(ModBlocks.PLASTIC_PLANTER.get());
                            })
                            .build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
