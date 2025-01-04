package org.game.services;

import org.game.UnitData;
import org.game.Weather;
import org.game.gui.Coordinates;
import org.game.gui.MapCell;
import org.game.gui.StateType;
import org.game.map.Surface;
import org.game.state.*;
import org.game.unit.*;

import java.util.*;

public class Converter implements StateConverter{
    /**
     * @param gameState 
     * @return
     */
    @Override
    public State convertState(GameState gameState) {
       return State.builder()
               .mapAreaState(MapAreaState.builder()
                       .lost(gameState.getEndGame().isEndGame())
                       .looser(gameState.getEndGame().getMessage())
                       .fleet(convertFleet(gameState.getFleet()))
                       .route(convertRoute(gameState.getRoute()))
                       .map(convertMap(gameState.getMap()))
                       .selectedID(testOnAvailability(gameState.getSelected())) //null
                       .targetID(testOnAvailability(gameState.getTarget()))
                       .vesselInStorm(gameState.getVesselInStorm())
                       .stormDestination(gameState.getStormDestination())
                       .build())
               .logAreaState(LogAreaState.builder().build())
               .infoAreaState(InfoAreaState.builder()
                       .day(String.valueOf(gameState.getDay()))
                       .selected(Optional.ofNullable(gameState.getSelected()).isPresent())
                       .selectedData(testOnUnitData(gameState.getSelected()))
                       .target(Optional.ofNullable(gameState.getTarget()).isPresent())
                       .targetData(testOnUnitData(gameState.getTarget()))
                       .build())
               .windRoseAreaState(WindRoseAreaState.builder()
                       .weather(testOnWeather(gameState.getSelected()))
                       .build())
               .buttonAreaState(ButtonAreaState.builder()
                       .onRepairButton(gameState.isButtonOnRepairActive())
                       .helpButton(gameState.isButtonReadyForHelpActive())
                       .selectedReadyForRepair(selectedReadyForRepair(gameState.getSelected()))
                       .selectedReadyToHelp(selectedReadyForHelp(gameState.getSelected()))
                       .selectedIsHelping(selectedIsHelping(gameState.getSelected()))
                       .selectedOnRepair(selectedIsOnRepair(gameState.getSelected()))
                       .build())
               .build();
    }
    private MapCell[] [] convertMap(Surface[] [] map){
        MapCell[][] tmp = new MapCell[map.length][map[0].length];
        Arrays.stream(map).forEach(subArr -> Arrays.stream(subArr).forEach(surface -> tmp[surface.getCoordinates().axisX()][surface.getCoordinates().axisY()]=new MapCell(surface.getCoordinates(),surface.getType())));
        return tmp;
    }
    private Map<Coordinates, GUIUnit> convertFleet(Map<String, GameUnit> fleet){
        Map<Coordinates,GUIUnit> tmp = new HashMap<>();

        fleet.values().forEach(gameUnit -> {
            tmp.put(gameUnit.getCoordinates(),makeGUIUnit(gameUnit)/*new GUIUnit(gameUnit.getId(),gameUnit.getCoordinates(),setIcons(gameUnit),gameUnit.getStateType())*/);

        });
        return tmp;
    }
    private UnitIcons setIcons(GameUnit unit){
        return switch (unit.getUnitType()){
            case FORTIFICATION -> setFortificationIcons((Fortification) unit);
            case VESSEL -> setVesselIcons((Vessel) unit);
        };
    }

    private UnitIcons setVesselIcons(Vessel unit) {
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

    private UnitIcons setFortificationIcons(Fortification unit) {
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

    private boolean checkPlayer(GameUnit unit) {
        return unit.isFirstPlayer();
    }

    private GUIUnit makeGUIUnit(GameUnit unit){
        GUIUnit tmp = new GUIUnit();
        tmp.setCoordinates(unit.getCoordinates());
        tmp.setId(unit.getId());
        tmp.setStateType(unit.getStateType());
        tmp.setIcons(setIcons(unit));
        tmp.setActivity(unit.getStateType());

        tmp.setType(unit.getUnitType());
     /*   tmp.setStateType(unit.getStateType());
        tmp.setActivity(unit.getStateType());*/
        return tmp;
    }
    private ArrayList<Coordinates> convertRoute(ArrayList<Surface> route){
        ArrayList <Coordinates> tmp = new ArrayList<>();
        route.forEach(surface -> tmp.add(surface.getCoordinates()));
        return tmp;
    }
    private Coordinates testOnAvailability(GameUnit unit){
        if(unit!=null){
            return unit.getCoordinates();
        }else {
            return null;
        }
    }
    private UnitData testOnUnitData(GameUnit unit){
        if(unit!=null){
            return unit.toUnitData();
        }else {
            return null;
        }
    }
    private Weather testOnWeather(GameUnit unit){
        if(unit!=null){
            return unit.getCurrentWeather();
        }else {
            return null;
        }
    }
    private boolean selectedReadyForRepair(GameUnit unit){
        if(unit!=null){
            return unit.isReadyForRepair();
        }else {
            return false;
        }
    }
    private boolean selectedReadyForHelp(GameUnit unit){
        if(unit!=null){
            return unit.isReadyToHelp();
        }else {
            return false;
        }
    }
    private boolean selectedIsHelping(GameUnit unit){
        if(unit!=null){
            return unit.isReadyForRepair();
        }else {
            return false;
        }
    }
    private boolean selectedIsOnRepair(GameUnit unit){
        if(unit!=null){
            return unit.isReadyForRepair();
        }else {
            return false;
        }
    }
}
