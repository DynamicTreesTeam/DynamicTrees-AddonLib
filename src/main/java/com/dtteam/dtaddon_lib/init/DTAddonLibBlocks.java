package com.dtteam.dtaddon_lib.init;

import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;
import com.dtteam.dtaddon_lib.blocks.MapleSpileBlock;
import com.dtteam.dtaddon_lib.blocks.MapleSpileBucketBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class DTAddonLibBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, DynamicTreesAddonLib.MOD_ID);

    public static final Supplier<Block> MAPLE_SPILE_BLOCK = registerBlock("maple_spile",
            () -> new MapleSpileBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).strength(0.5f).randomTicks()));
    public static final Supplier<Block> MAPLE_SPILE_BUCKET_BLOCK = registerBlock("maple_spile_bucket",
            () -> new MapleSpileBucketBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).strength(0.7f).randomTicks()));

    private static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
