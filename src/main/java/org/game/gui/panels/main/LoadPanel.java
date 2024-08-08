package org.game.gui.panels.main;

import javax.swing.*;
import java.awt.*;

public class LoadPanel extends JPanel {
    JButton back = new JButton("Back");
    public LoadPanel (){
        this.add(new Label("Here will be list of saved games"));
        back.addActionListener(e->{
            CardLayout layout = (CardLayout) this.getParent().getLayout();
            layout.show(this.getParent(),"Main");
        });
        this.add(back);
    }
}
