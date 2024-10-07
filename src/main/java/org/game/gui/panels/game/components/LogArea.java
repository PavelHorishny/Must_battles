package org.game.gui.panels.game.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.game.gui.panels.Settings;

import javax.swing.*;

public class LogArea extends GamePanelComponent {
    private static final Logger logger = LogManager.getLogger(LogArea.class);
    public LogArea() {
/*        name="log_area";
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(800,25));
        JLabel l = new JLabel("test log area");
        add(l);
        logger.debug(name);*/
    }
    public LogArea(Settings settings){
        super(settings);
        JLabel l = new JLabel(name);
        add(l);
        logger.debug(name);
    }
}
