package org.game.services;

import org.game.state.GameState;
import org.game.unit.Vessel;

public class UnitProcessor implements UnitService {
    VesselProcessor vesselService = new VesselProcessor();
    FortificationProcessor fortificationService = new FortificationProcessor();
    MapService mapService = new MapProcessor();
    /**
     * @param gameState
     */
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
}
