package org.game.services;

import org.game.gui.Constants;
import org.game.gui.Coordinates;
import org.game.gui.panels.PanelsConstrains;
import org.game.map.Surface;
import org.game.map.SurfaceType;

import org.game.mockData.MockedData;

import java.util.Arrays;

public class MapProcessor implements MapService{

    @Override
    public Surface[][] getStaticMap() {
        Surface [] [] tmp = new Surface[PanelsConstrains.GAME_AREA_SETTINGS.getPreferredSize().width/ Constants.CELL_SIZE][PanelsConstrains.GAME_AREA_SETTINGS.getPreferredSize().height/Constants.CELL_SIZE];
        for (int i = 0; i < tmp.length; i++){
            for (int y = 0; y < tmp[0].length; y++){
                tmp[i][y] = new Surface(new Coordinates(i,y), SurfaceType.WATER);
            }
        }
        Arrays.stream(MockedData.LAND).forEach(land->
                tmp[land.getCoordinates().axisX()][land.getCoordinates().axisY()] = land);
        return tmp;
    }

    @Override
    public void getRandomMap() {

    }
}
