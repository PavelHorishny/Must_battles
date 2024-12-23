package org.game.gui.panels.InfoAreaPanels;

import org.game.UnitData;

import javax.swing.*;
import java.awt.*;

public class SelectedInfoPanel extends JPanel {

    private final SolidTextComponent name = new SolidTextComponent();
    private final SolidTextComponent type = new SolidTextComponent();
    private final MixedComponent hit_points = new MixedComponent("HP", Color.GREEN);
    private final MixedComponent shots = new MixedComponent("Shots",Color.GREEN);
    private final ImageComponent testName5 = new ImageComponent();

    public SelectedInfoPanel(){
        add(name);
        add(type);
        add(hit_points);
        add(shots);
        add(testName5);
    }

    public void setState(UnitData data){
        name.setText(data.name());
        type.setText(data.type());
        hit_points.setData(data.base_hit_points(),data.current_hit_points());
        shots.setData(data.base_shots(),data.current_shots());
    }
}
