package org.game.gui.panels;

import org.game.gui.panels.game.GamePanel;
import org.game.gui.panels.main.LoadPanel;
import org.game.gui.panels.main.MainPanel;
import org.game.gui.panels.main.SettingsPanel;

import javax.swing.*;
import java.awt.*;

public class AppPanel extends JPanel {
    public AppPanel(){
        setLayout(new CardLayout());
        setPreferredSize(new Dimension(1280,960));
        add(new MainPanel(),"Main");
        add(new LoadPanel(), "Load");
        add(new SettingsPanel(),"Settings");
        add(new GamePanel(), "Game");
    }

}
