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
    private boolean readyToHelp;
    private boolean helping;

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

    @Override
    public String toString() {
        return "GameUnit{" +
                "readyForRepair=" + readyForRepair +
                ", onRepair=" + onRepair +
                ", readyToHelp=" + readyToHelp +
                ", helping=" + helping +
                '}';
    }
}
