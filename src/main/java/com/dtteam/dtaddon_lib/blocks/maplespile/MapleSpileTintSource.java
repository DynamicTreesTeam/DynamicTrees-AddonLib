package com.dtteam.dtaddon_lib.blocks.maplespile;

import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class MapleSpileTintSource implements BlockTintSource {
    private final MapleSpileCommon spile;

    public MapleSpileTintSource(MapleSpileCommon spile) {
        this.spile = spile;
    }

    @Override
    public int color(BlockState state) {
        return -1;
    }

    @Override
    public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
        return spile.colorMultiplier(state, level, pos, 0);
    }

}
