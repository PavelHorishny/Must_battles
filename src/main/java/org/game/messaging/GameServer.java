package org.game.messaging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game.*;
import org.game.services.GameProcessor;
import org.game.services.GameService;


public class GameServer implements Server{
    public static final Logger logger = LogManager.getLogger(GameServer.class);
    private final GameService gameService = new GameProcessor();
    public GameServer() {
        logger.debug("GameServer.class runs");
        Context.setServer(this);
        logger.trace(Context.getServer());
    }

    @Override
    public void onRequest(Client client, Request request) {
        logger.debug(request);
        switch (request.getMessage()){
            case SELECT -> client.response(gameService.unitSelected(request.getId()));
            case START -> client.response(gameService.initialGameState());
            case MOVEMENTS_START -> client.response(gameService.movementStarts(request.getId()));
            case MOVEMENTS_END -> client.response(gameService.movementEnds(request.getId(),request.getDestination()));
            case SHOT -> client.response(gameService.shooting(request.getId(),request.getTargetID(),request.getShotType()));
            case END -> client.response(gameService.dayEnd());
            case REPAIR -> client.response(gameService.unitReadyForRepair(request.isState()));
            case HELP -> client.response(gameService.unitReadyForHelp(request.isState()));
        }
    }
}
