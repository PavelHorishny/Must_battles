package org.game;

import org.game.gui.MapCell;
import org.game.map.Surface;
import org.game.unit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class BackToGUIConverter {
    public static ArrayList<MapCell> convertMap(Surface [] [] map){
        ArrayList<MapCell> tmp = new ArrayList<>();
        Arrays.stream(map).forEach(subArr -> Arrays.stream(subArr).forEach(surface -> tmp.add(new MapCell(surface.getCoordinates(),surface.getType()))));
        return tmp;
    }
    public static ArrayList<GUIUnit> convertFleet(Map<String, GameUnit> fleet){
        ArrayList<GUIUnit> tmp = new ArrayList<>();

        fleet.values().forEach(gameUnit -> {
            GUIUnit u = new GUIUnit();

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
        return switch (unit.getFortificationType()){
            case FIRST_LINE_FORT -> checkPlayer(unit) ? Images.FIRST_LINE_FORT_ST : Images.FIRST_LINE_FORT_ND;
            case SECOND_LINE_FORT -> checkPlayer(unit) ? Images.SECOND_LINE_FORT_ST : Images.SECOND_LINE_FORT_ND;
            case ROYAL_PORT -> checkPlayer(unit) ? Images.ROYAL_PORT_ST : Images.ROYAL_PORT_ND;
        };
    }

    private static boolean checkPlayer(GameUnit unit) {
        return unit.isFirstPlayer();
    }

    private GUIUnit makeGUIUnit(GameUnit unit){
        GUIUnit tmp = new GUIUnit();
        tmp.setCoordinates(unit.getCoordinates());
        tmp.setId(unit.getId());
        tmp.setIcons(setIcons(unit));
/*        if(unit.getUnitType()==UnitType.FORTIFICATION){
            Fortification f = (Fortification) unit;
            if(f.getFortificationType()==FortificationType.ROYAL_PORT){
                tmp.setCurrentIcon(checkPlayer(f) ? Images.ROYAL_PORT_ST : Images.ROYAL_PORT_ND);
            }
        }*/
        tmp.setActivity(unit.getStateType());
        return tmp;
    }
}
