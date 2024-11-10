package org.game.messaging;

import org.game.State;
import org.game.gui.panels.Mediator;
import org.game.gui.panels.Message;

public class GameClient implements Client{
    Mediator mediator;
    Server server;
    @Override
    public void request(Message message) {
        server.onRequest(this, Message.START);
    }

    @Override
    public void connect(Server server) {
        this.server=server;
    }

    @Override
    public void registerMediator(Mediator mediator) {
        this.mediator=mediator;
    }

    @Override
    public void response(State state) {
        mediator.update(state);
    }
}
