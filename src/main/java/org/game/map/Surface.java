package org.game.map;

import lombok.Getter;
import lombok.Setter;
import org.game.gui.Coordinates;
import org.game.unit.Fortification;
import org.game.unit.GameUnit;
import org.game.unit.Vessel;

@Getter
@Setter
public class Surface {

    private Coordinates coordinates;
    private SurfaceType type;
    private GameUnit unit;
    private Fortification fortification;
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
    public boolean isEmpty(){
        return this.unit==null;
    }
    public void setUnit(GameUnit unit){
        if(unit == null){
            this.unit = null;
        }else {
            this.unit = unit;
            unit.setCoordinates(coordinates);
            if(!getType().equals(SurfaceType.PORT)){
                this.unit.setReadyForRepair(false);
                if(this.unit.getCurrent_shots()>0) this.unit.setCanShoot(true);
                if(this.unit instanceof Vessel){
                    ((Vessel) this.unit).setReadyToHelp(false);
                }
            }
        }
    }
}
