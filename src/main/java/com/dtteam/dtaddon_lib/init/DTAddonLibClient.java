package com.dtteam.dtaddon_lib.init;

import com.dtteam.dtaddon_lib.blocks.MapleSpileCommon;
import com.ferreusveritas.dynamictrees.api.client.ModelHelper;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public class DTAddonLibClient {

    public static void setup (){
        registerRenderLayers();
        registerColorHandlers();

    }

    private static void registerRenderLayers() {
        ItemBlockRenderTypes.setRenderLayer(DTAddonLibBlocks.MAPLE_SPILE_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(DTAddonLibBlocks.MAPLE_SPILE_BUCKET_BLOCK.get(), RenderType.translucent());
    }

    private static void registerColorHandlers() {

        Block[] spiles = new Block[]{DTAddonLibBlocks.MAPLE_SPILE_BLOCK.get(), DTAddonLibBlocks.MAPLE_SPILE_BUCKET_BLOCK.get()};

        for (Block spile : spiles){
            ModelHelper.regColorHandler(spile, ((MapleSpileCommon)spile)::colorMultiplier);
        }

    }

}
