package com.dtteam.dtaddon_lib.resources;

import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;
import com.dtteam.dtaddon_lib.tree.family.*;
import com.dtteam.dtaddon_lib.tree.species.*;
import com.dtteam.dynamictrees.deserialization.PropertyAppliers;
import com.dtteam.dynamictrees.event.ApplierRegistryEvent;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import com.google.gson.JsonElement;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = DynamicTreesAddonLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class RegisterJSONAppliers {

    @SubscribeEvent
    public static void registerAppliersSpecies(final ApplierRegistryEvent.Reload<Species, JsonElement> event) {
        registerSpeciesAppliers(event.getAppliers());
    }

    @SubscribeEvent
    public static void registerAppliersFamily(final ApplierRegistryEvent.Reload<Family, JsonElement> event) {
        registerFamilyAppliers(event.getAppliers());
    }

    public static void registerSpeciesAppliers(PropertyAppliers<Species, JsonElement> appliers) {
        appliers.register("alternative_species", PlaceAlternateLavaSpecies.class, Species.class, PlaceAlternateLavaSpecies::setAltSpecies)
                .register("extra_soil_for_worldgen", GenOnExtraSoilSpecies.class, Block.class, GenOnExtraSoilSpecies::setExtraSoil)
                .register("soil_replacement_for_worldgen", GenOnExtraSoilSpecies.class, Block.class, GenOnExtraSoilSpecies::setSoilReplacement)
                .register("extra_soil_for_worldgen", GenOnExtraSoilSpecies.class, Block.class, GenOnExtraSoilSpecies::setExtraSoil)
                .register("log_drop_item", FruitLogSpecies.class, Item.class, FruitLogSpecies::setDropItem)
                .register("log_drop_item_multiplier", FruitLogSpecies.class, Float.class, FruitLogSpecies::setItemMultiplier)
                .register("log_drop_fake_log", FruitLogSpecies.class, Item.class, FruitLogSpecies::setFakeLog)
                .register("log_drop_fake_log_multiplier", FruitLogSpecies.class, Float.class, FruitLogSpecies::setFakeLogMultiplier)
                .registerArrayApplier("underwater_acceptable_soils", GenUnderwaterSpecies.class, String.class, GenUnderwaterSpecies::addAcceptableUnderwaterSoilsForWorldGen)
                .register("max_depth", GenUnderwaterSpecies.class, Integer.class, GenUnderwaterSpecies::setMaxDepth);
    }

    public static void registerFamilyAppliers(PropertyAppliers<Family, JsonElement> appliers) {
        appliers.register("primitive_alt_log", AltLogFamily.class, Block.class,
                AltLogFamily::setPrimitiveAltLog);
    }

    @SubscribeEvent public static void registerAppliersSpecies(final ApplierRegistryEvent.GatherData<Species, JsonElement> event) { registerSpeciesAppliers(event.getAppliers()); }
    @SubscribeEvent public static void registerAppliersFamily(final ApplierRegistryEvent.GatherData<Family, JsonElement> event) { registerFamilyAppliers(event.getAppliers()); }

}
