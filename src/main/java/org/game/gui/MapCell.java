package org.game.gui;

import lombok.Getter;
import org.game.map.SurfaceType;

import java.awt.*;

@Getter
public class MapCell extends Rectangle {
    Coordinates coordinates;
    Color color;
    SurfaceType type;

    public MapCell(Coordinates coordinates, SurfaceType type){
        color = setColor(type);
        this.coordinates = coordinates;
        this.type=type;
        width = Constants.CELL_SIZE;
        height = Constants.CELL_SIZE;
        setPoints(coordinates);
    }


    private void setPoints(Coordinates coordinates) {
        x = coordinates.axisX() * Constants.CELL_SIZE;
        y = coordinates.axisY() * Constants.CELL_SIZE;
    }

    private Color setColor(SurfaceType type){
        return switch (type){
            case LAND -> Constants.LAND;
            case WATER -> Constants.WATER;
            case PORT -> Constants.PORT;
            case ROUTE -> Constants.ROUTE;
        };
    }
}
