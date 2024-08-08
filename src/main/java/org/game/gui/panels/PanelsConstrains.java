package org.game.gui.panels;

import java.awt.*;

public class PanelsConstrains {
    public static final LayoutManager LAYOUT = new GridBagLayout();
    public static final GridBagConstraints GAME_AREA = new GridBagConstraints(0,0,1,4,1.0,1.0,23,0,new Insets(0,0,0,0),0,0);
    public static final GridBagConstraints WIND_ROSE_AREA = new GridBagConstraints(1,0,1,1,1.0,1.0,24,0,new Insets(0,0,0,0),0,0);
    public static final GridBagConstraints BUTTON_AREA = new GridBagConstraints(1,1,1,1,1.0,1.0,10,0,new Insets(0,0,0,0),0,0);
    public static final GridBagConstraints INFO_AREA = new GridBagConstraints(1,2,1,1,1.0,1.0,10,0,new Insets(0,0,0,0),0,0);
    public static final GridBagConstraints LOG_AREA = new GridBagConstraints(1,3,1,1,1.0,1.0,10,0,new Insets(0,0,0,0),0,0);
    public static final Settings LOG_AREA_SETTINGS = Settings.builder().name("LOG_AREA").background(Color.DARK_GRAY).preferredSize(new Dimension(250,90)).build();
    public static final Settings WIND_ROSE_AREA_SETTINGS = Settings.builder().name("WIND_ROSE_AREA").background(Color.ORANGE).preferredSize(new Dimension(250,250)).layout(new GridBagLayout()).build();
    public static final Settings BUTTON_AREA_SETTINGS = Settings.builder().name("BUTTON_AREA").background(Color.CYAN).preferredSize(new Dimension(250,100)).build();
    public static final Settings GAME_AREA_SETTINGS = Settings.builder().name("GAME_AREA").background(Color.BLUE).preferredSize(new Dimension(960,690)).build();
    public static final Settings INFO_AREA_SETTINGS = Settings.builder().name("INFO_AREA").background(Color.RED).preferredSize(new Dimension(250,250)).build();
    public static final GridBagConstraints NORTH = new GridBagConstraints(2,0,1,1,1.0,1.0,10,0,new Insets(0,0,0,0),0,0);
    public static final GridBagConstraints NORTH_WEST = new GridBagConstraints(1,1,1,1,1.0,1.0,10,0,new Insets(0,0,0,0),0,0);
    public static final GridBagConstraints NORTH_EAST = new GridBagConstraints(3,1,1,1,1.0,1.0,10,0,new Insets(0,0,0,0),0,0);
    public static final GridBagConstraints WEST = new GridBagConstraints(0,2,1,1,1.0,1.0,10,0,new Insets(0,0,0,0),0,0);
    public static final GridBagConstraints EAST = new GridBagConstraints(4,2,1,1,1.0,1.0,10,0,new Insets(0,0,0,0),0,0);
    public static final GridBagConstraints SOUTH_WEST = new GridBagConstraints(1,3,1,1,1.0,1.0,10,0,new Insets(0,0,0,0),0,0);
    public static final GridBagConstraints SOUTH_EAST = new GridBagConstraints(3,3,1,1,1.0,1.0,10,0,new Insets(0,0,0,0),0,0);
    public static final GridBagConstraints SOUTH = new GridBagConstraints(2,4,1,1,1.0,1.0,10,0,new Insets(0,0,0,0),0,0);
    public static final GridBagConstraints FORCE = new GridBagConstraints(2,2,1,1,1.0,1.0,10,0,new Insets(0,0,0,0),0,0);
    //public static final Dimension FIELD_SIZE = new Dimension(250,250);
    public static final Dimension LABEL_SIZE = new Dimension(50,50);
}
