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

    @Override
    public void getRoute(GameUnit unit, ArrayList<Surface> route, Surface[][] map) {
        if(unit.getUnitType().equals(UnitType.VESSEL)){
            Vessel vessel = (Vessel) unit;
            if(vessel.isCanMove()) {
                switch (vessel.getCurrentWeather().wind()) {
                    case BREEZE ->
                        Arrays.stream(CardinalPoint.cardinalPoints).forEach(cardinalPoint ->
                                setRoute(vessel.getMovePoints() - weatherProcessor.getPenalty(vessel.getCurrentWeather().cardinalPoint(), cardinalPoint), map, cardinalPoint, route,vessel));

                    case CALM ->
                        Arrays.stream(CardinalPoint.cardinalPoints).forEach(cardinalPoint ->
                                setRoute(vessel.getMovePoints(), map, cardinalPoint, route,vessel));

                    case STORM ->
                        setRoute(vessel.getMovePoints(), map, vessel.getCurrentWeather().cardinalPoint(), route,vessel);
                }
            }
        }
    }

    private boolean canAddSurfaceToRoute(Fortification fortification, GameUnit gameUnit){
        boolean able = false;
        switch (fortification.getFortificationType()){
            case FIRST_LINE_FORT, SECOND_LINE_FORT -> able = fortification.isFirstPlayer() == gameUnit.isFirstPlayer() || fortification.getStateType().equals(StateType.DESTROYED);
            case ROYAL_PORT -> able = fortification.isFirstPlayer() != gameUnit.isFirstPlayer();
        }
        return able;
    }

    private void setRoute(int current_move_points,Surface [][] map,CardinalPoint cardinalPoint,ArrayList<Surface> route,GameUnit gameUnit){
        for(int i = 1; i<=current_move_points; i++){
            Coordinates c = new Coordinates(gameUnit.getCoordinates().axisX()+cardinalPoint.getValue().axisX() * i, gameUnit.getCoordinates().axisY() + cardinalPoint.getValue().axisY() * i);
            if(checkIfPositionIsValid(map,c)){
                if(checkIfPositionIsWater(map,c)){
                    if(!map[c.axisX()][c.axisY()].isEmpty()){
                        break;
                    }else{
                        map[c.axisX()][c.axisY()].setType(SurfaceType.ROUTE);
                        route.add(map[c.axisX()][c.axisY()]);
                    }
                }else if(checkIfPositionIsPort(map,c)){
                    if(!map[c.axisX()][c.axisY()].isEmpty()){
                        break;
                    }else {
                        Fortification fortification = map[c.axisX()][c.axisY()].getFortification();
                        if(canAddSurfaceToRoute(fortification,gameUnit)){
                            route.add(map[c.axisX()][c.axisY()]);
                        }else {
                            break;
                        }
                    }
                }else {
                    break;
                }
            }else {
                break;
            }
        }
    }

    public boolean checkIfPositionIsPort(Surface[][] map, Coordinates c) {
        return map[c.axisX()][c.axisY()].getType().equals(SurfaceType.PORT);
    }

    private boolean checkIfPositionIsWater(Surface[][] map, Coordinates c) {
        return map[c.axisX()][c.axisY()].getType().equals(SurfaceType.WATER);
    }

    @Override
    public void clearRoute(ArrayList<Surface> route) {
        route.forEach(surface -> surface.setType(SurfaceType.WATER));
        route.clear();
    }

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

    @Override
    public boolean isNotInPort(GameUnit unit, Surface [] [] map) {
        return !map[unit.getCoordinates().axisX()][unit.getCoordinates().axisY()].getType().equals(SurfaceType.PORT);
    }

    @Override
    public void addUnit(GameUnit gameUnit, Surface[][] map) {
        if (checkIfPositionIsValid(map,gameUnit.getCoordinates())){
            if(gameUnit instanceof Fortification){
                if(checkIfPositionIsLand(map[gameUnit.getCoordinates().axisX()][gameUnit.getCoordinates().axisY()])){
                    map[gameUnit.getCoordinates().axisX()][gameUnit.getCoordinates().axisY()].setUnit(gameUnit);
                }
            }else if(gameUnit instanceof Vessel){
                if(checkIfSurfaceIsWaterOrPort(map[gameUnit.getCoordinates().axisX()][gameUnit.getCoordinates().axisY()])){
                    map[gameUnit.getCoordinates().axisX()][gameUnit.getCoordinates().axisY()].setUnit(gameUnit);
                }
            }
        }
    }

    @Override
    public void removeUnit(GameUnit gameUnit, Surface[][] map){
        if(checkIfPositionIsValid(map,gameUnit.getCoordinates())){
            map[gameUnit.getCoordinates().axisX()][gameUnit.getCoordinates().axisY()].setUnit(null);
        }
    }

    private boolean checkIfPositionIsLand(Surface surface) {
        return surface.getType() == SurfaceType.LAND;
    }

    private boolean checkIfPositionIsValidOnAxisY(Surface [][] map, int index){
        return index >= 0 && index < map[0].length;
    }

    private boolean checkIfPositionIsValidOnAxisX(Surface [][] map, int index){
        return index >= 0 && index < map.length;
    }
    private boolean checkIfPositionIsValid(Surface [] [] map, Coordinates coordinates){
        if(checkIfPositionIsValidOnAxisX(map,coordinates.axisX())){
            return checkIfPositionIsValidOnAxisY(map, coordinates.axisY());
        }else {
            return false;
        }
    }

    private boolean checkIfPositionValidAndWater(Surface[][] map, Coordinates coordinates){
        if(checkIfPositionIsValidOnAxisX(map,coordinates.axisX())){
            if(checkIfPositionIsValidOnAxisY(map,coordinates.axisY())){
                return checkIfSurfaceIsWaterOrPort(map[coordinates.axisX()][coordinates.axisY()]);
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    private boolean checkIfSurfaceIsWaterOrPort(Surface surface){
        return surface.getType() == SurfaceType.WATER||surface.getType() == SurfaceType.PORT;
    }
}
