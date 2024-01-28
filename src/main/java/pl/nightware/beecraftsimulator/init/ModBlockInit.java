package pl.nightware.beecraftsimulator.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pl.nightware.beecraftsimulator.BeeCraftSimulator;

public class ModBlockInit
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BeeCraftSimulator.MOD_ID);

    public static final RegistryObject<Block> PLASTIC_PLANTER = BLOCKS.register("plastic_planter",
            () -> new Block(BlockBehaviour.Properties.of()
                    .noOcclusion()

            ));

    public static final RegistryObject<BlockItem> PLASTIC_PLANTER_ITEM = ModItemInit.ITEMS.register("plastic_planter",
            () -> new BlockItem(ModBlockInit.PLASTIC_PLANTER.get(), new Item.Properties()));
}
