package com.dtteam.dtaddon_lib.tree.species;

import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import com.ferreusveritas.dynamictrees.worldgen.GenerationContext;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

/**
 * This species will place another alternative species
 * if the soil is acceptable for the alternative species.
 *
 * @author Max Hyper
 */
public class PlaceAlternateSpecies extends Species {

    public static final TypedRegistry.EntryType<Species> TYPE = createDefaultType(PlaceAlternateSpecies::new);

    private Species altSpecies = Species.NULL_SPECIES;

    public void setAltSpecies(Species altSpecies) {
        if (altSpecies != this)
            this.altSpecies = altSpecies;
    }

    public PlaceAlternateSpecies(ResourceLocation name, Family family, LeavesProperties leavesProperties) {
        super(name, family, leavesProperties);
    }

    @Override
    public boolean generate(GenerationContext context) {
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
