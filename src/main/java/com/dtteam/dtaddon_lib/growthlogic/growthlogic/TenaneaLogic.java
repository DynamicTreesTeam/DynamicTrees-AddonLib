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
import net.minecraft.world.level.Level;

import java.util.Random;

/**
 * @author Joe Vettek
 */
// signal_energy controls the maximum trunk length.
// The value of each position in the six directions of probMap affects the initial branch size,
// but in the end, only values greater than 0 will always grow unless the fertility is exhausted.
// Because each growth pulse is sent throughout the tree along the trunk.
public class TenaneaLogic extends GrowthLogicKit {

    public static final ConfigurationProperty<Integer> TURNING_LENGTH = ConfigurationProperty.integer("turning_length");

    public TenaneaLogic(ResourceLocation registryName) {
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
            probMap = new int[]{0, 0, 1 + random.nextInt(2), 1 + random.nextInt(2), 1 + random.nextInt(2), 1 + random.nextInt(2)};
            // probMap = new int[]{0, 0, 0, 0, 0, 1};
        } else {
            // south
            float ran = random.nextFloat();
            if (delta.getX() >= 0 && delta.getZ() > 0) {
                probMap = new int[]{0, 0, 0, 1, 0, 0};
                if (ran < 0.332) {
                    probMap = new int[]{0, 10, 0, 1, 0, 0};
                } else if (ran < 0.52) {
                    probMap = new int[]{0, 0, 0, 1, 0, 10};
                }
            }
            // north
            else if (delta.getX() <= 0 && delta.getZ() < 0) {
                probMap = new int[]{0, 0, 1, 0, 0, 0};
                if (ran < 0.332) {
                    probMap = new int[]{0, 10, 1, 0, 0, 0};
                } else if (ran < 0.52) {
                    probMap = new int[]{0, 0, 0, 1, 10, 0};
                }
            }
            // west
            else if (delta.getX() < 0 && delta.getZ() >= 0) {
                probMap = new int[]{0, 0, 0, 0, 1, 0};
                if (ran < 0.332) {
                    probMap = new int[]{0, 10, 0, 0, 1, 0};
                } else if (ran < 0.52) {
                    probMap = new int[]{0, 0, 0, 10, 1, 0};
                }
            }
            // test east
            else if (delta.getX() > 0 && delta.getZ() <= 0) {
                probMap = new int[]{0, 0, 0, 0, 0, 1};
                if (ran < 0.332) {
                    probMap = new int[]{0, 10, 0, 0, 0, 1};
                } else if (ran < 0.52) {
                    probMap = new int[]{0, 0, 10, 0, 0, 1};
                }
            } else {
                probMap[Direction.UP.ordinal()] = 1;
                probMap[2 + random.nextInt(4)] = 4;
                probMap = new int[]{0, 3, 1 + random.nextInt(2), 1 + random.nextInt(2), 1 + random.nextInt(2), 1 + random.nextInt(2)};
            }

            if (delta.getY() > 15 + random.nextInt(7)) {
                probMap = new int[]{0, 0, random.nextInt(4), random.nextInt(4), random.nextInt(4), random.nextInt(4)};
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
        var en = super.getEnergy(configuration, context);
        return en;
    }
}
