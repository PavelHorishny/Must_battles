package org.game.gui.panels.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game.Context;
import org.game.gui.panels.*;
import org.game.gui.panels.game.areas.*;

import javax.swing.*;

public class GamePanel extends JPanel {
    public static final Logger logger = LogManager.getLogger(GamePanel.class);
    private Mediator mediator;
    public GamePanel(){
        logger.info("GamePanel class runs");
        mediator = Context.getMediator();
        init();
       }
    private void init(){
        logger.debug("init method runs");
        setLayout(PanelsConstrains.LAYOUT);
        GamePanelComponent mapArea = new MapArea(PanelsConstrains.GAME_AREA_SETTINGS);
        mediator.registerComponent(mapArea);
        add(mapArea, PanelsConstrains.GAME_AREA);
        GamePanelComponent windRoseArea = new WindRoseArea(PanelsConstrains.WIND_ROSE_AREA_SETTINGS);
        mediator.registerComponent(windRoseArea);
        add(windRoseArea,PanelsConstrains.WIND_ROSE_AREA);
        GamePanelComponent buttonArea = new ButtonArea(PanelsConstrains.BUTTON_AREA_SETTINGS);
        mediator.registerComponent(buttonArea);
        add(buttonArea,PanelsConstrains.BUTTON_AREA);
        GamePanelComponent infoArea = new InfoArea(PanelsConstrains.INFO_AREA_SETTINGS);
        mediator.registerComponent(infoArea);
        add(infoArea,PanelsConstrains.INFO_AREA);
        GamePanelComponent logArea = new LogArea(PanelsConstrains.LOG_AREA_SETTINGS);
        mediator.registerComponent(logArea);
        add(logArea,PanelsConstrains.LOG_AREA);
        //TODO get rid or rename panel settings
    }

    public void setMediator(GamePanelMediator mediator) {
        this.mediator = mediator;
        init();
        mediator.start();
    }
}
