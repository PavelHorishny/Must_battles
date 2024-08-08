package org.game.gui.panels.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private static final Logger logger = LogManager.getLogger(MainPanel.class);
    protected final JButton start, load, settings, quit;
    public MainPanel(){
        this.setBackground(Color.ORANGE);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        //Toolkit.getDefaultToolkit().getScreenSize()
        this.setPreferredSize(new Dimension(1050,600));

        this.add(new Box.Filler(new Dimension(300,150),new Dimension(300,150),new Dimension(300,150)));
        start = new JButton("Start");
        start.setMaximumSize(new Dimension(300,50));
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.addActionListener(e-> {
                    logger.info("Start Button pressed");
                    ((CardLayout) this.getParent().getLayout()).show(this.getParent(),"Game");

                }
        );

        this.add(start);

        load = new JButton("Load");
        load.setAlignmentX(Component.CENTER_ALIGNMENT);
        load.setMaximumSize(new Dimension(300,50));
        load.addActionListener(e-> {
            logger.info("Load button pressed");
            ((CardLayout) this.getParent().getLayout()).show(this.getParent(),"Load");
        });
        this.add(load);

        settings = new JButton("Settings");
        settings.setAlignmentX(Component.CENTER_ALIGNMENT);
        settings.setMaximumSize(new Dimension(300,50));
        settings.addActionListener(e-> {
                    logger.info("Settings button pressed");
                    ((CardLayout) this.getParent().getLayout()).show(this.getParent(),"Settings");
                }
        );
        this.add(settings);
        quit = new JButton("Quit");
        quit.setMaximumSize(new Dimension(300,50));
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);
        quit.addActionListener(e->System.exit(0));
        this.add(quit);
    }
}
