package org.game.unit;

import lombok.Getter;

@Getter
public enum VesselType {
    THREE_DECKER_SHIP_OF_LINE("Big battleship",5,0,3,3,6,7),
    TWO_DECKER_SHIP_OF_LINE("Small battleship",5,0,3,2,5,5),
    FRIGATE("Frigate",6,0,2,1,5,4),
    TENDER("Tender",6,1,2,1,1,2),
    BRIG("Brig",5,1,2,1,3,3),
    GALLEON("Galleon",4,0,3,2,3,3),
    STEAM_FRIGATE("Steam frigate",6,4,1,1,3,4),
    BATTERY("Naval battery",4,2,2,3,6,5),
    GALLEY("Galley",5,3,1,1,3,3),
    CORVETTE("Steam corvette",6,4,1,1,3,3),
    MONITOR("Monitor",3,2,3,1,3,3),
    STEAMSHIP("Steamship",5,2,2,1,3,3);

    private final String description;
    private final int breeze_move_points;
    private final int calm_move_points;
    private final int storm_move_points;
    private final int shots;
    private final int fire_range;
    private final int hit_points;

    VesselType(String description, int breeze_move_points, int calm_move_points, int storm_move_points,
               int shots, int fire_range, int hit_points) {
        this.description = description;
        this.breeze_move_points = breeze_move_points;
        this.calm_move_points = calm_move_points;
        this.storm_move_points = storm_move_points;
        this.shots = shots;
        this.fire_range = fire_range;
        this.hit_points = hit_points;
    }
}
