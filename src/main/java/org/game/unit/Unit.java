package org.game.unit;

import lombok.Getter;
import lombok.Setter;
import org.game.gui.Coordinates;
import org.game.gui.StateType;

@Getter
@Setter
public abstract class Unit {
    private Coordinates coordinates;
    private String id;
    private StateType stateType;
    abstract void initialState();
}
