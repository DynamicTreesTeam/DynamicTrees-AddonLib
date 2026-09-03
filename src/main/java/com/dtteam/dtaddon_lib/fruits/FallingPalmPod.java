package com.dtteam.dtaddon_lib.fruits;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.pod.Pod;
import com.dtteam.dynamictrees.block.pod.PodBlock;
import com.dtteam.dtaddon_lib.blocks.fruit.FallingPodBlock;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

// From DTPHC2
public class FallingPalmPod extends Pod {

    public static final TypedRegistry.EntryType<Pod> TYPE = TypedRegistry.newType(FallingPalmPod::new);

    public FallingPalmPod(Identifier registryName) {
        super(registryName);
    }

    protected PodBlock createBlock(Identifier id, Block.Properties properties) {
        return new FallingPodBlock(id, properties, this);
    }

}
