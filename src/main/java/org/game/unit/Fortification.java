package org.game.unit;

import lombok.Getter;
import org.game.gui.Coordinates;

@Getter
public class Fortification extends GameUnit {
    private final FortificationType fortificationType;

    public Fortification(Coordinates coordinates,FortificationType fortificationType,boolean player){
        super(player,coordinates);
        this.fortificationType = fortificationType;
    }
}
