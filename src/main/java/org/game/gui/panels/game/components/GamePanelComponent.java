package org.game.gui.panels.game.components;

import lombok.Setter;
import org.game.gui.panels.GamePanelMediator;
import org.game.gui.panels.Mediator;
import org.game.gui.panels.Settings;

import javax.swing.*;

public abstract class GamePanelComponent extends JPanel implements GameComponent {
    GamePanelMediator mediator;
    @Setter
    String name;
    public GamePanelComponent() {

    }
    public GamePanelComponent(Settings settings) {
        if(settings.getName()!=null) setName(settings.getName());
        if(settings.getBackground()!=null) setBackground(settings.getBackground());
        if(settings.getPreferredSize()!=null) setPreferredSize(settings.getPreferredSize());
        if(settings.getLayout()!=null) setLayout(settings.getLayout());
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = (GamePanelMediator) mediator;
    }

    @Override
    public String getGameComponentName() {
        return name;
    }
}
