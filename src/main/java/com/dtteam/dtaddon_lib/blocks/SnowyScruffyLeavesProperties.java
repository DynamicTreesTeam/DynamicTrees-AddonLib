package com.dtteam.dtaddon_lib.blocks;

import com.dtteam.dynamictrees.data.Generator;
import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.leaves.DynamicLeavesBlock;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.block.leaves.ScruffyLeavesProperties;
import com.dtteam.dynamictrees.data.provider.DTBlockStateProvider;
import com.dtteam.dynamictrees.api.lazyvalue.MutableLazyValue;
import com.dtteam.dtaddon_lib.data.SnowyLeavesStateGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class SnowyScruffyLeavesProperties extends ScruffyLeavesProperties {

    public static final TypedRegistry.EntryType<LeavesProperties> TYPE = TypedRegistry.newType(SnowyScruffyLeavesProperties::new);

    public SnowyScruffyLeavesProperties(ResourceLocation registryName) {
        super(registryName);
    }

    protected final MutableLazyValue<Generator<DTBlockStateProvider, LeavesProperties>> stateGenerator =
            MutableLazyValue.supplied(SnowyLeavesStateGenerator::new);

    @Override
    public void generateStateData(DTBlockStateProvider provider) {
        // Generate leaves block state and model.
        this.stateGenerator.get().generate(provider, this);
    }

    private float scruffyLeafChance = 0.66f;
    private int scruffyMaxHydro = 1;

    @Override
    public void setLeafChance (float leafChance){
        this.scruffyLeafChance = leafChance;
    }
    @Override
    public void setMaxHydro (int maxHydro) {
        this.scruffyMaxHydro = maxHydro;
    }

    @Override
    public BlockState getDynamicLeavesState(int hydro) {
        BlockState state = dynamicLeavesBlockHydroStates[Mth.clamp(hydro, 0, LeavesProperties.maxHydro)];
        if (state != null && state.hasProperty(SnowyLeavesBlock.SNOWY))
            state = state.setValue(SnowyLeavesBlock.SNOWY, false);
        return Optional.ofNullable(state)
                .orElse(Blocks.AIR.defaultBlockState());
    }

    @Override
    protected DynamicLeavesBlock createDynamicLeaves(BlockBehaviour.Properties properties) {
        return new SnowyLeavesBlock(this, properties, scruffyLeafChance, scruffyMaxHydro);
    }
}
