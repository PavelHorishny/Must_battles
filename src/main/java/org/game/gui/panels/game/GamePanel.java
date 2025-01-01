package org.game.gui.panels.game;

import org.game.Context;
import org.game.gui.panels.*;
import org.game.gui.panels.game.areas.*;

import javax.swing.*;

public class GamePanel extends JPanel {
    private GamePanelMediator mediator;
    public GamePanel(){
        mediator = (GamePanelMediator) Context.getMediator();
        init();
       }
    private void init(){
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
    }

    public void setMediator(GamePanelMediator mediator) {
        this.mediator = mediator;
        init();
        mediator.start();
    }
}
