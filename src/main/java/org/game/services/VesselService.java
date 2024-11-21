package org.game.services;

import org.game.map.Surface;
import org.game.unit.GameUnit;

import java.util.Map;

public interface VesselService {
    void getVessels(Map<String, GameUnit> fleet, Surface [][] map);
}
