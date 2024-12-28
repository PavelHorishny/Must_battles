package org.game.services;

import org.game.gui.Coordinates;
import org.game.state.State;

public interface UnitService {
    State initialGameState();
    State unitSelected(String id);

    State movementStarts(String id);

    State movementEnds(String id, Coordinates destination);

    State makeShot(String attackerID, String targetID, String shotType);
}
