package org.game.gui.panels.game.areas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game.state.GameComponentState;
import org.game.gui.panels.Settings;

import javax.swing.*;
import java.awt.*;

public class ButtonArea extends GamePanelComponent {
    public static final Logger logger = LogManager.getLogger(ButtonArea.class);
    JButton menu = new JButton("Menu");
    JButton end = new JButton("End");
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
        add(menu);
        System.out.println("Button panel exists");
    }

    @Override
    public void updateState(GameComponentState state) {

    }
}
