package org.game.services;

import org.game.map.Surface;
import org.game.unit.GameUnit;

public interface TakingPartAtRepair {
    boolean isUnitReadyToTakePartAtRepair(GameUnit gameUnit, Surface [] [] map);
    void setHelpInRepairStates(GameUnit selected, boolean state);
}
