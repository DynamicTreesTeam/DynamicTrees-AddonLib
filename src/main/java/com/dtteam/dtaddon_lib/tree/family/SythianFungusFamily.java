package com.dtteam.dtaddon_lib.tree.family;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.family.NetherFungusFamily;
import com.dtteam.dynamictrees.api.voxmap.BlockPosBounds;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

// From DTBYG
public class SythianFungusFamily extends NetherFungusFamily {

    public static final TypedRegistry.EntryType<Family> TYPE = TypedRegistry.newType(SythianFungusFamily::new);

    public SythianFungusFamily(ResourceLocation name) {
        super(name);
    }

    public BlockPosBounds expandLeavesBlockBounds(BlockPosBounds bounds) {
        return bounds.expand(2).shrink(Direction.DOWN, 1);
    }

}
