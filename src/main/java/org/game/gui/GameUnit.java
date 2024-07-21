package org.game.gui;

import lombok.Getter;

import java.awt.image.BufferedImage;
@Getter
public class GameUnit {
    UnitIcons icons;
    BufferedImage currentIcon;
    StateType currentState;
    int x;
    int y;
    Coordinates coordinates;
    
    public GameUnit(UnitIcons icons, Coordinates coordinates, StateType state){
        this.icons = icons;
        this.coordinates=coordinates;
        setPosition(this.coordinates);
        setActivity(state);
    }

    public void setActivity(StateType type){
        switch (type){
            case PASSIVE -> currentIcon=icons.icon();
            case SELECTED -> currentIcon=icons.active();
            case UNDER_ATTACK -> currentIcon=icons.underAttack();
        }
    }
    private void setPosition(Coordinates coordinates){
        x=coordinates.axisX()*Constants.CELL_SIZE;
        y=coordinates.axisY()*Constants.CELL_SIZE;
    }
}
