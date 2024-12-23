package org.game.services;

import org.game.CardinalPoint;
import org.game.gui.Constants;
import org.game.gui.Coordinates;
import org.game.gui.panels.PanelsConstrains;
import org.game.map.Surface;
import org.game.map.SurfaceType;

import org.game.mockData.MockedData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    public void getRandomMap() {

    }

    /**
     * @param coordinates object
     * @return Set<Surface>
     */
    @Override
    public Set<Surface> getPort(Coordinates coordinates, Surface [] [] map) {
        Set<Surface> tmp = new HashSet<>();
        Arrays.stream(CardinalPoint.cardinalPoints).forEach(cardinalPoint -> {
            if(checkIfPositionValid(map,new Coordinates(coordinates.axisX()+cardinalPoint.getValue().axisX(),coordinates.axisY()+cardinalPoint.getValue().axisY()))){
                map[coordinates.axisX()+cardinalPoint.getValue().axisX()][coordinates.axisY()+cardinalPoint.getValue().axisY()].setType(SurfaceType.PORT);
                tmp.add(map[coordinates.axisX()+cardinalPoint.getValue().axisX()][coordinates.axisY()+cardinalPoint.getValue().axisY()]);
                //TODO add missing check on unit presence in surface object
            }
        });
        return tmp;
    }

    /**
     * @param coordinates
     * @param route
     * @param map
     */
    @Override
    public void getRoute(Coordinates coordinates, int current_move_points,  ArrayList<Surface> route, Surface[][] map) {
        Arrays.stream(CardinalPoint.cardinalPoints).forEach(cardinalPoint -> {
                for(int i = 1; i<=current_move_points; i++){
                    if(checkIfPositionValid(map,new Coordinates(coordinates.axisX()+cardinalPoint.getValue().axisX()*i,coordinates.axisY()+cardinalPoint.getValue().axisY()*i))){
                        //if(map[coordinates.axisX()+cardinalPoint.getValue().axisX()*i][coordinates.axisY()+cardinalPoint.getValue().axisY()*i].getType().equals(SurfaceType.PORT)) break;
                        map[coordinates.axisX()+cardinalPoint.getValue().axisX()*i][coordinates.axisY()+cardinalPoint.getValue().axisY()*i].setType(SurfaceType.ROUTE);
                        route.add(map[coordinates.axisX()+cardinalPoint.getValue().axisX()*i][coordinates.axisY()+cardinalPoint.getValue().axisY()*i]);
                    }else {
                        break;
                    }
                }
        });
        //route.forEach(System.out::println);
    }

    /**
     * @param route
     */
    @Override
    public void clearRoute(ArrayList<Surface> route) {
        route.forEach(surface -> surface.setType(SurfaceType.WATER));
        route.clear();
    }

    /**
     * Method accepts Surface multidimensional array and int
     * returns boolean
     * checks if int in bounds of sub array*/
    private boolean checkValidPositionOnAxisY(Surface [][] map, int index){
        return index >= 0 && index < map[0].length;
    }
    /**
     * @param map, index
     * @return boolean
     */
    private boolean checkValidPositionOnAxisX(Surface [][] map, int index){
        return index >= 0 && index < map.length;
    }
    /**
     * Accepts Surface multidimensional array and Coordinate object
     * Returns boolean
     * Checks if Coordinate object might be in bounds of multidimensional array*/
    private boolean checkIfPositionValid(Surface[][] map, Coordinates coordinates){
        if(checkValidPositionOnAxisX(map,coordinates.axisX())){
            if(checkValidPositionOnAxisY(map,coordinates.axisY())){
                return checkIfSurfaceIsWaterOrPort(map[coordinates.axisX()][coordinates.axisY()]);
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
    /**
     * Method accepts Surface object
     * returns boolean
     * returns true if SurfaceType of Surface object is WATER*/
    private boolean checkIfSurfaceIsWaterOrPort(Surface surface){
        return surface.getType() == SurfaceType.WATER||surface.getType() == SurfaceType.PORT;
    }
}
