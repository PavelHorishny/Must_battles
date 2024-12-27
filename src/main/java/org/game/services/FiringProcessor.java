package org.game.services;

import org.game.gui.StateType;
import org.game.unit.GameUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    /**
     * @param attacker
     * @param target
     * @return
     */
    @Override
    public Optional<GameUnit> shot(GameUnit attacker, GameUnit target) {
            if(getHit()){
                attacker.setCurrent_shots(attacker.getCurrent_shots()-1);
                target.setCurrent_hit_point(target.getCurrent_hit_point()-1);
                if(target.getCurrent_hit_point()<=0){
                    return Optional.of(target);
                }else{
                    return Optional.empty();
                }
            }else {
                attacker.setCurrent_shots(attacker.getCurrent_shots()-1);
                return Optional.empty();
            }
    }

    /**
     * @param attacker
     * @param target
     * @return
     */
    @Override
    public Optional<GameUnit> salvoShot(GameUnit attacker, GameUnit target) {
        int salvo = attacker.getCurrent_shots();
        if(getHit()){
            attacker.setCurrent_shots(attacker.getCurrent_shots()-salvo);
            target.setCurrent_hit_point(target.getCurrent_hit_point()-salvo);
            if(target.getCurrent_hit_point()<=0){
                return Optional.of(target);
            }else{
                return Optional.empty();
            }
        }else {
            attacker.setCurrent_shots(attacker.getCurrent_shots()-salvo);
            return Optional.empty();
        }
    }
    private boolean getHit(){
        Random random = new Random();
        return random.nextInt(0, 1) > 0;
    }
}
