package org.game.messaging;

import org.game.*;
import org.game.services.UnitProcessor;
import org.game.services.UnitService;


public class GameServer implements Server{
    private final UnitService unitService = new UnitProcessor();
    public GameServer() {
        Context.setServer(this);
    }

    @Override
    public void onRequest(Client client, Request request) {
        switch (request.getMessage()){
            case SELECT -> client.response(unitService.unitSelected(request.getId()));
            case START -> client.response(unitService.initialGameState());
            case MOVEMENTS_START -> client.response(unitService.movementStarts(request.getId()));
            case MOVEMENTS_END -> client.response(unitService.movementEnds(request.getId(),request.getDestination()));
            case SHOT -> client.response(unitService.makeShot(request.getId(),request.getTargetID(),request.getShotType()));
            case END -> client.response(unitService.dayEnd());
            case REPAIR -> client.response(unitService.unitReadyForRepair(request.isState()));
            case HELP -> client.response(unitService.unitReadyForHelp(request.isState()));
        }
    }
}
