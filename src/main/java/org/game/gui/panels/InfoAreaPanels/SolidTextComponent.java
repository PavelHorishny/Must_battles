package org.game.gui.panels.InfoAreaPanels;

import javax.swing.*;
import java.awt.*;

public class SolidTextComponent extends JComponent {

    private String text = "new text";
    public SolidTextComponent(){
        setPreferredSize(new Dimension(260,35));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(text,10,15);
    }

    public void setText(String text) {
        this.text = text;
        repaint();
    }
}
