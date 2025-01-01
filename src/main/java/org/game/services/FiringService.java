package org.game.services;

import org.game.unit.GameUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface FiringService {
    void setUnderAttack(List<GameUnit> inFiringZone, ArrayList<GameUnit> aimedUnits, GameUnit attacker);
    Optional<GameUnit> shot(GameUnit attacker, GameUnit target);
    Optional<GameUnit> salvoShot(GameUnit attacker, GameUnit target);
    void clearAimed(ArrayList<GameUnit> aimedUnits);
}
