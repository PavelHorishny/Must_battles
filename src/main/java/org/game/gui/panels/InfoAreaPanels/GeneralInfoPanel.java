package org.game.gui.panels.InfoAreaPanels;

import lombok.Getter;

import javax.swing.*;

@Getter
public class GeneralInfoPanel extends JPanel {
    private final JLabel day = new JLabel();
    public GeneralInfoPanel(){
        add(day);
        day.setText("this is the general panel");
    }
}
