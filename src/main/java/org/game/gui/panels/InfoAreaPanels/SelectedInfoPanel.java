package org.game.gui.panels.InfoAreaPanels;

import org.game.UnitData;

import javax.swing.*;

public class SelectedInfoPanel extends JPanel {
    private JLabel type = new JLabel("type");
    private JLabel name = new JLabel("name");
    private JLabel hitPoints = new JLabel("1");
    private JLabel movePoints = new JLabel("1");
    private JLabel firePoints = new JLabel("1");
    public SelectedInfoPanel(){
        add(type);
        add(name);
        add(hitPoints);
        add(movePoints);
        add(firePoints);
    }
    public void setState(UnitData data){
        type.setText(data.type());
        name.setText(data.name());
        hitPoints.setText(String.valueOf(data.current_hp()));
        movePoints.setText(String.valueOf(data.fire_range()));
        firePoints.setText(String.valueOf(data.fire_points()));
    }
}
