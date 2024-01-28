package pl.nightware.beecraftsimulator.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pl.nightware.beecraftsimulator.BeeCraftSimulator;

public class ModItemInit
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BeeCraftSimulator.MOD_ID);

    public static final RegistryObject<Item> ROYAL_JELLY = ITEMS.register("royal_jelly",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.COMMON)
            ));

    public static final RegistryObject<Item> STAR_JELLY = ITEMS.register("star_jelly",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.EPIC)
            ));


}
