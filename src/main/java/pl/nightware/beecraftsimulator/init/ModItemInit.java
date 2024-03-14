package pl.nightware.beecraftsimulator.init;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pl.nightware.beecraftsimulator.item.*;
import pl.nightware.beecraftsimulator.BeeCraftSimulator;
import pl.nightware.beecraftsimulator.util.ModTags;

public class ModItemInit
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BeeCraftSimulator.MOD_ID);

    public static final RegistryObject<Item> ROYAL_JELLY = ITEMS.register("royal_jelly",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.COMMON)
            ));

    public static final RegistryObject<Item> STAR_JELLY = ITEMS.register("star_jelly",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.UNCOMMON)
            ));

    public static final RegistryObject<Item> TREAT = ITEMS.register("treat",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.COMMON)
                    .food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.1f).build()

            )));

    public static final RegistryObject<Item> STAR_TREAT = ITEMS.register("star_treat",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE)
                    .food((new FoodProperties.Builder()).nutrition(20).saturationMod(1f).build()

                    )));

    public static final RegistryObject<Item> ATOMIC_TREAT = ITEMS.register("atomic_treat",
            ModAtomicTreatItem::new);

    public static final RegistryObject<Item> SUNFLOWER_SEED = ITEMS.register("sunflower_seed",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.COMMON)
                    .food((new FoodProperties.Builder()).nutrition(1).saturationMod(0.1f).build()

                    )));

    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.COMMON)
                    .food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.1f).build()

                    )));

    public static final RegistryObject<Item> PINEAPPLE = ITEMS.register("pineapple",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.COMMON)
                    .food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.1f).build()

                    )));

    public static final RegistryObject<Item> BLUEBERRY = ITEMS.register("blueberry",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.COMMON)
                    .food((new FoodProperties.Builder()).nutrition(1).saturationMod(0.1f).build()

                    )));

    public static final RegistryObject<Item> BITTERBERRY = ITEMS.register("bitterberry",
            ModBitterberryItem::new);

    public static final RegistryObject<Item> NEONBERRY = ITEMS.register("neonberry",
            ModNeonberryItem::new);

    public static final RegistryObject<ShovelItem> SCOOPER = ITEMS.register("scooper",
            () -> new ShovelItem(ModTierInit.FIELD_TOOL, 1, 1, new Item.Properties()));
}
