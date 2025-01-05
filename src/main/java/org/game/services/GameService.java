package org.game.services;

import org.game.gui.Coordinates;
import org.game.state.State;

public interface GameService {
    State initialGameState();
    State unitSelected(String id);
    State movementStarts(String id);
    State movementEnds(String id, Coordinates destination);
    State makeShot(String attackerID, String targetID, String shotType);
    State dayEnd();
    State unitReadyForRepair(boolean state);
    State unitReadyForHelp(boolean state);
}
