package org.game.gui.panels.InfoAreaPanels;

import org.game.UnitData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TargetInfoPanel extends JPanel implements MouseListener {

    private final SolidTextComponent name = new SolidTextComponent();
    private final SolidTextComponent type = new SolidTextComponent();
    private final MixedComponent hit_points = new MixedComponent("HP", Color.RED);
    private final MixedComponent move_points = new MixedComponent("MP", Color.RED);
    private final MixedComponent fire_range = new MixedComponent("Range", Color.RED);
    private final MixedComponent shots = new MixedComponent("Shots",Color.RED);

    public TargetInfoPanel(){
        add(name);
        add(type);
        add(hit_points);
        add(move_points);
        add(fire_range);
        add(shots);
        addMouseListener(this);
    }
    public void setState(UnitData data){
        name.setText(data.name());
        type.setText(data.type());
        hit_points.setData(data.base_hit_points(),data.base_hit_points());
        move_points.setData(data.move_points(),data.move_points());
        fire_range.setData(data.fire_range(),data.fire_range());
        shots.setData(data.base_shots(),data.base_shots());
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        ((CardLayout) this.getParent().getLayout()).show(this.getParent(),"Selected");
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
