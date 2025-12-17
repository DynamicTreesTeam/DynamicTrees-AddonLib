package com.dtteam.dtaddon_lib.data;

import com.dtteam.dynamictrees.data.DTDataProvider;
import com.dtteam.dynamictrees.data.generator.LeavesStateGenerator;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.data.provider.DTBlockStateProvider;
import com.dtteam.dtaddon_lib.blocks.SnowyLeavesBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;

// From DTAether
public class SnowyLeavesStateGenerator extends LeavesStateGenerator {

    @Override
    public void generate(DTDataProvider.BlockState provider, LeavesProperties input, Dependencies dependencies) {
        provider.getVariantBuilder(dependencies.get(LEAVES)).partialState()
                .with(SnowyLeavesBlock.SNOWY, false)
                .addModels(new ConfiguredModel(
                        provider.models().getExistingFile(
                                input.getModelPath(LeavesProperties.LEAVES).orElse(
                                        provider.block(BuiltInRegistries.BLOCK.getKey(dependencies.get(PRIMITIVE_LEAVES)))
                                )
                        )
                )).partialState()
                .with(SnowyLeavesBlock.SNOWY, true)
                .addModels(new ConfiguredModel(
                        provider.models().getExistingFile(
                                input.getModelPath("snowy_leaves").orElse(
                                        provider.block(BuiltInRegistries.BLOCK.getKey(dependencies.get(PRIMITIVE_LEAVES)))
                                )
                        )
                ));
    }

}
