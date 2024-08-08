package org.game.gui.panels.game.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game.gui.panels.Settings;

import javax.swing.*;

public class InfoArea extends GamePanelComponent {
    private static final Logger logger = LogManager.getLogger(InfoArea.class);
    public JLabel l = new JLabel();
    public JLabel hit_points = new JLabel();
    String textHP = "hit points: ";
    String textShots = "shots: ";
    String textMP = "move points: ";
    public JLabel shots = new JLabel();
    public JLabel movePoints = new JLabel();
    public InfoArea(Settings settings) {

        super(settings);
        l.setText(name);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(l);
        add(hit_points);
        add(shots);
        add(movePoints);
        /*bu*/
        logger.debug(name);
    }
    public void setHit_pointsData(int data){
        hit_points.setText(textHP+data);
    }
    public void setMovePointsData(int data){
        movePoints.setText(textMP+data);
    }
    public void setShotsData(int data){
        shots.setText(textShots+data);
    }
}
