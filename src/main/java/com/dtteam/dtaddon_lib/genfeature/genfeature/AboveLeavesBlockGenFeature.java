package com.dtteam.dtaddon_lib.genfeature.genfeature;

import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.api.configuration.ConfigurationProperty;
import com.ferreusveritas.dynamictrees.api.network.MapSignal;
import com.ferreusveritas.dynamictrees.block.branch.BranchBlock;
import com.ferreusveritas.dynamictrees.systems.genfeature.context.PostGenerationContext;
import com.ferreusveritas.dynamictrees.systems.genfeature.context.PostGrowContext;
import com.ferreusveritas.dynamictrees.systems.nodemapper.FindEndsNode;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import com.ferreusveritas.dynamictrees.util.CoordUtils;
import com.ferreusveritas.dynamictrees.util.SafeChunkBounds;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeatureConfiguration;

import java.util.List;

public class AboveLeavesBlockGenFeature extends GenFeature {

    public static final ConfigurationProperty<Block> BLOCK = ConfigurationProperty.block("block");

    public AboveLeavesBlockGenFeature(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected void registerProperties() {
        this.register(QUANTITY, VERTICAL_SPREAD, RAY_DISTANCE, BLOCK, FRUITING_RADIUS);
    }

    @Override
    public GenFeatureConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(QUANTITY, 4)
                .with(VERTICAL_SPREAD, 60f)
                .with(RAY_DISTANCE, 5f)
                .with(BLOCK, Blocks.RED_MUSHROOM)
                .with(FRUITING_RADIUS, -1);
    }

    @Override
    public boolean shouldApply(Species species, GenFeatureConfiguration configuration) {
        return configuration.get(BLOCK) != Blocks.AIR;
    }

    @Override
    protected boolean postGenerate(GenFeatureConfiguration configuration, PostGenerationContext context) {
        if (!context.isWorldGen() || context.endPoints().isEmpty()) {
            return false;
        }

        final int quantity = configuration.get(QUANTITY);

        for (int i = 0; i < quantity; i++) {
            final BlockPos endPoint = context.endPoints().get(context.random().nextInt(context.endPoints().size()));
            this.addBlockAboveLeaves(configuration, context.level(), context.species(), context.pos(), endPoint, context.bounds());
        }

        return true;
    }

    @Override
    protected boolean postGrow(GenFeatureConfiguration configuration, PostGrowContext context) {
        final LevelAccessor level = context.level();
        final BlockPos rootPos = context.pos();
        final Species species = context.species();
        final int fruitingRadius = configuration.get(FRUITING_RADIUS);

        if (fruitingRadius < 0 || context.fertility() < 1) {
            return false;
        }

        final BlockState blockState = level.getBlockState(context.treePos());
        final BranchBlock branch = TreeHelper.getBranch(blockState);

        if (branch != null && branch.getRadius(blockState) >= fruitingRadius && context.natural()) {
            if (species.seasonalFruitProductionFactor(context.levelContext(), rootPos) > level.getRandom().nextFloat()) {
                final FindEndsNode endFinder = new FindEndsNode();
                TreeHelper.startAnalysisFromRoot(level, rootPos, new MapSignal(endFinder));
                final List<BlockPos> endPoints = endFinder.getEnds();
                final int qty = configuration.get(QUANTITY);

                if (!endPoints.isEmpty()) {
                    for (int i = 0; i < qty; i++) {
                        BlockPos endPoint = endPoints.get(level.getRandom().nextInt(endPoints.size()));
                        this.addBlockAboveLeaves(configuration, level, species, rootPos, endPoint, SafeChunkBounds.ANY);
                    }
                    return true;
                }
            }
        }

        return true;
    }

    protected void addBlockAboveLeaves(GenFeatureConfiguration configuration, LevelAccessor level, Species species, BlockPos rootPos, BlockPos branchPos, SafeChunkBounds safeBounds) {
        // Uses fruit ray trace method to grab a position above the tree's leaves.
        BlockPos vinePos = getRayTraceAbovePos(level, species, rootPos, branchPos, safeBounds);

        if (!safeBounds.inBounds(vinePos, true)) {
            return;
        }

        if (vinePos == BlockPos.ZERO) {
            return;
        }

        this.placeBlock(level, vinePos, configuration.get(BLOCK).defaultBlockState());
    }

    protected void placeBlock(LevelAccessor level, BlockPos vinePos, BlockState vinesState) {
        final BlockPos.MutableBlockPos mPos = new BlockPos.MutableBlockPos(vinePos.getX(), vinePos.getY(), vinePos.getZ());
        level.setBlock(mPos,vinesState,3);
    }

    public static BlockPos getRayTraceAbovePos(LevelAccessor level, Species species, BlockPos treePos, BlockPos branchPos, SafeChunkBounds safeBounds) {
        final HitResult result = CoordUtils.branchRayTrace(level, species, treePos, branchPos, 45, 60, 4 + level.getRandom().nextInt(3), safeBounds);

        if (result != null) {
            BlockPos hitPos = BlockPos.containing(result.getLocation());
            if (hitPos != BlockPos.ZERO) {
                do { // Run straight up until we hit a block that's non-compatible leaves.
                    hitPos = hitPos.above();
                } while (species.getFamily().isCompatibleGenericLeaves(species, level.getBlockState(hitPos), level, hitPos));

                if (level.isEmptyBlock(hitPos)) { // If that block is air then we have a winner.
                    return hitPos;
                }
            }
        }

        return BlockPos.ZERO;
    }

}
