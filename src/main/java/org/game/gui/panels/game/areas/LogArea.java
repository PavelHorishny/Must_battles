package org.game.gui.panels.game.areas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.game.state.GameComponentState;
import org.game.state.LogAreaState;
import org.game.gui.panels.Mediator;
import org.game.gui.panels.Settings;

import javax.swing.*;

public class LogArea extends GamePanelComponent {
    private static final Logger logger = LogManager.getLogger(LogArea.class);
    LogAreaState state;
    JLabel l;

    public LogArea(Settings settings){
        super(settings);
        l = new JLabel(name);
        add(l);
        logger.debug("runs");
    }

    @Override
    public void updateState(GameComponentState state) {
        if(state!=null){
            this.state = (LogAreaState) state;
            if(!this.state.getLabel().isBlank()) l.setText(this.state.getLabel());
            repaint();
        }

    }

    @Override
    public void setMediator(Mediator mediator) {
        super.setMediator(mediator);
    }
}
