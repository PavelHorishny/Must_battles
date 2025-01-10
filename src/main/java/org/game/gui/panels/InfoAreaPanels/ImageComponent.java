package org.game.gui.panels.InfoAreaPanels;

import javax.swing.*;
import java.awt.*;

public class ImageComponent extends JComponent {
    public ImageComponent(){
        setPreferredSize(new Dimension(260,260));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.fillRect(0,0,260,260);
        g.setColor(Color.BLACK);
        String text = "Image currently unavailable";
        g.drawString(text,10,15);

    }
}
