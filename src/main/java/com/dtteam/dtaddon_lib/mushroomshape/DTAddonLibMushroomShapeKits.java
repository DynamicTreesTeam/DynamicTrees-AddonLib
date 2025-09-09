package com.dtteam.dtaddon_lib.mushroomshape;

import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;
import com.dtteam.dtaddon_lib.mushroomshape.mushroomshape.*;
import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictreesplus.systems.mushroomlogic.shapekits.MushroomShapeKit;

public class DTAddonLibMushroomShapeKits {

    public static final MushroomShapeKit LINEAR_MUSHROOM_SHAPE = new LinearShape(DynamicTreesAddonLib.location("linear"));
    public static final MushroomShapeKit BALL_MUSHROOM_SHAPE = new BallShape(DynamicTreesAddonLib.location("ball"));

    public static void register(final Registry<MushroomShapeKit> registry) {
        registry.registerAll(LINEAR_MUSHROOM_SHAPE, BALL_MUSHROOM_SHAPE);
    }

}
