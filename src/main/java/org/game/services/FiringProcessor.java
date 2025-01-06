package org.game.services;

import org.game.gui.Coordinates;
import org.game.gui.StateType;
import org.game.unit.GameUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class FiringProcessor implements FiringService{

    @Override
    public void setUnderAttack(List<GameUnit> inFiringZone, ArrayList<GameUnit> aimedUnits, GameUnit attacker) {
        if(!aimedUnits.isEmpty()) {
            clearAimed(aimedUnits);
        }
        if(attacker.getCurrent_shots()>0&&attacker.isCanShoot()) {
            inFiringZone.forEach(unit -> {
                if (unit.isFirstPlayer() != attacker.isFirstPlayer()) {
                    if (!unit.getStateType().equals(StateType.DESTROYED)) {
                        unit.setStateType(StateType.AIMED);
                        aimedUnits.add(unit);
                    }
                }
            });
        }
    }


    @Override
    public Optional<GameUnit> shot(GameUnit attacker, GameUnit target) {
        attacker.setCurrent_shots(10);
        if(attacker.isCanShoot()&&attacker.getCurrent_shots()>0) {
            if (getHit(getDistance(attacker.getCoordinates(),target.getCoordinates()))) {
                attacker.setCurrent_shots(attacker.getCurrent_shots() - 1);
                target.setCurrent_hit_point(target.getCurrent_hit_point() - 1);
                if (target.getCurrent_hit_point() <= 0) {
                    return Optional.of(target);
                } else {
                    return Optional.empty();
                }
            } else {
                attacker.setCurrent_shots(attacker.getCurrent_shots() - 1);
                return Optional.empty();
            }
        }else {
            return Optional.empty();
        }
    }


    @Override
    public Optional<GameUnit> salvoShot(GameUnit attacker, GameUnit target) {
        if(attacker.isCanShoot()&&attacker.getCurrent_shots()>0) {
            int salvo = attacker.getCurrent_shots();
            if (getHit(getDistance(attacker.getCoordinates(),target.getCoordinates()))) {
                attacker.setCurrent_shots(attacker.getCurrent_shots() - salvo);
                target.setCurrent_hit_point(target.getCurrent_hit_point() - salvo);
                if (target.getCurrent_hit_point() <= 0) {
                    return Optional.of(target);
                } else {
                    return Optional.empty();
                }
            } else {
                attacker.setCurrent_shots(attacker.getCurrent_shots() - salvo);
                return Optional.empty();
            }
        }else {
            return Optional.empty();
        }
    }


    @Override
    public void clearAimed(ArrayList<GameUnit> aimedUnits) {
        aimedUnits.forEach(unit -> {
            if(!unit.getStateType().equals(StateType.DESTROYED)){
                unit.setStateType(StateType.PASSIVE);
            }
        });
        aimedUnits.clear();
    }

    private boolean getHit(int distance){
        Random random = new Random();
        ArrayList <Boolean> tmp = new ArrayList<>(100);
        for(int i = 0; i< 100; i++){
            if(i<getAccuracy(distance)){
                tmp.add(false);
            }else {
                tmp.add(true);
            }
        }
        ArrayList<Boolean> tmp2 = new ArrayList<>();
        for(int i = 99;i>=0; i--){
            int rnd = random.nextInt(0,tmp.size());
            tmp2.add(tmp.get(rnd));
            tmp.remove(rnd);
        }
        return tmp2.get(random.nextInt(0,tmp2.size()));
    }
    private int getDistance(Coordinates attacker, Coordinates target) {
        int distance;
        if(target.axisX()- attacker.axisX()!=0){
            distance = Math.abs(target.axisX() - attacker.axisX());
        }else{
            distance = Math.abs(target.axisY() - attacker.axisY());
        }
        return distance;
    }
    private int getAccuracy(int distance){
        return switch (distance) {
            case 1 -> 25;
            case 2 -> 30;
            case 3 -> 35;
            case 4 -> 40;
            case 5 -> 45;
            default -> 50;
        };
    }
}
