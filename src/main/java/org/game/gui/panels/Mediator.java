package org.game.gui.panels;

import org.game.gui.panels.game.components.GameComponent;

public interface Mediator {
    void registerComponent(GameComponent gameComponent);

    void unitSelected(Message message);


    void gameStarted(Message message);
    void test (String message);
}
