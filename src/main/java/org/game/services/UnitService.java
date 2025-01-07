package org.game.services;

import org.game.gui.StateType;
import org.game.state.GameState;
import org.game.unit.GameUnit;

public interface UnitService {
    void setAllUnits(GameState gameState);

    void setButtonsState(GameState state);

    void onDestruction(GameUnit unit, GameState state);

    void OnTurnEnd(GameState state);

    void OnDayEnd(GameState state);

    void setState(StateType stateType, GameUnit unit);

    boolean isSelectedDestroyedFort(GameUnit unit);
}
