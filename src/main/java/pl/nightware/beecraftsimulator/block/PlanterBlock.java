package pl.nightware.beecraftsimulator.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class PlanterBlock extends Block
{
    public PlanterBlock(Properties pProperties)
    {
        super(pProperties);
    }

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
}
