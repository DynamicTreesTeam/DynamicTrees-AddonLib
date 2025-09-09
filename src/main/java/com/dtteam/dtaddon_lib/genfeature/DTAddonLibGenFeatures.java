package com.dtteam.dtaddon_lib.genfeature;

import com.dtteam.dtaddon_lib.genfeature.genfeature.*;
import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.systems.genfeature.BiomePredicateGenFeature;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;

public class DTAddonLibGenFeatures {

    public static final GenFeature BIG_BOTTOM_FLARE = new BigBottomFlareGenFeature(DynamicTreesAddonLib.location("big_bottom_flare"));
    public static final GenFeature EXTRA_BOTTOM_FLARE = new ExtraBottomFlareGenFeature(DynamicTreesAddonLib.location("extra_bottom_flare"));
    public static final GenFeature BIOME_PREDICATE_2 = new BiomePredicateGenFeature(DynamicTreesAddonLib.location("biome_predicate"));
    public static final GenFeature SYTHIAN_TOPPER = new SythianTopperGenFeature(DynamicTreesAddonLib.location("sythian_topper"));
    public static final GenFeature ALTERNATIVE_BRANCH = new AlternativeBranchGenFeature(DynamicTreesAddonLib.location("alt_branch"));
    public static final GenFeature LUSH_VINES = new LushVinesGenFeature(DynamicTreesAddonLib.location("lush_vines"));
    public static final GenFeature ALT_LEAVES_HEIGHT_LIMIT = new AlternativeLeavesWithHeightLimitGenFeature(DynamicTreesAddonLib.location("alt_leaves_with_heigth_limit"));
    public static final GenFeature ALT_LEAVES = new AlternativeLeavesGenFeature(DynamicTreesAddonLib.location("alt_leaves"));
    public static final GenFeature ABOVE_LEAVES_BLOCK = new AboveLeavesBlockGenFeature(DynamicTreesAddonLib.location("above_leaves_block"));
    public static final GenFeature FALLEN_LEAVES = new FallenLeavesGenFeature(DynamicTreesAddonLib.location("fallen_leaves"));
    public static final GenFeature HANGER_VINES = new HangerVinesGenFeature(DynamicTreesAddonLib.location("hanger_vines"));
    public static final GenFeature SPORES = new SporesGenFeature(DynamicTreesAddonLib.location("spores"));
    public static final GenFeature VINES_ON_TRUNK = new VinesOnTrunkGenFeature(DynamicTreesAddonLib.location("vines_on_trunk"));
    public static final GenFeature PLANT_SUCKERS = new PlantSuckerGenFeature(DynamicTreesAddonLib.location("plant_suckers"));
    public static final GenFeature TRUNK_VINES = new VinesInTrunkGenFeature(DynamicTreesAddonLib.location("trunk_vines"));
    public static final GenFeature CHANGE_GRASS = new ChangeGrassGenFeature(DynamicTreesAddonLib.location("change_grass"));
    public static final GenFeature BANANA_FRUIT = new BananaFruitGenFeature(DynamicTreesAddonLib.location("banana_fruit"));
    public static final GenFeature DRAGON_FRUIT_FRUIT = new DragonFruitFruitGenFeature(DynamicTreesAddonLib.location("dragon_fruit_fruit"));
    public static final GenFeature PALM_FRUIT = new PalmFruitGenFeature(DynamicTreesAddonLib.location("palm_fruit"));
    public static final GenFeature RANDOM_STRIPPED_BRANCHES = new RandomStrippedBranches(DynamicTreesAddonLib.location("random_stripped_branches"));
    public static final GenFeature FIREFLY = new FireflyGenFeature(DynamicTreesAddonLib.location("firefly"));

    public static void register(final Registry<GenFeature> registry) {
        registry.registerAll(BIG_BOTTOM_FLARE, EXTRA_BOTTOM_FLARE, BIOME_PREDICATE_2, SYTHIAN_TOPPER,
                ALTERNATIVE_BRANCH, LUSH_VINES, ALT_LEAVES_HEIGHT_LIMIT, ALT_LEAVES, ABOVE_LEAVES_BLOCK,
                FALLEN_LEAVES, HANGER_VINES, SPORES, VINES_ON_TRUNK, PLANT_SUCKERS, TRUNK_VINES, CHANGE_GRASS,
                BANANA_FRUIT, DRAGON_FRUIT_FRUIT, PALM_FRUIT, RANDOM_STRIPPED_BRANCHES, FIREFLY);
    }

}
