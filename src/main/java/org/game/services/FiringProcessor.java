package org.game.services;

import org.game.gui.StateType;
import org.game.unit.GameUnit;

import java.util.ArrayList;
import java.util.List;

public class FiringProcessor implements FiringService{
    /**
     * @param inFiringZone
     * @param aimedUnits
     * @param isFirstPlayer
     */
    @Override
    public void setUnderAttack(List<GameUnit> inFiringZone, ArrayList<GameUnit> aimedUnits, boolean isFirstPlayer) {
        if(!aimedUnits.isEmpty()) {
            aimedUnits.forEach(unit -> unit.setStateType(StateType.PASSIVE));
            aimedUnits.clear();
        }

        inFiringZone.forEach(unit -> {
            if(unit.isFirstPlayer()!=isFirstPlayer){
                unit.setStateType(StateType.AIMED);
                aimedUnits.add(unit);
            }
        });
    }
}
