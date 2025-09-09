package com.dtteam.dtaddon_lib.growthlogic;

import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;
import com.dtteam.dtaddon_lib.growthlogic.thicknesslogic.PricklyPearCactusThicknessLogic;
import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictreesplus.systems.thicknesslogic.CactusThicknessLogic;

public class DTAddonLibThicknessLogicKits {

    public static final CactusThicknessLogic PRICKLY_PEAR = new PricklyPearCactusThicknessLogic(DynamicTreesAddonLib.location("prickly_pear"));

    public static void register(final Registry<CactusThicknessLogic> registry) {
        registry.registerAll(PRICKLY_PEAR);
    }

}