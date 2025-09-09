package com.dtteam.dtaddon_lib.growthlogic.growthlogic;

import com.ferreusveritas.dynamictrees.api.configuration.ConfigurationProperty;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKitConfiguration;
import com.ferreusveritas.dynamictrees.growthlogic.context.DirectionManipulationContext;
import com.ferreusveritas.dynamictrees.growthlogic.context.DirectionSelectionContext;
import com.ferreusveritas.dynamictrees.growthlogic.context.PositionalSpeciesContext;
import com.ferreusveritas.dynamictrees.systems.GrowSignal;
import com.ferreusveritas.dynamictrees.util.CoordUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Random;

/**
 * @author Joe Vettek
 */
// signal_energy controls the maximum trunk length.
// The value of each position in the six directions of probMap affects the initial branch size,
// but in the end, only values greater than 0 will always grow unless the fertility is exhausted.
// Because each growth pulse is sent throughout the tree along the trunk.
public class LucerniaLogic extends GrowthLogicKit {

    public static final ConfigurationProperty<Integer> TURNING_LENGTH = ConfigurationProperty.integer("turning_length");

    public LucerniaLogic(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected GrowthLogicKitConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(TURNING_LENGTH, 0);
    }

    @Override
    protected void registerProperties() {
        this.register(TURNING_LENGTH);
    }

    @Override
    public int[] populateDirectionProbabilityMap(GrowthLogicKitConfiguration configuration, DirectionManipulationContext context) {
        final Level world = context.level();
        final GrowSignal signal = context.signal();
        int[] probMap = new int[]{0, 2, 0, 0, 0, 0};
        final BlockPos pos = context.pos();
        Direction originDir = signal.dir.getOpposite();
        long seed = CoordUtils.coordHashCode(pos, 3) + ((ServerLevel) world).getSeed();
        Random random = new Random(seed);
        var delta = signal.delta;

        if (signal.numSteps == 5+random.nextInt(2)) {
            probMap = new int[]{0, 3, 1 + random.nextInt(2), 1 + random.nextInt(2), 1 + random.nextInt(2), 1 + random.nextInt(2)};
            // probMap = new int[]{0, 0, 0, 0, 0, 1};
        } else {
            // south
            float ran = random.nextFloat();
            if (delta.getX() >= 0 && delta.getZ() > 0) {
                probMap = new int[]{0, 0, 0, 10, 0, 0};
                if (ran < 0.456) {
                    probMap = new int[]{0, 10, 0, 1, 0, 0};
                } else if (ran < 0.66) {
                    probMap = new int[]{0, 0, 0, 1, 0, 10};
                }
            }
            // north
            else if (delta.getX() <= 0 && delta.getZ() < 0) {
                probMap = new int[]{0, 0, 10, 0, 0, 0};
                if (ran < 0.456) {
                    probMap = new int[]{0, 10, 1, 0, 0, 0};
                } else if (ran < 0.66) {
                    probMap = new int[]{0, 0, 0, 1, 10, 0};
                }
            }
            // west
            else if (delta.getX() < 0 && delta.getZ() >= 0) {
                probMap = new int[]{0, 0, 0, 0, 10, 0};
                if (ran < 0.456) {
                    probMap = new int[]{0, 10, 0, 0, 1, 0};
                } else if (ran < 0.66) {
                    probMap = new int[]{0, 0, 0, 10, 1, 0};
                }
            }
            // test east
            else if (delta.getX() > 0 && delta.getZ() <= 0) {
                probMap = new int[]{0, 0, 0, 0, 0, 10};
                if (ran < 0.456) {
                    probMap = new int[]{0, 10, 0, 0, 0, 1};
                } else if (ran < 0.66) {
                    probMap = new int[]{0, 0, 10, 0, 0, 1};
                }
            } else {
                probMap[Direction.UP.ordinal()] = 1;
                probMap[2 + random.nextInt(4)] = 4;
                probMap = new int[]{0, 3, 1 + random.nextInt(2), 1 + random.nextInt(2), 1 + random.nextInt(2), 1 + random.nextInt(2)};
            }

            if (Mth.abs(delta.getX()) > 9 || Mth.abs(delta.getZ()) > 9)
                probMap[Direction.UP.ordinal()] = 20;


            if (delta.getY() > 15 + random.nextInt(7)) {
                probMap = new int[]{0, 0, random.nextInt(4), random.nextInt(4), random.nextInt(4), random.nextInt(4)};
            }

            if (delta.getY() > 8 + random.nextInt(4))
                for (Direction direction : List.of(Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH)) {
                    probMap[direction.ordinal()] = random.nextInt(2);
                }
        }
        probMap[originDir.ordinal()] = 0;
        return probMap;
    }

    @Override
    public Direction selectNewDirection(GrowthLogicKitConfiguration configuration, DirectionSelectionContext context) {
        var d = super.selectNewDirection(configuration, context);
        return d;
    }

    @Override
    public int getLowestBranchHeight(GrowthLogicKitConfiguration configuration, PositionalSpeciesContext context) {
        return super.getLowestBranchHeight(configuration, context);
    }

    @Override
    public float getEnergy(GrowthLogicKitConfiguration configuration, PositionalSpeciesContext context) {
        long day = context.level().getGameTime() / 24000L;
        int month = (int) day / 30; // Change the hashs every in-game month
        var en= super.getEnergy(configuration, context) *
                context.species().biomeSuitability(context.level(), context.pos()) +
                (CoordUtils.coordHashCode(context.pos().above(month), 3) %
                        3); // Vary the height energy by a pseudo-random hash function
        return en;
    }
}
