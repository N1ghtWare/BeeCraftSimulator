package pl.nightware.beecraftsimulator.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import pl.nightware.beecraftsimulator.init.ModBlockEntitiesInit;

public class PlasticPlanterBlockEntity extends BlockEntity
{
    private int counter;
    public PlasticPlanterBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(ModBlockEntitiesInit.PLASTIC_PLANTER_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag nbtTag)
    {
        super.saveAdditional(nbtTag);
        nbtTag.putInt("Counter", this.counter);
    }

    @Override
    public void load(CompoundTag nbtTag)
    {
        super.load(nbtTag);
        this.counter = nbtTag.getInt("Counter");
    }

    public int incrementCounter()
    {
        this.counter++;
        setChanged();
        return this.counter;
    }

    public int getCounter()
    {
        return this.counter;
    }
}
