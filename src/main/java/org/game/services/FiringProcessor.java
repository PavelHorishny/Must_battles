package org.game.services;

import org.game.gui.StateType;
import org.game.map.Surface;

import java.util.List;

public class FiringProcessor implements FiringService{
    /**
     * @param fireZone
     * @param isFirstPlayer
     */
    @Override
    public void setUnderAttack(List<Surface> fireZone, boolean isFirstPlayer) {
        //if(!activeCells.isEmpty()) activeCells.clear();
        fireZone.forEach(surface -> {
            if(!surface.isEmpty()){
                if(surface.getUnit().isFirstPlayer()!=isFirstPlayer) surface.getUnit().setStateType(StateType.AIMED);
            }
        });
    }
}
