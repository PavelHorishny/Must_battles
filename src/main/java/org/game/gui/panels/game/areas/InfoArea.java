package org.game.gui.panels.game.areas;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game.gui.panels.InfoAreaPanels.SelectedInfoPanel;
import org.game.gui.panels.InfoAreaPanels.TargetInfoPanel;
import org.game.state.GameComponentState;
import org.game.gui.panels.InfoAreaPanels.GeneralInfoPanel;
import org.game.gui.panels.Settings;
import org.game.state.InfoAreaState;

import javax.swing.*;
import java.awt.*;

public class InfoArea extends GamePanelComponent {
    private static final Logger logger = LogManager.getLogger(InfoArea.class);
    @Getter
    private final GeneralInfoPanel generalInfoPanel = new GeneralInfoPanel();
    private final SelectedInfoPanel selectedInfoPanel = new SelectedInfoPanel();
    private final TargetInfoPanel targetInfoPanel = new TargetInfoPanel();
    public JLabel l = new JLabel();
    public JLabel hit_points = new JLabel();
    String textHP = "hit points: ";
    String textShots = "shots: ";
    String textMP = "move points: ";
    public JLabel shots = new JLabel();
    public JLabel movePoints = new JLabel();
    public InfoArea(Settings settings) {

        super(settings);
        setLayout(new CardLayout());
        add(generalInfoPanel,"General");
        add(selectedInfoPanel,"Selected");
        add(targetInfoPanel,"Target");
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

    @Override
    public void updateState(GameComponentState state) {
        InfoAreaState s = (InfoAreaState) state;
        generalInfoPanel.getDay().setText("Day "+s.getDay());
        if(s.isSelected()||s.isTarget()){
            if(s.isSelected()){
                ((CardLayout) this.getLayout()).show(this,"Selected");
                selectedInfoPanel.setState(((InfoAreaState) state).getSelectedData());
            }
            if(s.isTarget()){
                ((CardLayout) this.getLayout()).show(this,"Target");
                targetInfoPanel.setState(((InfoAreaState) state).getTargetData());
            }

        }else {
            ((CardLayout) this.getLayout()).show(this,"General");
        }
    }
}
