package org.game.gui.panels.InfoAreaPanels;

import javax.swing.*;
import java.awt.*;

public class MixedComponent extends JComponent {
    private final String text;
    private int empty;
    private int filled;
    private final Color color;

    public MixedComponent(String text,Color color){
        setPreferredSize(new Dimension(260,35));
        this.text = text+":";
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(text,10,25);

            for (int i = 1; i <= filled; i++) {
                g.setColor(color);
                g.fillRect((50 + (20 * i)), 0, 15, 30);
            }
            for (int i = 1; i <= empty; i++) {
                g.setColor(Color.BLACK);
                g.drawRect((50 + (20 * i)), 0, 15, 30);
            }
    }

    public void setData(int empty,int filled){
        this.empty = empty;
        this.filled = filled;
        repaint();
    }
}
