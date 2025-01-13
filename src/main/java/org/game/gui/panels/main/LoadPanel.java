package org.game.gui.panels.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class LoadPanel extends JPanel {
    public static final Logger logger = LogManager.getLogger(LoadPanel.class);
    JButton back = new JButton("Back");
    public LoadPanel (){
        this.add(new Label("Here will be list of saved games"));
        back.addActionListener(e->{
            logger.info("Back button pressed");
            CardLayout layout = (CardLayout) this.getParent().getLayout();
            layout.show(this.getParent(),"Main");
        });
        this.add(back);
    }
}
