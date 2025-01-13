package org.game.gui.panels;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game.gui.panels.game.GamePanel;
import org.game.gui.panels.main.LoadPanel;
import org.game.gui.panels.main.MainPanel;
import org.game.gui.panels.main.SettingsPanel;

import javax.swing.*;
import java.awt.*;

public class AppPanel extends JPanel {
    public static final Logger logger = LogManager.getLogger(AppPanel.class);
    public AppPanel(){
        logger.trace("AppPanel started");
        setLayout(new CardLayout());
        setPreferredSize(new Dimension(1280,960));
        //TODO magic number
        add(new MainPanel(),"Main");
        add(new LoadPanel(), "Load");
        add(new SettingsPanel(),"Settings");
        add(new GamePanel(), "Game");
    }

}
