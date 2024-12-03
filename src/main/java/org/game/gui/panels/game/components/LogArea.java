package org.game.gui.panels.game.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.game.GameComponentState;
import org.game.LogAreaState;
import org.game.gui.panels.Mediator;
import org.game.gui.panels.Settings;

import javax.swing.*;

public class LogArea extends GamePanelComponent {
    private static final Logger logger = LogManager.getLogger(LogArea.class);
    LogAreaState state = new LogAreaState();
    JLabel l;
    public LogArea() {
/*        name="log_area";
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(800,25));
        JLabel l = new JLabel("test log area");
        add(l);
        logger.debug(name);*/
    }
    public LogArea(Settings settings){
        super(settings);
        l = new JLabel(name);
        add(l);
        logger.debug(name);
    }

    @Override
    public void updateState(GameComponentState state) {
        if(state!=null){
            this.state = (LogAreaState) state;
            l.setText(this.state.getLabel());
            repaint();
        }

    }

    @Override
    public void setMediator(Mediator mediator) {
        super.setMediator(mediator);
    }
}
