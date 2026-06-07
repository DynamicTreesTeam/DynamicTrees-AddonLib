package com.dtteam.dtaddon_lib.tree.species;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictrees.worldgen.DynamicTreeGenerationContext;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

/**
 * This species will place another alternative species,
 * if the soil is acceptable for the alternative species.
 *
 * @author Max Hyper
 */
public class PlaceAlternateLavaSpecies extends Species {

    public static final TypedRegistry.EntryType<Species> TYPE = createDefaultType(PlaceAlternateLavaSpecies::new);

    private Species altSpecies = Species.NULL_SPECIES;

    public void setAltSpecies(Species altSpecies) {
        if (altSpecies != this)
            this.altSpecies = altSpecies;
    }

    public PlaceAlternateLavaSpecies(Identifier name, Family family, LeavesProperties leavesProperties) {
        super(name, family, leavesProperties);
    }

    @Override
    public boolean generate(DynamicTreeGenerationContext context) {
        LevelAccessor level = context.level();
        BlockPos rootPos = context.rootPos();
        if (altSpecies.isAcceptableSoilForWorldgen(level, rootPos, level.getBlockState(rootPos)))
            return altSpecies.generate(context);
        return super.generate(context);
    }

    @Override
    public boolean isAcceptableSoilForWorldgen(LevelAccessor level, BlockPos pos, BlockState soilBlockState) {
        return super.isAcceptableSoilForWorldgen(level, pos, soilBlockState) || altSpecies.isAcceptableSoilForWorldgen(level, pos, soilBlockState);
    }
    @Override
    public boolean isAcceptableSoil(LevelReader level, BlockPos pos, BlockState soilBlockState) {
        return super.isAcceptableSoil(level, pos, soilBlockState) || altSpecies.isAcceptableSoil(level, pos, soilBlockState);
    }

    @Override
    protected boolean transitionToTree(Level level, BlockPos pos, Family family) {
        if (altSpecies.isAcceptableSoil(level, pos.below(), level.getBlockState(pos.below())))
            return altSpecies.transitionToTree(level, pos);
        return super.transitionToTree(level, pos, family);
    }
}
