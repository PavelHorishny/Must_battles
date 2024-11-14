package org.game.unit;

import lombok.Getter;

@Getter
public enum FortificationType {
    FIRST_LINE_FORT("1st line fort",5,7,3),
    SECOND_LINE_FORT("2nd line fort",4,5,2),
    ROYAL_PORT("Royal port",0,2,0);

    private final String description;
    private final int fire_range;
    private final int hit_points;
    private final int shots;
    private final int movePoints = 0;

    FortificationType(String description, int fire_range, int hit_points, int shots) {
        this.description = description;
        this.fire_range = fire_range;
        this.hit_points = hit_points;
        this.shots = shots;
    }
}
