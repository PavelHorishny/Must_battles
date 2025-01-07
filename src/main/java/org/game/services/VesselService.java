package org.game.services;

import org.game.map.Surface;
import org.game.unit.GameUnit;
import org.game.unit.Vessel;

import java.util.List;
import java.util.Map;

public interface VesselService extends Repairable,TakingPartAtRepair,Destroyable,Moveable {
    List<Vessel> getListOfAllVessels(Map <String, GameUnit> fleet);
    void setAllVessels(Map<String, GameUnit> fleet, Surface[][] map);
    void setFortificationService(FortificationService service);

    boolean checkIfCanMove(Vessel vessel);

    boolean checkIfCanShoot(Vessel vessel, Surface[][] map);
}
