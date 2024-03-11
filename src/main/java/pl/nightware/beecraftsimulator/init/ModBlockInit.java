package pl.nightware.beecraftsimulator.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pl.nightware.beecraftsimulator.BeeCraftSimulator;
import pl.nightware.beecraftsimulator.block.CandyPlanterBlock;
import pl.nightware.beecraftsimulator.block.DarkFireBlock;
import pl.nightware.beecraftsimulator.block.PlasticPlanterBlock;

public class ModBlockInit
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BeeCraftSimulator.MOD_ID);

    // planters
    public static final RegistryObject<PlasticPlanterBlock> PLASTIC_PLANTER = BLOCKS.register("plastic_planter",
            () -> new PlasticPlanterBlock(BlockBehaviour.Properties.of()
                    //.dynamicShape()
                    .noOcclusion()
                    .strength(1.5F)
            ));
    public static final RegistryObject<BlockItem> PLASTIC_PLANTER_ITEM = ModItemInit.ITEMS.register("plastic_planter",
            () -> new BlockItem(ModBlockInit.PLASTIC_PLANTER.get(), new Item.Properties()
                    .stacksTo(1)
            ));
    public static final RegistryObject<CandyPlanterBlock> CANDY_PLANTER = BLOCKS.register("candy_planter",
            () -> new CandyPlanterBlock(BlockBehaviour.Properties.of()
                    //.dynamicShape()
                    .noOcclusion()
                    .strength(1.5F)
            ));
    public static final RegistryObject<BlockItem> CANDY_PLANTER_ITEM = ModItemInit.ITEMS.register("candy_planter",
            () -> new BlockItem(ModBlockInit.CANDY_PLANTER.get(), new Item.Properties()
                    .stacksTo(1)
            ));

    public static final RegistryObject<Block> CABBAGE = BLOCKS.register("cabbage",
            () -> new Block(BlockBehaviour.Properties.of()
                    .sound(SoundType.GRASS)
            ));
    public static final RegistryObject<BlockItem> CABBAGE_ITEM = ModItemInit.ITEMS.register("cabbage",
            () -> new BlockItem(ModBlockInit.CABBAGE.get(), new Item.Properties()

            ));

    // blocks

    public static final RegistryObject<Block> FIELD_BLOCK = BLOCKS.register("field_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .sound(SoundType.GRASS)
                    .noCollission()
            ));
    public static final RegistryObject<BlockItem> FIELD_BLOCK_ITEM = ModItemInit.ITEMS.register("field_block",
            () -> new BlockItem(ModBlockInit.FIELD_BLOCK.get(), new Item.Properties()

            ));

    // misc

    public static final RegistryObject<DarkFireBlock> DARK_FIRE = BLOCKS.register("dark_fire",
            () -> new DarkFireBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.FIRE)
                    .replaceable()
                    .noCollission()
                    .instabreak()
                    .lightLevel((p_152607_) -> {
                        return 15;
                    })
                    .sound(SoundType.WOOL)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<BlockItem> DARK_FIRE_ITEM = ModItemInit.ITEMS.register("dark_fire",
            () -> new BlockItem(ModBlockInit.DARK_FIRE.get(), new Item.Properties()));
}
