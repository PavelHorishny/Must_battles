package org.game.services;

import org.game.state.State;

public interface UnitService {
    State initialGameState();
    State unitSelected(String id);

    State clearRoute(String id);
}
