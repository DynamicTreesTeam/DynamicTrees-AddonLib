package com.dtteam.dtaddon_lib.init;

import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;
import com.dtteam.dtaddon_lib.blocks.leaves.*;
import com.dtteam.dtaddon_lib.blocks.soil.LavaSoilProperties;
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
import com.ferreusveritas.dynamictrees.block.rooty.SoilProperties;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import com.ferreusveritas.dynamictrees.systems.pod.Pod;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import com.ferreusveritas.dynamictrees.util.CommonVoxelShapes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class DTAddonLibRegistries {

    public static final TagKey<Block> CAN_BE_SPILED = BlockTags.create(DynamicTreesAddonLib.location("can_be_spiled"));
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DynamicTreesAddonLib.MOD_ID);
    public static final RegistryObject<SoundEvent> FRUIT_BONK = registerSound("falling_fruit.bonk");

    public static final VoxelShape DRAGON_FRUIT_CACTUS_SAPLING_SHAPE = Shapes.box(
            0.375f, 0.0f, 0.375f,
            0.625f, 0.5f, 0.625f);

    public static final VoxelShape BANANA_SAPLING_SHAPE = Shapes.box(
            0.375f, 0.0f, 0.375f,
            0.625f, 0.9375f, 0.625f);

    public static void setup() {
        DTAddonLibCapShapeRegistries.register();
        ShapeRegitries();
    }

    public static RegistryObject<SoundEvent> registerSound (String name){
        return SOUNDS.register(name, ()-> SoundEvent.createVariableRangeEvent(DynamicTreesAddonLib.location(name)));
    }

    public static void ShapeRegitries() {
        CommonVoxelShapes.SHAPES.put(DynamicTreesAddonLib.location("dragon_fruit_cactus").toString(), DRAGON_FRUIT_CACTUS_SAPLING_SHAPE);
        CommonVoxelShapes.SHAPES.put(DynamicTreesAddonLib.location("banana_sapling").toString(), BANANA_SAPLING_SHAPE);
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
        event.registerType(DynamicTreesAddonLib.location("dragon_fruit"), DragonFruitLeavesProperties.TYPE);
    }

    @SubscribeEvent
    public static void registerSpeciesTypes (final TypeRegistryEvent<Species> event) {
        event.registerType(DynamicTreesAddonLib.location("poplar"), PoplarSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("twiglet"), TwigletSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("generates_underwater"), GenUnderwaterSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("generates_on_extra_soil"), GenOnExtraSoilSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("place_alternate"), PlaceAlternateSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("grow_on_lava"), GrowOnLavaSpecies.TYPE);
        event.registerType(DynamicTreesAddonLib.location("fruit_log"), FruitLogSpecies.TYPE);
    }

    @SubscribeEvent
    public static void registerFamilyTypes (final TypeRegistryEvent<Family> event) {
        event.registerType(DynamicTreesAddonLib.location("alt_log"), AltLogFamily.TYPE);
        event.registerType(DynamicTreesAddonLib.location("diagonal_palm"), DiagonalPalmFamily.TYPE);
        event.registerType(DynamicTreesAddonLib.location("stripped_transition_log"), TransitionLogFamily.TYPE_STRIPPED);
        event.registerType(DynamicTreesAddonLib.location("base_transition_log"), TransitionLogFamily.TYPE_BASE);
    }

    @SubscribeEvent
    public static void registerFruitType(final TypeRegistryEvent<Fruit> event) {
        event.registerType(DynamicTreesAddonLib.location("offset_down"), OffsetFruit.TYPE);
        event.registerType(DynamicTreesAddonLib.location("falling_fruit"), FallingFruit.TYPE);
        event.registerType(DynamicTreesAddonLib.location("cobweb"), CobwebFruit.TYPE);
    }

    @SubscribeEvent
    public static void registerPodType(final TypeRegistryEvent<Pod> event) {
        event.registerType(DynamicTreesAddonLib.location("palm"), PalmPod.TYPE);
        event.registerType(DynamicTreesAddonLib.location("falling_palm"), FallingPalmPod.TYPE);
    }

    @SubscribeEvent
    public static void registerSoilPropertiesType(final TypeRegistryEvent<SoilProperties> event) {
        event.registerType(DynamicTreesAddonLib.location("lava"), LavaSoilProperties.TYPE);
    }

}
