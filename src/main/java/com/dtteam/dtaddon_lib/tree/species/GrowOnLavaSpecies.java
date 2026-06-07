package com.dtteam.dtaddon_lib.tree.species;

import com.dtteam.dynamictrees.api.registry.RegistryHandler;
import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.block.sapling.DynamicSaplingBlock;
import com.dtteam.dynamictrees.item.Seed;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

/**
 * This species can be planted on lava
 *
 * @author Max Hyper
 */
public class GrowOnLavaSpecies extends Species {

    public static final TypedRegistry.EntryType<Species> TYPE = createDefaultType(GrowOnLavaSpecies::new);

    public GrowOnLavaSpecies(Identifier name, Family family, LeavesProperties leavesProperties) {
        super(name, family, leavesProperties);
    }

    @Override
    public Species generateSeed() {
        return !this.shouldGenerateSeed() || this.seed != null ? this :
                this.setSeed(RegistryHandler.addItem(getSeedName(), () ->
                        new Seed(this, new Item.Properties().fireResistant()) {
                            @Override
                            public InteractionResult useOn(UseOnContext context) {
                                Level world = context.getLevel();
                                Player player = context.getPlayer();
                                if (player == null) return super.useOn(context);
                                InteractionHand hand = context.getHand();
                                BlockHitResult rayTraceResult = getPlayerPOVHitResult(world, player, ClipContext.Fluid.SOURCE_ONLY);
                                BlockHitResult rayTraceResultUp = rayTraceResult.withPosition(rayTraceResult.getBlockPos().above());
                                BlockHitResult selectedResult = rayTraceResult.getDirection() == Direction.UP ? rayTraceResultUp : rayTraceResult;

                                return super.useOn(new UseOnContext(player, hand, selectedResult));
                            }
                        }));
    }

    @Override
    public boolean plantSapling(LevelAccessor level, BlockPos pos, boolean locationOverride) {
        FluidState fluidState = level.getFluidState(pos);
        FluidState fluidStateUp = level.getFluidState(pos.above());

        final DynamicSaplingBlock sapling = this.getSapling().orElse(null);

        if (sapling != null && fluidState.getType() == Fluids.LAVA && fluidStateUp.getType() == Fluids.EMPTY){
            return super.plantSapling(level, pos.above(), locationOverride);
        }
        return super.plantSapling(level, pos, locationOverride);
    }

}
