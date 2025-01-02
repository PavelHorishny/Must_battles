package org.game.services;

import org.game.CardinalPoint;
import org.game.gui.Constants;
import org.game.gui.Coordinates;
import org.game.gui.StateType;
import org.game.gui.panels.PanelsConstrains;
import org.game.map.Surface;
import org.game.map.SurfaceType;

import org.game.mockData.MockedData;
import org.game.unit.*;

import java.util.*;

public class MapProcessor implements MapService{
    private final WeatherService weatherProcessor = new WeatherProcessor();


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
            if(checkIfPositionValidAndWater(map,new Coordinates(coordinates.axisX()+cardinalPoint.getValue().axisX(),coordinates.axisY()+cardinalPoint.getValue().axisY()))){
                map[coordinates.axisX()+cardinalPoint.getValue().axisX()][coordinates.axisY()+cardinalPoint.getValue().axisY()].setType(SurfaceType.PORT);
                tmp.add(map[coordinates.axisX()+cardinalPoint.getValue().axisX()][coordinates.axisY()+cardinalPoint.getValue().axisY()]);
            }
        });
        return tmp;
    }

    /**
     * @param unit
     * @param route
     * @param map
     */
    @Override
    public void getRoute(GameUnit unit, ArrayList<Surface> route, Surface[][] map) {
        if(unit.getUnitType().equals(UnitType.VESSEL)){
            Vessel vessel = (Vessel) unit;
            switch (vessel.getCurrentWeather().wind()){
                case BREEZE -> {
                    Arrays.stream(CardinalPoint.cardinalPoints).forEach(cardinalPoint -> {
                        setRoute(vessel.getMovePoints()-weatherProcessor.getPenalty(vessel.getCurrentWeather().cardinalPoint(),cardinalPoint), map, vessel.getCoordinates(), cardinalPoint, route);
                    });
                }
                case CALM -> {
                    Arrays.stream(CardinalPoint.cardinalPoints).forEach(cardinalPoint ->
                            setRoute(vessel.getMovePoints(),map,vessel.getCoordinates(),cardinalPoint,route));
                }
                case STORM -> {
                    setRoute(vessel.getMovePoints(),map,vessel.getCoordinates(),vessel.getCurrentWeather().cardinalPoint(),route);
                }
            }
        }
    }
    private void setRoute(int current_move_points,Surface [][] map,Coordinates coordinates, CardinalPoint cardinalPoint,ArrayList<Surface> route){
        for(int i = 1; i<=current_move_points; i++) {
            Coordinates c = new Coordinates(coordinates.axisX() + cardinalPoint.getValue().axisX() * i,coordinates.axisY() + cardinalPoint.getValue().axisY() * i);
            if(checkIfPositionIsValid(map,c)){
                if(checkIfPositionIsWater(map,c)){
                    if(!map[c.axisX()][c.axisY()].isEmpty()) break;
                    map[c.axisX()][c.axisY()].setType(SurfaceType.ROUTE);
                    route.add(map[c.axisX()][c.axisY()]);
                } else if (checkIfPositionIsPort(map,c)) {
                    if(!map[c.axisX()][c.axisY()].isEmpty()){
                        break;
                    }else{
                        Arrays.stream(CardinalPoint.cardinalPoints).forEach(cp -> {
                            if(!map[c.axisX()+cp.getValue().axisX()][c.axisY()+cp.getValue().axisY()].isEmpty()){
                                GameUnit unit = map[c.axisX()+cp.getValue().axisX()][c.axisY()+cp.getValue().axisY()].getUnit();
                                if(unit.getUnitType().equals(UnitType.FORTIFICATION)){
                                    Fortification f = (Fortification) unit;
                                    switch (f.getFortificationType()){
                                        case FIRST_LINE_FORT, SECOND_LINE_FORT -> {
                                            if(f.isFirstPlayer()==map[coordinates.axisX()][coordinates.axisY()].getUnit().isFirstPlayer()||f.getStateType().equals(StateType.DESTROYED)){
                                                route.add(map[c.axisX()][c.axisY()]);
                                            }
                                        }
                                        case ROYAL_PORT -> {
                                            if(f.isFirstPlayer()!=map[coordinates.axisX()][coordinates.axisY()].getUnit().isFirstPlayer()){
                                                route.add(map[c.axisX()][c.axisY()]);
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    }

                }
            }else{
                break;
            }
        }
    }

    private boolean checkIfPositionIsPort(Surface[][] map, Coordinates c) {
        return map[c.axisX()][c.axisY()].getType().equals(SurfaceType.PORT);
    }

    private boolean checkIfPositionIsWater(Surface[][] map, Coordinates c) {
        return map[c.axisX()][c.axisY()].getType().equals(SurfaceType.WATER);
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
     * @param unit
     * @param map
     * @return
     */
    @Override
    public List<GameUnit> getFiringZone(GameUnit unit, Surface[][] map) {
        int range = unit.getFire_range();
        List<GameUnit> tmp = new ArrayList<>();
        Arrays.stream(CardinalPoint.cardinalPoints).forEach(cardinalPoint -> {
            for(int i = 1; i<=range; i++){
                Coordinates c = new Coordinates(unit.getCoordinates().axisX()+cardinalPoint.getValue().axisX()*i,unit.getCoordinates().axisY()+cardinalPoint.getValue().axisY()*i);
                if(checkIfPositionIsValid(map,c)) {
                    if(!map[c.axisX()][c.axisY()].isEmpty()){
                        tmp.add(map[c.axisX()][c.axisY()].getUnit());
                    }
                }
            }
        });
        return tmp;
    }

    /**
     * @param unit 
     * @return
     */
    @Override
    public boolean isNotInPort(GameUnit unit, Surface [] [] map) {
        return !map[unit.getCoordinates().axisX()][unit.getCoordinates().axisY()].getType().equals(SurfaceType.PORT);
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
    private boolean checkIfPositionIsValid(Surface [] [] map, Coordinates coordinates){
        if(checkValidPositionOnAxisX(map,coordinates.axisX())){
            return checkValidPositionOnAxisY(map, coordinates.axisY());
        }else {
            return false;
        }
    }
    /**
     * Accepts Surface multidimensional array and Coordinate object
     * Returns boolean
     * Checks if Coordinate object might be in bounds of multidimensional array*/
    private boolean checkIfPositionValidAndWater(Surface[][] map, Coordinates coordinates){
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
