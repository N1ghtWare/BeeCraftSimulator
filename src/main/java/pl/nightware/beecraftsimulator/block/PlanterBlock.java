package pl.nightware.beecraftsimulator.block;

import it.unimi.dsi.fastutil.floats.FloatPredicate;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class PlanterBlock extends Block
{
    public PlanterBlock(Properties pProperties)
    {
        super(pProperties);
    }
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
}
