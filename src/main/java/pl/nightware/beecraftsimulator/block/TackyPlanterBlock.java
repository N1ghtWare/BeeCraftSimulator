package pl.nightware.beecraftsimulator.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import pl.nightware.beecraftsimulator.blockentity.BlueClayPlanterBlockEntity;
import pl.nightware.beecraftsimulator.blockentity.TackyPlanterBlockEntity;
import pl.nightware.beecraftsimulator.init.ModBlockEntitiesInit;


public class TackyPlanterBlock extends PlanterBlock implements EntityBlock
{
    public TackyPlanterBlock(Properties pProperties)
    {
        super(pProperties);
        registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide() ? null : (_level, _pos, _state, _blockEntity) -> ((TackyPlanterBlockEntity)_blockEntity).tick();
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState)
    {
        return ModBlockEntitiesInit.TACKY_PLANTER_ENTITY.get().create(blockPos, blockState);
    }

    @Override
    public void destroy(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
        super.destroy(pLevel, pPos, pState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit)
    {
        if (!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND)
        {
            BlockEntity _blockEntity = pLevel.getBlockEntity(pPos);
            if (_blockEntity instanceof TackyPlanterBlockEntity blockEntity)
            {
                int counter = blockEntity.getPassedTime();
                int maxCounter = blockEntity.getFullGrowthTime();
                pPlayer.sendSystemMessage(Component.literal("Tacky Planter Growth: %d/%d.".formatted(counter, maxCounter)));

                return InteractionResult.SUCCESS;
            }
        }

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.or(box(2, 2, 2, 14, 12, 14), box(3, 0, 3, 13, 14, 13), box(13, 14, 1, 15, 17, 15), box(1, 14, 1, 3, 17, 15), box(3, 14, 13, 13, 17, 15), box(3, 14, 1, 13, 17, 3));
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }
}
