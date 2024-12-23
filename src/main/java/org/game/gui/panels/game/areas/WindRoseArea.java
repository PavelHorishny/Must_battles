package org.game.gui.panels.game.areas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game.state.GameComponentState;
import org.game.gui.panels.PanelsConstrains;
import org.game.gui.panels.Settings;
import org.game.state.WindRoseAreaState;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class WindRoseArea extends GamePanelComponent {
    private final JLabel north = new JLabel("N");
    private final JLabel northWest = new JLabel("NW");
    private final JLabel northEast = new JLabel("NE");
    private final JLabel west = new JLabel("W");
    private final JLabel east = new JLabel("E");
    private final JLabel southWest = new JLabel("SW");
    private final JLabel southEast = new JLabel("SE");
    private final JLabel south = new JLabel("S");
    private final JLabel force = new JLabel("CALM");
    private final JLabel [] set = new JLabel[] {
            north,northWest, northEast,west,east,southWest,southEast,south,force
    };
    private JLabel active = north;
    private static final Logger logger = LogManager.getLogger(WindRoseArea.class);
    public WindRoseArea() {

    }
    public WindRoseArea(Settings settings) {
        super(settings);
        setState();
        logger.debug(name);
    }


    private void setState (){
        setLabels();
        this.add(north, PanelsConstrains.NORTH);
        this.add(northWest,PanelsConstrains.NORTH_WEST);
        this.add(northEast,PanelsConstrains.NORTH_EAST);
        this.add(west,PanelsConstrains.WEST);
        this.add(east,PanelsConstrains.EAST);
        this.add(southWest,PanelsConstrains.SOUTH_WEST);
        this.add(southEast,PanelsConstrains.SOUTH_EAST);
        this.add(south,PanelsConstrains.SOUTH);
        this.add(force,PanelsConstrains.FORCE);
    }

    private void setLabels() {
        Arrays.stream(set).forEach(label -> {
            label.setForeground(Color.BLACK);
            label.setSize(PanelsConstrains.LABEL_SIZE);
        });
    }
    public void highLight(String direction, String force){
        active.setForeground(Color.BLACK);
        Arrays.stream(set).forEach(label->{
            if(label.getText().equals(direction)){
                active=label;
                active.setForeground(Color.RED);
                this.force.setForeground(Color.RED);
                this.force.setText(force);
            }
        });
    }

    @Override
    public void updateState(GameComponentState state) {
        System.out.println("WINDROSEAREA");
        System.out.println(state);
        System.out.println("WINDROSEAREA");
        if(state!=null) {
            WindRoseAreaState s = (WindRoseAreaState) state;
            if (s.getWeather() != null) {
                highLight(s.getWeather().cardinalPoint().getName(), s.getWeather().wind().getValue());
            }else {
                this.force.setForeground(Color.BLACK);
                this.active.setForeground(Color.BLACK);
            }
        }else{
            this.force.setForeground(Color.BLACK);
            this.active.setForeground(Color.BLACK);
        }
    }
}
