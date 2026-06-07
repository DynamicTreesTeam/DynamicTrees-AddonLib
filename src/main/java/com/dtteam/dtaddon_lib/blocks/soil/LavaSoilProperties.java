package com.dtteam.dtaddon_lib.blocks.soil;

import com.dtteam.dtaddon_lib.blocks.SimpleLavaloggedBlock;
import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.branch.BranchBlock;
import com.dtteam.dynamictrees.block.soil.SoilBlock;
import com.dtteam.dynamictrees.block.soil.SoilProperties;
import com.dtteam.dynamictrees.data.generator.WaterRootSoilGenerator;
import com.dtteam.dynamictrees.platform.Services;
import com.dtteam.dynamictrees.platform.services.IConfigHelper;
import com.dtteam.dynamictrees.tree.TreeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class LavaSoilProperties extends SoilProperties {

    public static final TypedRegistry.EntryType<SoilProperties> TYPE = TypedRegistry.newType(LavaSoilProperties::new);

    public LavaSoilProperties(Identifier registryName) {
        super(null, registryName);
    }

    @Override
    protected SoilBlock createBlock(Block.Properties blockProperties) {
        return new RootyLavaBlock(this.getRegistryName(), this, blockProperties);
    }

    @Override
    public BlockBehaviour.Properties getDefaultBlockProperties() {
        return Block.Properties.ofFullCopy(Blocks.LAVA);
    }

    public static class RootyLavaBlock extends SoilBlock implements SimpleLavaloggedBlock {

        protected static final AABB LAVA_ROOTS_AABB = new AABB(0.1, 0.0, 0.1, 0.9, 1.0, 0.9);

        public RootyLavaBlock(Identifier id, SoilProperties properties, Properties blockProperties) {
            super(id, properties, blockProperties);
            registerDefaultState(defaultBlockState().setValue(SimpleLavaloggedBlock.LAVALOGGED, true));
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
            super.createBlockStateDefinition(builder.add(LAVALOGGED));
        }

        @Override
        public int getRadiusForConnection(BlockState state, BlockGetter level, BlockPos pos, BranchBlock from, Direction side, int fromRadius) {
            return 1;
        }

        @Override
        protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
            BlockState upState = level.getBlockState(pos.above());
            BranchBlock branch = TreeHelper.getBranch(upState);
            if (branch == null) return ItemStack.EMPTY;
            return branch.getFamily().getBranchItem().map(ItemStack::new).orElse(ItemStack.EMPTY);
        }

        @Override
        public float getHardness(BlockState state, BlockGetter level, BlockPos pos) {
            return (float) (0.5D * Services.CONFIG.getDoubleConfig(IConfigHelper.ROOTY_BLOCK_HARDNESS_MULTIPLIER));
        }

        @Override
        public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
            return Shapes.create(LAVA_ROOTS_AABB);
        }

        @Override
        public VoxelShape getBlockSupportShape(BlockState state, BlockGetter reader, BlockPos pos) {
            return Shapes.empty();
        }

        @Override
        public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
            return false;
        }

        @Override
        public FluidState getFluidState(BlockState state) {
            return state.getValue(LAVALOGGED) ? Fluids.LAVA.getSource(false) : super.getFluidState(state);
        }

        @NotNull
        @Override
        protected BlockState updateShape(BlockState pState, LevelReader pLevel, ScheduledTickAccess pTicks, BlockPos pCurrentPos, Direction pDirection, BlockPos pNeighborPos, BlockState pNeighborState, RandomSource pRandom) {
            if (pState.getValue(LAVALOGGED)) {
                pTicks.scheduleTick(pCurrentPos, Fluids.LAVA, Fluids.LAVA.getTickDelay(pLevel));
            }
            return super.updateShape(pState, pLevel, pTicks, pCurrentPos, pDirection, pNeighborPos, pNeighborState, pRandom);
        }

        @Override
        public BlockState getDecayBlockState(BlockState state, BlockGetter level, BlockPos pos) {
            if (state.hasProperty(LAVALOGGED) && !state.getValue(LAVALOGGED)) {
                return Blocks.AIR.defaultBlockState();
            }
            return super.getDecayBlockState(state, level, pos);
        }

        ///////////////////////////////////////////
        // RENDERING
        ///////////////////////////////////////////

        @Override
        public boolean getColorFromBark() {
            return true;
        }

        @Override
        public boolean fallWithTree(BlockState state, Level level, BlockPos pos, boolean hasRoots) {
            level.setBlockAndUpdate(pos, getDecayBlockState(state, level, pos));
            return true;
        }

        @Override
        public Optional<SoundEvent> getPickupSound(BlockState state) {
            return getPickupSound();
        }

        @Override
        public Optional<SoundEvent> getPickupSound() {
            return Fluids.LAVA.getPickupSound();
        }
    }
}

