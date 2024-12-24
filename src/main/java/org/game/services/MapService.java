package org.game.services;


import org.game.gui.Coordinates;
import org.game.map.Surface;
import org.game.unit.Vessel;

import java.util.ArrayList;
import java.util.Set;


public interface MapService {
    Surface [] [] generateStandardMap();
    void getRandomMap();
    Set<Surface> getPort(Coordinates coordinates, Surface [] [] map);
    void getRoute(Vessel vessel, ArrayList<Surface> route, Surface [][] map);
    void clearRoute(ArrayList<Surface> route);
    //TODO remake generateStandardMap (Surface [][] map) <- change signature
}
