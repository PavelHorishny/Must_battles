package org.game.gui.panels.game.components;

import org.game.gui.panels.Mediator;

public interface GameComponent {
    void setMediator(Mediator mediator);
    String getGameComponentName();
}
