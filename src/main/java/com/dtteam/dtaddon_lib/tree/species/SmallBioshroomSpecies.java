package com.dtteam.dtaddon_lib.tree.species;

import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import com.ferreusveritas.dynamictreesplus.block.mushroom.CapProperties;
import com.ferreusveritas.dynamictreesplus.systems.nodemapper.MushroomInflatorNode;
import com.ferreusveritas.dynamictreesplus.tree.HugeMushroomSpecies;
import com.dtteam.dtaddon_lib.systems.SmallBioshroomInflatorNode;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import oshi.util.tuples.Pair;

import java.util.List;

public class SmallBioshroomSpecies extends HugeMushroomSpecies {

    public static final TypedRegistry.EntryType<Species> TYPE = createDefaultMushroomType(SmallBioshroomSpecies::new);

    public SmallBioshroomSpecies(ResourceLocation name, Family family, CapProperties capProperties) {
        super(name, family, capProperties);
    }

    @Override
    public MushroomInflatorNode getNodeInflator(List<Pair<BlockPos, Integer>> capAges, int radius, BlockPos rootPos) {
        return new SmallBioshroomInflatorNode(this, capAges, radius, rootPos);
    }
}
