package org.game.services;

import lombok.Getter;
import org.game.BackToGUIConverter;
import org.game.gui.Coordinates;
import org.game.gui.StateType;
import org.game.state.InfoAreaState;
import org.game.state.MapAreaState;
import org.game.state.State;
import org.game.map.Surface;
import org.game.state.WindRoseAreaState;
import org.game.unit.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;



public class UnitProcessor implements UnitService{
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



    //NamesRandomizer namesRandomizer = new NamesRandomizer();
    public UnitProcessor() {
        //giveFortNames();
    }


    @Override
    public State initialGameState() {
        day = 1;
        isFirstPlayerMove = true;

        map = mapProcessor.generateStandardMap();
        //fortificationProcessor.setPortLocations(map);
        fortificationProcessor.getStandardFortifications(map,fleet);
        fleet.values().stream().map(Fortification.class::cast).toList().forEach(fortification -> fortificationProcessor.setPortLocations(mapProcessor.getPort(fortification.getCoordinates(),map),fortification));
        vesselProcessor.setVessels(fleet,map);
        map[20][3].setUnit(new Vessel(true, VesselType.THREE_DECKER_SHIP_OF_LINE));
        map[20][3].getUnit().setCoordinates(new Coordinates(20,3));
        map[20][3].getUnit().setUnitType(UnitType.VESSEL);
        map[20][3].getUnit().setId("test");

        fleet.put("test",map[20][3].getUnit());

        vesselProcessor.getVessels(fleet).forEach(e->e.setCurrentWeather(weatherProcessor.getWeather()));

        //fleet.values().stream().map(Fortification.class::cast).toList().forEach(u-> System.out.println(u.getPort().size()+" "+u.getCoordinates().toString()));
        return State.builder().mapAreaState(MapAreaState.builder().map(BackToGUIConverter.convertMap(map)).fleet(BackToGUIConverter.convertFleet(fleet)).selectedID_TEST(getOpt(selected_Test)).targetID_TEST(getOpt(target_Test)).build())
                .infoAreaState(InfoAreaState.builder().day(String.valueOf(day)).build()).build();
        //TODO make to gui converter
    }

    @Override
    public State unitSelected(String id) {
        stormMove=null;
        vesselInStorm = null;
        /**
         * if id is not empty
         * get unit by id
         * check if unit is belonged to player 
         * if so check is unit is already selected
         * if not replace optional and change state*/
        if(id.isBlank()){
            selected_Test=null;
            target_Test=null;
            mapProcessor.clearRoute(route);
            return State.builder().mapAreaState(MapAreaState.builder().map(BackToGUIConverter.convertMap(map)).fleet(BackToGUIConverter.convertFleet(fleet)).selectedID_TEST(getOpt(selected_Test)).targetID_TEST(getOpt(target_Test)).build())
                    .infoAreaState(InfoAreaState.builder().day(String.valueOf(day)).selected(false).build())
                    .windRoseAreaState(WindRoseAreaState.builder().weather(null).build())
                    .build();
        }else{
            GameUnit unit = fleet.get(id);
            if(unit.isFirstPlayer()==isFirstPlayerMove){
                if(selected_Test!=null&&!selected_Test.equals(unit)) {
                    selected_Test.setStateType(StateType.PASSIVE);
                    //stormMove=null;
                    //vesselInStorm = null;
                    mapProcessor.clearRoute(route);
                }
                selected_Test = unit;
                selected_Test.setStateType(StateType.SELECTED);
                mapProcessor.getRoute(selected_Test,route,map);
                firingProcessor.setUnderAttack(mapProcessor.getFiringZone(selected_Test,map),aimedUnits,selected_Test);
                Optional.of(unit).ifPresent(unit1 -> {
                    if(unit1.getUnitType().equals(UnitType.VESSEL)){
                        if(unit.getMovePoints()>0) {
                            if (weatherProcessor.isStorm(unit1) && mapProcessor.isNotInPort(unit1, map)) {
                                //System.out.println("Storm");
                                //System.out.println(route.get(route.size()-1).getCoordinates());
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
        //fleet.get(id);
        vesselProcessor.move((Vessel)fleet.get(id),destination,map);
        return unitSelected(id);
        //return State.builder().mapAreaState(MapAreaState.builder().map(BackToGUIConverter.convertMap(map)).fleet(BackToGUIConverter.convertFleet(fleet)).build())
        //        .infoAreaState(InfoAreaState.builder().day(String.valueOf(day)).build()).build();
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
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println(unit);
                        System.out.println(target_Test);
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        //target_Test=null;
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
                        //target_Test=null;
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
        Optional.of(selected_Test).ifPresent(unit -> unit.setStateType(StateType.PASSIVE));
        selected_Test = null;
        target_Test = null;
        vesselInStorm = null;
        stormMove = null;
        mapProcessor.clearRoute(route);
        firingProcessor.clearAimed(aimedUnits);
        if(isFirstPlayerMove){
              isFirstPlayerMove = false;
        }else {
            isFirstPlayerMove = true;
            day++;
        }
        return unitSelected("");
    }

    private Optional<Coordinates> getOpt(GameUnit unit){
        if(unit==null){
            return Optional.empty();
        }else{
            return Optional.of(unit.getCoordinates());
        }
    }
}
