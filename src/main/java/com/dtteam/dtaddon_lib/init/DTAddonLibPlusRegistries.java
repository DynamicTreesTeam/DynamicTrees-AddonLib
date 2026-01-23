package com.dtteam.dtaddon_lib.init;

import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;
import com.dtteam.dtaddon_lib.genfeature.DTAddonLibGenFeaturesPlus;
import com.dtteam.dtaddon_lib.growthlogic.DTAddonLibThicknessLogicKits;
import com.dtteam.dtaddon_lib.mushroomshape.DTAddonLibMushroomShapeKits;
import com.dtteam.dtaddon_lib.tree.species.mushroom.DropLogsMushroomSpecies;
import com.dtteam.dtaddon_lib.tree.species.mushroom.SilkTouchMushroomSpecies;
import com.dtteam.dynamictrees.event.RegistryEvent;
import com.dtteam.dynamictrees.event.TypeRegistryEvent;
import com.dtteam.dynamictrees.systems.genfeature.GenFeature;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictreesplus.block.mushroom.CapProperties;
import com.dtteam.dynamictreesplus.systems.mushroomlogic.shapekits.MushroomShapeKit;
import com.dtteam.dynamictreesplus.systems.thicknesslogic.CactusThicknessLogic;
import net.neoforged.bus.api.SubscribeEvent;

public class DTAddonLibPlusRegistries {

    @SubscribeEvent
    public static void registerSpeciesTypes (final TypeRegistryEvent<Species> event) {
        if (event.isEntryOfType(Species.class)) {
            event.registerType(DynamicTreesAddonLib.location("silk_touch"), SilkTouchMushroomSpecies.TYPE);
            event.registerType(DynamicTreesAddonLib.location("drop_logs"), DropLogsMushroomSpecies.TYPE);
        }
    }

    @SubscribeEvent
    public static void registerFamilyTypes (final TypeRegistryEvent<Family> event) {
        if (event.isEntryOfType(Family.class)){
        }
    }

    @SubscribeEvent
    public static void registerCapPropertiesTypes (final TypeRegistryEvent<CapProperties> event){
        if (event.isEntryOfType(CapProperties.class)){
        }
    }

    @SubscribeEvent
    public static void registerCactusThicknessLogic(final RegistryEvent<CactusThicknessLogic> event) {
        if (event.isEntryOfType(CactusThicknessLogic.class)) {
            DTAddonLibThicknessLogicKits.register(event.getRegistry());
        }
    }

    @SubscribeEvent
    public static void onMushroomShapeKitRegistry(final RegistryEvent<MushroomShapeKit> event) {
        if (event.isEntryOfType(MushroomShapeKit.class)) {
            DTAddonLibMushroomShapeKits.register(event.getRegistry());
        }
    }

    @SubscribeEvent
    public static void onGenFeatureRegistry (final RegistryEvent<GenFeature> event) {
        if (event.isEntryOfType(GenFeature.class)) {
            DTAddonLibGenFeaturesPlus.register(event.getRegistry());
        }
    }

}
