package org.game.services;

import org.game.map.Surface;
import org.game.unit.Fortification;
import org.game.unit.FortificationType;
import org.game.unit.GameUnit;

import java.util.List;
import java.util.Map;

public interface FortificationService extends Repairable,Destroyable{
    boolean isRoyalPortIsNotEmpty(Fortification fortification);

    void setStandardFortifications(Map<String,GameUnit> fleet, Surface [] [] map);

    List<Fortification> getFortificationsOfPlayer(Map<String, GameUnit> fleet, boolean isFirstPlayer);

    List<Fortification> getFortificationsByType(List<Fortification> forts, FortificationType firstLineFort);

    Fortification getFortWIthBigPort(List<Fortification> fortificationsOfPlayer);
    void setVesselService(VesselService service);

    List<Fortification> getAllFortifications(Map<String, GameUnit> fleet);


    String testString(Fortification fortification);

    boolean checkIfPortIsRoyal(Fortification fortification);

    boolean checkIfCanShoot(Fortification fortification);

    void restoreRoyalPort(Fortification fortification);
}
