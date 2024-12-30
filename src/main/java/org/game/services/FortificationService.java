package org.game.services;

import org.game.EndGame;
import org.game.map.Surface;
import org.game.unit.Fortification;
import org.game.unit.GameUnit;

import java.util.Map;
import java.util.Set;

public interface FortificationService {
    //public void setNames(Map<String, GameUnit> fleet, List<Fortification> fortifications);
    //public void generateFortification(Map<String,GameUnit> fleet);
    //void setPortLocations(Surface[][] map);

    void getStandardFortifications(Surface [][] map, Map<String, GameUnit> fleet);
    void setPortLocations(Set<Surface> port, Fortification fortification);
    //void checkFortificationsAtMoveEnd(Map <String,GameUnit> fleet, EndGame endGame);

    void checkFortificationsAtMoveEnd(Map<String, GameUnit> fleet, boolean player);
    void checkFortificationsAtDayEnd(Map<String,GameUnit> fleet, EndGame endGame);
}
