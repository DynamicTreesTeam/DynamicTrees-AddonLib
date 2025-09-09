package com.dtteam.dtaddon_lib.growthlogic.thicknesslogic;

import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.systems.GrowSignal;
import com.ferreusveritas.dynamictrees.util.CoordUtils;
import com.ferreusveritas.dynamictreesplus.block.CactusBranchBlock;
import com.ferreusveritas.dynamictreesplus.systems.thicknesslogic.CactusThicknessLogic;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class PricklyPearCactusThicknessLogic extends CactusThicknessLogic {

    public PricklyPearCactusThicknessLogic(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    public CactusBranchBlock.CactusThickness thicknessAfterGrowthSignal(Level world, BlockPos pos, GrowSignal signal, CactusBranchBlock.CactusThickness currentThickness) {
        if (CoordUtils.coordHashCode(pos, 3) % 2 == 0)
            return CactusBranchBlock.CactusThickness.TRUNK;
        else return CactusBranchBlock.CactusThickness.BRANCH;
    }

    @Override
    public CactusBranchBlock.CactusThickness thicknessForBranchPlaced(LevelAccessor world, BlockPos pos, boolean isLast) {
        if (TreeHelper.isRooty(world.getBlockState(pos.below())))
            return CactusBranchBlock.CactusThickness.TRUNK;
        return  CactusBranchBlock.CactusThickness.BRANCH;
    };

}
