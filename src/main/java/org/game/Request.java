package org.game;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.game.gui.Coordinates;
import org.game.gui.panels.Message;
@Getter
@Setter
@Builder
@ToString
public class Request {
    private Message message;
    private String id;
    private String targetID;
    private String shotType;
    private Coordinates destination;
    private boolean state;
}
