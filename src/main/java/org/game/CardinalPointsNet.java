package org.game;

import lombok.Getter;

import java.util.HashMap;

@Getter
public enum CardinalPointsNet {
    N(CardinalPoint.NORTH,CardinalPoint.NORTH_WEST,CardinalPoint.NORTH_EAST),
    NE(CardinalPoint.NORTH_EAST,CardinalPoint.NORTH,CardinalPoint.EAST),
    E(CardinalPoint.EAST,CardinalPoint.NORTH_EAST,CardinalPoint.SOUTH_EAST),
    SE(CardinalPoint.SOUTH_EAST,CardinalPoint.EAST,CardinalPoint.SOUTH),
    S(CardinalPoint.SOUTH,CardinalPoint.SOUTH_EAST,CardinalPoint.SOUTH_WEST),
    SW(CardinalPoint.SOUTH_WEST,CardinalPoint.SOUTH,CardinalPoint.WEST),
    W(CardinalPoint.WEST,CardinalPoint.SOUTH_WEST,CardinalPoint.NORTH_WEST),
    NW(CardinalPoint.NORTH_WEST,CardinalPoint.WEST,CardinalPoint.NORTH);

    private final CardinalPoint point;
    private final CardinalPoint left;
    private final CardinalPoint right;

    CardinalPointsNet(CardinalPoint point, CardinalPoint left, CardinalPoint right) {
        this.point = point;
        this.left = left;
        this.right = right;
    }

    public static final HashMap<CardinalPoint,CardinalPointsNet> pointers=new HashMap<>(){{
        put(CardinalPoint.NORTH,CardinalPointsNet.N);
        put(CardinalPoint.NORTH_EAST,CardinalPointsNet.NE);
        put(CardinalPoint.EAST,CardinalPointsNet.E);
        put(CardinalPoint.SOUTH_EAST,CardinalPointsNet.SE);
        put(CardinalPoint.SOUTH,CardinalPointsNet.S);
        put(CardinalPoint.SOUTH_WEST,CardinalPointsNet.SW);
        put(CardinalPoint.WEST,CardinalPointsNet.W);
        put(CardinalPoint.NORTH_WEST,CardinalPointsNet.NW);
    }};
}
