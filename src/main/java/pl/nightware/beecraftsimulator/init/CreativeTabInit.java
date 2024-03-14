package pl.nightware.beecraftsimulator.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pl.nightware.beecraftsimulator.BeeCraftSimulator;

public class CreativeTabInit
{
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BeeCraftSimulator.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MOD_CREATIVE_TAB = TABS.register("beecraftsimulator_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creative_tab.beecraftsimulator_tab"))
                    .icon(ModItemInit.STAR_JELLY.get()::getDefaultInstance)
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModItemInit.SCOOPER.get());

                        output.accept(ModBlockInit.FIELD_BLOCK_ITEM.get());

                        output.accept(ModBlockInit.PAPER_PLANTER_ITEM.get());
                        output.accept(ModBlockInit.PLASTIC_PLANTER_ITEM.get());
                        output.accept(ModBlockInit.CANDY_PLANTER_ITEM.get());
                        output.accept(ModBlockInit.BLUE_CLAY_PLANTER_ITEM.get());
                        output.accept(ModBlockInit.RED_CLAY_PLANTER_ITEM.get());

                        output.accept(ModItemInit.ROYAL_JELLY.get());
                        output.accept(ModItemInit.STAR_JELLY.get());

                        output.accept(ModItemInit.TREAT.get());
                        output.accept(ModItemInit.STAR_TREAT.get());
                        output.accept(ModItemInit.ATOMIC_TREAT.get());

                        output.accept(ModItemInit.SUNFLOWER_SEED.get());
                        output.accept(ModItemInit.STRAWBERRY.get());
                        output.accept(ModItemInit.PINEAPPLE.get());
                        output.accept(ModItemInit.BLUEBERRY.get());

                        output.accept(ModItemInit.BITTERBERRY.get());
                        output.accept(ModItemInit.NEONBERRY.get());

                        //test blocks
                        output.accept(ModBlockInit.DARK_FIRE_ITEM.get());
                    }))
                    .build()
    );
}
