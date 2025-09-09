package com.dtteam.dtaddon_lib.tree.species;

import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.block.DynamicSaplingBlock;
import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public class GigaSpruceSpecies extends Species {

    public static final TypedRegistry.EntryType<Species> TYPE = createDefaultType(GigaSpruceSpecies::new);

    public GigaSpruceSpecies(ResourceLocation resourceLocation, Family family, LeavesProperties leavesProperties) {
        super(resourceLocation, family, leavesProperties);
    }

    //This species is just to fix a particular bug in DT. Will not be needed on 1.20.
    @Override
    public Optional<DynamicSaplingBlock> getSapling() {
        if (isMegaSpecies()){
            if (getPreMegaSpecies().isMegaSpecies()){
                return getPreMegaSpecies().getPreMegaSpecies().getSapling();
            }
            return getPreMegaSpecies().getSapling();
        }
        return super.getSapling();
    }
}
