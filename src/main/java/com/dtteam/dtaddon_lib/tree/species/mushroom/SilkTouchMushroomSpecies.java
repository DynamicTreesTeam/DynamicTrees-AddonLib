package com.dtteam.dtaddon_lib.tree.species.mushroom;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.systems.nodemapper.NetVolumeNode;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictreesplus.block.mushroom.CapProperties;
import com.dtteam.dynamictreesplus.tree.HugeMushroomSpecies;
import net.minecraft.resources.ResourceLocation;

// From DTBWG (Woody Mushroom)
public class SilkTouchMushroomSpecies extends HugeMushroomSpecies {

    public static final TypedRegistry.EntryType<Species> TYPE = createDefaultMushroomType(SilkTouchMushroomSpecies::new);

    public SilkTouchMushroomSpecies(ResourceLocation name, Family family, CapProperties capProperties) {
        super(name, family, capProperties);
    }

    public LogsAndSticks getLogsAndSticks(NetVolumeNode.Volume volume, boolean silkTouch, int fortuneLevel) {
        return super.getLogsAndSticks(volume, true, fortuneLevel);
    }

}
