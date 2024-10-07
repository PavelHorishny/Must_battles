package org.game.gui.frame;

import org.game.gui.panels.AppPanel;


import javax.swing.*;

public class AppFrame extends JFrame {
    public AppFrame() {
        setVisible(true);
        add(new AppPanel());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }
}
