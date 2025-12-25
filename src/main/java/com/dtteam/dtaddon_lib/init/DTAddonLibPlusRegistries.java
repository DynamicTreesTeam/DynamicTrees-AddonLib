package com.dtteam.dtaddon_lib.init;

import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;
import com.dtteam.dtaddon_lib.blocks.*;
import com.dtteam.dtaddon_lib.genfeature.DTAddonLibGenFeaturesPlus;
import com.dtteam.dtaddon_lib.growthlogic.DTAddonLibThicknessLogicKits;
import com.dtteam.dtaddon_lib.mushroomshape.DTAddonLibMushroomShapeKits;
import com.dtteam.dtaddon_lib.tree.family.*;
import com.dtteam.dtaddon_lib.tree.species.*;
import com.ferreusveritas.dynamictrees.api.registry.RegistryEvent;
import com.ferreusveritas.dynamictrees.api.registry.TypeRegistryEvent;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import com.ferreusveritas.dynamictreesplus.block.mushroom.CapProperties;
import com.ferreusveritas.dynamictreesplus.systems.mushroomlogic.shapekits.MushroomShapeKit;
import com.ferreusveritas.dynamictreesplus.systems.thicknesslogic.CactusThicknessLogic;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DTAddonLibPlusRegistries {

    @SubscribeEvent
    public static void registerSpeciesTypes (final TypeRegistryEvent<Species> event) {
        event.registerType(DynamicTreesAddonLib.location("silk_touch"), SilkTouchMushroomSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("drop_logs"), DropLogsMushroomSpecies.TYPE);
    }

    @SubscribeEvent
    public static void registerFamilyTypes (final TypeRegistryEvent<Family> event) {
    }

    @SubscribeEvent
    public static void registerCapPropertiesTypes (final TypeRegistryEvent<CapProperties> event){
    }

    @SubscribeEvent
    public static void registerCactusThicknessLogic(final RegistryEvent<CactusThicknessLogic> event) {
        DTAddonLibThicknessLogicKits.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void onMushroomShapeKitRegistry(final RegistryEvent<MushroomShapeKit> event) {
        DTAddonLibMushroomShapeKits.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void onGenFeatureRegistry (final RegistryEvent<GenFeature> event) {
        DTAddonLibGenFeaturesPlus.register(event.getRegistry());
    }

}
