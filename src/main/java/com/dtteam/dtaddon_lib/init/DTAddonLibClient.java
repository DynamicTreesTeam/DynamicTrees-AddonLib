package com.dtteam.dtaddon_lib.init;

import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;
import com.dtteam.dtaddon_lib.blocks.MapleSpileCommon;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = DynamicTreesAddonLib.MOD_ID, value = {Dist.CLIENT}, bus = EventBusSubscriber.Bus.MOD)
public class DTAddonLibClient {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerBlockColorHandlersEvent(RegisterColorHandlersEvent.Block event) {

        Block[] spiles = new Block[]{DTAddonLibBlocks.MAPLE_SPILE_BLOCK.get(), DTAddonLibBlocks.MAPLE_SPILE_BUCKET_BLOCK.get()};
        for (Block spile : spiles){
            event.register(((MapleSpileCommon)spile)::colorMultiplier, spile);
        }
    }

}
