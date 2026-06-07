package com.dtteam.dtaddon_lib.blocks.fruit;

import com.dtteam.dynamictrees.block.fruit.Fruit;
import com.dtteam.dynamictrees.block.fruit.FruitBlock;
import com.dtteam.dynamictrees.block.soil.SoilBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class FallingFruitBlock extends FruitBlock implements IFallingFruit {

    private final ResourceKey<DamageType> damageTypeKey;

    public static float randomFruitFallChance = 0.005f;
    public static float playerDistanceToFall = 10f;

    public FallingFruitBlock(Identifier id, Properties properties, Fruit fruit) {
        super(id, properties, fruit);
        this.damageTypeKey = ResourceKey.create(
                Registries.DAMAGE_TYPE,
                Identifier.fromNamespaceAndPath(fruit.getRegistryName().getNamespace(), "falling_fruit/" + fruit.getRegistryName().getPath())
        );
    }

    @Override
    public void doTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (checkToFall(state, world, pos, random)){
            doFall(state, world, pos);
        } else
            super.doTick(state, world, pos, random);
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, @Nullable Orientation orientation, boolean isMoving) {
        if (!isSupported(world, pos, state)) {
            if (!doFall(state, world, pos))
                super.neighborChanged(state, world, pos, block, orientation, isMoving);
        }
    }

    @Override
    public ItemStack getDropOnFallItems(@Nonnull FallingBlockEntity entity) {
        if (!(entity.level() instanceof ServerLevel world)) return ItemStack.EMPTY;
        List<ItemStack> items = getDrops(entity.getBlockState(), world, entity.blockPosition(), null);
        return items.isEmpty() ? ItemStack.EMPTY : items.get(0);
    }

    @Override
    public DamageSource getDamageSource(Level level) {
        return new DamageSource(level.registryAccess()
                .lookupOrThrow(Registries.DAMAGE_TYPE)
                .getOrThrow(damageTypeKey));
    }

    @Override
    public int getRootY(BlockState state, Level world, BlockPos pos) {
        for (int i=0;i<20;i++){
            BlockPos pos2 = pos.below(i);
            if (world.getBlockState(pos2).getBlock() instanceof SoilBlock){
                return pos2.getY();
            }
        }
        return pos.getY();
    }

    @Override
    public float getRandomFruitFallChance() {
        return randomFruitFallChance;
    }

    @Override
    public float getPlayerDistanceToFall() {
        return playerDistanceToFall;
    }
}
