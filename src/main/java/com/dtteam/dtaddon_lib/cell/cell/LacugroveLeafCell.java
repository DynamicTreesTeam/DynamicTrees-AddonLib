package com.dtteam.dtaddon_lib.cell.cell;

import com.ferreusveritas.dynamictrees.cell.MatrixCell;

public class LacugroveLeafCell extends MatrixCell {

    public LacugroveLeafCell(int value) { super(value, valMap); }

    static final byte[] valMap = {
            0, 1, 2, 3, 3, 4, 0, 0, // D Maps 4 -> 3, * -> *
            0, 1, 2, 3, 3, 4, 0, 0, // U Maps 4 -> 3, * -> *
            0, 1, 2, 3, 4, 5, 0, 0, // N Maps * -> *
            0, 1, 2, 3, 4, 5, 0, 0, // S Maps * -> *
            0, 1, 2, 3, 4, 5, 0, 0, // W Maps * -> *
            0, 1, 2, 3, 4, 5, 0, 0  // E Maps * -> *
    };

}
