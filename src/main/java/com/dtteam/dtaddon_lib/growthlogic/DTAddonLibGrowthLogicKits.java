package com.dtteam.dtaddon_lib.growthlogic;

import com.dtteam.dtaddon_lib.growthlogic.growthlogic.*;
import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;

public class DTAddonLibGrowthLogicKits {

    public static final GrowthLogicKit POPLAR = new PoplarLogic(DynamicTreesAddonLib.location("poplar"));
    public static final GrowthLogicKit MAPLE = new MapleLogic(DynamicTreesAddonLib.location("maple"));
    public static final GrowthLogicKit ASPEN = new AspenLogic(DynamicTreesAddonLib.location("aspen"));
    public static final GrowthLogicKit TAPERED = new TaperedOakLogic(DynamicTreesAddonLib.location("tapered"));
    public static final GrowthLogicKit DIAGONAL_PALM = new DiagonalPalmLogic(DynamicTreesAddonLib.location("diagonal_palm"));
    public static final GrowthLogicKit ZELKOVA = new ZelkovaLogic(DynamicTreesAddonLib.location("zelkova"));
    public static final GrowthLogicKit THIN_CONIFER = new PineLogic(DynamicTreesAddonLib.location("thin_conifer"));
    public static final GrowthLogicKit MEGA_PINE = new MegaPineLogic(DynamicTreesAddonLib.location("mega_pine"));
    public static final GrowthLogicKit EBONY = new EbonyLogic(DynamicTreesAddonLib.location("ebony"));
    public static final GrowthLogicKit REDWOOD = new RedwoodLogic(DynamicTreesAddonLib.location("redwood"));
    public static final GrowthLogicKit SMALL_REDWOOD = new SmallRedwoodLogic(DynamicTreesAddonLib.location("small_redwood"));
    public static final GrowthLogicKit BAOBAB = new BaobabLogic(DynamicTreesAddonLib.location("baobab"));
    public static final GrowthLogicKit VARIATE_HEIGHT = new VariateHeightLogic(DynamicTreesAddonLib.location("variate_height"));
    public static final GrowthLogicKit SYTHIAN_FUNGUS = new SythianLogic(DynamicTreesAddonLib.location("sythian_fungus"));
    public static final GrowthLogicKit CYPRESS = new CypressLogic(DynamicTreesAddonLib.location("cypress"));
    public static final GrowthLogicKit MANGROVE = new MangroveLogic(DynamicTreesAddonLib.location("mangrove"));
    public static final GrowthLogicKit TAPERED_WITHERED = new TaperedWitheredOakLogic(DynamicTreesAddonLib.location("tapered_withered"));
    public static final GrowthLogicKit ANCIENT_LOGIC = new AncientLogic(DynamicTreesAddonLib.location("ancient"));
    public static final GrowthLogicKit MEGA_RAINBOW_EUCALYPTUS = new MegaRainbowEucalyptusLogic(DynamicTreesAddonLib.location("mega_rainbow_eucalyptus"));
    public static final GrowthLogicKit WILLOW = new WillowLogic(DynamicTreesAddonLib.location("willow"));
    public static final GrowthLogicKit ARAUCARIA = new AraucariaLogic(DynamicTreesAddonLib.location("araucaria"));
    public static final GrowthLogicKit TWISTING = new TwistingTreeLogic(DynamicTreesAddonLib.location("twisting"));
    public static final GrowthLogicKit ETHER = new EtherLogic(DynamicTreesAddonLib.location("ether"));
    public static final GrowthLogicKit ENCHANTED = new EnchantedTreeLogic(DynamicTreesAddonLib.location("enchanted"));
    public static final GrowthLogicKit MAHOGANY = new MahoganyLogic(DynamicTreesAddonLib.location("mahogany"));
    public static final GrowthLogicKit TENANEA = new TenaneaLogic(DynamicTreesAddonLib.location("tenanea"));
    public static final GrowthLogicKit LUCERNIA = new LucerniaLogic(DynamicTreesAddonLib.location("lucernia"));
    public static final GrowthLogicKit DRAGON_TREE = new DragonLogic(DynamicTreesAddonLib.location("dragon_tree"));
    public static final GrowthLogicKit LACUGROVE = new LacugroveLogic(DynamicTreesAddonLib.location("lacugrove"));
    public static final GrowthLogicKit PYTHADENDRON = new PythadendronLogic(DynamicTreesAddonLib.location("pythadendron"));
    public static final GrowthLogicKit PRICKLY_PEAR = new PricklyPearCactusLogic(DynamicTreesAddonLib.location("prickly_pear"));
    public static final GrowthLogicKit SOULBLIGHT = new SoulblightLogic(DynamicTreesAddonLib.location("soulblight"));
    public static final GrowthLogicKit CROOKED_PALM = new CrookedPalmLogic(DynamicTreesAddonLib.location("crooked_palm"));
    public static final GrowthLogicKit WISTERIA = new WisteriaLogic(DynamicTreesAddonLib.location("wisteria"));
    public static final GrowthLogicKit BIFURCATED = new BifurcatedLogic(DynamicTreesAddonLib.location("bifurcated"));
    public static final GrowthLogicKit LARGE_CROWN = new LargeCrownLogic(DynamicTreesAddonLib.location("large_crown"));
    public static final GrowthLogicKit CANOPY = new CanopyLogic(DynamicTreesAddonLib.location("canopy"));
    public static final GrowthLogicKit MINING = new MiningTreeLogic(DynamicTreesAddonLib.location("mining"));
    public static final GrowthLogicKit TALL_DECIDUOUS = new TallDeciduousLogic(DynamicTreesAddonLib.location("tall_deciduous"));
    public static final GrowthLogicKit CONBERRY = new ConberryLogic(DynamicTreesAddonLib.location("conberry"));
    public static final GrowthLogicKit YAGROOT_ROOTS = new YagrootRootsLogic(DynamicTreesAddonLib.location("yagroot_roots"));
    public static final GrowthLogicKit BLIGHTWILLOW = new BlightwillowLogic(DynamicTreesAddonLib.location("blightwillow"));
    public static final GrowthLogicKit FIELDSPROOT = new FieldsprootLogic(DynamicTreesAddonLib.location("fieldsproot"));

    public static void register(final Registry<GrowthLogicKit> registry) {
        registry.registerAll(POPLAR, MAPLE, ASPEN, TAPERED, DIAGONAL_PALM, MAHOGANY, ZELKOVA, THIN_CONIFER, MEGA_PINE,
                EBONY, REDWOOD, SMALL_REDWOOD, BAOBAB, VARIATE_HEIGHT, SYTHIAN_FUNGUS, CYPRESS, MANGROVE,
                TAPERED_WITHERED, ANCIENT_LOGIC, MEGA_RAINBOW_EUCALYPTUS, WILLOW, ARAUCARIA, TWISTING, ETHER, ENCHANTED,
                LUCERNIA, DRAGON_TREE, LACUGROVE, PYTHADENDRON, PRICKLY_PEAR, SOULBLIGHT, CROOKED_PALM, WISTERIA,
                BIFURCATED, LARGE_CROWN, TENANEA, CANOPY, MINING, TALL_DECIDUOUS, CONBERRY, YAGROOT_ROOTS,
                BLIGHTWILLOW, FIELDSPROOT);
    }

}
