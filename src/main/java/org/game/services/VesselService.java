package org.game.services;

import org.game.gui.Coordinates;
import org.game.map.Surface;
import org.game.unit.GameUnit;
import org.game.unit.Vessel;

import java.util.List;
import java.util.Map;

public interface VesselService {
    void setVessels(Map<String, GameUnit> fleet, Surface [][] map);
    List<Vessel> getVessels(Map <String, GameUnit> fleet);


    void move(Vessel vessel, Coordinates destination, Surface[][] map);
}
