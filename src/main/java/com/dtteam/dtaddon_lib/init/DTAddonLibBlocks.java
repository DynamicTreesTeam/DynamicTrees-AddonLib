package com.dtteam.dtaddon_lib.init;

import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;
import com.dtteam.dtaddon_lib.blocks.MapleSpileBlock;
import com.dtteam.dtaddon_lib.blocks.MapleSpileBucketBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class DTAddonLibBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DynamicTreesAddonLib.MOD_ID);

    public static final RegistryObject<Block> MAPLE_SPILE_BLOCK = registerBlock("maple_spile", MapleSpileBlock::new);
    public static final RegistryObject<Block> MAPLE_SPILE_BUCKET_BLOCK = registerBlock("maple_spile_bucket", MapleSpileBucketBlock::new);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
