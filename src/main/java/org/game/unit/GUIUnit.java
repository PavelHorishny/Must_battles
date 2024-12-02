package org.game.unit;

import lombok.Getter;
import lombok.Setter;
import org.game.gui.Coordinates;
import org.game.gui.StateType;

import java.awt.image.BufferedImage;
@Getter
@Setter

public class GUIUnit extends Unit {


    private UnitIcons icons;
    private BufferedImage currentIcon;
    private StateType currentState;
    private UnitType type;

    public GUIUnit() {
    }
    public GUIUnit(UnitType type){

    }

    public GUIUnit(UnitIcons icons, Coordinates coordinates, StateType state){
        this.icons = icons;
        //this.coordinates=coordinates;
        this.setCoordinates(coordinates);
        //setPosition(this.getCoordinates());
        setActivity(state);
    }

    public GUIUnit(String id, Coordinates coordinates, UnitIcons unitIcons, StateType stateType) {
        this.setId(id);
        this.setCoordinates(coordinates);
        icons = unitIcons;
        setActivity(stateType);
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
    }*//*
    @Override
    public Coordinates getCoordinates() {
    return super.getCoordinates();
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setCoordinates(Coordinates coordinates) {
        super.setCoordinates(coordinates);
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }*/
/*    public void setCurrentIcon(UnitIcons icons){
        if(icons==null){
            currentIcon =
        }
    }*/
}
