package com.dtteam.dtaddon_lib.init;

import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;
import com.dtteam.dtaddon_lib.blocks.*;
import com.dtteam.dtaddon_lib.cell.DTAddonLibCellKits;
import com.dtteam.dtaddon_lib.fruits.*;
import com.dtteam.dtaddon_lib.genfeature.DTAddonLibGenFeatures;
import com.dtteam.dtaddon_lib.growthlogic.DTAddonLibGrowthLogicKits;
import com.dtteam.dtaddon_lib.tree.family.*;
import com.dtteam.dtaddon_lib.tree.species.*;
import com.ferreusveritas.dynamictrees.api.cell.CellKit;
import com.ferreusveritas.dynamictrees.api.registry.RegistryEvent;
import com.ferreusveritas.dynamictrees.api.registry.TypeRegistryEvent;
import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class DTAddonLibRegistries {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DynamicTreesAddonLib.MOD_ID);
    public static final RegistryObject<SoundEvent> FRUIT_BONK = registerSound("falling_fruit.bonk");

    public static void setup() {
    }

    public static RegistryObject<SoundEvent> registerSound (String name){
        return SOUNDS.register(name, ()-> SoundEvent.createVariableRangeEvent(DynamicTreesAddonLib.location(name)));
    }

    @SubscribeEvent
    public static void onGenFeatureRegistry (final RegistryEvent<GenFeature> event) {
        DTAddonLibGenFeatures.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void onCellKitRegistry (final RegistryEvent<CellKit> event) {
        DTAddonLibCellKits.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void onGrowthLogicKitRegistry (final RegistryEvent<GrowthLogicKit> event) {
        DTAddonLibGrowthLogicKits.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerLeavesPropertiesTypes(TypeRegistryEvent<LeavesProperties> event) {
        event.registerType(DynamicTreesAddonLib.location("cobweb"), CobwebLeavesProperties.TYPE);
        event.registerType(DynamicTreesAddonLib.location("particle"), ParticleLeavesProperties.TYPE);
        event.registerType(DynamicTreesAddonLib.location("scruffy_particle"), ScruffyParticleLeavesProperties.TYPE);
        event.registerType(DynamicTreesAddonLib.location("scruffy_snowy"), SnowyScruffyLeavesProperties.TYPE);
    }

    @SubscribeEvent
    public static void registerSpeciesTypes (final TypeRegistryEvent<Species> event) {
        event.registerType(DynamicTreesAddonLib.location("poplar"), PoplarSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("twiglet"), TwigletSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("generates_underwater"), GenUnderwaterSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("generates_on_extra_soil"), GenOnExtraSoilSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("mangrove"), MangroveSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("lament"), LamentSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("cypress"), CypressSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("generates_on_stone"), GenOnStoneSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("bush"), Bush.TYPE);
        event.registerType(DynamicTreesAddonLib.location("giga_spruce"), GigaSpruceSpecies.TYPE);
    }

    @SubscribeEvent
    public static void registerFamilyTypes (final TypeRegistryEvent<Family> event) {
        event.registerType(DynamicTreesAddonLib.location("imbued_log"), ImbuedLogFamily.TYPE);
        event.registerType(DynamicTreesAddonLib.location("diagonal_palm"), DiagonalPalmFamily.TYPE);
        event.registerType(DynamicTreesAddonLib.location("sythian_fungus"), SythianFungusFamily.TYPE);
        event.registerType(DynamicTreesAddonLib.location("stripped_transition_log"), TransitionLogFamily.TYPE_STRIPPED);
        event.registerType(DynamicTreesAddonLib.location("base_transition_log"), TransitionLogFamily.TYPE_BASE);
    }

    @SubscribeEvent
    public static void registerFruitType(final TypeRegistryEvent<Fruit> event) {
        event.registerType(DynamicTreesAddonLib.location("offset_down"), OffsetFruit.TYPE);
        event.registerType(DynamicTreesAddonLib.location("falling_fruit"), FallingFruit.TYPE);
        event.registerType(DynamicTreesAddonLib.location("cobweb"), CobwebFruit.TYPE);
    }

}
