package org.game.services;

import org.game.gui.StateType;
import org.game.state.GameState;
import org.game.unit.Fortification;
import org.game.unit.FortificationType;
import org.game.unit.GameUnit;
import org.game.unit.Vessel;

public class UnitProcessor implements UnitService {
    VesselService vesselService;
    FortificationService fortificationService;
    MapService mapService;

    public UnitProcessor(VesselService vesselService, FortificationService fortificationService, MapService mapService) {
        this.vesselService = vesselService;
        this.fortificationService = fortificationService;
        this.mapService = mapService;
        vesselService.setFortificationService(fortificationService);
        fortificationService.setVesselService(vesselService);
    }

    @Override
    public void setAllUnits(GameState gameState) {
        fortificationService.setStandardFortifications(gameState.getFleet(), gameState.getMap());
        vesselService.setAllVessels(gameState.getFleet(),gameState.getMap());
    }

    @Override
    public void setButtonsState(GameState state) {
        switch (state.getSelected().getUnitType()){
            case FORTIFICATION -> {
                state.setButtonOnRepairActive(fortificationService.isUnitCanBeRepaired(state.getSelected(), state.getMap()));
                state.setButtonReadyForHelpActive(false);
            }
            case VESSEL -> {
                state.setButtonOnRepairActive(vesselService.isUnitCanBeRepaired(state.getSelected(),state.getMap()));
                state.setButtonReadyForHelpActive(vesselService.isUnitReadyToTakePartAtRepair(state.getSelected(), state.getMap()));
            }
        }
    }

    @Override
    public void onDestruction(GameUnit unit, GameState state) {
        switch (unit.getUnitType()){
            case FORTIFICATION ->
                fortificationService.whenUnitDestroyed(unit,state.getFleet(),state.getMap());

            case VESSEL ->
                vesselService.whenUnitDestroyed(unit,state.getFleet(), state.getMap());
        }
    }

    @Override
    public void OnTurnEnd(GameState state) {
        fortificationService.getFortificationsByType(fortificationService.getAllFortifications(state.getFleet()), FortificationType.ROYAL_PORT)
                .forEach(fortification -> {
                    if(!fortificationService.isRoyalPortIsNotEmpty(fortification)){
                        fortificationService.restoreRoyalPort(fortification);
                    }else{
                        fortification.getPort().forEach(surface -> {
                            if(!surface.isEmpty()){
                                surface.getUnit().setCanShoot(false);
                            }
                        });
                    }
                });
    }

    @Override
    public void OnDayEnd(GameState state) {
        vesselService.getListOfAllVessels(state.getFleet()).forEach(vessel -> {
            vessel.setMovePoints(vessel.getVesselType().getBreeze_move_points());
            vessel.setCurrent_shots(vessel.getVesselType().getShots());
            vessel.setCanShoot(true);
            if(vessel.isOnRepair()) vesselService.repairUnit(vessel);
            if(vessel.isReadyForRepair()){
                //vessel.setOnRepair(true);
                vesselService.setUnitOnRepair(vessel);
                //vessel.setReadyForRepair(false);
            }
            vessel.setCanMove(vesselService.checkIfCanMove(vessel));
            vessel.setCanShoot(vesselService.checkIfCanShoot(vessel,state.getMap()));
        });
        fortificationService.getAllFortifications(state.getFleet()).forEach(fortification -> {
            fortification.setCurrent_shots(fortification.getFortificationType().getShots());
            fortification.setCanShoot(fortificationService.checkIfCanShoot(fortification));
            if(fortification.getFortificationType().equals(FortificationType.ROYAL_PORT)){
                if(fortification.isCapturing()){
                    fortification.setCurrent_hit_point(fortification.getCurrent_hit_point()-1);
                    if(fortification.getCurrent_hit_point()<=0){
                        state.getEndGame().setEndGame(true);
                        state.getEndGame().setMessage(fortificationService.testString(fortification));
                    }
                }
                if(!fortification.isCapturing()&&fortificationService.isRoyalPortIsNotEmpty(fortification)){
                    fortification.setCapturing(true);
                }
            }else {
                if(fortification.isOnRepair()){
                    fortificationService.repairUnit(fortification);
                }
                if(fortification.isReadyForRepair()){
                    fortificationService.setUnitOnRepair(fortification);
                 /*   fortification.setOnRepair(true);
                    fortification.setReadyForRepair(false);*/
                    fortification.getPort().stream().filter(surface -> !surface.isEmpty()).toList().forEach(surface -> {
                        Vessel vessel = (Vessel) surface.getUnit();
                        if(vessel.isReadyToHelp()) vesselService.setHelpInRepairStates(surface.getUnit(),true); //surface.getUnit().setHelping(true);//Find
                    });
                }
            }
        });
    }
    @Override
    public void setState(StateType stateType, GameUnit unit) {
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
    @Override
    public boolean isSelectedDestroyedFort(GameUnit unit) {
        if(unit instanceof Fortification){
            return unit.getStateType().equals(StateType.DESTROYED);
        }else {
            return false;
        }
    }

    @Override
    public void setRepairableStates(GameUnit gameState, boolean state) {
/*        Optional.ofNullable(gameState).ifPresent(gameUnit -> {
            if(state){
                gameUnit.setReadyForRepair(true);
            }else{
                if(gameUnit.isOnRepair()) {
                    gameUnit.setOnRepair(false);
                    gameUnit.setReadyForRepair(false);
                }
            }
        });*/

        switch (gameState.getUnitType()){
            case FORTIFICATION ->
                fortificationService.setRepairableStates(state, gameState);

            case VESSEL ->
                vesselService.setRepairableStates(state, gameState);

        }
    }

    @Override
    public void setTakingPartInRepairStates(GameUnit selected, boolean state) {
        if(selected instanceof Vessel){
            vesselService.setHelpInRepairStates(selected,state);
        }
    }
}
