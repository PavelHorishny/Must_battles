package org.game.services;

import lombok.Getter;
import org.game.BackToGUIConverter;
import org.game.EndGame;
import org.game.gui.Coordinates;
import org.game.gui.StateType;
import org.game.map.SurfaceType;
import org.game.state.*;
import org.game.map.Surface;
import org.game.unit.*;

import java.util.*;


public class UnitProcessor implements UnitService{
    private boolean buttonOnRepairActive;
    private boolean buttonReadyForHelpActive;
    private final EndGame endGame = new EndGame();
    private GameUnit selected_Test;
    private GameUnit target_Test;
    private Coordinates vesselInStorm;
    private Coordinates stormMove;
    private Surface[][] map;
    private final ArrayList <Surface> route = new ArrayList<>();
    private final ArrayList <GameUnit> aimedUnits = new ArrayList<>();
    @Getter
    private final Map <String, GameUnit> fleet = new HashMap<>();
    private int day;
    private boolean isFirstPlayerMove;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional <GameUnit> selected = Optional.empty();
    private final MapService mapProcessor = new MapProcessor();
    private final FortificationService fortificationProcessor = new FortificationProcessor();
    private final VesselService vesselProcessor = new VesselProcessor();
    private final WeatherService weatherProcessor = new WeatherProcessor();
    private final FiringService firingProcessor = new FiringProcessor();

    public UnitProcessor() {
    }


    @Override
    public State initialGameState() {
        day = 1;
        isFirstPlayerMove = true;

        map = mapProcessor.generateStandardMap();
        fortificationProcessor.getStandardFortifications(map,fleet);
        fleet.values().stream().map(Fortification.class::cast).toList().forEach(fortification -> fortificationProcessor.setPortLocations(mapProcessor.getPort(fortification.getCoordinates(),map),fortification));
        vesselProcessor.setTheFleet(fleet,map);
        map[20][3].setUnit(new Vessel(true, VesselType.THREE_DECKER_SHIP_OF_LINE));
        map[20][3].getUnit().setCoordinates(new Coordinates(20,3));
        map[20][3].getUnit().setUnitType(UnitType.VESSEL);
        map[20][3].getUnit().setId("test");

        fleet.put("test",map[20][3].getUnit());

        vesselProcessor.getListOfAllVessels(fleet).forEach(e->e.setCurrentWeather(weatherProcessor.getWeather()));

        return State.builder().mapAreaState(MapAreaState.builder().map(BackToGUIConverter.convertMap(map)).fleet(BackToGUIConverter.convertFleet(fleet)).selectedID_TEST(getOpt(selected_Test)).targetID_TEST(getOpt(target_Test)).build())
                .infoAreaState(InfoAreaState.builder().day(String.valueOf(day)).build()).build();
        //TODO make to gui converter
    }

    @Override
    public State unitSelected(String id) {
        stormMove=null;
        vesselInStorm = null;
        if(id.isBlank()){
            if(selected_Test!=null) {
                if(selected_Test instanceof Fortification){
                    if(selected_Test.getStateType().equals(StateType.DESTROYED)){
                        selected_Test.setStateType(StateType.DESTROYED);
                    }else {
                        selected_Test.setStateType(StateType.PASSIVE);
                    }
                }else {
                    selected_Test.setStateType(StateType.PASSIVE);
                }
            }
            selected_Test=null;
            target_Test=null;
            mapProcessor.clearRoute(route);
            firingProcessor.clearAimed(aimedUnits);
            return State.builder().mapAreaState(MapAreaState.builder().map(BackToGUIConverter.convertMap(map)).fleet(BackToGUIConverter.convertFleet(fleet)).selectedID_TEST(getOpt(selected_Test)).targetID_TEST(getOpt(target_Test)).build())
                    .infoAreaState(InfoAreaState.builder().day(String.valueOf(day)).selected(false).build())
                    .windRoseAreaState(WindRoseAreaState.builder().weather(null).build())
                    .build();
        }else{
            GameUnit unit = fleet.get(id);
            if(unit.isFirstPlayer()==isFirstPlayerMove||isSelectedDestroyedFort(unit)){
                if(selected_Test!=null&&!selected_Test.equals(unit)) {
                    if(selected_Test instanceof Fortification){
                        if(selected_Test.getStateType().equals(StateType.DESTROYED)){
                            selected_Test.setStateType(StateType.DESTROYED);
                        }else {
                            selected_Test.setStateType(StateType.PASSIVE);
                        }
                    }else {
                        selected_Test.setStateType(StateType.PASSIVE);
                    }
                    mapProcessor.clearRoute(route);
                }
                selected_Test = unit;
                if(map[selected_Test.getCoordinates().axisX()][selected_Test.getCoordinates().axisY()].getType().equals(SurfaceType.PORT)) {
                    System.out.println(map[selected_Test.getCoordinates().axisX()][selected_Test.getCoordinates().axisY()].getFortification().toUnitData().toString());
                }
                if(selected_Test instanceof Vessel){
                    System.out.println(selected_Test.isReadyForRepair());
                }
                switch (selected_Test.getUnitType()){
                    case FORTIFICATION -> {
                        Fortification fortification = (Fortification) selected_Test;
                        buttonOnRepairActive = fortificationProcessor.checkIfFortificationCanBeRepaired(fortification);
                        buttonReadyForHelpActive = false;
                    }
                    case VESSEL -> {
                        Vessel vessel = (Vessel) selected_Test;
                        buttonOnRepairActive = vesselProcessor.checkIfVesselCanBeRepaired(vessel,map);
                        buttonReadyForHelpActive = vesselProcessor.checkIfVesselCanHelp(vessel,map);
                    }
                }
                if(selected_Test instanceof Fortification){
                    if(selected_Test.getStateType().equals(StateType.DESTROYED)){
                        selected_Test.setStateType(StateType.DESTROYED);
                    }else {
                        selected_Test.setStateType(StateType.SELECTED);
                    }
                }else {
                    selected_Test.setStateType(StateType.SELECTED);
                }

                mapProcessor.getRoute(selected_Test,route,map);
                firingProcessor.setUnderAttack(mapProcessor.getFiringZone(selected_Test,map),aimedUnits,selected_Test);
                Optional.of(unit).ifPresent(unit1 -> {
                    if(unit1.getUnitType().equals(UnitType.VESSEL)){
                        if(unit.getMovePoints()>0) {
                            if (weatherProcessor.isStorm(unit1) && mapProcessor.isNotInPort(unit1, map)) {
                                selected_Test = unit1;
                                vesselInStorm = unit1.getCoordinates();
                                stormMove = route.get(route.size() - 1).getCoordinates();
                            }
                        }
                    }
                });

                return State.builder().mapAreaState(MapAreaState.builder().vesselInStorm(vesselInStorm).stormDestination(stormMove).route(BackToGUIConverter.convertRoute(route)).selectedID_TEST(getOpt(selected_Test)).targetID_TEST(getOpt(target_Test)).map(BackToGUIConverter.convertMap(map)).fleet(BackToGUIConverter.convertFleet(fleet)).build())
                        .infoAreaState(InfoAreaState.builder().day(String.valueOf(day)).selected(true).selectedData(selected_Test.toUnitData()).build())
                        .windRoseAreaState(WindRoseAreaState.builder().weather(selected_Test.getCurrentWeather()).build())
                        .buttonAreaState(ButtonAreaState.builder().onRepairButton(buttonOnRepairActive).helpButton(buttonReadyForHelpActive)
                                .selectedReadyForRepair(selected_Test.isReadyForRepair()).selectedOnRepair(selected_Test.isOnRepair())
                                .selectedIsHelping(getReadyIsHelping(selected_Test)).selectedReadyForHelp(getReadyForHelp(selected_Test)).build())
                        .build();
            }else{
                target_Test = unit;
                if(selected_Test!=null){
                    return State.builder().mapAreaState(MapAreaState.builder().selectedID_TEST(getOpt(selected_Test)).targetID_TEST(getOpt(target_Test)).map(BackToGUIConverter.convertMap(map)).fleet(BackToGUIConverter.convertFleet(fleet)).build())
                            .infoAreaState(InfoAreaState.builder().day(String.valueOf(day)).selected(true).selectedData(selected_Test.toUnitData()).target(true).targetData(target_Test.toUnitData()).build())
                            .windRoseAreaState(WindRoseAreaState.builder().weather(selected_Test.getCurrentWeather()).build())
                            .build();
                }else{
                    return State.builder().mapAreaState(MapAreaState.builder().selectedID_TEST(getOpt(selected_Test)).targetID_TEST(getOpt(target_Test)).map(BackToGUIConverter.convertMap(map)).fleet(BackToGUIConverter.convertFleet(fleet)).build())
                            .infoAreaState(InfoAreaState.builder().day(String.valueOf(day)).target(true).targetData(target_Test.toUnitData()).build())
                            .windRoseAreaState(WindRoseAreaState.builder()/*.weather(selected_Test.getCurrentWeather())*/.build())
                            .build();
                }
            }
        }
    }

    private boolean isSelectedDestroyedFort(GameUnit unit) {
        if(unit instanceof Fortification){
            return unit.getStateType().equals(StateType.DESTROYED);
        }else {
            return false;
        }
    }

    private boolean getTargetOnRepair(GameUnit targetTest) {
        if(targetTest instanceof Fortification fortification){
            return fortification.getStateType().equals(StateType.DESTROYED);
        }else {
            return false;
        }
    }

    private boolean getTargetReadyForRepair(GameUnit targetTest) {
        if(targetTest instanceof Fortification fortification){
            return fortification.getStateType().equals(StateType.DESTROYED);
        }else {
            return false;
        }
    }

    private boolean getReadyForHelp(GameUnit selectedTest) {
        if(selectedTest instanceof Vessel){
            return ((Vessel) selectedTest).isReadyToHelp();
        }else {
            return false;
        }
    }
    private boolean getReadyIsHelping(GameUnit selectedTest) {
        if(selectedTest instanceof Vessel){
            return ((Vessel) selectedTest).isHelping();
        }else {
            return false;
        }
    }

    /**
     * @return 
     */
    @Override
    public State movementStarts(String id) {
        fleet.get(id).setStateType(StateType.PASSIVE);
        mapProcessor.clearRoute(route);
        return State.builder().mapAreaState(MapAreaState.builder().map(BackToGUIConverter.convertMap(map)).fleet(BackToGUIConverter.convertFleet(fleet)).selectedID_TEST(getOpt(selected_Test)).targetID_TEST(getOpt(target_Test)).build())
                .infoAreaState(InfoAreaState.builder().day(String.valueOf(day)).build()).build();
    }

    /**
     * @param id 
     * @param destination
     * @return
     */
    @Override
    public State movementEnds(String id, Coordinates destination) {
        vesselProcessor.moveVesselToDestinationPoint((Vessel)fleet.get(id),destination,map);
        return unitSelected(id);
    }
    //TODO merge movement methods as shoot methods

    /**
     * @param attackerID 
     * @param targetID
     * @param shotType
     * @return
     */
    @Override
    public State makeShot(String attackerID, String targetID, String shotType) {
        Optional<GameUnit> gameUnit;
        switch (shotType){
            case "single" -> firingProcessor.shot(fleet.get(attackerID), fleet.get(targetID)).ifPresent(unit -> {
                switch (unit.getUnitType()){
                    case FORTIFICATION -> {
                        unit.setStateType(StateType.DESTROYED);
                    }
                    case VESSEL -> {
                        map[unit.getCoordinates().axisX()][unit.getCoordinates().axisY()].setUnit(null);
                        fleet.remove(unit.getId(),unit);
                    }
                }
            });
            case "salvo" -> firingProcessor.salvoShot(fleet.get(attackerID),fleet.get(targetID)).ifPresent(unit -> {
                switch (unit.getUnitType()){
                    case FORTIFICATION -> {
                        unit.setStateType(StateType.DESTROYED);
                    }
                    case VESSEL -> {
                        map[unit.getCoordinates().axisX()][unit.getCoordinates().axisY()].setUnit(null);
                        fleet.remove(unit.getId(),unit);
                    }
                }
            });
        }
        return unitSelected(attackerID);
    }

    /**
     * @return 
     */
    @Override
    public State dayEnd() {
        Optional.ofNullable(selected_Test).ifPresent(unit -> {
            if(unit instanceof Fortification){
                if(unit.getStateType().equals(StateType.DESTROYED)){
                    unit.setStateType(StateType.DESTROYED);
                }else {
                    unit.setStateType(StateType.PASSIVE);
                }
            }else {
                unit.setStateType(StateType.PASSIVE);
            }
        });
        selected_Test = null;
        target_Test = null;
        vesselInStorm = null;
        stormMove = null;
        mapProcessor.clearRoute(route);
        firingProcessor.clearAimed(aimedUnits);
        fortificationProcessor.checkFortificationsAtMoveEnd(fleet,isFirstPlayerMove);
        if(isFirstPlayerMove){
              isFirstPlayerMove = false;
        }else {
            isFirstPlayerMove = true;
            day++;
            fortificationProcessor.checkFortificationsAtDayEnd(fleet, endGame);
            vesselProcessor.checkVesselsAtDayEnd(fleet);
            //restoreInitialData();
        }
        if(!endGame.isEmpty()){
            return State.builder().mapAreaState(MapAreaState.builder().lost(endGame.isEndGame()).looser(endGame.getMessage()).build()).build();
        }else {
            return unitSelected("");

        }
    }

/*    private void restoreInitialData() {
        fortificationProcessor.restoreFortificationsData(fleet);
        //vesselProcessor.restoreVesselsData(fleet);
    }*/

    /**
     * @return 
     */
    @Override
    public State unitReadyForRepair(boolean state) {
       selected_Test.setReadyForRepair(state);
       if(selected_Test instanceof Vessel){
           ((Vessel) selected_Test).setCanMove(false);
       }
       return unitSelected(selected_Test.getId());
    }

    /**
     * @return 
     */
    @Override
    public State unitReadyForHelp(boolean state) {
       if (selected_Test instanceof Vessel vessel) {
           vessel.setReadyToHelp(state);
           return unitSelected(vessel.getId());
       }else {
           return unitSelected("");
       }
    }

    private Optional<Coordinates> getOpt(GameUnit unit){
        if(unit==null){
            return Optional.empty();
        }else{
            return Optional.of(unit.getCoordinates());
        }
    }
}
