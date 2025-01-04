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
    JToggleButton vesselRepair = new JToggleButton("VR");
    JButton grid = new JButton("#");
    JToggleButton participateRepair = new JToggleButton("Help");
    ButtonAreaState state;
    public ButtonArea(Settings settings) {
        super(settings);
        end.addActionListener(e->mediator.endTurn());
        logger.debug(name);
        menu.addActionListener(e -> {
            System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
            System.out.println(this.getParent().getClass());
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
            System.out.println(" ");
        });
        add(end);
        add(vesselRepair);
        vesselRepair.addItemListener(itemEvent -> {
            if(itemEvent.getStateChange()==ItemEvent.SELECTED) mediator.unitReadyForRepair(true);
            if(itemEvent.getStateChange()==ItemEvent.DESELECTED) mediator.unitReadyForRepair(false);
        });
        vesselRepair.setEnabled(false);
        add(participateRepair);

        participateRepair.addItemListener(itemEvent -> {
            if(itemEvent.getStateChange()==ItemEvent.SELECTED) mediator.unitReadyForHelp(true);
            if(itemEvent.getStateChange()==ItemEvent.DESELECTED) mediator.unitReadyForHelp(false);
        });
        participateRepair.setEnabled(false);

        add(menu);
        add(grid);
        grid.addActionListener(actionEvent -> mediator.grid());

        System.out.println("Button panel exists");
    }

    @Override
    public void updateState(GameComponentState state) {
        if(state!=null){
            System.out.println(state);
            this.state = (ButtonAreaState) state;
            if(this.state.isOnRepairButton()){
                vesselRepair.setEnabled(true);
                vesselRepair.setSelected(this.state.isSelectedReadyForRepair() || this.state.isSelectedOnRepair());
            }else{
                vesselRepair.setEnabled(false);
            }

            if(this.state.isHelpButton()){
                participateRepair.setEnabled(true);
                participateRepair.setSelected(this.state.isSelectedReadyToHelp());
            }else {
                participateRepair.setEnabled(false);
            }
        }
        repaint();
    }
}
