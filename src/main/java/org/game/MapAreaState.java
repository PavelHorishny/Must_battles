package org.game;

import lombok.Getter;
import lombok.Setter;
import org.game.gui.MapCell;
import org.game.unit.GUIUnit;

import java.util.ArrayList;

@Getter
@Setter
public class MapAreaState extends GameComponentState{
    ArrayList<GUIUnit> fleet = new ArrayList<>();
    public MapAreaState (ArrayList<MapCell> map){
        this.map = map;
    }
    public MapAreaState () {}
    ArrayList<MapCell> map = new ArrayList<>();
}
