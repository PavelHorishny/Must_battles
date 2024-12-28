package org.game;

import org.game.gui.Coordinates;
import org.game.gui.MapCell;
import org.game.gui.StateType;
import org.game.map.Surface;
import org.game.unit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BackToGUIConverter {
    public static MapCell [] [] convertMap(Surface [] [] map){
        MapCell[][] tmp = new MapCell[map.length][map[0].length];
        Arrays.stream(map).forEach(subArr -> Arrays.stream(subArr).forEach(surface -> tmp[surface.getCoordinates().axisX()][surface.getCoordinates().axisY()]=new MapCell(surface.getCoordinates(),surface.getType())));
        return tmp;
    }
    public static Map<Coordinates,GUIUnit> convertFleet(Map<String, GameUnit> fleet){
        Map<Coordinates,GUIUnit> tmp = new HashMap<>();

        fleet.values().forEach(gameUnit -> {
            tmp.put(gameUnit.getCoordinates(),makeGUIUnit(gameUnit)/*new GUIUnit(gameUnit.getId(),gameUnit.getCoordinates(),setIcons(gameUnit),gameUnit.getStateType())*/);

        });
        return tmp;
    }
    private static UnitIcons setIcons(GameUnit unit){
        return switch (unit.getUnitType()){
            case FORTIFICATION -> setFortificationIcons((Fortification) unit);
            case VESSEL -> setVesselIcons((Vessel) unit);
        };
    }

    private static UnitIcons setVesselIcons(Vessel unit) {
        return switch (unit.getVesselType()){
            case THREE_DECKER_SHIP_OF_LINE -> checkPlayer(unit) ? Images.THREE_DECKER_SHIP_OF_LINE_ST : Images.THREE_DECKER_SHIP_OF_LINE_ND;
            case TWO_DECKER_SHIP_OF_LINE -> checkPlayer(unit) ? Images.TWO_DECKER_SHIP_OF_LINE_ST : Images.TWO_DECKER_SHIP_OF_LINE_ND;
            case FRIGATE -> checkPlayer(unit) ? Images.FRIGATE_ST : Images.FRIGATE_ND;
            case TENDER -> checkPlayer(unit) ? Images.TENDER_ST : Images.TENDER_ND;
            case BRIG -> checkPlayer(unit) ? Images.BRIG_ST : Images.BRIG_ND;
            case GALLEON -> checkPlayer(unit) ? Images.GALLEON_ST : Images.GALLEON_ND;
            case STEAM_FRIGATE -> checkPlayer(unit) ? Images.STEAM_FRIGATE_ST : Images.STEAM_FRIGATE_ND;
            case BATTERY -> checkPlayer(unit) ? Images.BATTERY_ST : Images.BATTERY_ND;
            case GALLEY -> checkPlayer(unit) ? Images.GALLEY_ST : Images.GALLEY_ND;
            case CORVETTE -> checkPlayer(unit) ? Images.CORVETTE_ST : Images.CORVETTE_ND;
            case MONITOR -> checkPlayer(unit) ? Images.MONITOR_ST : Images.MONITOR_ND;
            case STEAMSHIP -> checkPlayer(unit) ? Images.STEAMER_ST : Images.STEAMER_ND;
        };
    }

    private static UnitIcons setFortificationIcons(Fortification unit) {
        if(unit.getStateType().equals(StateType.DESTROYED)){
            return switch (unit.getFortificationType()) {
                case FIRST_LINE_FORT -> Images.FIRST_LINE_FORT_DESTROYED;
                case SECOND_LINE_FORT -> Images.SECOND_LINE_FORT_DESTROYED;
                case ROYAL_PORT -> null;
            };
        }else {
            return switch (unit.getFortificationType()) {
                case FIRST_LINE_FORT -> checkPlayer(unit) ? Images.FIRST_LINE_FORT_ST : Images.FIRST_LINE_FORT_ND;
                case SECOND_LINE_FORT -> checkPlayer(unit) ? Images.SECOND_LINE_FORT_ST : Images.SECOND_LINE_FORT_ND;
                case ROYAL_PORT -> checkPlayer(unit) ? Images.ROYAL_PORT_ST : Images.ROYAL_PORT_ND;
            };
        }
    }

    private static boolean checkPlayer(GameUnit unit) {
        return unit.isFirstPlayer();
    }

    private static GUIUnit makeGUIUnit(GameUnit unit){
        GUIUnit tmp = new GUIUnit();
        tmp.setCoordinates(unit.getCoordinates());
        tmp.setId(unit.getId());
        tmp.setIcons(setIcons(unit));
        tmp.setType(unit.getUnitType());
        tmp.setStateType(unit.getStateType());
/*        if(unit.getUnitType()==UnitType.FORTIFICATION){
            Fortification f = (Fortification) unit;
            if(f.getFortificationType()==FortificationType.ROYAL_PORT){
                tmp.setCurrentIcon(checkPlayer(f) ? Images.ROYAL_PORT_ST : Images.ROYAL_PORT_ND);
            }
        }*/
        tmp.setActivity(unit.getStateType());
        return tmp;
    }
    public static ArrayList<Coordinates> convertRoute(ArrayList<Surface> route){
        ArrayList <Coordinates> tmp = new ArrayList<>();
        route.forEach(surface -> tmp.add(surface.getCoordinates()));
        return tmp;
    }
}
