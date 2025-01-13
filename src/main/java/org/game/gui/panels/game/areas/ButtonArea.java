package org.game.gui.panels.game.areas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game.state.ButtonAreaState;
import org.game.state.GameComponentState;
import org.game.gui.panels.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class ButtonArea extends GamePanelComponent {
    public static final Logger logger = LogManager.getLogger(ButtonArea.class);
    JButton menu = new JButton("Menu");
    JButton end = new JButton("End");
    JToggleButton onRepair = new JToggleButton("onR");
    JButton grid = new JButton("#");
    JToggleButton participateRepair = new JToggleButton("Help");
    ButtonAreaState state;
    public ButtonArea(Settings settings) {
        super(settings);
        end.addActionListener(e->mediator.endTurn());
        logger.debug("runs");
        //TODO magic numbers
        menu.addActionListener(e -> {
            JDialog d = new JDialog();
            d.setSize(200,200);
            d.setUndecorated(true);
            d.setLocationRelativeTo(null);
            d.getContentPane().setBackground(Color.MAGENTA);
            d.setVisible(true);
            d.setLayout(new FlowLayout());
            JButton button = new JButton("back");

            button.setSize(40,20);
            JButton mainMenu = new JButton("Main Menu");
            mainMenu.setSize(40,20);
            mainMenu.addActionListener(ev->{
                CardLayout layout = (CardLayout) this.getParent().getParent().getLayout();
                layout.show(this.getParent().getParent(),"Main");
                d.dispose();
            });
            button.addActionListener(ev->{
                d.dispose();
                mediator.test("back button pressed");
            });
            d.add(button);
            d.add(mainMenu);
        });
        add(end);
        add(onRepair);
        onRepair.addItemListener(itemEvent -> {
            if(itemEvent.getStateChange()==ItemEvent.SELECTED){
                mediator.unitReadyForRepair(true);
                participateRepair.setEnabled(false);
            }
            if(itemEvent.getStateChange()==ItemEvent.DESELECTED) mediator.unitReadyForRepair(false);
        });
        onRepair.setEnabled(false);
        add(participateRepair);

        participateRepair.addItemListener(itemEvent -> {
            if(itemEvent.getStateChange()==ItemEvent.SELECTED) {
                mediator.unitReadyForHelp(true);
                onRepair.setEnabled(false);
            }
            if(itemEvent.getStateChange()==ItemEvent.DESELECTED) mediator.unitReadyForHelp(false);
        });
        participateRepair.setEnabled(false);

        add(menu);
        add(grid);
        grid.addActionListener(actionEvent -> mediator.grid());

        //TODO refactor
    }

    @Override
    public void updateState(GameComponentState gameComponentState) {
        if(gameComponentState != null){
            state = (ButtonAreaState) gameComponentState;
            if(state.isOnRepairButton()){
                if(state.isSelectedIsHelping()){
                    onRepair.setEnabled(false);
                }else {
                    onRepair.setEnabled(true);
                    onRepair.setSelected(state.isSelectedReadyForRepair()|| state.isSelectedOnRepair());
                }
            }else {
                onRepair.setEnabled(false);
            }
            if(state.isHelpButton()){
                if(state.isSelectedOnRepair()){
                    participateRepair.setEnabled(false);
                }else {
                    participateRepair.setEnabled(true);
                    participateRepair.setSelected(state.isSelectedIsHelping()||state.isSelectedReadyToHelp());
                }
            }else {
                participateRepair.setEnabled(false);
            }
        }
        repaint();
    }
}
