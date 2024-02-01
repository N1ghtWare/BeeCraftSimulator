package pl.nightware.beecraftsimulator.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import pl.nightware.beecraftsimulator.BeeCraftSimulator;
import pl.nightware.beecraftsimulator.init.ModBlockEntitiesInit;

public class PlasticPlanterBlockEntity extends BlockEntity
{
    private int counter;

    public PlasticPlanterBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(ModBlockEntitiesInit.PLASTIC_PLANTER_ENTITY.get(), pPos, pBlockState);
    }

    public void tick()
    {
        if (this.level == null || this.level.isClientSide()) { return; }
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
        if (counter < 10)
        {
            this.counter++;
            setChanged();
        }
        else
        {
            ItemEntity diamond = new ItemEntity(this.level, this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ(), new ItemStack(Items.DIAMOND));
            this.level.addFreshEntity(diamond);
            this.counter = 0;
        }

        return this.counter;
    }

    public int getCounter()
    {
        return this.counter;
    }
}
