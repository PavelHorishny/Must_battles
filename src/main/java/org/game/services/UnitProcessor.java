package org.game.services;

import lombok.Getter;
import org.game.BackToGUIConverter;
import org.game.UnitData;
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
    private Surface[][] map;
    private final ArrayList <Surface> route = new ArrayList<>(); // maybe redundant
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
        vesselProcessor.getVessels(fleet).forEach(e->e.setCurrentWeather(weatherProcessor.getWeather()));
    /*    map[7][19].setUnit(new Vessel(true, VesselType.THREE_DECKER_SHIP_OF_LINE));
        map[7][19].getUnit().setCoordinates(new Coordinates(7,19));
        map[7][19].getUnit().setUnitType(UnitType.VESSEL);

        fleet.put("test",map[7][19].getUnit());*/
        //fleet.values().stream().map(Fortification.class::cast).toList().forEach(u-> System.out.println(u.getPort().size()+" "+u.getCoordinates().toString()));
        return State.builder().mapAreaState(MapAreaState.builder().map(BackToGUIConverter.convertMap(map)).fleet(BackToGUIConverter.convertFleet(fleet)).build())
                .infoAreaState(InfoAreaState.builder().day(String.valueOf(day)).build()).build();
        //TODO make to gui converter
    }

    @Override
    public State unitSelected(String id) {
        /**
         * if id is not empty
         * get unit by id
         * check if unit is belonged to player 
         * if so check is unit is already selected
         * if not replace optional and change state*/
        if(!id.isEmpty()){
            GameUnit unit = fleet.get(id);
            System.out.println(map[unit.getCoordinates().axisX()][unit.getCoordinates().axisY()].getUnit());
            System.out.println("watch");
            if(unit.isFirstPlayer()==isFirstPlayerMove){
                mapProcessor.getRoute(unit, route,map);
                if(selected.isPresent()){
                    if(!selected.get().equals(unit)){
                        mapProcessor.clearRoute(route);
                        selected.get().setStateType(StateType.PASSIVE);
                        selected = Optional.empty();
                    }
                }
                unit.setStateType(StateType.SELECTED);
                selected = Optional.of(unit);
                mapProcessor.getRoute(unit, route,map);
                UnitData data = unit.toUnitData();
                return State.builder().mapAreaState(MapAreaState.builder().map(BackToGUIConverter.convertMap(map)).fleet(BackToGUIConverter.convertFleet(fleet)).build())
                        .infoAreaState(InfoAreaState.builder().day(String.valueOf(day)).selected(true).selectedData(data).build())
                        .windRoseAreaState(WindRoseAreaState.builder().weather(unit.getCurrentWeather()).build())
                        .build();
            }else{
                UnitData data = unit.toUnitData();
                return State.builder().mapAreaState(MapAreaState.builder().map(BackToGUIConverter.convertMap(map)).fleet(BackToGUIConverter.convertFleet(fleet)).build())
                        .infoAreaState(InfoAreaState.builder().day(String.valueOf(day)).target(true).targetData(data).build())
                        //.windRoseAreaState(WindRoseAreaState.builder().weather(unit.getTemp_field_weather()).build())
                        .build();
            }
        }else{

            if(selected.isPresent()){
                selected.get().setStateType(StateType.PASSIVE);
                selected = Optional.empty();
            }
            mapProcessor.clearRoute(route);
            return State.builder().mapAreaState(MapAreaState.builder().map(BackToGUIConverter.convertMap(map)).fleet(BackToGUIConverter.convertFleet(fleet)).build())
                    .infoAreaState(InfoAreaState.builder().day(String.valueOf(day)).selected(false).build())
                    .windRoseAreaState(WindRoseAreaState.builder().weather(null).build())
                    .build();
        }
    }

    /**
     * @return 
     */
    @Override
    public State movementStarts(String id) {
        fleet.get(id).setStateType(StateType.PASSIVE);
        mapProcessor.clearRoute(route);
        return State.builder().mapAreaState(MapAreaState.builder().map(BackToGUIConverter.convertMap(map)).fleet(BackToGUIConverter.convertFleet(fleet)).build())
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
}
