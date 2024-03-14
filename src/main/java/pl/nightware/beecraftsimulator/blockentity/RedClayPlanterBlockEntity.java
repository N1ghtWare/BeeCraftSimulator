package pl.nightware.beecraftsimulator.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import pl.nightware.beecraftsimulator.init.ModBlockEntitiesInit;

public class RedClayPlanterBlockEntity extends BlockEntity
{
    private int ticksOfGrowth = 0;
    public int ticksRequiredForFullGrowth = 100;
    public float cabbageScaleMultiplier = 1f;
    public RedClayPlanterBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(ModBlockEntitiesInit.RED_CLAY_PLANTER_ENTITY.get(), pPos, pBlockState);
    }

    public void tick()
    {
        if (this.level == null || this.level.isClientSide()) { return; }

        if (this.ticksOfGrowth < this.ticksRequiredForFullGrowth)
        {
            this.ticksOfGrowth++;
            setChanged();

            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }

    }

    @Override
    protected void saveAdditional(CompoundTag nbtTag)
    {
        super.saveAdditional(nbtTag);
        nbtTag.putInt("Growth", this.ticksOfGrowth);
        nbtTag.putInt("RequiredGrowth", this.ticksRequiredForFullGrowth);
    }

    @Override
    public void load(CompoundTag nbtTag)
    {
        super.load(nbtTag);
        this.ticksOfGrowth = nbtTag.getInt("Growth");
        this.ticksRequiredForFullGrowth = nbtTag.getInt("RequiredGrowth");
    }

    @Override
    public CompoundTag getUpdateTag()
    {
        CompoundTag nbt = super.getUpdateTag();
        saveAdditional(nbt);
        return nbt;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }

//    super() already does that, we don't need to override it
//    @Override
//    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt)
//    {
//        handleUpdateTag(pkt.getTag());
//    }

    public float getCabbageScale()
    {
        return cabbageScaleMultiplier;
    }

    public int getPassedTime()
    {
        return this.ticksOfGrowth;
    }

    public int getFullGrowthTime()
    {
        return this.ticksRequiredForFullGrowth;
    }
}
