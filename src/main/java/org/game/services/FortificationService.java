package org.game.services;

import org.game.map.Surface;
import org.game.unit.Fortification;
import org.game.unit.GameUnit;

import java.util.Map;

public interface FortificationService {
    //public void setNames(Map<String, GameUnit> fleet, List<Fortification> fortifications);
    public void generateFortification(Map<String,GameUnit> fleet);

    //void setPortLocations(Surface[][] map);
    void getStandardFortifications(Surface [][] map, Map<String, GameUnit> fleet);
}
