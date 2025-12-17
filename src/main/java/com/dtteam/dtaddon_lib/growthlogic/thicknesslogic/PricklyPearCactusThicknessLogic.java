package com.dtteam.dtaddon_lib.growthlogic.thicknesslogic;

import com.dtteam.dynamictrees.tree.TreeHelper;
import com.dtteam.dynamictrees.systems.GrowSignal;
import com.dtteam.dynamictrees.utility.CoordUtils;
import com.dtteam.dynamictreesplus.block.CactusBranchBlock;
import com.dtteam.dynamictreesplus.systems.thicknesslogic.CactusThicknessLogic;
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
