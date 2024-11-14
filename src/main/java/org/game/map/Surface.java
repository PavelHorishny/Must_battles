package org.game.map;

import lombok.Getter;
import lombok.Setter;
import org.game.gui.Coordinates;
import org.game.unit.GameUnit;

@Getter
@Setter
public class Surface {
    private int X;
    private int Y;
    private Coordinates coordinates;
    private SurfaceType type;
    private GameUnit unit;
    private boolean isUnderAttack;

    public Surface(int x, int y, SurfaceType type) {
        X = x;
        Y = y;
        this.type = type;
    }
    public Surface (Coordinates coordinates){
        this.coordinates=coordinates;
    }
    public Surface (Coordinates coordinates,SurfaceType type){
        this.coordinates=coordinates;
        this.type = type;
    }
}
