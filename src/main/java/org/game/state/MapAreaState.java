package org.game.state;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.game.gui.Coordinates;
import org.game.gui.MapCell;
import org.game.unit.GUIUnit;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@Builder
public class MapAreaState extends GameComponentState {
    Map<Coordinates,GUIUnit> fleet;
    ArrayList<Coordinates> route;
    MapCell [][] map;
    Optional<Coordinates> selectedID_TEST;
    Optional<Coordinates> targetID_TEST;

}
