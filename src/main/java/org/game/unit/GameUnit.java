package org.game.unit;

import lombok.Getter;
import lombok.Setter;
import org.game.gui.Coordinates;
import org.game.gui.StateType;

@Setter
@Getter
public class GameUnit extends Unit {
    private UnitType unitType;
    private StateType stateType;
    private boolean isFirstPlayer;
    private String description;
    private int fire_range;
    private int hit_points;
    private int shots;
    private int movePoints;

    public GameUnit(boolean isFirstPlayer, Coordinates coordinates) {
        //this.unitType = unitType;
        this.isFirstPlayer = isFirstPlayer;
        setCoordinates(coordinates);
    }
}
