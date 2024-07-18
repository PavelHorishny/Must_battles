package org.game.gui.frame;

import org.game.gui.panels.GamePanel;

import javax.swing.*;

public class AppFrame extends JFrame {
    public AppFrame(){
        setVisible(true);
        add(new GamePanel());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }
}
