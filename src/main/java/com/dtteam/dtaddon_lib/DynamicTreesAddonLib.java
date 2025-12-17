package com.dtteam.dtaddon_lib;

import com.dtteam.dynamictrees.block.fruit.Fruit;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.block.pod.Pod;
import com.dtteam.dynamictrees.block.soil.SoilProperties;
import com.dtteam.dynamictrees.data.GatherDataHelper;
import com.dtteam.dynamictrees.registry.NeoForgeRegistryHandler;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictrees.api.registry.RegistryHandler;
//import com.dtteam.dynamictreesplus.block.mushroom.CapProperties;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(DynamicTreesAddonLib.MOD_ID)
public final class DynamicTreesAddonLib {

    public static final String MOD_ID = "dtaddon_lib";

    public DynamicTreesAddonLib(IEventBus eventBus, ModContainer container) {
        eventBus.addListener(this::gatherData);

        NeoForgeRegistryHandler.setup(MOD_ID, eventBus);
    }

    private void gatherData(final GatherDataEvent event) {
        GatherDataHelper.gatherAllData(MOD_ID, event,
                SoilProperties.REGISTRY,
                Family.REGISTRY,
                Species.REGISTRY,
                LeavesProperties.REGISTRY,
                Fruit.REGISTRY,
                Pod.REGISTRY
                //,CapProperties.REGISTRY
        );
    }

}
