package org.game.state;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.game.gui.Coordinates;
import org.game.gui.MapCell;
import org.game.unit.GUIUnit;

import java.util.ArrayList;
import java.util.Map;

@Getter
@Setter
@Builder
public class MapAreaState extends GameComponentState {
    Map<Coordinates,GUIUnit> fleet;
    ArrayList<MapCell> map;

}
