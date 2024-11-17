package org.game.unit;

import org.game.gui.Coordinates;

public class Vessel extends GameUnit {
    private VesselType type;
    private int breeze_move_points;
    private int calm_move_points;
    private int storm_move_points;

    public Vessel(boolean isFirstPlayer, Coordinates coordinates) {
        super(isFirstPlayer, coordinates);
    }
}
