package org.game.gui.panels;

import org.game.gui.panels.game.components.*;

public class GamePanelMediator implements Mediator{
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
    public void unitSelected(Message message) {

    }

    @Override
    public void gameStarted(Message message) {

    }

    @Override
    public void test(String message) {

    }

    public void start() {

    }
}
