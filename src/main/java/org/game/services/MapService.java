package org.game.services;


import org.game.map.Surface;


public interface MapService {
    Surface [] [] generateStandardMap();
    void getRandomMap();
    //TODO remake generateStandardMap (Surface [][] map) <- change signature
}
