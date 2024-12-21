package org.game.gui.panels.InfoAreaPanels;

import org.game.UnitData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TargetInfoPanel extends JPanel implements MouseListener {
    private JLabel type = new JLabel("type");
    private JLabel name = new JLabel("type");
    private JLabel hitPoints = new JLabel("1");
    private JLabel movePoints = new JLabel("1");
    private JLabel firePoints = new JLabel("1");

    public TargetInfoPanel(){
        add(type);
        add(name);
        add(hitPoints);
        add(movePoints);
        add(firePoints);
        addMouseListener(this);
    }
    public void setState(UnitData data){
        type.setText(data.name());
        name.setText(data.type());
        hitPoints.setText(String.valueOf(data.current_hit_points()));
        movePoints.setText(String.valueOf(data.move_points()));
        firePoints.setText(String.valueOf(data.fire_range()));
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
