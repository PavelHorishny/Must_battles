package org.game.services;

import org.game.unit.GameUnit;

import java.util.ArrayList;
import java.util.List;

public interface FiringService {
    void setUnderAttack(List<GameUnit> inFiringZone, ArrayList<GameUnit> aimedUnits, boolean isFirstPlayer);
}
