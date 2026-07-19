package com.dtteam.dtaddon_lib.tree.family;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.branch.BasicBranchBlock;
import com.dtteam.dynamictrees.block.branch.BranchBlock;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dtaddon_lib.blocks.branch.TransitionLogBranchBlock;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockBehaviour;

// From DTRU
public class TransitionLogFamily extends Family {

    public static final TypedRegistry.EntryType<Family> TYPE_STRIPPED = TypedRegistry.newType(res -> new TransitionLogFamily(res, true, false));
    public static final TypedRegistry.EntryType<Family> TYPE_BASE = TypedRegistry.newType(res -> new TransitionLogFamily(res, false, true));

    public TransitionLogFamily(Identifier name, boolean stripped, boolean base) {
        super(name);
        transitionOnStripped = stripped;
        transitionOnBase = base;
    }

    boolean transitionOnStripped;
    boolean transitionOnBase;

    @Override
    protected BranchBlock createBranch(Identifier name, BlockBehaviour.Properties properties) {
        final BasicBranchBlock branch = new TransitionLogBranchBlock(name, properties, transitionOnStripped, transitionOnBase);
        if (this.isFireProof()) branch.setFireSpreadSpeed(0).setFlammability(0);
        return branch;
    }

}
