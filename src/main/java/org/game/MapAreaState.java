package org.game;

import lombok.Getter;
import lombok.Setter;
import org.game.gui.MapCell;
import org.game.gui.panels.game.components.MapArea;

import java.util.ArrayList;

@Getter
@Setter
public class MapAreaState extends GameComponentState{
    public MapAreaState (ArrayList<MapCell> map){
        this.map = map;
    }
    public MapAreaState () {}
    ArrayList<MapCell> map = new ArrayList<>();
}
