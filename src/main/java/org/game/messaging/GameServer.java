package org.game.messaging;

import org.game.*;
import org.game.map.Surface;
import org.game.services.MapProcessor;
import org.game.services.MapService;
import org.game.services.UnitProcessor;
import org.game.services.UnitService;
import org.game.state.State;

public class GameServer implements Server{
    State state;
    Surface [] [] map;
    MapService mapProcessor = new MapProcessor();
    private final UnitService unitService = new UnitProcessor();
    public GameServer() {
        Context.setServer(this);
        System.out.println("Server exists");
    }

    @Override
    public void onRequest(Client client, Request request) {
        switch (request.getMessage()){
            case SELECT -> {
                client.response(unitService.unitSelected(request.getId()));
            }
            case START -> {
                //state = unitService.initialGameState();
                //map = mapProcessor.generateStandardMap();
                //state.setMapAreaState(new MapAreaState().setMap(StandardMap.MAP));
                //state.setMapAreaState(new MapAreaState(BackToGUIConverter.convertMap(state.getMap())));
                //state.setLogAreaState(new LogAreaState("Day 1"));
                //state.update();
                //state.setMapAreaState(state.getMapAreaState());
                client.response(unitService.initialGameState());
            }
        }
    }
}
