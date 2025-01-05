package org.game.services;

import org.game.map.Surface;
import org.game.unit.GameUnit;

public interface Repairable {
    boolean isUnitCanBeRepaired(GameUnit gameUnit, Surface [] [] map);
}
