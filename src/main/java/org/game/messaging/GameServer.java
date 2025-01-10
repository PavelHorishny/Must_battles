package org.game.messaging;

import org.game.*;
import org.game.services.GameProcessor;
import org.game.services.GameService;


public class GameServer implements Server{
    private final GameService gameService = new GameProcessor();
    public GameServer() {
        Context.setServer(this);
    }

    @Override
    public void onRequest(Client client, Request request) {
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
