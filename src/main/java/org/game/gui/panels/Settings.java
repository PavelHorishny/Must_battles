package org.game.gui.panels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.awt.*;

@Getter
@AllArgsConstructor
@Builder
public class Settings {

    private String name;
    private Color background;
    private Dimension preferredSize;
    private LayoutManager layout;

}
