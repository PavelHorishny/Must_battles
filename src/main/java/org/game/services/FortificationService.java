package org.game.services;

import org.game.unit.Fortification;
import org.game.unit.GameUnit;

import java.util.List;
import java.util.Map;

public interface FortificationService {
    public void setNames(Map<String, GameUnit> fleet, List<Fortification> fortifications);
}
