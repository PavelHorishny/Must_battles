package org.game.gui;

import lombok.Getter;

import java.awt.*;

@Getter
public class MapCell extends Rectangle {
    Coordinates coordinates;
    Color color;
    public MapCell(Coordinates coordinates, MapCellType type){
        color = setColor(type);
        this.coordinates = coordinates;
        width = Constants.CELL_SIZE;
        height = Constants.CELL_SIZE;
        setPoints(coordinates);
    }

    private void setPoints(Coordinates coordinates) {
        x = coordinates.axisX() * Constants.CELL_SIZE;
        y = coordinates.axisY() * Constants.CELL_SIZE;
    }

    private Color setColor(MapCellType type){
        return switch (type){
            case LAND -> Constants.LAND;
            case WATER -> Constants.WATER;
        };
    }
}
