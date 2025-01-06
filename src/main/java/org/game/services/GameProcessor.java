package org.game.services;

import org.game.gui.Coordinates;
import org.game.gui.StateType;
import org.game.state.*;
import org.game.unit.*;

import java.util.*;


public class GameProcessor implements GameService {
    private final GameState state = new GameState();
    private final MapService mapProcessor = new MapProcessor();
    private final FortificationService fortificationProcessor = new FortificationProcessor();
    private final VesselService vesselProcessor = new VesselProcessor();
    private final WeatherService weatherProcessor = new WeatherProcessor();
    private final FiringService firingProcessor = new FiringProcessor();
    private final StateConverter converter = new Converter();
    private final UnitService unitService = new UnitProcessor(vesselProcessor,fortificationProcessor,mapProcessor);

    public GameProcessor() {

    }


    @Override
    public State initialGameState() {
        state.setDay(1);
        state.setFirstPlayerMove(true);
        state.setMap(mapProcessor.generateStandardMap());
        unitService.setAllUnits(state);

        state.getMap()[20][3].setUnit(new Vessel(true, VesselType.THREE_DECKER_SHIP_OF_LINE));
        state.getMap()[20][3].getUnit().setCoordinates(new Coordinates(20,3));
        state.getMap()[20][3].getUnit().setUnitType(UnitType.VESSEL);
        state.getMap()[20][3].getUnit().setId("test");

        state.getFleet().put("test",state.getMap()[20][3].getUnit());

        vesselProcessor.getListOfAllVessels(state.getFleet()).forEach(e->e.setCurrentWeather(weatherProcessor.getWeather()));

        return converter.convertState(state);
    }

    @Override
    public State unitSelected(String id) {
        state.setStormDestination(null);
        state.setVesselInStorm(null);
        if(id.isBlank()){
            if(state.getSelected() !=null) {
                setState(StateType.PASSIVE, state.getSelected());
            }
            state.setSelected(null);
            state.setTarget(null);
            mapProcessor.clearRoute(state.getRoute());
            firingProcessor.clearAimed(state.getAimedUnits());
        }else{
            GameUnit unit = state.getFleet().get(id);
            if(unit.isFirstPlayer()==state.isFirstPlayerMove()||isSelectedDestroyedFort(unit)){
                if(state.getSelected() !=null&&!state.getSelected().equals(unit)) {
                    setState(StateType.PASSIVE, state.getSelected());
                    mapProcessor.clearRoute(state.getRoute());
                }
                state.setSelected(unit);
/*                if(map[selected.getCoordinates().axisX()][selected.getCoordinates().axisY()].getType().equals(SurfaceType.PORT)) {
                    System.out.println(map[selected.getCoordinates().axisX()][selected.getCoordinates().axisY()].getFortification().toUnitData().toString());
                }
                if(selected instanceof Vessel){
                    System.out.println(selected.isReadyForRepair());
                }*/
                unitService.setButtonsState(state);

                setState(StateType.SELECTED, state.getSelected());
                mapProcessor.getRoute(state.getSelected(),state.getRoute(), state.getMap());
                firingProcessor.setUnderAttack(mapProcessor.getFiringZone(state.getSelected(), state.getMap()),state.getAimedUnits(), state.getSelected());
                Optional.of(unit).ifPresent(unit1 -> {
                    if(unit1.getUnitType().equals(UnitType.VESSEL)){
                        if(unit.getMovePoints()>0) {
                            if (weatherProcessor.isStorm(unit1) && mapProcessor.isNotInPort(unit1, state.getMap())) {
                                state.setSelected(unit1);
                                state.setVesselInStorm(unit1.getCoordinates());
                                state.setStormDestination(state.getRoute().get(state.getRoute().size()-1).getCoordinates());
                            }
                        }
                    }
                });
            }else{
                state.setTarget(unit);
            }
        }
        return converter.convertState(state);
    }

    private void setState(StateType stateType, GameUnit unit) {
        if(unit instanceof Fortification){
            if(unit.getStateType().equals(StateType.DESTROYED)){
                unit.setStateType(StateType.DESTROYED);
            }else {
                unit.setStateType(stateType);
            }
        }else {
            unit.setStateType(stateType);
        }
    }

    private boolean isSelectedDestroyedFort(GameUnit unit) {
        if(unit instanceof Fortification){
            return unit.getStateType().equals(StateType.DESTROYED);
        }else {
            return false;
        }
    }

    @Override
    public State movementStarts(String id) {
        state.getFleet().get(id).setStateType(StateType.PASSIVE);
        mapProcessor.clearRoute(state.getRoute());
        return converter.convertState(state);
    }

    @Override
    public State movementEnds(String id, Coordinates destination) {
        vesselProcessor.moveVesselToDestinationPoint((Vessel)state.getFleet().get(id),destination, state.getMap());
        return unitSelected(id);
    }
    //TODO merge movement methods as shoot methods

    @Override
    public State shooting (String attackerID, String targetID, String shotType) {
        switch (shotType){
            case "single" -> firingProcessor.shot(state.getFleet().get(attackerID), state.getFleet().get(targetID)).ifPresent(unit ->
                unitService.onDestruction(unit,state));
            case "salvo" -> firingProcessor.salvoShot(state.getFleet().get(attackerID),state.getFleet().get(targetID)).ifPresent(unit ->
                unitService.onDestruction(unit,state));
        }
        return unitSelected(attackerID);
    }

    @Override
    public State dayEnd() {
        Optional.ofNullable(state.getSelected()).ifPresent(unit ->
            setState(StateType.PASSIVE,unit));
        state.setSelected(null);
        state.setTarget(null);
        state.setVesselInStorm(null);
        state.setStormDestination(null);
        mapProcessor.clearRoute(state.getRoute());
        firingProcessor.clearAimed(state.getAimedUnits());
        unitService.OnTurnEnd(state);
        if(state.isFirstPlayerMove()){
            state.setFirstPlayerMove(false);
        }else {
            state.setFirstPlayerMove(true);
            state.setDay(state.getDay()+1);
            unitService.OnDayEnd(state);
        }
        if(!state.getEndGame().isEmpty()){
            return converter.convertState(state);
        }else {
            return unitSelected("");

        }
    }

    @Override
    public State unitReadyForRepair(boolean state) {
       this.state.getSelected().setReadyForRepair(state);
       if(this.state.getSelected() instanceof Vessel){
           ((Vessel) this.state.getSelected()).setCanMove(false);
       }
       return unitSelected(this.state.getSelected().getId());
    }

    @Override
    public State unitReadyForHelp(boolean state) {
       if (this.state.getSelected() instanceof Vessel vessel) {
           vessel.setReadyToHelp(state);
           return unitSelected(vessel.getId());
       }else {
           return unitSelected("");
       }
    }
}
