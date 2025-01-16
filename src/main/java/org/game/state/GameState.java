package org.game.state;

import lombok.*;
import org.game.EndGame;
import org.game.gui.Coordinates;
import org.game.map.Surface;
import org.game.unit.GameUnit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameState {
    private boolean buttonOnRepairActive = false;
    private boolean buttonReadyForHelpActive = false;
    private final EndGame endGame = new EndGame();
    private GameUnit selected = null;
    private GameUnit target = null;
    private Coordinates vesselInStorm = null;
    private Coordinates stormDestination = null;
    private Surface[][] map;
    private final ArrayList<Surface> route = new ArrayList<>();
    private final ArrayList <GameUnit> aimedUnits = new ArrayList<>();
    private final Map<String, GameUnit> fleet = new HashMap<>();
    private int day = 0;
    private boolean isFirstPlayerMove = true;
    private String logMessage;
    private ArrayList<String> log = new ArrayList<>();
}
