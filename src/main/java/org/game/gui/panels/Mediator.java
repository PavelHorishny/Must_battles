package org.game.gui.panels;

import org.game.State;
import org.game.gui.panels.game.components.GameComponent;

public interface Mediator {
    void registerComponent(GameComponent gameComponent);

    void unitSelected(String vesselID);


    void gameStarted();
    void test (String message);
    void update(State state);
}

