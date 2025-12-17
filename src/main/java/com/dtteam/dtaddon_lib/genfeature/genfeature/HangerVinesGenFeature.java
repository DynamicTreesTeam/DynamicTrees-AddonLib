package com.dtteam.dtaddon_lib.genfeature.genfeature;

import com.dtteam.dynamictrees.systems.genfeature.GenFeatureConfiguration;
import com.dtteam.dynamictrees.systems.genfeature.VinesGenFeature;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictrees.utility.CoordUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;

// From DTAether
public class HangerVinesGenFeature extends VinesGenFeature {

    public HangerVinesGenFeature(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    public GenFeatureConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(VINE_TYPE, VineType.CEILING);
    }

    @Override
    public boolean shouldApply(Species species, GenFeatureConfiguration configuration) {
        return configuration.get(VINE_TYPE) == VineType.CEILING;
    }

    protected void addVerticalVines(GenFeatureConfiguration configuration, LevelAccessor level, Species species, BlockPos rootPos, BlockPos branchPos, boolean worldgen) {
        // Uses fruit ray trace method to grab a position under the tree's leaves.
        BlockPos vinePos = CoordUtils.getRayTraceFruitPos(level, species, rootPos, branchPos, worldgen);

        if (vinePos == BlockPos.ZERO) {
            return;
        }

        this.placeVines(level, vinePos, configuration.get(BLOCK).defaultBlockState(),
                configuration.get(MAX_LENGTH),
                configuration.get(BLOCK).defaultBlockState(),
                VineType.CEILING, worldgen);
    }

}
