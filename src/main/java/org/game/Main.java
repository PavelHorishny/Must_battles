package org.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game.gui.frame.AppFrame;
import org.game.messaging.GameServer;

public class Main {
    public static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args)  {
        logger.debug("Main.class runs");
        new GameServer();
        new AppFrame();
    }
}