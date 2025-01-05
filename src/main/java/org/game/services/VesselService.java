package org.game.services;

import org.game.gui.Coordinates;
import org.game.map.Surface;
import org.game.unit.GameUnit;
import org.game.unit.Vessel;

import java.util.List;
import java.util.Map;

public interface VesselService {
    //void setTheFleet(Map<String, GameUnit> fleet, Surface [][] map);
    List<Vessel> getListOfAllVessels(Map <String, GameUnit> fleet);
    //boolean checkIfVesselCanBeRepaired(Vessel vessel, Surface [] [] map);
    void moveVesselToDestinationPoint(Vessel vessel, Coordinates destination, Surface[][] map);
    //void restoreVesselsData(Map<String, GameUnit> fleet);
    void checkVesselsAtDayEnd(Map<String, GameUnit> fleet);
    //boolean checkIfVesselCanHelp(Vessel vessel, Surface[][] map);
    void destroyRepairingVessels(GameUnit unit, Map<String, GameUnit> fleet);

    void setAllVessels(Map<String, GameUnit> fleet, Surface[][] map);
}
