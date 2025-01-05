package org.game.services;


import org.game.gui.Coordinates;
import org.game.map.Surface;
import org.game.unit.GameUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public interface MapService {
    Surface [] [] generateStandardMap();
    void getRandomMap();
    Set<Surface> getPort(Coordinates coordinates, Surface [] [] map);
    void getRoute(GameUnit unit, ArrayList<Surface> route, Surface [][] map);
    void clearRoute(ArrayList<Surface> route);
    List<GameUnit> getFiringZone(GameUnit unit, Surface [] [] map);
    boolean isNotInPort(GameUnit unit,Surface [] [] map);
    boolean checkIfPositionIsPort(Surface[][] map, Coordinates c);

    void addUnit(GameUnit gameUnit, Surface[][] map);
    //TODO remake generateStandardMap (Surface [][] map) <- change signature
}
