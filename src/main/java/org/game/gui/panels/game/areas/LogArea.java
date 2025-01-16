package org.game.gui.panels.game.areas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.game.state.GameComponentState;
import org.game.state.LogAreaState;
import org.game.gui.panels.Mediator;
import org.game.gui.panels.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LogArea extends GamePanelComponent {
    private static final Logger logger = LogManager.getLogger(LogArea.class);
    LogAreaState state;
    JLabel l;
    JTextArea log;

    public LogArea(Settings settings){
        super(settings);
        l = new JLabel(name);
        add(l);
        l.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                JDialog d = new JDialog();
                JButton ok = new JButton("ok");
                log = new JTextArea();
                d.add(log);
                d.add(ok);
                log.setEnabled(false);
                d.setSize(600,600);
                d.setUndecorated(true);
                d.setLocationRelativeTo(null);
                d.getContentPane().setBackground(Color.MAGENTA);
                d.setVisible(true);
                d.setLayout(new FlowLayout());
                log.removeAll();
                state.getLog().forEach(string -> log.append(string+"\n"));
                ok.addActionListener(actionEvent -> d.dispose());
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
        });
        logger.debug("runs");
    }

    @Override
    public void updateState(GameComponentState state) {
        if(state!=null){
            this.state = (LogAreaState) state;
            if(!this.state.getLabel().isEmpty()) l.setText(this.state.getLabel());
            repaint();
        }

    }

    @Override
    public void setMediator(Mediator mediator) {
        super.setMediator(mediator);
    }
}
