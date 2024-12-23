package org.game.map;

import lombok.Getter;
import lombok.Setter;
import org.game.gui.Coordinates;
import org.game.unit.GameUnit;

@Getter
@Setter
public class Surface {

    private Coordinates coordinates;
    private SurfaceType type;
    private GameUnit unit;
    private boolean isUnderAttack;

    public Surface (Coordinates coordinates,SurfaceType type){
        this.coordinates=coordinates;
        this.type = type;
    }

    public void setType(SurfaceType type) {
        if(this.type!=null){
            if(this.type.equals(SurfaceType.WATER)&&type.equals(SurfaceType.ROUTE)) this.type = type;
            if(this.type.equals(SurfaceType.WATER)&&type.equals(SurfaceType.PORT)) this.type = type;
            if(this.type.equals(SurfaceType.ROUTE)&&type.equals(SurfaceType.WATER)) this.type = type;
        }else{
            this.type = type;
        }
    }
}
