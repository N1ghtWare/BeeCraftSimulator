package pl.nightware.beecraftsimulator.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import pl.nightware.beecraftsimulator.BeeCraftSimulator;

public class ModTagInit
{
    public static final TagKey<Block> NEEDS_FIELD_TOOL = tag("needs_field_tool");
    public static final TagKey<Block> NEEDS_SHARP_TOOL = tag("needs_sharp_tool");

    private static TagKey<Block> tag(String name) { return BlockTags.create(new ResourceLocation(BeeCraftSimulator.MOD_ID, name)); }
}
