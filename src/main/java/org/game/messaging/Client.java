package org.game.messaging;

import org.game.Request;
import org.game.state.State;
import org.game.gui.panels.Mediator;


public interface Client {
    void request(Request request);
    void connect(Server server);
    void registerMediator(Mediator mediator);
    void response(State state);
}
