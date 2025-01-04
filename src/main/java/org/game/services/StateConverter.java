package org.game.services;

import org.game.state.GameState;
import org.game.state.State;

public interface StateConverter {
    State convertState(GameState gameState);
}
