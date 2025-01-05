package org.game.services;

import org.game.EndGame;
import org.game.map.Surface;
import org.game.unit.Fortification;
import org.game.unit.FortificationType;
import org.game.unit.GameUnit;

import java.util.List;
import java.util.Map;

public interface FortificationService {
    void checkFortificationsAtMoveEnd(Map<String, GameUnit> fleet, boolean player);
    void checkFortificationsAtDayEnd(Map<String,GameUnit> fleet, EndGame endGame);

    void setStandardFortifications(Map<String,GameUnit> fleet, Surface [] [] map);

    List<Fortification> getFortificationsOfPlayer(Map<String, GameUnit> fleet, boolean isFirstPlayer);

    List<Fortification> getFortificationsByType(List<Fortification> forts, FortificationType firstLineFort);

    Fortification getFortWIthBigPort(List<Fortification> fortificationsOfPlayer);

}
