package com.dtteam.dtaddon_lib.init;

import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;
import com.dtteam.dtaddon_lib.blocks.maplespile.MapleSpileCommon;
import com.dtteam.dtaddon_lib.blocks.maplespile.MapleSpileTintSource;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.util.List;

@EventBusSubscriber(modid = DynamicTreesAddonLib.MOD_ID, value = {Dist.CLIENT})
public class DTAddonLibClient {

    @SubscribeEvent
    public static void registerBlockColorHandlersEvent(RegisterColorHandlersEvent.BlockTintSources event) {

        Block[] spiles = new Block[]{DTAddonLibBlocks.MAPLE_SPILE_BLOCK.get(), DTAddonLibBlocks.MAPLE_SPILE_BUCKET_BLOCK.get()};
        for (Block spile : spiles) {
            event.register(List.of(new MapleSpileTintSource((MapleSpileCommon) spile)), spile);
        }
    }

}
