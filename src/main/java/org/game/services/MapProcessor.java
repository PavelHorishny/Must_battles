package org.game.services;

import org.game.CardinalPoint;
import org.game.gui.Constants;
import org.game.gui.Coordinates;
import org.game.gui.panels.PanelsConstrains;
import org.game.map.Surface;
import org.game.map.SurfaceType;

import org.game.mockData.MockedData;
import org.game.unit.Fortification;

import java.util.Arrays;

public class MapProcessor implements MapService{


    @Override
    public Surface[][] generateStandardMap() {
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
    public void setPortLocations(Surface[][] map, Fortification fortification) {

        Arrays.stream(CardinalPoint.cardinalPoints).forEach(cardinalPoint -> {
           if (checkIfPositionValid(map,new Coordinates(fortification.getCoordinates().axisX()+cardinalPoint.getValue().axisX(),fortification.getCoordinates().axisY()+cardinalPoint.getValue().axisY()))){
               map[fortification.getCoordinates().axisX()+cardinalPoint.getValue().axisX()][fortification.getCoordinates().axisY()+cardinalPoint.getValue().axisY()].setType(SurfaceType.PORT);
               fortification.getPort().add(map[fortification.getCoordinates().axisX()+cardinalPoint.getValue().axisX()][fortification.getCoordinates().axisY()+cardinalPoint.getValue().axisY()]);
           }
        });
    }

    @Override
    public void getRandomMap() {

    }


    private boolean checkIfPositionValid(Surface [][] map, Coordinates coordinates){
        //Surface surface = map[coordinates.axisX()][coordinates.axisY()];
        if(checkValidPositionOnAxisX(map,coordinates)){
            if(checkValidPositionOnAxisY(map,coordinates)){
                return checkIfSurfaceIsWater(map[coordinates.axisX()][coordinates.axisY()]);
            }else {
                return false;
            }
        }else {
            return false;
        }
        //return surface.getCoordinates().axisX() >= 0 && surface.getCoordinates().axisX() < map.length;
    }
    private boolean checkValidPositionOnAxisX(Surface [][] map, Coordinates coordinates){
        return coordinates.axisX() >= 0 && coordinates.axisX() < map.length;
    }
    private boolean checkValidPositionOnAxisY(Surface [][] map, Coordinates coordinates){
        return coordinates.axisY() >= 0 && coordinates.axisY() < map[0].length;
    }
    private boolean checkIfSurfaceIsWater(Surface surface){
        return surface.getType() == SurfaceType.WATER;
    }
}
