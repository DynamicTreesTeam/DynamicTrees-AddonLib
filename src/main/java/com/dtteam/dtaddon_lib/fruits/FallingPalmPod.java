package com.dtteam.dtaddon_lib.fruits;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.pod.Pod;
import com.dtteam.dynamictrees.block.pod.PodBlock;
import com.dtteam.dtaddon_lib.blocks.FallingPodBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

// From DTPHC2
public class FallingPalmPod extends Pod {

    public static final TypedRegistry.EntryType<Pod> TYPE = TypedRegistry.newType(FallingPalmPod::new);

    public FallingPalmPod(ResourceLocation registryName) {
        super(registryName);
    }

    protected PodBlock createBlock(Block.Properties properties) {
        return new FallingPodBlock(properties, this);
    }

}
