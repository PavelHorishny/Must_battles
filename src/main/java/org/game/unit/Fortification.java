package org.game.unit;

import lombok.Getter;
import lombok.Setter;
import org.game.gui.Coordinates;
import org.game.map.Surface;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Fortification extends GameUnit {
    private FortificationType fortificationType;
    private List<Surface> port = new ArrayList<>();

    public Fortification(Coordinates coordinates,FortificationType fortificationType,boolean player){
        super(player,coordinates);
        this.fortificationType = fortificationType;
        this.setUnitType(UnitType.FORTIFICATION);
    }
}
