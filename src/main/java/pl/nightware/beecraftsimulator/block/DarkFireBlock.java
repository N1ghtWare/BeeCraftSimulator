package pl.nightware.beecraftsimulator.block;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import pl.nightware.beecraftsimulator.util.ModTags;

public class DarkFireBlock extends BaseDarkFireBlock {
    public static final int MAX_AGE = 15;
    public static final IntegerProperty AGE;
    public static final BooleanProperty NORTH;
    public static final BooleanProperty EAST;
    public static final BooleanProperty SOUTH;
    public static final BooleanProperty WEST;
    public static final BooleanProperty UP;
    private static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION;
    private static final VoxelShape UP_AABB;
    private static final VoxelShape WEST_AABB;
    private static final VoxelShape EAST_AABB;
    private static final VoxelShape NORTH_AABB;
    private static final VoxelShape SOUTH_AABB;
    private final Map<BlockState, VoxelShape> shapesCache;
    private static final int IGNITE_INSTANT = 60;
    private static final int IGNITE_EASY = 30;
    private static final int IGNITE_MEDIUM = 15;
    private static final int IGNITE_HARD = 5;
    private static final int BURN_INSTANT = 100;
    private static final int BURN_EASY = 60;
    private static final int BURN_MEDIUM = 20;
    private static final int BURN_HARD = 5;
    private final Object2IntMap<Block> igniteOdds = new Object2IntOpenHashMap();
    private final Object2IntMap<Block> burnOdds = new Object2IntOpenHashMap();

    public DarkFireBlock(BlockBehaviour.Properties p_53425_) {
        super(p_53425_, 5.0F);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(AGE, 0)).setValue(NORTH, false)).setValue(EAST, false)).setValue(SOUTH, false)).setValue(WEST, false)).setValue(UP, false));
        this.shapesCache = ImmutableMap.copyOf((Map)this.stateDefinition.getPossibleStates().stream().filter((p_53497_) -> {
            return (Integer)p_53497_.getValue(AGE) == 0;
        }).collect(Collectors.toMap(Function.identity(), pl.nightware.beecraftsimulator.block.DarkFireBlock::calculateShape)));
    }

    private static VoxelShape calculateShape(BlockState p_53491_) {
        VoxelShape voxelshape = Shapes.empty();
        if ((Boolean)p_53491_.getValue(UP)) {
            voxelshape = UP_AABB;
        }

        if ((Boolean)p_53491_.getValue(NORTH)) {
            voxelshape = Shapes.or(voxelshape, NORTH_AABB);
        }

        if ((Boolean)p_53491_.getValue(SOUTH)) {
            voxelshape = Shapes.or(voxelshape, SOUTH_AABB);
        }

        if ((Boolean)p_53491_.getValue(EAST)) {
            voxelshape = Shapes.or(voxelshape, EAST_AABB);
        }

        if ((Boolean)p_53491_.getValue(WEST)) {
            voxelshape = Shapes.or(voxelshape, WEST_AABB);
        }

        return voxelshape.isEmpty() ? DOWN_AABB : voxelshape;
    }


    public static boolean canSurviveOnBlock(BlockState pState) {
        return pState.is(ModTags.Blocks.DARK_FIRE_INFINIBURN);
    }



    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return (VoxelShape)this.shapesCache.get(pState.setValue(AGE, 0));
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.getStateForPlacement(pContext.getLevel(), pContext.getClickedPos());
    }

    protected BlockState getStateForPlacement(BlockGetter pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        BlockState blockstate = pLevel.getBlockState(blockpos);
        if (!this.canCatchFire(pLevel, pPos, Direction.UP) && !blockstate.isFaceSturdy(pLevel, blockpos, Direction.UP)) {
            BlockState blockstate1 = this.defaultBlockState();
            Direction[] var6 = Direction.values();
            int var7 = var6.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                Direction direction = var6[var8];
                BooleanProperty booleanproperty = (BooleanProperty)PROPERTY_BY_DIRECTION.get(direction);
                if (booleanproperty != null) {
                    blockstate1 = (BlockState)blockstate1.setValue(booleanproperty, this.canCatchFire(pLevel, pPos.relative(direction), direction.getOpposite()));
                }
            }

            return blockstate1;
        } else {
            return this.defaultBlockState();
        }
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        return pLevel.getBlockState(blockpos).isFaceSturdy(pLevel, blockpos, Direction.UP) || this.isValidFireLocation(pLevel, pPos);
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        pLevel.scheduleTick(pPos, this, getFireTickDelay(pLevel.random));
        if (pLevel.getGameRules().getBoolean(GameRules.RULE_DOFIRETICK)) {
            if (!pState.canSurvive(pLevel, pPos)) {
            }

            BlockState blockstate = pLevel.getBlockState(pPos.below());
            boolean flag = blockstate.isFireSource(pLevel, pPos, Direction.UP);
            int i = (Integer)pState.getValue(AGE);
            if (!flag && pLevel.isRaining() && this.isNearRain(pLevel, pPos) && pRandom.nextFloat() < 0.2F + (float)i * 0.03F) {
                pLevel.removeBlock(pPos, false);
                pLevel.removeBlock(pPos, false);
            } else {
                int j = Math.min(15, i + pRandom.nextInt(3) / 2);
                if (i != j) {
                    pState = (BlockState)pState.setValue(AGE, j);
                    pLevel.setBlock(pPos, pState, 4);
                }

                if (!flag) {
                    if (!this.isValidFireLocation(pLevel, pPos)) {
                        BlockPos blockpos = pPos.below();
                        if (!pLevel.getBlockState(blockpos).isFaceSturdy(pLevel, blockpos, Direction.UP) || i > 3) {
                            pLevel.removeBlock(pPos, false);
                        }

                        return;
                    }

                    if (i == 15 && pRandom.nextInt(4) == 0 && !this.canCatchFire(pLevel, pPos.below(), Direction.UP)) {
                        pLevel.removeBlock(pPos, false);
                        return;
                    }
                }

                boolean flag1 = pLevel.getBiome(pPos).is(BiomeTags.INCREASED_FIRE_BURNOUT);
                int k = flag1 ? -50 : 0;
                this.tryCatchFire(pLevel, pPos.east(), 300 + k, pRandom, i, Direction.WEST);
                this.tryCatchFire(pLevel, pPos.west(), 300 + k, pRandom, i, Direction.EAST);
                this.tryCatchFire(pLevel, pPos.below(), 250 + k, pRandom, i, Direction.UP);
                this.tryCatchFire(pLevel, pPos.above(), 250 + k, pRandom, i, Direction.DOWN);
                this.tryCatchFire(pLevel, pPos.north(), 300 + k, pRandom, i, Direction.SOUTH);
                this.tryCatchFire(pLevel, pPos.south(), 300 + k, pRandom, i, Direction.NORTH);
                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for(int l = -1; l <= 1; ++l) {
                    for(int i1 = -1; i1 <= 1; ++i1) {
                        for(int j1 = -1; j1 <= 4; ++j1) {
                            if (l != 0 || j1 != 0 || i1 != 0) {
                                int k1 = 100;
                                if (j1 > 1) {
                                    k1 += (j1 - 1) * 100;
                                }

                                blockpos$mutableblockpos.setWithOffset(pPos, l, j1, i1);
                                int l1 = this.getIgniteOdds(pLevel, blockpos$mutableblockpos);
                                if (l1 > 0) {
                                    int i2 = (l1 + 40 + pLevel.getDifficulty().getId() * 7) / (i + 30);
                                    if (flag1) {
                                        i2 /= 2;
                                    }

                                    if (i2 > 0 && pRandom.nextInt(k1) <= i2 && (!pLevel.isRaining() || !this.isNearRain(pLevel, blockpos$mutableblockpos))) {
                                        int j2 = Math.min(15, i + pRandom.nextInt(5) / 4);
                                        pLevel.setBlock(blockpos$mutableblockpos, this.getStateWithAge(pLevel, blockpos$mutableblockpos, j2), 3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    protected boolean isNearRain(Level pLevel, BlockPos pPos) {
        return pLevel.isRainingAt(pPos) || pLevel.isRainingAt(pPos.west()) || pLevel.isRainingAt(pPos.east()) || pLevel.isRainingAt(pPos.north()) || pLevel.isRainingAt(pPos.south());
    }

    /** @deprecated */
    @Deprecated
    public int getBurnOdds(BlockState pState) {
        return pState.hasProperty(BlockStateProperties.WATERLOGGED) && (Boolean)pState.getValue(BlockStateProperties.WATERLOGGED) ? 0 : this.burnOdds.getInt(pState.getBlock());
    }

    /** @deprecated */
    @Deprecated
    public int getIgniteOdds(BlockState pState) {
        return pState.hasProperty(BlockStateProperties.WATERLOGGED) && (Boolean)pState.getValue(BlockStateProperties.WATERLOGGED) ? 0 : this.igniteOdds.getInt(pState.getBlock());
    }

    private void tryCatchFire(Level p_53432_, BlockPos p_53433_, int p_53434_, RandomSource p_53435_, int p_53436_, Direction face) {
        int i = p_53432_.getBlockState(p_53433_).getFlammability(p_53432_, p_53433_, face);
        if (p_53435_.nextInt(p_53434_) < i) {
            BlockState blockstate = p_53432_.getBlockState(p_53433_);
            blockstate.onCaughtFire(p_53432_, p_53433_, face, (LivingEntity)null);
            if (p_53435_.nextInt(p_53434_ + 10) < 5 && !p_53432_.isRainingAt(p_53433_)) {
                int j = Math.min(p_53434_ + p_53435_.nextInt(5) / 4, 15);
                p_53432_.setBlock(p_53433_, this.getStateWithAge(p_53432_, p_53433_, j), 3);
            } else {
                p_53432_.removeBlock(p_53433_, false);
            }
        }

    }

    private BlockState getStateWithAge(LevelAccessor pLevel, BlockPos pPos, int pAge) {
        BlockState blockstate = getState(pLevel, pPos);
        return blockstate.is(Blocks.FIRE) ? (BlockState)blockstate.setValue(AGE, pAge) : blockstate;
    }

    private boolean isValidFireLocation(BlockGetter pLevel, BlockPos pPos) {
        Direction[] var3 = Direction.values();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Direction direction = var3[var5];
            if (this.canCatchFire(pLevel, pPos.relative(direction), direction.getOpposite())) {
                return true;
            }
        }

        return false;
    }

    private int getIgniteOdds(LevelReader pLevel, BlockPos pPos) {
        if (!pLevel.isEmptyBlock(pPos)) {
            return 0;
        } else {
            int i = 0;
            Direction[] var4 = Direction.values();
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Direction direction = var4[var6];
                BlockState blockstate = pLevel.getBlockState(pPos.relative(direction));
                i = Math.max(blockstate.getFireSpreadSpeed(pLevel, pPos.relative(direction), direction.getOpposite()), i);
            }

            return i;
        }
    }

    /** @deprecated */
    @Deprecated
    protected boolean canBurn(BlockState pState) {
        return this.getIgniteOdds(pState) > 0;
    }

    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        super.onPlace(pState, pLevel, pPos, pOldState, pIsMoving);
        pLevel.scheduleTick(pPos, this, getFireTickDelay(pLevel.random));
    }

    private static int getFireTickDelay(RandomSource pRandom) {
        return 30 + pRandom.nextInt(10);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(new Property[]{AGE, NORTH, EAST, SOUTH, WEST, UP});
    }

    private void setFlammable(Block pBlock, int pEncouragement, int pFlammability) {
        if (pBlock == Blocks.AIR) {
            throw new IllegalArgumentException("Tried to set air on fire... This is bad.");
        } else {
            this.igniteOdds.put(pBlock, pEncouragement);
            this.burnOdds.put(pBlock, pFlammability);
        }
    }

    public boolean canCatchFire(BlockGetter world, BlockPos pos, Direction face) {
        return world.getBlockState(pos).isFlammable(world, pos, face);
    }

    public static void bootStrap() {
        pl.nightware.beecraftsimulator.block.DarkFireBlock fireblock = (pl.nightware.beecraftsimulator.block.DarkFireBlock)Blocks.FIRE;
        fireblock.setFlammable(Blocks.OAK_PLANKS, 5, 40);
        fireblock.setFlammable(Blocks.SPRUCE_PLANKS, 5, 40);
        fireblock.setFlammable(Blocks.BIRCH_PLANKS, 5, 40);
        fireblock.setFlammable(Blocks.JUNGLE_PLANKS, 5, 40);
        fireblock.setFlammable(Blocks.ACACIA_PLANKS, 5, 40);
        fireblock.setFlammable(Blocks.CHERRY_PLANKS, 5, 40);
        fireblock.setFlammable(Blocks.DARK_OAK_PLANKS, 5, 40);
        fireblock.setFlammable(Blocks.MANGROVE_PLANKS, 5, 40);
        fireblock.setFlammable(Blocks.BAMBOO_PLANKS, 5, 40);
        fireblock.setFlammable(Blocks.BAMBOO_MOSAIC, 5, 40);
        fireblock.setFlammable(Blocks.OAK_SLAB, 5, 40);
        fireblock.setFlammable(Blocks.SPRUCE_SLAB, 5, 40);
        fireblock.setFlammable(Blocks.BIRCH_SLAB, 5, 40);
        fireblock.setFlammable(Blocks.JUNGLE_SLAB, 5, 40);
        fireblock.setFlammable(Blocks.ACACIA_SLAB, 5, 40);
        fireblock.setFlammable(Blocks.CHERRY_SLAB, 5, 40);
        fireblock.setFlammable(Blocks.DARK_OAK_SLAB, 5, 40);
        fireblock.setFlammable(Blocks.MANGROVE_SLAB, 5, 40);
        fireblock.setFlammable(Blocks.BAMBOO_SLAB, 5, 40);
        fireblock.setFlammable(Blocks.BAMBOO_MOSAIC_SLAB, 5, 40);
        fireblock.setFlammable(Blocks.OAK_FENCE_GATE, 5, 40);
        fireblock.setFlammable(Blocks.SPRUCE_FENCE_GATE, 5, 40);
        fireblock.setFlammable(Blocks.BIRCH_FENCE_GATE, 5, 40);
        fireblock.setFlammable(Blocks.JUNGLE_FENCE_GATE, 5, 40);
        fireblock.setFlammable(Blocks.ACACIA_FENCE_GATE, 5, 40);
        fireblock.setFlammable(Blocks.CHERRY_FENCE_GATE, 5, 40);
        fireblock.setFlammable(Blocks.DARK_OAK_FENCE_GATE, 5, 40);
        fireblock.setFlammable(Blocks.MANGROVE_FENCE_GATE, 5, 40);
        fireblock.setFlammable(Blocks.BAMBOO_FENCE_GATE, 5, 40);
        fireblock.setFlammable(Blocks.OAK_FENCE, 5, 40);
        fireblock.setFlammable(Blocks.SPRUCE_FENCE, 5, 40);
        fireblock.setFlammable(Blocks.BIRCH_FENCE, 5, 40);
        fireblock.setFlammable(Blocks.JUNGLE_FENCE, 5, 40);
        fireblock.setFlammable(Blocks.ACACIA_FENCE, 5, 40);
        fireblock.setFlammable(Blocks.CHERRY_FENCE, 5, 40);
        fireblock.setFlammable(Blocks.DARK_OAK_FENCE, 5, 40);
        fireblock.setFlammable(Blocks.MANGROVE_FENCE, 5, 40);
        fireblock.setFlammable(Blocks.BAMBOO_FENCE, 5, 40);
        fireblock.setFlammable(Blocks.OAK_STAIRS, 5, 40);
        fireblock.setFlammable(Blocks.BIRCH_STAIRS, 5, 40);
        fireblock.setFlammable(Blocks.SPRUCE_STAIRS, 5, 40);
        fireblock.setFlammable(Blocks.JUNGLE_STAIRS, 5, 40);
        fireblock.setFlammable(Blocks.ACACIA_STAIRS, 5, 40);
        fireblock.setFlammable(Blocks.CHERRY_STAIRS, 5, 40);
        fireblock.setFlammable(Blocks.DARK_OAK_STAIRS, 5, 40);
        fireblock.setFlammable(Blocks.MANGROVE_STAIRS, 5, 40);
        fireblock.setFlammable(Blocks.BAMBOO_STAIRS, 5, 40);
        fireblock.setFlammable(Blocks.BAMBOO_MOSAIC_STAIRS, 5, 40);
        fireblock.setFlammable(Blocks.OAK_LOG, 5, 10);
        fireblock.setFlammable(Blocks.SPRUCE_LOG, 5, 10);
        fireblock.setFlammable(Blocks.BIRCH_LOG, 5, 10);
        fireblock.setFlammable(Blocks.JUNGLE_LOG, 5, 10);
        fireblock.setFlammable(Blocks.ACACIA_LOG, 5, 10);
        fireblock.setFlammable(Blocks.CHERRY_LOG, 5, 10);
        fireblock.setFlammable(Blocks.DARK_OAK_LOG, 5, 10);
        fireblock.setFlammable(Blocks.MANGROVE_LOG, 5, 10);
        fireblock.setFlammable(Blocks.BAMBOO_BLOCK, 5, 10);
        fireblock.setFlammable(Blocks.STRIPPED_OAK_LOG, 5, 10);
        fireblock.setFlammable(Blocks.STRIPPED_SPRUCE_LOG, 5, 10);
        fireblock.setFlammable(Blocks.STRIPPED_BIRCH_LOG, 5, 10);
        fireblock.setFlammable(Blocks.STRIPPED_JUNGLE_LOG, 5, 10);
        fireblock.setFlammable(Blocks.STRIPPED_ACACIA_LOG, 5, 10);
        fireblock.setFlammable(Blocks.STRIPPED_CHERRY_LOG, 5, 10);
        fireblock.setFlammable(Blocks.STRIPPED_DARK_OAK_LOG, 5, 10);
        fireblock.setFlammable(Blocks.STRIPPED_MANGROVE_LOG, 5, 10);
        fireblock.setFlammable(Blocks.STRIPPED_BAMBOO_BLOCK, 5, 10);
        fireblock.setFlammable(Blocks.STRIPPED_OAK_WOOD, 5, 10);
        fireblock.setFlammable(Blocks.STRIPPED_SPRUCE_WOOD, 5, 10);
        fireblock.setFlammable(Blocks.STRIPPED_BIRCH_WOOD, 5, 10);
        fireblock.setFlammable(Blocks.STRIPPED_JUNGLE_WOOD, 5, 10);
        fireblock.setFlammable(Blocks.STRIPPED_ACACIA_WOOD, 5, 10);
        fireblock.setFlammable(Blocks.STRIPPED_CHERRY_WOOD, 5, 10);
        fireblock.setFlammable(Blocks.STRIPPED_DARK_OAK_WOOD, 5, 10);
        fireblock.setFlammable(Blocks.STRIPPED_MANGROVE_WOOD, 5, 10);
        fireblock.setFlammable(Blocks.OAK_WOOD, 5, 10);
        fireblock.setFlammable(Blocks.SPRUCE_WOOD, 5, 10);
        fireblock.setFlammable(Blocks.BIRCH_WOOD, 5, 10);
        fireblock.setFlammable(Blocks.JUNGLE_WOOD, 5, 10);
        fireblock.setFlammable(Blocks.ACACIA_WOOD, 5, 10);
        fireblock.setFlammable(Blocks.CHERRY_WOOD, 5, 10);
        fireblock.setFlammable(Blocks.DARK_OAK_WOOD, 5, 10);
        fireblock.setFlammable(Blocks.MANGROVE_WOOD, 5, 10);
        fireblock.setFlammable(Blocks.MANGROVE_ROOTS, 5, 40);
        fireblock.setFlammable(Blocks.OAK_LEAVES, 30, 120);
        fireblock.setFlammable(Blocks.SPRUCE_LEAVES, 30, 120);
        fireblock.setFlammable(Blocks.BIRCH_LEAVES, 30, 120);
        fireblock.setFlammable(Blocks.JUNGLE_LEAVES, 30, 120);
        fireblock.setFlammable(Blocks.ACACIA_LEAVES, 30, 120);
        fireblock.setFlammable(Blocks.CHERRY_LEAVES, 30, 120);
        fireblock.setFlammable(Blocks.DARK_OAK_LEAVES, 30, 120);
        fireblock.setFlammable(Blocks.MANGROVE_LEAVES, 30, 120);
        fireblock.setFlammable(Blocks.BOOKSHELF, 30, 40);
        fireblock.setFlammable(Blocks.TNT, 15, 200);
        fireblock.setFlammable(Blocks.GRASS, 60, 200);
        fireblock.setFlammable(Blocks.FERN, 60, 200);
        fireblock.setFlammable(Blocks.DEAD_BUSH, 60, 200);
        fireblock.setFlammable(Blocks.SUNFLOWER, 60, 200);
        fireblock.setFlammable(Blocks.LILAC, 60, 200);
        fireblock.setFlammable(Blocks.ROSE_BUSH, 60, 200);
        fireblock.setFlammable(Blocks.PEONY, 60, 200);
        fireblock.setFlammable(Blocks.TALL_GRASS, 60, 200);
        fireblock.setFlammable(Blocks.LARGE_FERN, 60, 200);
        fireblock.setFlammable(Blocks.DANDELION, 60, 200);
        fireblock.setFlammable(Blocks.POPPY, 60, 200);
        fireblock.setFlammable(Blocks.BLUE_ORCHID, 60, 200);
        fireblock.setFlammable(Blocks.ALLIUM, 60, 200);
        fireblock.setFlammable(Blocks.AZURE_BLUET, 60, 200);
        fireblock.setFlammable(Blocks.RED_TULIP, 60, 200);
        fireblock.setFlammable(Blocks.ORANGE_TULIP, 60, 200);
        fireblock.setFlammable(Blocks.WHITE_TULIP, 60, 200);
        fireblock.setFlammable(Blocks.PINK_TULIP, 60, 200);
        fireblock.setFlammable(Blocks.OXEYE_DAISY, 60, 200);
        fireblock.setFlammable(Blocks.CORNFLOWER, 60, 200);
        fireblock.setFlammable(Blocks.LILY_OF_THE_VALLEY, 60, 200);
        fireblock.setFlammable(Blocks.TORCHFLOWER, 60, 200);
        fireblock.setFlammable(Blocks.PITCHER_PLANT, 60, 200);
        fireblock.setFlammable(Blocks.WITHER_ROSE, 60, 200);
        fireblock.setFlammable(Blocks.PINK_PETALS, 60, 200);
        fireblock.setFlammable(Blocks.WHITE_WOOL, 30, 120);
        fireblock.setFlammable(Blocks.ORANGE_WOOL, 30, 120);
        fireblock.setFlammable(Blocks.MAGENTA_WOOL, 30, 120);
        fireblock.setFlammable(Blocks.LIGHT_BLUE_WOOL, 30, 120);
        fireblock.setFlammable(Blocks.YELLOW_WOOL, 30, 120);
        fireblock.setFlammable(Blocks.LIME_WOOL, 30, 120);
        fireblock.setFlammable(Blocks.PINK_WOOL, 30, 120);
        fireblock.setFlammable(Blocks.GRAY_WOOL, 30, 120);
        fireblock.setFlammable(Blocks.LIGHT_GRAY_WOOL, 30, 120);
        fireblock.setFlammable(Blocks.CYAN_WOOL, 30, 120);
        fireblock.setFlammable(Blocks.PURPLE_WOOL, 30, 120);
        fireblock.setFlammable(Blocks.BLUE_WOOL, 30, 120);
        fireblock.setFlammable(Blocks.BROWN_WOOL, 30, 120);
        fireblock.setFlammable(Blocks.GREEN_WOOL, 30, 120);
        fireblock.setFlammable(Blocks.RED_WOOL, 30, 120);
        fireblock.setFlammable(Blocks.BLACK_WOOL, 30, 120);
        fireblock.setFlammable(Blocks.VINE, 15, 200);
        fireblock.setFlammable(Blocks.COAL_BLOCK, 5, 10);
        fireblock.setFlammable(Blocks.HAY_BLOCK, 60, 40);
        fireblock.setFlammable(Blocks.TARGET, 15, 40);
        fireblock.setFlammable(Blocks.WHITE_CARPET, 60, 40);
        fireblock.setFlammable(Blocks.ORANGE_CARPET, 60, 40);
        fireblock.setFlammable(Blocks.MAGENTA_CARPET, 60, 40);
        fireblock.setFlammable(Blocks.LIGHT_BLUE_CARPET, 60, 40);
        fireblock.setFlammable(Blocks.YELLOW_CARPET, 60, 40);
        fireblock.setFlammable(Blocks.LIME_CARPET, 60, 40);
        fireblock.setFlammable(Blocks.PINK_CARPET, 60, 40);
        fireblock.setFlammable(Blocks.GRAY_CARPET, 60, 40);
        fireblock.setFlammable(Blocks.LIGHT_GRAY_CARPET, 60, 40);
        fireblock.setFlammable(Blocks.CYAN_CARPET, 60, 40);
        fireblock.setFlammable(Blocks.PURPLE_CARPET, 60, 40);
        fireblock.setFlammable(Blocks.BLUE_CARPET, 60, 40);
        fireblock.setFlammable(Blocks.BROWN_CARPET, 60, 40);
        fireblock.setFlammable(Blocks.GREEN_CARPET, 60, 40);
        fireblock.setFlammable(Blocks.RED_CARPET, 60, 40);
        fireblock.setFlammable(Blocks.BLACK_CARPET, 60, 40);
        fireblock.setFlammable(Blocks.DRIED_KELP_BLOCK, 30, 120);
        fireblock.setFlammable(Blocks.BAMBOO, 60, 120);
        fireblock.setFlammable(Blocks.SCAFFOLDING, 60, 120);
        fireblock.setFlammable(Blocks.LECTERN, 30, 40);
        fireblock.setFlammable(Blocks.COMPOSTER, 5, 40);
        fireblock.setFlammable(Blocks.SWEET_BERRY_BUSH, 60, 200);
        fireblock.setFlammable(Blocks.BEEHIVE, 5, 40);
        fireblock.setFlammable(Blocks.BEE_NEST, 30, 40);
        fireblock.setFlammable(Blocks.AZALEA_LEAVES, 30, 120);
        fireblock.setFlammable(Blocks.FLOWERING_AZALEA_LEAVES, 30, 120);
        fireblock.setFlammable(Blocks.CAVE_VINES, 15, 120);
        fireblock.setFlammable(Blocks.CAVE_VINES_PLANT, 15, 120);
        fireblock.setFlammable(Blocks.SPORE_BLOSSOM, 60, 200);
        fireblock.setFlammable(Blocks.AZALEA, 30, 120);
        fireblock.setFlammable(Blocks.FLOWERING_AZALEA, 30, 120);
        fireblock.setFlammable(Blocks.BIG_DRIPLEAF, 60, 200);
        fireblock.setFlammable(Blocks.BIG_DRIPLEAF_STEM, 60, 200);
        fireblock.setFlammable(Blocks.SMALL_DRIPLEAF, 60, 200);
        fireblock.setFlammable(Blocks.HANGING_ROOTS, 30, 120);
        fireblock.setFlammable(Blocks.GLOW_LICHEN, 15, 200);
    }

    static {
        AGE = BlockStateProperties.AGE_15;
        NORTH = PipeBlock.NORTH;
        EAST = PipeBlock.EAST;
        SOUTH = PipeBlock.SOUTH;
        WEST = PipeBlock.WEST;
        UP = PipeBlock.UP;
        PROPERTY_BY_DIRECTION = (Map)PipeBlock.PROPERTY_BY_DIRECTION.entrySet().stream().filter((p_53467_) -> {
            return p_53467_.getKey() != Direction.DOWN;
        }).collect(Util.toMap());
        UP_AABB = Block.box(0.0, 15.0, 0.0, 16.0, 16.0, 16.0);
        WEST_AABB = Block.box(0.0, 0.0, 0.0, 1.0, 16.0, 16.0);
        EAST_AABB = Block.box(15.0, 0.0, 0.0, 16.0, 16.0, 16.0);
        NORTH_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 1.0);
        SOUTH_AABB = Block.box(0.0, 0.0, 15.0, 16.0, 16.0, 16.0);
    }
}