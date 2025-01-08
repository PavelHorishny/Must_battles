package org.game.state;

import lombok.Builder;
import lombok.Getter;
import org.game.gui.Coordinates;
import org.game.gui.MapCell;
import org.game.unit.GUIUnit;

import java.util.ArrayList;
import java.util.Map;


@Getter
@Builder
public class MapAreaState extends GameComponentState {
    private boolean lost;
    private String looser;
    private Map<Coordinates,GUIUnit> fleet;
    private ArrayList<Coordinates> route;
    private MapCell [][] map;
    private Coordinates selectedID;
    private Coordinates targetID;
    private Coordinates vesselInStorm;
    private Coordinates stormDestination;
}
