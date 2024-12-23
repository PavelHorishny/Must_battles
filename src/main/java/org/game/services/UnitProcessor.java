package org.game.services;

import org.game.BackToGUIConverter;
import org.game.UnitData;
import org.game.gui.StateType;
import org.game.state.InfoAreaState;
import org.game.state.MapAreaState;
import org.game.state.State;
import org.game.map.Surface;
import org.game.unit.Fortification;
import org.game.unit.GameUnit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;



public class UnitProcessor implements UnitService{
    private Surface[][] map;
    private final ArrayList <Surface> route = new ArrayList<>(); // maybe redundant
    private final Map <String, GameUnit> fleet = new HashMap<>();
    private int day;
    private boolean isFirstPlayerMove;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional <GameUnit> selected = Optional.empty();
    private final MapService mapProcessor = new MapProcessor();
    private final FortificationService fortificationProcessor = new FortificationProcessor();
    private final VesselProcessor vesselProcessor = new VesselProcessor();



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
        vesselProcessor.getVessels(fleet,map);
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
                mapProcessor.getRoute(unit.getCoordinates(), unit.getMovePoints(), route,map);
                if(selected.isPresent()){
                    if(!selected.get().equals(unit)){
                        mapProcessor.clearRoute(route);
                        selected.get().setStateType(StateType.PASSIVE);
                        selected = Optional.empty();
                    }
                }
                unit.setStateType(StateType.SELECTED);
                selected = Optional.of(unit);
                mapProcessor.getRoute(unit.getCoordinates(), unit.getMovePoints(), route,map);
                UnitData data = unit.toUnitData();
                return State.builder().mapAreaState(MapAreaState.builder().map(BackToGUIConverter.convertMap(map)).fleet(BackToGUIConverter.convertFleet(fleet)).build())
                        .infoAreaState(InfoAreaState.builder().day(String.valueOf(day)).selected(true).selectedData(data).build()).build();
            }else{
                UnitData data = unit.toUnitData();
                return State.builder().mapAreaState(MapAreaState.builder().map(BackToGUIConverter.convertMap(map)).fleet(BackToGUIConverter.convertFleet(fleet)).build())
                        .infoAreaState(InfoAreaState.builder().day(String.valueOf(day)).target(true).targetData(data).build()).build();
            }
        }else{
            if(selected.isPresent()){
                selected.get().setStateType(StateType.PASSIVE);
                selected = Optional.empty();
            }
            return State.builder().mapAreaState(MapAreaState.builder().map(BackToGUIConverter.convertMap(map)).fleet(BackToGUIConverter.convertFleet(fleet)).build())
                    .infoAreaState(InfoAreaState.builder().day(String.valueOf(day)).selected(false).build()).build();
        }
    }
}
