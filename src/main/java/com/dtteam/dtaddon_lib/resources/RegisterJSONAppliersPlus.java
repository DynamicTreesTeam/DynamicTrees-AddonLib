package com.dtteam.dtaddon_lib.resources;

import com.dtteam.dtaddon_lib.blocks.WartyCapProperties;
import com.ferreusveritas.dynamictrees.api.applier.ApplierRegistryEvent;
import com.ferreusveritas.dynamictrees.deserialisation.PropertyAppliers;
import com.ferreusveritas.dynamictreesplus.block.mushroom.CapProperties;
import com.google.gson.JsonElement;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class RegisterJSONAppliersPlus {

    @SubscribeEvent
    public static void registerAppliersCapProperties(final ApplierRegistryEvent.Reload<CapProperties, JsonElement> event) {
        registerCapPropertiesAppliers(event.getAppliers());
    }

    public static void registerCapPropertiesAppliers(PropertyAppliers<CapProperties, JsonElement> appliers) {
        appliers.register("shroomlight_block", WartyCapProperties.class, Block.class,
                WartyCapProperties::setShroomlightBlock)
                .register("shroomlight_above_place_chance", WartyCapProperties.class, Float.class,
                        WartyCapProperties::setShroomlightUpChance)
                .register("shroomlight_below_place_chance", WartyCapProperties.class, Float.class,
                        WartyCapProperties::setShroomlightDownChance)
                .register("shroomlight_requires_support", WartyCapProperties.class, Boolean.class,
                        WartyCapProperties::setShroomlightRequireSupport);
    }

}