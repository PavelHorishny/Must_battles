package org.game.mockData;

import org.game.gui.Constants;
import org.game.gui.Coordinates;
import org.game.gui.MapCell;
import org.game.map.Surface;
import org.game.map.SurfaceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StandardMapTest {
    ArrayList<MapCell> map;
    Surface [] [] backMap;
    @BeforeEach
    void setUp(){
        map = StandardMap.MAP;
        backMap = StandardMap.BACK_MAP;
    }
    @Test
    void listSize(){
        assertEquals(736,map.size());
    }
    @Test
    void checkIfLandAdded(){
        assertTrue(map.stream().filter(mapCell -> mapCell.getCoordinates().equals(new Coordinates(3, 0))).anyMatch(mapCell -> mapCell.getColor().equals(Constants.LAND)));
        assertTrue(map.stream().filter(mapCell -> mapCell.getCoordinates().equals(new Coordinates(7, 14))).anyMatch(mapCell -> mapCell.getColor().equals(Constants.LAND)));
        assertTrue(map.stream().filter(mapCell -> mapCell.getCoordinates().equals(new Coordinates(29, 22))).anyMatch(mapCell -> mapCell.getColor().equals(Constants.LAND)));
        assertFalse(map.stream().filter(mapCell -> mapCell.getCoordinates().equals(new Coordinates(3, 21))).anyMatch(mapCell -> mapCell.getColor().equals(Constants.LAND)));
    }
    @Test
    void checkSurfaceArray(){
        assertSame(backMap[3][0].getType(), SurfaceType.LAND);
        assertEquals(backMap[3][0].getCoordinates(),new Coordinates(3,0));
        assertSame(backMap[3][0].getCoordinates().axisX(),3);
        assertSame(backMap[3][0].getCoordinates().axisY(),0);
    }
}