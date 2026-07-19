package com.dtteam.dtaddon_lib.init;

import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;
import com.dtteam.dtaddon_lib.blocks.maplespile.MapleSpileBlock;
import com.dtteam.dtaddon_lib.blocks.maplespile.MapleSpileBucketBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class DTAddonLibBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, DynamicTreesAddonLib.MOD_ID);

    public static final DeferredHolder<Block, MapleSpileBlock> MAPLE_SPILE_BLOCK = BLOCKS.register(
            "maple_spile",
            registryName -> new MapleSpileBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    .mapColor(MapColor.METAL)
                    .sound(SoundType.METAL)
                    .strength(0.5f)
                    .randomTicks())
    );

    public static final DeferredHolder<Block, MapleSpileBucketBlock> MAPLE_SPILE_BUCKET_BLOCK = BLOCKS.register(
            "maple_spile_bucket",
            registryName -> new MapleSpileBucketBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    .mapColor(MapColor.METAL)
                    .sound(SoundType.METAL)
                    .strength(0.7f)
                    .randomTicks())
    );

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
