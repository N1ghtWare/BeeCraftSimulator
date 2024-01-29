package pl.nightware.beecraftsimulator.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import pl.nightware.beecraftsimulator.BeeCraftSimulator;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> DARK_FIRE_INFINIBURN = tag("dark_fire_infiniburn");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(BeeCraftSimulator.MOD_ID, name));
        }
    }

    public static class Items{

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(BeeCraftSimulator.MOD_ID, name));
        }


    }
}

