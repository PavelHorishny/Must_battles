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
    private StateType stateType;
    private boolean isFirstPlayer;
    private Weather temp_field_weather;
    private String type;
    private int fire_range;
    private int base_hit_points;
    private int current_hit_point;
    private int base_shots;
    private int current_shots;
    private int movePoints;


    public GameUnit(boolean isFirstPlayer, Coordinates coordinates) {
        //this.unitType = unitType;
        this.isFirstPlayer = isFirstPlayer;
        setCoordinates(coordinates);
        stateType = StateType.PASSIVE;
    }
    public GameUnit(){
        stateType=StateType.PASSIVE;
    }


    public UnitData toUnitData() {
        return new UnitData(getId(),type,base_hit_points,current_hit_point,base_shots,current_shots,movePoints,fire_range);
    }

    @Override
    void initialState() {

    }
}
