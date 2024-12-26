package org.game.gui.panels;

import org.game.gui.Coordinates;
import org.game.state.State;
import org.game.gui.panels.game.areas.GameComponent;

public interface Mediator {
    void registerComponent(GameComponent gameComponent);

    void unitSelected(String vesselID);


    void gameStarted();
    void test (String message);
    void update(State state);

    void movementStarts(String id);

    void movementEnds(String id, Coordinates destination);
}

