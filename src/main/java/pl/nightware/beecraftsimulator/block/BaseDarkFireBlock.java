package pl.nightware.beecraftsimulator.block;

import java.util.Optional;
import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.Plane;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.SoulFireBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.event.ForgeEventFactory;

public abstract class BaseDarkFireBlock extends Block {
    private static final int SECONDS_ON_FIRE = 8;
    private final float fireDamage;
    protected static final float AABB_OFFSET = 1.0F;
    protected static final VoxelShape DOWN_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);

    public BaseDarkFireBlock(BlockBehaviour.Properties pProperties, float pFireDamage) {
        super(pProperties);
        this.fireDamage = pFireDamage;
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return getState(pContext.getLevel(), pContext.getClickedPos());
    }

    public static BlockState getState(BlockGetter pReader, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        BlockState blockstate = pReader.getBlockState(blockpos);
        return DarkFireBlock.canSurviveOnBlock(blockstate) ? Blocks.SOUL_FIRE.defaultBlockState() : ((DarkFireBlock)Blocks.FIRE).getStateForPlacement(pReader, pPos);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return DOWN_AABB;
    }

    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(24) == 0) {
            pLevel.playLocalSound((double)pPos.getX() + 0.5, (double)pPos.getY() + 0.5, (double)pPos.getZ() + 0.5, SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS, 1.0F + pRandom.nextFloat(), pRandom.nextFloat() * 0.7F + 0.3F, false);
        }

        BlockPos blockpos = pPos.below();
        BlockState blockstate = pLevel.getBlockState(blockpos);
        int j1;
        double d7;
        double d12;
        double d17;
        if (!this.canBurn(blockstate) && !blockstate.isFaceSturdy(pLevel, blockpos, Direction.UP)) {
            if (this.canBurn(pLevel.getBlockState(pPos.west()))) {
                for(j1 = 0; j1 < 2; ++j1) {
                    d7 = (double)pPos.getX() + pRandom.nextDouble() * 0.10000000149011612;
                    d12 = (double)pPos.getY() + pRandom.nextDouble();
                    d17 = (double)pPos.getZ() + pRandom.nextDouble();
                    pLevel.addParticle(ParticleTypes.LARGE_SMOKE, d7, d12, d17, 0.0, 0.0, 0.0);
                }
            }

            if (this.canBurn(pLevel.getBlockState(pPos.east()))) {
                for(j1 = 0; j1 < 2; ++j1) {
                    d7 = (double)(pPos.getX() + 1) - pRandom.nextDouble() * 0.10000000149011612;
                    d12 = (double)pPos.getY() + pRandom.nextDouble();
                    d17 = (double)pPos.getZ() + pRandom.nextDouble();
                    pLevel.addParticle(ParticleTypes.LARGE_SMOKE, d7, d12, d17, 0.0, 0.0, 0.0);
                }
            }

            if (this.canBurn(pLevel.getBlockState(pPos.north()))) {
                for(j1 = 0; j1 < 2; ++j1) {
                    d7 = (double)pPos.getX() + pRandom.nextDouble();
                    d12 = (double)pPos.getY() + pRandom.nextDouble();
                    d17 = (double)pPos.getZ() + pRandom.nextDouble() * 0.10000000149011612;
                    pLevel.addParticle(ParticleTypes.LARGE_SMOKE, d7, d12, d17, 0.0, 0.0, 0.0);
                }
            }

            if (this.canBurn(pLevel.getBlockState(pPos.south()))) {
                for(j1 = 0; j1 < 2; ++j1) {
                    d7 = (double)pPos.getX() + pRandom.nextDouble();
                    d12 = (double)pPos.getY() + pRandom.nextDouble();
                    d17 = (double)(pPos.getZ() + 1) - pRandom.nextDouble() * 0.10000000149011612;
                    pLevel.addParticle(ParticleTypes.LARGE_SMOKE, d7, d12, d17, 0.0, 0.0, 0.0);
                }
            }

            if (this.canBurn(pLevel.getBlockState(pPos.above()))) {
                for(j1 = 0; j1 < 2; ++j1) {
                    d7 = (double)pPos.getX() + pRandom.nextDouble();
                    d12 = (double)(pPos.getY() + 1) - pRandom.nextDouble() * 0.10000000149011612;
                    d17 = (double)pPos.getZ() + pRandom.nextDouble();
                    pLevel.addParticle(ParticleTypes.LARGE_SMOKE, d7, d12, d17, 0.0, 0.0, 0.0);
                }
            }
        } else {
            for(j1 = 0; j1 < 3; ++j1) {
                d7 = (double)pPos.getX() + pRandom.nextDouble();
                d12 = (double)pPos.getY() + pRandom.nextDouble() * 0.5 + 0.5;
                d17 = (double)pPos.getZ() + pRandom.nextDouble();
                pLevel.addParticle(ParticleTypes.LARGE_SMOKE, d7, d12, d17, 0.0, 0.0, 0.0);
            }
        }

    }

    protected abstract boolean canBurn(BlockState var1);

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (!pEntity.fireImmune()) {
            pEntity.setRemainingFireTicks(pEntity.getRemainingFireTicks() + 1);
            if (pEntity.getRemainingFireTicks() == 0) {
                pEntity.setSecondsOnFire(8);
            }
        }

        pEntity.hurt(pLevel.damageSources().inFire(), this.fireDamage);
        super.entityInside(pState, pLevel, pPos, pEntity);
    }

    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (!pOldState.is(pState.getBlock())) {
            if (inPortalDimension(pLevel)) {
                Optional<PortalShape> optional = PortalShape.findEmptyPortalShape(pLevel, pPos, Axis.X);
                optional = ForgeEventFactory.onTrySpawnPortal(pLevel, pPos, optional);
                if (optional.isPresent()) {
                    ((PortalShape)optional.get()).createPortalBlocks();
                    return;
                }
            }

            if (!pState.canSurvive(pLevel, pPos)) {
                pLevel.removeBlock(pPos, false);
            }
        }

    }

    private static boolean inPortalDimension(Level pLevel) {
        return pLevel.dimension() == Level.OVERWORLD || pLevel.dimension() == Level.NETHER;
    }

    protected void spawnDestroyParticles(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState) {
    }

    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if (!pLevel.isClientSide()) {
            pLevel.levelEvent((Player)null, 1009, pPos, 0);
        }

        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }

    public static boolean canBePlacedAt(Level pLevel, BlockPos pPos, Direction pDirection) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        if (!blockstate.isAir()) {
            return false;
        } else {
            return getState(pLevel, pPos).canSurvive(pLevel, pPos) || isPortal(pLevel, pPos, pDirection);
        }
    }

    private static boolean isPortal(Level pLevel, BlockPos pPos, Direction pDirection) {
        if (!inPortalDimension(pLevel)) {
            return false;
        } else {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.mutable();
            boolean flag = false;
            Direction[] var5 = Direction.values();
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Direction direction = var5[var7];
                if (pLevel.getBlockState(blockpos$mutableblockpos.set(pPos).move(direction)).isPortalFrame(pLevel, blockpos$mutableblockpos)) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                return false;
            } else {
                Direction.Axis direction$axis = pDirection.getAxis().isHorizontal() ? pDirection.getCounterClockWise().getAxis() : Plane.HORIZONTAL.getRandomAxis(pLevel.random);
                return PortalShape.findEmptyPortalShape(pLevel, pPos, direction$axis).isPresent();
            }
        }
    }
}
