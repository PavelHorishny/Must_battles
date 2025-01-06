package org.game.services;

import org.game.gui.Coordinates;
import org.game.map.Surface;
import org.game.unit.GameUnit;
import org.game.unit.Vessel;

import java.util.List;
import java.util.Map;

public interface VesselService extends Repairable,TakingPartAtRepair,Destroyable {
    List<Vessel> getListOfAllVessels(Map <String, GameUnit> fleet);
    void moveVesselToDestinationPoint(Vessel vessel, Coordinates destination, Surface[][] map);
    void setAllVessels(Map<String, GameUnit> fleet, Surface[][] map);
    void setFortificationService(FortificationService service);

    boolean checkIfCanMove(Vessel vessel);

    boolean checkIfCanShoot(Vessel vessel, Surface[][] map);
}
