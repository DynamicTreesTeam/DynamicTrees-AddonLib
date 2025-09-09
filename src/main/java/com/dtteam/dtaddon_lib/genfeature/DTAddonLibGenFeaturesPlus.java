package com.dtteam.dtaddon_lib.genfeature;

import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;
import com.dtteam.dtaddon_lib.genfeature.genfeature.MushroomVinesGenFeature;
import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;

public class DTAddonLibGenFeaturesPlus {

    public static final GenFeature MUSHROOM_VINES = new MushroomVinesGenFeature(DynamicTreesAddonLib.location("mushroom_vines"));

    public static void register(final Registry<GenFeature> registry) {
        registry.register(MUSHROOM_VINES);
    }

}
