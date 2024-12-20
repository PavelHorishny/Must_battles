package org.game;

public record UnitData
    (String type,
    String name,
    int max_hp,
    int current_hp,
    int fire_range,
    int fire_points){}

