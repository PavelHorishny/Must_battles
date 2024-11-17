package org.game.services;

import org.game.State;
import org.game.map.Surface;
import org.game.unit.Fortification;

public interface MapService {
    Surface [] [] generateStandardMap();
    void setPortLocations(Surface [][] map, Fortification fortification);
    void getRandomMap();

}
