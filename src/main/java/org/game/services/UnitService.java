package org.game.services;

import org.game.State;

public interface UnitService {
    State initialGameState();
    State unitSelected(String id);
}
