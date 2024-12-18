package org.game;

import lombok.Getter;
import lombok.Setter;
import org.game.gui.panels.GamePanelMediator;
import org.game.gui.panels.Mediator;
import org.game.messaging.Server;
import org.game.mockData.NamesRandomizer;

public final class Context {
    @Getter
    @Setter
    private static Server server;
    @Getter
    private static final Mediator mediator = new GamePanelMediator();
    @Getter
    private static final NamesRandomizer nameRandomizer = new NamesRandomizer();
}
