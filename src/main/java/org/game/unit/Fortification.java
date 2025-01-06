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
    private boolean capturing;
    private FortificationType fortificationType;
    private List<Surface> port = new ArrayList<>();

    public Fortification(Coordinates coordinates,FortificationType fortificationType,boolean player){
        super(player,coordinates);
        this.fortificationType = fortificationType;
        this.setUnitType(UnitType.FORTIFICATION);
        setCoordinates(coordinates);
        initialState();
    }

    @Override
    void initialState() {
        super.initialState();
        switch (fortificationType){
            case FIRST_LINE_FORT -> setUp(FortificationType.FIRST_LINE_FORT);
            case SECOND_LINE_FORT -> setUp(FortificationType.SECOND_LINE_FORT);
            case ROYAL_PORT -> setUp(FortificationType.ROYAL_PORT);
        }
    }
    private void setUp(FortificationType type){
        setType(type.getType());
        setFire_range(type.getFire_range());
        setBase_hit_points(type.getHit_points());
        setCurrent_hit_point(getBase_hit_points());
        setBase_shots(type.getShots());
        setCurrent_shots(getBase_shots());
        setMovePoints(type.getMovePoints());
        capturing = false;
    }


    @Override
    public void newDayState() {
        super.newDayState();
        setCurrent_shots(getFortificationType().getShots());
    }
}
