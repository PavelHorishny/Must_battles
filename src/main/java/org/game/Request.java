package org.game;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.game.gui.Coordinates;
import org.game.gui.panels.Message;
@Getter
@Setter
@Builder
public class Request {
    Message message;
    String id;
    String targetID;
    String shotType;
    Coordinates destination;
    boolean state;
}
