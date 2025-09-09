package com.dtteam.dtaddon_lib.cell;

import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;
import com.ferreusveritas.dynamictrees.api.cell.Cell;
import com.ferreusveritas.dynamictrees.api.cell.CellKit;
import com.ferreusveritas.dynamictrees.api.cell.CellNull;
import com.ferreusveritas.dynamictrees.api.cell.CellSolver;
import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.cell.*;
import com.ferreusveritas.dynamictrees.util.SimpleVoxmap;
import com.dtteam.dtaddon_lib.cell.cell.*;
import net.minecraft.core.Direction;

public class DTAddonLibCellKits {

    public static final CellKit ALLIUM = new CellKit(DynamicTreesAddonLib.location("allium")) {

        private final Cell alliumBranch = new NormalCell(4);

        private final Cell[] alliumLeafCells = {
                CellNull.NULL_CELL,
                new AlliumLeafCell(1),
                new AlliumLeafCell(2),
                new AlliumLeafCell(3),
                new AlliumLeafCell(4),
                new AlliumLeafCell(5),
                new AlliumLeafCell(6),
                new AlliumLeafCell(7)
        };

        private final CellKits.BasicSolver alliumSolver = new CellKits.BasicSolver(new short[]{0x0413, 0x0322, 0x0311, 0x0211});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return alliumLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 3 ? alliumBranch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.ALLIUM;
        }

        @Override
        public CellSolver getCellSolver() {
            return alliumSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit BAMBOO = new CellKit(DynamicTreesAddonLib.location("bamboo")) {

        private final Cell bambooTopBranch = new BambooTopBranchCell();
        private final Cell bambooUpperTrunk = new NormalCell(2);

        private final Cell[] bambooLeaves = new Cell[] {
                CellNull.NULL_CELL,
                new BambooLeafCell(1),
                new BambooLeafCell(2),
                new BambooLeafCell(3),
                new BambooLeafCell(4),
                new BambooLeafCell(5),
                new BambooLeafCell(6),
                new BambooLeafCell(7)
        };

        private final CellSolver solver = new CellKits.BasicSolver(new short[] {
                0x0514, 0x0423, 0x0411, 0x0312, 0x0211
        });

        @Override
        public Cell getCellForLeaves(int hydro) {
            return bambooLeaves[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (meta == MetadataCell.TOP_BRANCH) return bambooTopBranch;
            if (radius == 2) return bambooUpperTrunk;
            return CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.BAMBOO;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit BLIGHTWILLOW = new CellKit(DynamicTreesAddonLib.location("blightwillow")) {

        private final Cell blightwillowBranch = new NormalCell(3);
        private final Cell coniferTopBranch = new ConiferTopBranchCell();

        private final Cell[] coniferLeafCells = {
                CellNull.NULL_CELL,
                new BlightwillowLeafCell(1),
                new BlightwillowLeafCell(2),
                new BlightwillowLeafCell(3),
                new BlightwillowLeafCell(4),
                new BlightwillowLeafCell(5),
                new BlightwillowLeafCell(6),
                new BlightwillowLeafCell(7)
        };

        private final CellKits.BasicSolver blightwillowSolver = new CellKits.BasicSolver(new short[]{
                0x0514, 0x0413, 0x0312, 0x0221
        });

        @Override
        public Cell getCellForLeaves(int hydro) {
            return coniferLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (meta == MetadataCell.TOP_BRANCH) {
                return coniferTopBranch;
            } else if (radius == 1) {
                return blightwillowBranch;
            } else {
                return CellNull.NULL_CELL;
            }
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.BLIGHTWILLOW;
        }

        @Override
        public CellSolver getCellSolver() {
            return blightwillowSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit BRUSH = new CellKit(DynamicTreesAddonLib.location("brush")) {

        private final Cell branch = new Cell() {
            @Override
            public int getValue() {
                return 5;
            }

            final int[] map = {3, 3, 5, 5, 5, 5};

            @Override
            public int getValueFromSide(Direction side) {
                return map[side.ordinal()];
            }
        };

        private final Cell[] normalCells = {
                CellNull.NULL_CELL,
                new NormalCell(1),
                new NormalCell(2),
                new NormalCell(3),
                new NormalCell(4),
                new NormalCell(5),
                new NormalCell(6),
                new NormalCell(7),
        };

        private final CellSolver solver = new CellKits.BasicSolver(new short[]{
                0x0513, 0x0412, 0x0322, 0x0311, 0x0211,
        });

        @Override
        public Cell getCellForLeaves(int hydro) {
            return normalCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (radius == 1) return branch;
            return CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.BRUSH;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 3;
        }

    };

    public static final CellKit BUSHY = new CellKit(DynamicTreesAddonLib.location("bushy")) {

        private final Cell branchCell = new BushyBranchCell();
        private final Cell coniferTopBranch = new ConiferTopBranchCell();

        private final Cell[] coniferLeafCells = {
                CellNull.NULL_CELL,
                new BushyLeafCell(1),
                new BushyLeafCell(2),
                new BushyLeafCell(3),
                new BushyLeafCell(4),
                new BushyLeafCell(5),
                new BushyLeafCell(6),
                new BushyLeafCell(7)
        };

        private final CellKits.BasicSolver solver = new CellKits.BasicSolver(new short[]{0x0614, 0x0513, 0x0423, 0x0322, 0x0411, 0x0211});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return coniferLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (meta == MetadataCell.TOP_BRANCH) {
                return coniferTopBranch;
            } else if (radius == 1) {
                return branchCell;
            } else {
                return CellNull.NULL_CELL;
            }
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.BUSHY;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit COBALT = new CellKit(DynamicTreesAddonLib.location("cobalt")) {

        private final Cell branch = new Cell(){
            @Override
            public int getValue() {
                return 3;
            }
            @Override
            public int getValueFromSide(Direction side) {
                return side == Direction.DOWN ? 0 : getValue();
            }
        };

        private final Cell[] normalCells = {
                CellNull.NULL_CELL,
                new NormalCell(1),
                new NormalCell(2),
                new NormalCell(3),
                new NormalCell(4),
                new NormalCell(5),
                new NormalCell(6),
                new NormalCell(7),
        };

        private final CellSolver solver = new CellKits.BasicSolver(new short[]{0x0312, 0x0221});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return normalCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta)  {
            return radius == 3 ? branch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.COBALT;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 2;
        }

    };

    public static final CellKit DARKWOOD = new CellKit(DynamicTreesAddonLib.location("darkwood")) {

        /** Typical branch with hydration 5 */
        private final Cell branchCell = new NormalCell(8);

        private final Cell[] darkOakLeafCells = {
                CellNull.NULL_CELL,
                new DarkwoodLeafCell(1),
                new DarkwoodLeafCell(2),
                new DarkwoodLeafCell(3),
                new DarkwoodLeafCell(4),
                new DarkwoodLeafCell(5),
                new DarkwoodLeafCell(6),
                new DarkwoodLeafCell(7)
        };

        private final CellKits.BasicSolver darkOakSolver = new CellKits.BasicSolver(new short[]{0x0817, 0x0726, 0x0715, 0x0615, 0x0514, 0x0413, 0x0322, 0x0221});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return darkOakLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 1 ? branchCell : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.DARKWOOD;
        }

        @Override
        public CellSolver getCellSolver() {
            return darkOakSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 7;
        }

    };

    public static final CellKit DOME = new CellKit(DynamicTreesAddonLib.location("dome")) {

        private final Cell acaciaBranch = new Cell() {
            @Override
            public int getValue() {
                return 5;
            }

            final int[] map = {0, 3, 5, 5, 5, 5};

            @Override
            public int getValueFromSide(Direction side) {
                return map[side.ordinal()];
            }

        };

        private final Cell[] acaciaLeafCells = {
                CellNull.NULL_CELL,
                new AcaciaLeafCell(1),
                new AcaciaLeafCell(2),
                new AcaciaLeafCell(3),
                new AcaciaLeafCell(4),
                new AcaciaLeafCell(5),
                new AcaciaLeafCell(6),
                new AcaciaLeafCell(7)
        };

        private final CellKits.BasicSolver acaciaSolver = new CellKits.BasicSolver(new short[]{0x0514, 0x0423, 0x0412, 0x0312, 0x0211});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return acaciaLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 1 ? acaciaBranch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return LeafClusters.ACACIA;
        }

        @Override
        public CellSolver getCellSolver() {
            return acaciaSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit EUCALYPTUS = new CellKit(DynamicTreesAddonLib.location("eucalyptus")) {

        private final Cell eucalyptusTopBranch = new EucalyptusTopBranchCell();
        private final Cell eucalyptusBranch = new NormalCell(2);
        private final Cell eucalyptusUpperTrunk = new NormalCell(3);

        private final Cell[] eucalyptusLeaves = new Cell[]{
                CellNull.NULL_CELL,
                new EucalyptusLeafCell(1),
                new EucalyptusLeafCell(2),
                new EucalyptusLeafCell(3),
                new EucalyptusLeafCell(4),
                new EucalyptusLeafCell(5),
                new EucalyptusLeafCell(6),
                new EucalyptusLeafCell(7),
        };

        private final CellSolver solver = new CellKits.BasicSolver(new short[]{
                0x0514, 0x0423, 0x0411, 0x0312, 0x0211
        });

        @Override
        public Cell getCellForLeaves(int hydro) {
            return eucalyptusLeaves[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (meta == MetadataCell.TOP_BRANCH) return eucalyptusTopBranch;
            if (radius == 1) return eucalyptusBranch;
            if (radius <= 3) return eucalyptusUpperTrunk;
            return CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.EUCALYPTUS;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit HELLBARK = new CellKit(DynamicTreesAddonLib.location("hellbark")) {

        private final Cell hellbarkBranch = new Cell() {
            @Override
            public int getValue() {
                return 5;
            }

            final int[] map = {0, 7, 5, 5, 5, 5};

            @Override
            public int getValueFromSide(Direction side) {
                return map[side.ordinal()];
            }

        };

        private final Cell[] hellbarkLeafCells = {
                CellNull.NULL_CELL,
                new HellbarkLeafCell(1),
                new HellbarkLeafCell(2),
                new HellbarkLeafCell(3),
                new HellbarkLeafCell(4),
                new HellbarkLeafCell(5),
                new HellbarkLeafCell(6),
                new HellbarkLeafCell(7)
        };

        private final CellKits.BasicSolver hellbarkSolver = new CellKits.BasicSolver(new short[]{0x0716, 0x0615, 0x0514, 0x0423, 0x0312, 0x0221});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return hellbarkLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius <= 4 ? hellbarkBranch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.HELLBARK;
        }

        @Override
        public CellSolver getCellSolver() {
            return hellbarkSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 6;
        }

    };

    public static final CellKit HELLBARK_SPARSE = new CellKit(DynamicTreesAddonLib.location("hellbark_sparse")) {

        private final Cell sparseBranch = new SparseBranchCell();
        private final Cell sparseLeaves = new NormalCell(1);

        private final CellSolver solver = new CellKits.BasicSolver(new short[] {0x0211});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return hydro > 0 ? sparseLeaves : CellNull.NULL_CELL;
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius <= 3 ? sparseBranch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.SPARSE;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 1;
        }
    };

    public static final CellKit LACUGROVE = new CellKit(DynamicTreesAddonLib.location("lacugrove")) {

        private final Cell branchCell = new NormalCell(5);

        private final Cell[] lacugroveLeafCells = {
                CellNull.NULL_CELL,
                new LacugroveLeafCell(1),
                new LacugroveLeafCell(2),
                new LacugroveLeafCell(3),
                new LacugroveLeafCell(4),
                new LacugroveLeafCell(5),
                new LacugroveLeafCell(6),
                new LacugroveLeafCell(7)
        };

        private final CellKits.BasicSolver lacugroveSolver =
                new CellKits.BasicSolver(new short[]{0x0514, 0x0423, 0x0412, 0x0312, 0x0211});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return lacugroveLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 1 ? this.branchCell : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() { return DTAddonLibLeafClusters.LACUGROVE; }

        @Override
        public CellSolver getCellSolver() { return lacugroveSolver; }

        @Override
        public int getDefaultHydration() {
            return 6;
        }

    };

    public static final CellKit JOSHUA = new CellKit(DynamicTreesAddonLib.location("joshua")) {

        private final Cell branch = new Cell() {
            @Override
            public int getValue() {
                return 5;
            }

            @Override
            public int getValueFromSide(Direction side) {
                return side == Direction.UP ? getValue() : 0;
            }

        };

        private final Cell[] frondCells = {
                CellNull.NULL_CELL,
                new JoshuaFrondCell(1),
                new JoshuaFrondCell(2),
                new JoshuaFrondCell(3),
                new JoshuaFrondCell(4),
                new JoshuaFrondCell(5),
                new JoshuaFrondCell(6),
                new JoshuaFrondCell(7)
        };

        private final CellKits.BasicSolver joshuaSolver = new CellKits.BasicSolver(new short[]{0x0514, 0x0413});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return frondCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 2 ? branch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.JOSHUA;
        }

        @Override
        public CellSolver getCellSolver() {
            return joshuaSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

        class JoshuaFrondCell extends MatrixCell {

            public JoshuaFrondCell(int value) {
                super(value, valMap);
            }

            static final byte[] valMap = {
                    0, 0, 0, 0, 0, 0, 0, 0, //D Maps * -> 0
                    0, 1, 2, 3, 4, 5, 6, 7, //U Maps
                    0, 0, 0, 0, 0, 0, 0, 0, //N Maps * -> 0
                    0, 0, 0, 0, 0, 0, 0, 0, //S Maps * -> 0
                    0, 0, 0, 0, 0, 0, 0, 0, //W Maps * -> 0
                    0, 0, 0, 0, 0, 0, 0, 0  //E Maps * -> 0
            };

        }
    };

    public static final CellKit LAMENT = new CellKit(DynamicTreesAddonLib.location("lament")) {

        private final Cell lamentBranch = new Cell() {
            @Override
            public int getValue() {
                return 5;
            }

            final int[] map = {0, 2, 4, 4, 4, 4};

            @Override
            public int getValueFromSide(Direction side) {
                return map[side.ordinal()];
            }

        };

        private final Cell[] lamentLeafCells = {
                CellNull.NULL_CELL,
                new LamentLeafCell(1),
                new LamentLeafCell(2),
                new LamentLeafCell(3),
                new LamentLeafCell(4),
                new LamentLeafCell(5),
                new LamentLeafCell(6),
                new LamentLeafCell(7)
        };

        private final CellKits.BasicSolver lamentSolver = new CellKits.BasicSolver(new short[]{0x0413, 0x0322, 0x0311, 0x0211});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return lamentLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 1 ? lamentBranch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.LAMENT;
        }

        @Override
        public CellSolver getCellSolver() {
            return lamentSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 3;
        }

    };

    public static final CellKit MAHOGANY = new CellKit(DynamicTreesAddonLib.location("mahogany")) {

        private final Cell mahoganyBranch = new MahoganyBranchCell();

        private final Cell[] mahoganyLeafCells = {
                CellNull.NULL_CELL,
                new MahoganyLeafCell(1),
                new MahoganyLeafCell(2),
                new MahoganyLeafCell(3),
                new MahoganyLeafCell(4),
                new MahoganyLeafCell(5),
                new MahoganyLeafCell(6),
                new MahoganyLeafCell(7),
        };

        private final CellSolver solver = new CellKits.BasicSolver(new short[]{
                0x0513, 0x0413, 0x0322, 0x0311, 0x0211
        });

        @Override
        public Cell getCellForLeaves(int hydro) {
            return mahoganyLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (radius == 1) return mahoganyBranch;
            return CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.MAHOGANY;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 3;
        }

    };

    public static final CellKit POPLAR = new CellKit(DynamicTreesAddonLib.location("poplar")) {

        private final Cell poplarBranch = new PoplarBranchCell();
        private final Cell poplarTopBranch = new PoplarTopBranchCell();
        private final Cell poplarUpperTrunk = new NormalCell(4);

        private final Cell[] poplarLeaves = new Cell[] {
                CellNull.NULL_CELL,
                new PoplarLeafCell(1),
                new PoplarLeafCell(2),
                new PoplarLeafCell(3),
                new PoplarLeafCell(4),
        };

        private final CellSolver solver = new CellKits.BasicSolver(new short[] {
                0x0412, 0x0311, 0x0211
        });

        @Override
        public Cell getCellForLeaves(int hydro) {
            return poplarLeaves[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (meta == MetadataCell.TOP_BRANCH) return poplarTopBranch;
            if (radius == 1) return poplarBranch;
            if (radius < 4) return poplarUpperTrunk;
            return CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.POPLAR;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit ROSE = new CellKit(DynamicTreesAddonLib.location("rose")) {

        private final Cell roseBranch = new Cell() {
            final int[] map = new int[]{0, 3, 8, 8, 8, 8};

            public int getValue() {
                return 8;
            }

            public int getValueFromSide(Direction side) {
                return this.map[side.ordinal()];
            }
        };

        private final Cell[] roseLeafCells = {
                CellNull.NULL_CELL,
                new RoseLeafCell(1),
                new RoseLeafCell(2),
                new RoseLeafCell(3),
                new RoseLeafCell(4),
                new RoseLeafCell(5),
                new RoseLeafCell(6),
                new RoseLeafCell(7)
        };

        private final CellKits.BasicSolver roseSolver = new CellKits.BasicSolver(new short[]{0x0817, 0x0726, 0x0715, 0x0514, 0x0613, 0x0311, 0x0412, 0x0211});
        @Override
        public Cell getCellForLeaves(int hydro) {
            return roseLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 3 ? roseBranch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.ROSE;
        }

        @Override
        public CellSolver getCellSolver() {
            return roseSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 7;
        }

    };

    public static final CellKit ROUND_CONIFER = new CellKit(DynamicTreesAddonLib.location("round_conifer")) {

        private final Cell coniferBranch = new NormalCell(3);
        private final Cell coniferTopBranch = new ConiferTopBranchCell();

        private final Cell[] coniferLeafCells = {
                CellNull.NULL_CELL,
                new ConiferLeafCell2(1),
                new ConiferLeafCell2(2),
                new ConiferLeafCell2(3),
                new ConiferLeafCell2(4),
                new ConiferLeafCell2(5),
                new ConiferLeafCell2(6),
                new ConiferLeafCell2(7)
        };

        private final CellKits.BasicSolver coniferSolver =
                new CellKits.BasicSolver(new short[]{0x0514, 0x0413, 0x0312, 0x0221});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return coniferLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (meta == MetadataCell.TOP_BRANCH) {
                return coniferTopBranch;
            } else if (radius == 1) {
                return coniferBranch;
            } else {
                return CellNull.NULL_CELL;
            }
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.ROUND_CONIFER;
        }

        @Override
        public CellSolver getCellSolver() {
            return coniferSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit SKYRIS = new CellKit(DynamicTreesAddonLib.location("skyris")) {
        private final Cell skyrisBranch = new Cell() {
            final int[] map = new int[]{0, 3, 5, 5, 5, 5};

            public int getValue() {
                return 5;
            }

            public int getValueFromSide(Direction side) {
                return this.map[side.ordinal()];
            }
        };
        private final Cell coniferTopBranch = new ConiferTopBranchCell();
        private final Cell[] skyrisLeafCells;
        private final CellKits.BasicSolver skyrisSolver;

        {
            this.skyrisLeafCells = new Cell[]{CellNull.NULL_CELL, new AcaciaLeafCell(1), new AcaciaLeafCell(2), new AcaciaLeafCell(3), new AcaciaLeafCell(4), new AcaciaLeafCell(5), new AcaciaLeafCell(6), new AcaciaLeafCell(7)};
            this.skyrisSolver = new CellKits.BasicSolver(new short[]{1300, 1059, 1042, 786, 529});
        }

        public Cell getCellForLeaves(int hydro) {
            return this.skyrisLeafCells[hydro];
        }

        public Cell getCellForBranch(int radius, int meta) {
            if (meta == 1) {
                return this.coniferTopBranch;
            } else {
                return radius == 1 ? this.skyrisBranch : CellNull.NULL_CELL;
            }
        }

        public SimpleVoxmap getLeafCluster() {
            return LeafClusters.ACACIA;
        }

        public CellSolver getCellSolver() {
            return this.skyrisSolver;
        }

        public int getDefaultHydration() {
            return 4;
        }
    };

    public static final CellKit SMALL_DECIDUOUS = new CellKit(DynamicTreesAddonLib.location("small_deciduous")) {

        private final Cell sparseBranch = new NormalCell(4);
        private final Cell sparseLeaves = new NormalCell(1);

        private final CellSolver solver = new CellKits.BasicSolver(new short[] {0x0211});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return hydro > 0 ? sparseLeaves : CellNull.NULL_CELL;
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 1 ? sparseBranch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.SPARSE;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 1;
        }

    };

    public static final CellKit SPARSE = new CellKit(DynamicTreesAddonLib.location("sparse")) {

        private final Cell sparseBranch = new SparseBranchCell();
        private final Cell sparseLeaves = new NormalCell(1);

        private final CellSolver solver = new CellKits.BasicSolver(new short[] {0x0211});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return hydro > 0 ? sparseLeaves : CellNull.NULL_CELL;
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 1 ? sparseBranch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.SPARSE;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 1;
        }

    };

    public static final CellKit SOULBLIGHT = new CellKit(DynamicTreesAddonLib.location("soulblight")) {

        private final Cell[] soulblightCells = {
                CellNull.NULL_CELL,
                new SoulblightLeafCell(1),
                new SoulblightLeafCell(2),
                new SoulblightLeafCell(3),
                new SoulblightLeafCell(4),
                new SoulblightLeafCell(5),
                new SoulblightLeafCell(6),
                new SoulblightLeafCell(7)
        };

        private final Cell branchCell = new Cell() {
            @Override
            public int getValue() {
                return 4;
            }

            final int[] map = {0, 5, 4, 4, 4, 4};

            @Override
            public int getValueFromSide(Direction side) {
                return map[side.ordinal()];
            }

        };

        private final CellSolver SoulblightSolver = new CellKits.BasicSolver(new short[]{0x512, 0x0413, 0x0322, 0x0311, 0x0211});
        @Override
        public Cell getCellForLeaves(int hydro) {
            return soulblightCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (radius == 3) {
                return branchCell;
            }
            return CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.SOULBLIGHT;
        }

        @Override
        public CellSolver getCellSolver() {
            return SoulblightSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit SYTHIAN_FUNGUS = new CellKit(DynamicTreesAddonLib.location("sythian_fungus")) {

        private final Cell sythianBranch = new SythianWartCell(3);
        private final Cell sythianTopBranch = new SythianWartCell(4);

        private final Cell[] sythianLeafCells = {
                CellNull.NULL_CELL,
                new SythianWartCell(1),
                new SythianWartCell(2),
                new SythianWartCell(3),
                new SythianWartCell(4),
                new SythianWartCell(5),
                new SythianWartCell(6),
                new SythianWartCell(7)
        };

        private final CellKits.BasicSolver sythianSolver = new CellKits.BasicSolver(new short[]{0x0411, 0x0312, 0x0221});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return sythianLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (meta == MetadataCell.TOP_BRANCH) {
                return sythianTopBranch;
            } else if (radius == 3){
                return sythianBranch;
            } else {
                return CellNull.NULL_CELL;
            }
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.SYTHIAN_FUNGUS;
        }

        @Override
        public CellSolver getCellSolver() {
            return sythianSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit WIDE_DARK_OAK = new CellKit(DynamicTreesAddonLib.location("wide_dark_oak")) {

        /** Typical branch with hydration 5 */
        private final Cell branchCell = new NormalCell(8);

        private final Cell[] darkOakLeafCells = {
                CellNull.NULL_CELL,
                new WideDarkOakLeafCell(1),
                new WideDarkOakLeafCell(2),
                new WideDarkOakLeafCell(3),
                new WideDarkOakLeafCell(4),
                new WideDarkOakLeafCell(5),
                new WideDarkOakLeafCell(6),
                new WideDarkOakLeafCell(7)
        };

        private final CellKits.BasicSolver darkOakSolver = new CellKits.BasicSolver(new short[]{0x0817, 0x0726, 0x0715, 0x0615, 0x0514, 0x0413, 0x0322, 0x0221});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return darkOakLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 1 ? branchCell : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.WIDE_DARK_OAK;
        }

        @Override
        public CellSolver getCellSolver() {
            return darkOakSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 7;
        }

    };

    public static final CellKit WILLOW = new CellKit(DynamicTreesAddonLib.location("willow")) {

        private final Cell branch = new WillowBranchCell();

        private final Cell[] willowLeafCells = {
                CellNull.NULL_CELL,
                new WillowLeafCell(1),
                new WillowLeafCell(2),
                new WillowLeafCell(3),
                new WillowLeafCell(4),
                new WillowLeafCell(5),
                new WillowLeafCell(6),
                new WillowLeafCell(7)
        };

        private final CellKits.BasicSolver solver = new CellKits.BasicSolver(new short[]{0x0817, 0x0726, 0x0625, 0x0714, 0x0614, 0x0514, 0x0413, 0x0312, 0x0211});

        @Override
        public Cell getCellForLeaves(int distance) {
            return this.willowLeafCells[distance];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 1 ? this.branch : CellNull.NULL_CELL;
        }

        @Override
        public CellSolver getCellSolver() {
            return this.solver;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTAddonLibLeafClusters.WILLOW;
        }

        @Override
        public int getDefaultHydration() {
            return 7;
        }
    };

    public static void register(final Registry<CellKit> registry) {
        registry.registerAll(ALLIUM, BAMBOO, BRUSH, BLIGHTWILLOW, BUSHY, COBALT, DARKWOOD, DOME, EUCALYPTUS, HELLBARK,
                HELLBARK_SPARSE, JOSHUA, LACUGROVE, LAMENT, MAHOGANY, POPLAR, ROSE, ROUND_CONIFER, SKYRIS,
                SMALL_DECIDUOUS, SPARSE, SOULBLIGHT, SYTHIAN_FUNGUS, WIDE_DARK_OAK, WILLOW);
    }

}
