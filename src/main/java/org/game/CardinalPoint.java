package org.game;

import lombok.Getter;
import org.game.gui.Coordinates;

@Getter
public enum CardinalPoint {
    NORTH("N","North", new Coordinates(0,-1)),
    SOUTH("S", "South", new Coordinates(0,1)),
    WEST("W","West", new Coordinates(-1,0)),
    EAST("E","East", new Coordinates(1,0)),
    NORTH_WEST("NW","North West", new Coordinates(-1,-1)),
    NORTH_EAST("NE","North East", new Coordinates(1,-1)),
    SOUTH_WEST("SW","South West", new Coordinates(-1,1)),
    SOUTH_EAST("SE","South East", new Coordinates(1,1));

    private final String name;
    private final String description;
    private final Coordinates value;

    CardinalPoint(String name, String description,Coordinates value){
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public static final CardinalPoint[] cardinalPoints = new CardinalPoint[]{
            NORTH,SOUTH,WEST,EAST,NORTH_WEST,NORTH_EAST,SOUTH_WEST,SOUTH_EAST
    };
}
