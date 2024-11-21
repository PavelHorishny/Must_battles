package org.game.unit;

import lombok.Getter;
import lombok.Setter;
import org.game.gui.Coordinates;

@Getter
@Setter
public abstract class Unit {
    @Getter
    private Coordinates coordinates;
    private String id;

}
