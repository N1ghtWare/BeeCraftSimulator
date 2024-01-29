package pl.nightware.beecraftsimulator.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import pl.nightware.beecraftsimulator.BeeCraftSimulator;

import javax.annotation.Nullable;

public class PlasticPlanterBlock extends Block
{
    public PlasticPlanterBlock(Properties pProperties) {
        super(pProperties);
    }
}
