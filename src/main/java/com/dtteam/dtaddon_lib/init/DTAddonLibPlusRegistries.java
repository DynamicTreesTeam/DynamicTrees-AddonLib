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
        event.registerType(DynamicTreesAddonLib.location("woody_mushroom"), WoodyHugeMushroomSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("glowshroom"), GlowshroomSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("prickly_pear_cactus"), PricklyPearCactusSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("bioshroom"), BioshroomSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("small_bioshroom"), SmallBioshroomSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("drop_logs"), DropLogsMushroomSpecies.TYPE);
    }

    @SubscribeEvent
    public static void registerFamilyTypes (final TypeRegistryEvent<Family> event) {
        event.registerType(DynamicTreesAddonLib.location("warty_mushroom"), WartyMushroomFamily.TYPE);
    }

    @SubscribeEvent
    public static void registerCapPropertiesTypes (final TypeRegistryEvent<CapProperties> event){
        event.registerType(DynamicTreesAddonLib.location("warty_cap"), WartyCapProperties.TYPE);
        event.registerType(DynamicTreesAddonLib.location("weeping_milkcap_cap"), WeepingMilkcapCapProperties.TYPE);
        event.registerType(DynamicTreesAddonLib.location("bioshroom_cap"), BioshroomCapProperties.TYPE);
        event.registerType(DynamicTreesAddonLib.location("pink_bioshroom_cap"), PinkBioshroomCapProperties.TYPE);
        event.registerType(DynamicTreesAddonLib.location("yellow_bioshroom_cap"), YellowBioshroomCapProperties.TYPE);
        event.registerType(DynamicTreesAddonLib.location("green_bioshroom_cap"), GreenBioshroomCapProperties.TYPE);
        event.registerType(DynamicTreesAddonLib.location("embur_gel_cap"), EmburGelCapProperties.TYPE);
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
