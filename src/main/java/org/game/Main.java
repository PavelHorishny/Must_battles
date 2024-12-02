package org.game;

import org.game.gui.frame.AppFrame;
import org.game.messaging.GameServer;

public class Main {
    public static void main(String[] args)  {
        System.out.println("Hello world!");
        new GameServer();
        new AppFrame();
    }
}