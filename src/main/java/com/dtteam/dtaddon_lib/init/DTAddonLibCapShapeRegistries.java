package com.dtteam.dtaddon_lib.init;

import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;
import com.ferreusveritas.dynamictrees.util.CommonVoxelShapes;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DTAddonLibCapShapeRegistries {

    private static VoxelShape box(double p_49797_, double p_49798_, double p_49799_, double p_49800_, double p_49801_, double p_49802_) {
        return Shapes.box(p_49797_ / (double)16.0F, p_49798_ / (double)16.0F, p_49799_ / (double)16.0F, p_49800_ / (double)16.0F, p_49801_ / (double)16.0F, p_49802_ / (double)16.0F);
    }

    public static final VoxelShape GLOWSHROOM_AGE0 = Shapes.create(0, 0, 0, 1, 0.75, 1);
    public static final VoxelShape TOADSTOOL_AGE0 = Shapes.create(2/16f, 0, 2/16f, 14/16f, 1, 14/16f);
    public static final VoxelShape MUSHROOM_CAP_SHORT_ROUND = box(5D, 3D, 5D, 11D, 7D, 11D);
    public static final VoxelShape ROUND_SHORT_MUSHROOM = Shapes.or(CommonVoxelShapes.MUSHROOM_STEM, MUSHROOM_CAP_SHORT_ROUND);
    public static final VoxelShape TOADSTOOL_CAP = box(5.5D, 3.0D, 5.5D, 10.5D, 10.0D, 10.5D);
    public static final VoxelShape TOADSTOOL = Shapes.or(CommonVoxelShapes.MUSHROOM_STEM, TOADSTOOL_CAP);
    public static final VoxelShape MUSHROOM_STEM_LONG = box(7D, 0D, 7D, 9D, 10D, 9D);
    public static final VoxelShape TALL_MUSHROOM_CAP_FLAT = box(5.0D, 7.0D, 5.0D, 11.0D, 10.0D, 11.0D);
    public static final VoxelShape SMALL_MUSHROOM_CAP_FLAT = box(5.0D, 5.0D, 5.0D, 11.0D, 7.0D, 11.0D);;
    public static final VoxelShape SOUL_SHROOM_CAP = box(5.5D, 3.0D, 5.5D, 10.5D, 10.0D, 10.5D);
    public static final VoxelShape SYTHIAN_CAP_A = box(5D, 3D, 5D, 11D, 5D, 11D);
    public static final VoxelShape SYTHIAN_CAP_B = box(4D, 6D, 4D, 12D, 8D, 12D);
    public static final VoxelShape SYTHIAN_CAP_C = box(5D, 9D, 5D, 11D, 11D, 11D);
    public static final VoxelShape SHULKREN_CAP_A = box(4D, 3D, 4D, 12D, 6D, 12D);
    public static final VoxelShape SHULKREN_CAP_B = box(5D, 6D, 5D, 11D, 9D, 11D);
    public static final VoxelShape SHULKREN_CAP_C = box(6D, 9D, 6D, 10D, 11D, 10D);
    public static final VoxelShape TALL_FLAT_MUSHROOM = Shapes.or(MUSHROOM_STEM_LONG, TALL_MUSHROOM_CAP_FLAT);
    public static final VoxelShape SMALL_FLAT_MUSHROOM = Shapes.or(CommonVoxelShapes.MUSHROOM_STEM, SMALL_MUSHROOM_CAP_FLAT);
    public static final VoxelShape SHORT_ROUND_MUSHROOM = Shapes.or(CommonVoxelShapes.MUSHROOM_STEM, MUSHROOM_CAP_SHORT_ROUND);
    public static final VoxelShape SOUL_SHROOM = Shapes.or(CommonVoxelShapes.MUSHROOM_STEM, SOUL_SHROOM_CAP);
    public static final VoxelShape SYTHIAN_MUSHROOM = Shapes.or(MUSHROOM_STEM_LONG, SYTHIAN_CAP_A, SYTHIAN_CAP_B, SYTHIAN_CAP_C);
    public static final VoxelShape SHULKREN_MUSHROOM = Shapes.or(CommonVoxelShapes.MUSHROOM_STEM, SHULKREN_CAP_A, SHULKREN_CAP_B, SHULKREN_CAP_C);

    public static void register() {
        CommonVoxelShapes.SHAPES.put(DynamicTreesAddonLib.location("glowshroom_age0").toString(), GLOWSHROOM_AGE0);
        CommonVoxelShapes.SHAPES.put(DynamicTreesAddonLib.location("toadstool_age0").toString(), TOADSTOOL_AGE0);
        CommonVoxelShapes.SHAPES.put(DynamicTreesAddonLib.location("round_short_mushroom").toString(), ROUND_SHORT_MUSHROOM);
        CommonVoxelShapes.SHAPES.put(DynamicTreesAddonLib.location("toadstool").toString(), TOADSTOOL);
        CommonVoxelShapes.SHAPES.put(DynamicTreesAddonLib.location("tall_flat_mushroom").toString(), TALL_FLAT_MUSHROOM);
        CommonVoxelShapes.SHAPES.put(DynamicTreesAddonLib.location("small_flat_mushroom").toString(), SMALL_FLAT_MUSHROOM);
        CommonVoxelShapes.SHAPES.put(DynamicTreesAddonLib.location("short_round_mushroom").toString(), SHORT_ROUND_MUSHROOM);
        CommonVoxelShapes.SHAPES.put(DynamicTreesAddonLib.location("soul_shroom").toString(), SOUL_SHROOM);
        CommonVoxelShapes.SHAPES.put(DynamicTreesAddonLib.location("sythian_mushroom").toString(), SYTHIAN_MUSHROOM);
        CommonVoxelShapes.SHAPES.put(DynamicTreesAddonLib.location("shulkren_mushroom").toString(), SHULKREN_MUSHROOM);
    }
}
