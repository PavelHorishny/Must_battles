package org.game.gui.panels.main;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    JButton back = new JButton("Back");
    public SettingsPanel(){
        this.add(new Label("Here will be settings"));
        back.addActionListener(e->{
            CardLayout layout = (CardLayout) this.getParent().getLayout();
            layout.show(this.getParent(),"Main");
        });
        this.add(back);
    }
}
