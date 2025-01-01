package org.game.gui.panels;

import org.game.Context;
import org.game.Request;
import org.game.gui.Coordinates;
import org.game.state.State;
import org.game.gui.panels.game.areas.*;
import org.game.messaging.GameClient;

public class GamePanelMediator implements Mediator{
    GameClient client = new GameClient();
    public GamePanelMediator() {
        System.out.println("Mediator exists");
    }

    private MapArea game;
    private WindRoseArea wind;
    private ButtonArea buttons;
    private InfoArea info;
    private LogArea log;

    @Override
    public void registerComponent(GameComponent gameComponent) {
        switch (gameComponent.getGameComponentName()){
            case "GAME_AREA" -> {
                game = (MapArea)  gameComponent;
                game.setMediator(this);
                //game.moveVessel();
            }
            case "WIND_ROSE_AREA" -> {
                wind = (WindRoseArea) gameComponent;
                wind.setMediator(this);
            }
            case "BUTTON_AREA" -> {
                buttons = (ButtonArea) gameComponent;
                buttons.setMediator(this);
            }
            case "INFO_AREA" -> {
                info = (InfoArea) gameComponent;
                info.setMediator(this);

            }
            case "LOG_AREA" -> {
                log = (LogArea) gameComponent;
                log.setMediator(this);
            }
        }
    }

    @Override
    public void unitSelected(String vesselID) {
        client.request(Request.builder().message(Message.SELECT).id(vesselID).build());
    }

    @Override
    public void gameStarted() {

        client.connect(Context.getServer());
        client.registerMediator(this);
        //gameStarted();
        client.request(Request.builder().message(Message.START).build());
    }

    @Override
    public void test(String message) {

    }

    @Override
    public void update(State state) {
        game.updateState(state.getMapAreaState());
        log.updateState(state.getLogAreaState());
        info.updateState(state.getInfoAreaState());
        wind.updateState(state.getWindRoseAreaState());
        buttons.updateState(state.getButtonAreaState());
    }

    /**
     *
     */
    @Override
    public void movementStarts(String id) {
        client.request(Request.builder().message(Message.MOVEMENTS_START).id(id).build());
    }

    /**
     * @param id
     * @param destination
     */
    @Override
    public void movementEnds(String id, Coordinates destination) {
        client.request(Request.builder().message(Message.MOVEMENTS_END).id(id).destination(destination).build());
    }

    /**
     * @param attackerID
     * @param targetID
     * @param shotType
     */
    @Override
    public void shot(String attackerID, String targetID, String shotType) {
        client.request(Request.builder().message(Message.SHOT).id(attackerID).targetID(targetID).shotType(shotType).build());
    }

    /**
     *
     */
    @Override
    public void endTurn() {
        client.request(Request.builder().message(Message.END).build());
    }

    /**
     *
     */
    @Override
    public void unitReadyForRepair(boolean state) {
        client.request(Request.builder().message(Message.REPAIR).state(state).build());
    }

    /**
     *
     */
    @Override
    public void unitReadyForHelp(boolean state) {
        client.request(Request.builder().message(Message.HELP).state(state).build());
    }

    public void start() {

    }
}
