package org.game.gui;

import lombok.Getter;
import lombok.Setter;
import org.game.Unit;

import java.awt.image.BufferedImage;
@Getter
@Setter
public class GUIUnit extends Unit {
    private UnitIcons icons;
    private BufferedImage currentIcon;
    private StateType currentState;
    /*int x;
    int y;
    Coordinates coordinates;*/
    
    public GUIUnit(UnitIcons icons, Coordinates coordinates, StateType state){
        this.icons = icons;
        //this.coordinates=coordinates;
        this.setCoordinates(coordinates);
        //setPosition(this.getCoordinates());
        setActivity(state);
    }

    public void setActivity(StateType type){
        switch (type){
            case PASSIVE -> currentIcon=icons.icon();
            case SELECTED -> currentIcon=icons.active();
            case AIMED -> currentIcon=icons.aimed();
        }
    }
/*    private void setPosition(Coordinates coordinates){
        x=coordinates.axisX()*Constants.CELL_SIZE;
        y=coordinates.axisY()*Constants.CELL_SIZE;
    }*/
}
