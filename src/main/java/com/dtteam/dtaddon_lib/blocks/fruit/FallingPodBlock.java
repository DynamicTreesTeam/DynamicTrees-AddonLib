package com.dtteam.dtaddon_lib.blocks.fruit;

import com.dtteam.dynamictrees.block.branch.BranchBlock;
import com.dtteam.dynamictrees.block.pod.Pod;
import com.dtteam.dynamictrees.block.pod.PodBlock;
import com.dtteam.dynamictrees.block.soil.SoilBlock;
import com.dtteam.dynamictrees.tree.TreeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class FallingPodBlock extends PodBlock implements IFallingFruit {

    private final ResourceKey<DamageType> damageTypeKey;

    public static float randomFruitFallChance = 0.005f;
    public static float playerDistanceToFall = 10f;

    public FallingPodBlock(Identifier id, Properties properties, Pod pod) {
        super(id, properties, pod);
        this.damageTypeKey = ResourceKey.create(
                Registries.DAMAGE_TYPE,
                Identifier.fromNamespaceAndPath(pod.getRegistryName().getNamespace(), "falling_fruit/" + pod.getRegistryName().getPath())
        );
    }

    @Override
    public boolean isSupported(LevelReader world, BlockPos pos, BlockState state) {
        final BlockState branchState = world.getBlockState(pos.relative(state.getValue(HorizontalDirectionalBlock.FACING)));
        final BranchBlock branch = TreeHelper.getBranch(branchState);
        return branch != null && branch.getRadius(branchState) == 3;
    }

    @Override
    public void doTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (checkToFall(state, world, pos, random)){
            //System.out.println(this.asItem());
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
    public DamageSource getDamageSource(Level level) {
        return new DamageSource(level.registryAccess()
                .lookupOrThrow(Registries.DAMAGE_TYPE)
                .getOrThrow(damageTypeKey));
    }

    @Override
    public int getRootY(BlockState state, Level world, BlockPos pos) {
        Direction dir = state.getValue(FallingPodBlock.FACING);
        for (int i=0;i<20;i++){
            BlockPos pos2 = pos.offset(dir.getUnitVec3i()).below(i);
            if (world.getBlockState(pos2).getBlock() instanceof SoilBlock){
                return pos2.getY();
            }
        }
        return pos.getY();
    }

    @Override
    public ItemStack getDropOnFallItems(@Nonnull FallingBlockEntity entity) {
        if (!(entity.level() instanceof ServerLevel world)) return ItemStack.EMPTY;
        List<ItemStack> items = getDrops(entity.getBlockState(), world, entity.blockPosition(), null);
        return items.isEmpty() ? ItemStack.EMPTY : items.get(0);
    }

    public float getRandomFruitFallChance() {
        return randomFruitFallChance;
    }

    @Override
    public float getPlayerDistanceToFall() {
        return playerDistanceToFall;
    }
}
