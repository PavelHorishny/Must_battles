package org.game.gui.frame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game.gui.panels.AppPanel;
import javax.swing.*;

public class AppFrame extends JFrame {
    public static final Logger logger = LogManager.getLogger(AppFrame.class);
    public AppFrame() {
        setVisible(true);
        add(new AppPanel());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        logger.debug("AppFrame runs");
    }
}