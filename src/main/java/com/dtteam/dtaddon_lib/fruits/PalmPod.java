package com.dtteam.dtaddon_lib.fruits;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.branch.BranchBlock;
import com.dtteam.dynamictrees.block.pod.Pod;
import com.dtteam.dynamictrees.block.pod.PodBlock;
import com.dtteam.dynamictrees.tree.TreeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;

// From DTPHC2
public class PalmPod extends Pod {

    public static final TypedRegistry.EntryType<Pod> TYPE = TypedRegistry.newType(PalmPod::new);

    public PalmPod(Identifier registryName) {
        super(registryName);
    }

    protected PodBlock createBlock(Identifier id, Block.Properties properties) {
        return new PodBlock(id, properties, this){
            @Override public boolean isSupported(LevelReader world, BlockPos pos, BlockState state) {
                final BlockState branchState = world.getBlockState(pos.relative(state.getValue(HorizontalDirectionalBlock.FACING)));
                final BranchBlock branch = TreeHelper.getBranch(branchState);
                return branch != null && branch.getRadius(branchState) >= 2;
            }
        };
    }

}
