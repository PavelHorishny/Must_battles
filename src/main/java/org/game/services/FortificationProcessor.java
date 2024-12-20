package org.game.services;

import org.game.CardinalPoint;
import org.game.Context;
import org.game.gui.Coordinates;
import org.game.map.Surface;
import org.game.map.SurfaceType;
import org.game.mockData.MockedData;
import org.game.mockData.NamesRandomizer;
import org.game.unit.Fortification;
import org.game.unit.FortificationType;
import org.game.unit.GameUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FortificationProcessor implements FortificationService{

    private final NamesRandomizer namesRandomizer;

    public FortificationProcessor() {
        namesRandomizer = Context.getNameRandomizer();
    }

    @Override
    public void generateFortification(Map<String, GameUnit> fleet) {
        setFortificationNames(fleet, MockedData.STANDARD_FORT_POSITION);
        //TODO check if this method redundant
    }
    /**
     * Accepts Surface multidimensional array and Map <String, GameUnit>
     * void
     * Set random names for Fortifications, update Map*/
    @Override
    public void getStandardFortifications(Surface[][] map, Map<String, GameUnit> fleet) {
        ArrayList <Fortification> tmp = MockedData.STANDARD_FORT_POSITION;
        setFortificationNames(fleet,tmp);
        tmp.forEach(fortification -> setPortLocations(map,fortification));
    }
    /**
     * Accepts Surface multidimensional array and Coordinate object
     * Returns boolean
     * Check if Coordinate object might be in bounds of multidimensional array*/
    private boolean checkIfPositionValid(Surface[][] map, Coordinates coordinates){
        if(checkValidPositionOnAxisX(map,coordinates.axisX())){
            if(checkValidPositionOnAxisY(map,coordinates.axisY())){
                return checkIfSurfaceIsWater(map[coordinates.axisX()][coordinates.axisY()]);
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * Method accepts Surface multidimensional array and int
     * returns boolean
     * checks if int in bounds of sub array*/
    private boolean checkValidPositionOnAxisY(Surface [][] map, int index){
        return index >= 0 && index < map[0].length;
    }
    private boolean checkValidPositionOnAxisX(Surface [][] map, int index){
        return index >= 0 && index < map.length;
    }

    /**
     * Method accepts Surface object
     * returns boolean
     * returns true if SurfaceType of Surface object is WATER*/
    private boolean checkIfSurfaceIsWater(Surface surface){
        return surface.getType() == SurfaceType.WATER;
    }
    /**
     * Method accepts Map and List of Fortification Objects
     * sets a random name to each object
     * fulfill Map with Object's id as a key and Object itself as a value*/
    private void setFortificationNames(Map<String, GameUnit> fleet, List<Fortification> fortifications){
        fortifications.forEach(e->{
            if(e.getFortificationType()== FortificationType.ROYAL_PORT){
                e.setId(namesRandomizer.royalPortNames.pop());
            }else {
                e.setId(namesRandomizer.fortificationsNames.pop());
            }
            setBaseParameters(e);
            fleet.put(e.getId(), e);
        });
    }
    /**
     * Method accepts Surface array and Fortification object
     * switches SurfaceType to PORT
     * adds Surface to port Object's field */
    private void setPortLocations(Surface[][] map, Fortification fortification) {

        Arrays.stream(CardinalPoint.cardinalPoints).forEach(cardinalPoint -> {

            if (checkIfPositionValid(map,new Coordinates(fortification.getCoordinates().axisX()+cardinalPoint.getValue().axisX(),fortification.getCoordinates().axisY()+cardinalPoint.getValue().axisY())))
            {
                map[fortification.getCoordinates().axisX()+cardinalPoint.getValue().axisX()][fortification.getCoordinates().axisY()+cardinalPoint.getValue().axisY()].setType(SurfaceType.PORT);
                fortification.getPort().add(map[fortification.getCoordinates().axisX()+cardinalPoint.getValue().axisX()][fortification.getCoordinates().axisY()+cardinalPoint.getValue().axisY()]);
            }
        });
    }
    private void setBaseParameters(Fortification fortification){
        switch (fortification.getFortificationType()){
            case FIRST_LINE_FORT -> {
                fortification.setType(FortificationType.FIRST_LINE_FORT.getType());
                fortification.setFire_range(FortificationType.FIRST_LINE_FORT.getFire_range());
                fortification.setHit_points(FortificationType.FIRST_LINE_FORT.getHit_points());
                fortification.setShots(FortificationType.FIRST_LINE_FORT.getShots());
                fortification.setMovePoints(FortificationType.FIRST_LINE_FORT.getMovePoints());
            }
            case SECOND_LINE_FORT -> {
                fortification.setType(FortificationType.SECOND_LINE_FORT.getType());
                fortification.setFire_range(FortificationType.SECOND_LINE_FORT.getFire_range());
                fortification.setHit_points(FortificationType.SECOND_LINE_FORT.getHit_points());
                fortification.setShots(FortificationType.SECOND_LINE_FORT.getShots());
                fortification.setMovePoints(FortificationType.SECOND_LINE_FORT.getMovePoints());
            }
            case ROYAL_PORT -> {
                fortification.setType(FortificationType.ROYAL_PORT.getType());
                fortification.setFire_range(FortificationType.ROYAL_PORT.getFire_range());
                fortification.setHit_points(FortificationType.ROYAL_PORT.getHit_points());
                fortification.setShots(FortificationType.ROYAL_PORT.getShots());
                fortification.setMovePoints(FortificationType.ROYAL_PORT.getMovePoints());
            }
        }
    }
}
