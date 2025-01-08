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
        clearActivePoints();
        if(id.isBlank()){
            if(state.getSelected() !=null) {
                unitService.setState(StateType.PASSIVE, state.getSelected());
            }
            clearActiveUnits();
            clearActiveCells();
        }else{
            GameUnit gameUnit = state.getFleet().get(id);
            if(gameUnit.isFirstPlayer()==state.isFirstPlayerMove()||unitService.isSelectedDestroyedFort(gameUnit)){
                if(state.getSelected() !=null&&!state.getSelected().equals(gameUnit)) {
                    unitService.setState(StateType.PASSIVE, state.getSelected());
                    clearActiveCells();
                }
                state.setSelected(gameUnit);
/*                if(map[selected.getCoordinates().axisX()][selected.getCoordinates().axisY()].getType().equals(SurfaceType.PORT)) {
                    System.out.println(map[selected.getCoordinates().axisX()][selected.getCoordinates().axisY()].getFortification().toUnitData().toString());
                }
                if(selected instanceof Vessel){
                    System.out.println(selected.isReadyForRepair());
                }*/
                unitService.setButtonsState(state);

                unitService.setState(StateType.SELECTED, state.getSelected());
                mapProcessor.getRoute(state.getSelected(),state.getRoute(), state.getMap());
                firingProcessor.setUnderAttack(mapProcessor.getFiringZone(state.getSelected(), state.getMap()),state.getAimedUnits(), state.getSelected());
                Optional.of(gameUnit).ifPresent(unit -> {
                    if(unit.getUnitType().equals(UnitType.VESSEL)){
                        if(gameUnit.getMovePoints()>0) {
                            if (weatherProcessor.isStorm(unit) && mapProcessor.isNotInPort(unit, state.getMap())) {
                                state.setSelected(unit);
                                state.setVesselInStorm(unit.getCoordinates());
                                state.setStormDestination(state.getRoute().get(state.getRoute().size()-1).getCoordinates());
                            }
                        }
                    }
                });
            }else{
                state.setTarget(gameUnit);
            }
        }
        return converter.convertState(state);
    }


    @Override
    public State movementStarts(String id) {
        state.getFleet().get(id).setStateType(StateType.PASSIVE);
        clearActiveCells();
        return converter.convertState(state);
    }

    @Override
    public State movementEnds(String id, Coordinates destination) {
        vesselProcessor.moveUnitToDestinationPoint(state.getFleet().get(id),destination, state.getMap());
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
            unitService.setState(StateType.PASSIVE,unit));
        clearActiveUnits();
        clearActivePoints();
        clearActiveCells();
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
        if (this.state.getSelected()!=null) {
            unitService.setRepairableStates(this.state.getSelected(),state);
            return unitSelected(this.state.getSelected().getId());
        }else {
            return unitSelected("");
        }
    }

    @Override
    public State unitReadyForHelp(boolean state) {
        if(this.state.getSelected()!=null&& this.state.getSelected() instanceof Vessel){
            if(state){
                this.state.getSelected().setReadyToHelp(true);
                return unitSelected(this.state.getSelected().getId());
            }else {
                if(this.state.getSelected().isHelping()){
                    this.state.getSelected().setHelping(false);
                }
                this.state.getSelected().setReadyToHelp(false);
            }
            return unitSelected(this.state.getSelected().getId());

        }else {
            return unitSelected("");
        }
    }
    private void clearActiveUnits(){
        state.setSelected(null);
        state.setTarget(null);
    }
    private void clearActivePoints(){
        state.setVesselInStorm(null);
        state.setStormDestination(null);
    }
    private void clearActiveCells(){
        mapProcessor.clearRoute(state.getRoute());
        firingProcessor.clearAimed(state.getAimedUnits());
    }
}
