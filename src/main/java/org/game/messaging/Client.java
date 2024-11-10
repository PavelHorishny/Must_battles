package org.game.messaging;

import org.game.State;
import org.game.gui.panels.Mediator;
import org.game.gui.panels.Message;

public interface Client {
    void request(Message message);
    void connect(Server server);
    void registerMediator(Mediator mediator);
    void response(State state);
}
