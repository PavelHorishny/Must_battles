package org.game.services;

import org.game.gui.Coordinates;
import org.game.gui.panels.Message;
import org.game.state.GameState;
import org.game.unit.GameUnit;

import java.util.ArrayList;

public interface LogService {
    void setShortMessage(GameState state, String message);
    void setLongMessage(String message);

    void startMessage();

    void selectedMessage(String logMessage);

    void addMessage(/*GameState state,*/ Message message, String text);

    void setLog(GameState state);
    void setMessage(String message);
    void setMessages(ArrayList<String>messages);
    void setState(GameState state);

    void moveMessage(GameUnit gameUnit, Coordinates destination);

    void shotMessage(GameUnit attacker, GameUnit target, String single, int hp);

    void stormMessage(GameUnit unit, Coordinates stormDestination);
}
