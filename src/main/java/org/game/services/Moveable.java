package org.game.services;

import org.game.gui.Coordinates;
import org.game.map.Surface;
import org.game.unit.GameUnit;

public interface Moveable {
    void moveUnitToDestinationPoint(GameUnit gameUnit, Coordinates destination, Surface[][] map);
}
