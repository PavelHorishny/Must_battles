package org.game.unit;

import lombok.Getter;
import lombok.Setter;
import org.game.UnitData;
import org.game.Weather;
import org.game.gui.Coordinates;
import org.game.gui.StateType;

@Setter
@Getter
public class GameUnit extends Unit {
    private UnitType unitType;
    private boolean isFirstPlayer;
    @Setter
    private Weather currentWeather;
    @Setter
    private boolean readyForRepair;
    private boolean onRepair;
    private boolean canShoot;

    private String type;
    private int fire_range;
    private int base_hit_points;
    private int current_hit_point;
    private int base_shots;
    private int current_shots;
    private int movePoints;
    private int maxMP;


    public GameUnit(boolean isFirstPlayer, Coordinates coordinates) {

        this.isFirstPlayer = isFirstPlayer;
        setCoordinates(coordinates);
        setStateType(StateType.PASSIVE);
    }
    public GameUnit(){
        setStateType(StateType.PASSIVE);
    }


    public UnitData toUnitData() {
        return new UnitData(getId(),type,base_hit_points,current_hit_point,base_shots,current_shots,maxMP,fire_range);
    }

    @Override
    void initialState() {

    }
    public void newDayState(){

    }
    public void setOnRepair(boolean onRepair){
        if(onRepair){
            this.readyForRepair = false;
            this.onRepair = true;
            this.canShoot = false;
        }else {
            this.onRepair = false;
            if(current_shots>0){
                this.canShoot=true;
            }
        }
    }
    public void setCurrent_shots(int shots){
        current_shots = shots;
        if(current_shots<=0){
            canShoot=false;
        }
    }
    public void setCanShoot(boolean ability){
        if(isOnRepair()){
            canShoot = false;
        }else {
            canShoot = ability;
        }
    }
}
