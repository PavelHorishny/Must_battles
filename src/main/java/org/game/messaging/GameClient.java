package org.game.messaging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game.Request;
import org.game.state.State;
import org.game.gui.panels.Mediator;

public class GameClient implements Client{
    public static final Logger logger = LogManager.getLogger(GameClient.class);
    Mediator mediator;
    Server server;
    @Override
    public void request(Request request) {
        logger.debug("send request {}", request);
        server.onRequest(this, request);
    }

    @Override
    public void connect(Server server) {
        logger.debug("Connected to {}",server);
        this.server=server;
    }

    @Override
    public void registerMediator(Mediator mediator) {
        this.mediator=mediator;
    }

    @Override
    public void response(State state) {
        logger.debug(state);
        mediator.update(state);
    }
}
