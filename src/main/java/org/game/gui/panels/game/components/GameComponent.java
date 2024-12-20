package org.game.gui.panels.game.components;

import org.game.state.GameComponentState;
import org.game.gui.panels.Mediator;

public interface GameComponent {
    
    void setMediator(Mediator mediator);
    String getGameComponentName();
    void updateState(GameComponentState state);
}
