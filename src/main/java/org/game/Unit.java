package org.game;

import lombok.Getter;
import lombok.Setter;
import org.game.gui.Coordinates;

@Getter
@Setter
public abstract class Unit {
    private Coordinates coordinates;
    private String id;
}
