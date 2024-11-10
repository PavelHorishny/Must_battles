package org.game.messaging;

import org.game.*;
import org.game.gui.panels.Message;
import org.game.mockData.StandardMap;

public class GameServer implements Server{
    State state = new State();
    public GameServer() {
        Context.setServer(this);
        System.out.println("Server exists");
    }

    @Override
    public void onRequest(Client client, Message message) {
        switch (message){
            case START -> {
                //state.setMapAreaState(new MapAreaState().setMap(StandardMap.MAP));
                state.setMapAreaState(new MapAreaState(StandardMap.MAP));
                state.setLogAreaState(new LogAreaState("Day 1"));
                //state.setMapAreaState(state.getMapAreaState());
                client.response(state);
            }
        }
    }
}
