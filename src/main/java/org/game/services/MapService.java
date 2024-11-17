package org.game.services;

import org.game.map.Surface;

public interface MapService {
    Surface [] [] getStaticMap();
    void getRandomMap();
}
