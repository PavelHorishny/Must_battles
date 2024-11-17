package org.game;

import lombok.Getter;
import org.game.gui.Coordinates;

@Getter
public enum CardinalPoint {
    NORTH("N", new Coordinates(0,-1)),
    SOUTH("S", new Coordinates(0,1)),
    WEST("W", new Coordinates(-1,0)),
    EAST("E", new Coordinates(1,0)),
    NORTH_WEST("NW", new Coordinates(-1,-1)),
    NORTH_EAST("NE", new Coordinates(1,-1)),
    SOUTH_WEST("SW", new Coordinates(-1,1)),
    SOUTH_EAST("SE", new Coordinates(1,1));

    private final String name;
    private final Coordinates value;

    CardinalPoint(String name,Coordinates value){
        this.name = name;
        this.value = value;
    }

    public static final CardinalPoint[] cardinalPoints = new CardinalPoint[]{
            NORTH,SOUTH,WEST,EAST,NORTH_WEST,NORTH_EAST,SOUTH_WEST,SOUTH_EAST
    };
}
